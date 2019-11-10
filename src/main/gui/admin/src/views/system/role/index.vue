<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label="角色名称">
        <el-input v-model="queryParams.roleName" placeholder="请输入角色名称" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="权限字符">
        <el-input v-model="queryParams.roleKey" placeholder="请输入权限字符" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryParams.status" placeholder="角色状态" clearable size="small" style="width: 240px">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   @click="handleAdd">新增
        </el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="roleList">
      <el-table-column label="角色编号" align="center" prop="id"/>
      <el-table-column label="角色名称" align="center" prop="roleName" :show-overflow-tooltip="true"/>
      <el-table-column label="权限字符" prop="roleKey"  align="center":show-overflow-tooltip="true"/>
      <el-table-column label="显示顺序" align="center" prop="roleSort"/>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status" active-value="0" inactive-value="1"
                     @change="handleStatusChange(scope.row)"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit"
                     @click="handleUpdate(scope.row)">修改
          </el-button>
          <el-popover :ref="scope.row.id" placement="top" width="180">
            <p>确定删除本条数据吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消
              </el-button>
              <el-button :loading="loading" type="primary" size="mini" @click="handleSubDelete(scope.row.id)">确定
              </el-button>
            </div>
            <el-button slot="reference" type="text" icon="el-icon-delete" size="mini">删除
            </el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 添加或修改角色配置对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="form.roleName" placeholder="请输入角色名称"/>
        </el-form-item>
        <el-form-item label="权限字符" prop="roleKey">
          <el-input v-model="form.roleKey" placeholder="请输入权限字符"/>
        </el-form-item>
        <el-form-item label="角色顺序" prop="roleSort">
          <el-input-number v-model="form.roleSort" controls-position="right" :min="0"/>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单权限">
          <el-tree
            :data="menuOptions"
            show-checkbox
            ref="menu"
            node-key="id"
            empty-text="加载中，请稍后"
            :props="defaultProps"
          ></el-tree>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 分配角色数据权限对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="openDataScope" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="角色名称">
          <el-input v-model="form.roleName" :disabled="true"/>
        </el-form-item>
        <el-form-item label="权限字符">
          <el-input v-model="form.roleKey" :disabled="true"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitDataScope">确 定</el-button>
        <el-button @click="cancelDataScope">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import {listRole, getRole, delRole, addRole, updateRole, dataScope, changeRoleStatus} from "@/api/system/role";
    import {treeselect as menuTreeselect, roleMenuTreeselect} from "@/api/system/menu";

    export default {
        data() {
            return {
                // 遮罩层
                loading: true,
                // 总条数
                total: 0,
                // 角色表格数据
                roleList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 是否显示弹出层（数据权限）
                openDataScope: false,
                // 日期范围
                dateRange: [],
                // 状态数据字典
                statusOptions: [],
                // 菜单列表
                menuOptions: [],
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    roleName: undefined,
                    roleKey: undefined,
                    status: undefined
                },
                // 表单参数
                form: {},
                defaultProps: {
                    children: "children",
                    label: "label"
                },
                // 表单校验
                rules: {
                    roleName: [
                        {required: true, message: "角色名称不能为空", trigger: "blur"}
                    ],
                    roleKey: [
                        {required: true, message: "权限字符不能为空", trigger: "blur"}
                    ],
                    roleSort: [
                        {required: true, message: "角色顺序不能为空", trigger: "blur"}
                    ]
                }
            };
        },
        created() {
            this.getList();
            this.getDicts("sys_normal_disable").then(response => {
                this.statusOptions = response.data;
            });
        },
        methods: {
            /** 单条删除 */
            handleSubDelete(id) {
                this.loading = true;
                delRole(id).then((response) => {
                    this.$refs[id].doClose()
                    this.loading = false;
                    if (response.code == 200) {
                        this.msgSuccess("删除成功");
                    } else {
                        this.msgError("删除失败");
                    }
                    this.getList();
                }).catch(err => {
                    this.msgError("删除失败");
                    this.$refs[id].doClose()
                    this.loading = false;
                });
            },
            /** 查询角色列表 */
            getList() {
                this.loading = true;
                listRole(this.addDateRange(this.queryParams, this.dateRange)).then(
                    response => {
                        this.roleList = response.rows;
                        this.total = response.total;
                        this.loading = false;
                    }
                );
            },
            /** 查询菜单树结构 */
            getMenuTreeselect() {
                menuTreeselect().then(response => {
                    this.menuOptions = response.data;
                });
            },
            /** 查询部门树结构 */
            getDeptTreeselect() {
                deptTreeselect().then(response => {
                    this.deptOptions = response.data;
                });
            },
            // 所有菜单节点数据
            getMenuAllCheckedKeys() {
                // 目前被选中的菜单节点
                let checkedKeys = this.$refs.menu.getHalfCheckedKeys();
                // 半选中的菜单节点
                let halfCheckedKeys = this.$refs.menu.getCheckedKeys();
                checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
                return checkedKeys;
            },
            // 所有部门节点数据
            getDeptAllCheckedKeys() {
                // 目前被选中的部门节点
                let checkedKeys = this.$refs.dept.getHalfCheckedKeys();
                // 半选中的部门节点
                let halfCheckedKeys = this.$refs.dept.getCheckedKeys();
                checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
                return checkedKeys;
            },
            /** 根据角色ID查询菜单树结构 */
            getRoleMenuTreeselect(id) {
                roleMenuTreeselect(id).then(response => {
                    this.getMenuTreeselect();
                    this.$refs.menu.setCheckedKeys(response.data);
                });
            },
            /** 根据角色ID查询部门树结构 */
            getRoleDeptTreeselect(id) {
                roleDeptTreeselect(id).then(response => {
                    this.getDeptTreeselect();
                    this.$refs.dept.setCheckedKeys(response.data);
                });
            },
            // 角色状态修改
            handleStatusChange(row) {
                let text = row.status === "0" ? "启用" : "停用";
                this.$confirm('确认要"' + text + '""' + row.roleName + '"角色吗?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function () {
                    return changeRoleStatus(row.id, row.status);
                }).then((response) => {
                    if (response.code == 200) {
                        this.msgSuccess(text + "成功");
                    } else {
                        this.msgError(text + "失败");
                    }
                }).catch(function () {
                    row.status = row.status === "0" ? "1" : "0";
                });
            },
            // 取消按钮
            cancel() {
                this.open = false;
                this.reset();
            },
            // 取消按钮（数据权限）
            cancelDataScope() {
                this.openDataScope = false;
                this.reset();
            },
            // 表单重置
            reset() {
                if (this.$refs.tree != undefined) {
                    this.$refs.tree.setCheckedKeys([]);
                }
                this.form = {
                    id: undefined,
                    roleName: undefined,
                    roleKey: undefined,
                    roleSort: 0,
                    status: "0",
                    menuIds: [],
                    deptIds: [],
                    remark: undefined
                };
                this.resetForm("form");
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.queryParams.pageNum = 1;
                this.getList();
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.getMenuTreeselect();
                this.open = true;
                this.title = "添加角色";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                this.$nextTick(() => {
                    this.getRoleMenuTreeselect(row.id);
                });
                getRole(row.id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改角色";
                });
            },
            /** 提交按钮 */
            submitForm: function () {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            this.form.menuIds = this.getMenuAllCheckedKeys();
                            updateRole(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                } else {
                                    this.msgError(response.msg);
                                }
                            });
                        } else {
                            this.form.menuIds = this.getMenuAllCheckedKeys();
                            addRole(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("新增成功");
                                    this.open = false;
                                    this.getList();
                                } else {
                                    this.msgError(response.msg);
                                }
                            });
                        }
                    }
                });
            },
            /** 提交按钮（数据权限） */
            submitDataScope: function () {
                if (this.form.id != undefined) {
                    this.form.deptIds = this.getDeptAllCheckedKeys();
                    dataScope(this.form).then(response => {
                        if (response.code === 200) {
                            this.msgSuccess("修改成功");
                            this.openDataScope = false;
                            this.getList();
                        } else {
                            this.msgError(response.msg);
                        }
                    });
                }
            },
            /** 删除按钮操作 */
            handleDelete(row) {
                this.$confirm('是否确认删除名称为"' + row.roleName + '"的数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function () {
                    return delRole(row.id);
                }).then((response) => {
                    if (response.code == 200) {
                        this.msgSuccess("删除成功");
                    } else {
                        this.msgError("删除失败");
                    }
                    this.getList();
                }).catch(function () {
                });
            }
        }
    };
</script>
