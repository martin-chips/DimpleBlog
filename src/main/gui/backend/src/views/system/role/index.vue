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
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   @click="handleAdd">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single"
                   @click="handleUpdate(null)">修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :loading="delLoading" :disabled="multiple"
                   @click="handleDelete">删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-download" size="mini"
                   @click="handleExport">导出
        </el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" align="center"/>
      <el-table-column label="角色名称" align="center" prop="roleName" :show-overflow-tooltip="true"/>
      <el-table-column label="权限字符" prop="roleKey" align="center" :show-overflow-tooltip="true"/>
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
                @pagination="init"/>

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

  </div>
</template>

<script>
  import {getRole, addRole, updateRole, changeRoleStatus} from "@/api/system/role";
  import {treeselect as menuTreeselect, roleMenuTreeselect} from "@/api/system/menu";
  import initData from '@/mixins/initData'

  export default {
    mixins: [initData],
    data() {
      return {
        // 状态数据字典
        statusOptions: [],
        // 菜单列表
        menuOptions: [],
        // 查询参数
        queryParams: {
          roleName: undefined,
          roleKey: undefined,
          status: undefined
        },
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
      this.$nextTick(() => {
        this.init()
      });
      this.getDicts("sys_normal_disable").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      beforeInit() {
        this.base = '/system/role';
        this.modelName = '角色';
        return true
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
                  this.init();
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
                  this.open();
                } else {
                  this.msgError(response.msg);
                }
              });
            }
          }
        });
      },
    }
  };
</script>
