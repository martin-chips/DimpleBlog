<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label="Ip">
        <el-input v-model="queryParams.ip" placeholder="请输入Ip" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="封禁原因">
        <el-input v-model="queryParams.description" placeholder="请输入封禁原因" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini"
                   @click="handleQuery">搜索
        </el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini"
                   @click="handleAdd">新增
        </el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="blacklistList">
      <el-table-column label="黑名单编号" prop="id" width="120"/>
      <el-table-column label="IP" prop="ip" :show-overflow-tooltip="true" width="150"/>
      <el-table-column label="封禁原因" prop="description" :show-overflow-tooltip="true" width="180"/>
      <el-table-column label="上次拦截URL" prop="lastAccessUrl" width="120"/>
      <el-table-column label="上次拦截时间" align="center" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastAccessTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="拦截次数" prop="interceptCount" width="120"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
          >修改
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

    <!-- 添加或修改黑名单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="Ip" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入IP地址"/>
        </el-form-item>
        <el-form-item label="封禁原因" prop="description">
          <el-input v-model="form.description" placeholder="请输入封禁原因"/>
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
  import {listBlacklist, getBlacklist, addBlacklist, updateBlacklist, delBlacklist} from "@/api/monitor/blacklist";

  export default {
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 黑名单表格数据
        blacklistList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 日期范围
        dateRange: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          ip: undefined,
          description: undefined,
        },
        // 表单参数
        form: {},
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 表单校验
        rules: {
          ip: [
            {required: true, message: "IP不能为空", trigger: "blur"}
          ],
          description: [
            {required: true, message: "权限字符不能为空", trigger: "blur"},
            {min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'change'}
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 单个删除*/
      handleSubDelete(id) {
        this.loading = true;
        delBlacklist(id).then((response) => {
          if (response.code == 200) {
            this.msgSuccess("删除成功");
          } else {
            this.msgError("删除失败");
          }
          this.loading = false;
          this.getList();
        }).catch(function () {
        });
      },
      /** 查询黑名单列表 */
      getList() {
        this.loading = true;
        listBlacklist(this.addDateRange(this.queryParams, this.dateRange)).then(
          response => {
            this.blacklistList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          ip: undefined,
          description: undefined
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
        this.open = true;
        this.title = "添加黑名单";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        getBlacklist(row.id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改黑名单";
        });
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateBlacklist(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addBlacklist(this.form).then(response => {
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
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$confirm('是否确认删除IP为"' + row.ip + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delBlacklist(row.id);
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
