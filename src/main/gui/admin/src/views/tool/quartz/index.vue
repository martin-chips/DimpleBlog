<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label="任务名">
        <el-input v-model="queryParams.jobName" placeholder="请输入任务名" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="方法名">
        <el-input v-model="queryParams.methodName" placeholder="请输入方法名" clearable size="small" style="width: 240px"
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

    <el-table v-loading="loading" :data="quartzJobList">
      <el-table-column :show-overflow-tooltip="true" prop="jobName" width="100px" label="任务名称"/>
      <el-table-column :show-overflow-tooltip="true" prop="beanName" label="Bean名称"/>
      <el-table-column :show-overflow-tooltip="true" prop="methodName" width="90px" label="执行方法"/>
      <el-table-column :show-overflow-tooltip="true" prop="params" width="80px" label="参数"/>
      <el-table-column :show-overflow-tooltip="true" prop="cronExpression" width="100px" label="cron表达式"/>
      <el-table-column :show-overflow-tooltip="true" prop="isPause" width="90px" label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isPause ? 'warning' : 'success'">{{ scope.row.isPause ? '已暂停' : '运行中' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="remark" label="描述"/>
      <el-table-column :show-overflow-tooltip="true" prop="createTime" label="创建日期">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180px"
                       align="center" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" style="margin-right: 3px;" type="text"
                     @click="edit(scope.row)">编辑
          </el-button>
          <el-button style="margin-left: -2px" type="text" size="mini"
                     @click="execute(scope.row.id)">执行
          </el-button>
          <el-button style="margin-left: 3px" type="text" size="mini"
                     @click="updateStatus(scope.row.id,scope.row.isPause ? '恢复' : '暂停')">
            {{ scope.row.isPause ? '恢复' : '暂停' }}
          </el-button>
          <el-popover
            :ref="scope.row.id"
            placement="top"
            width="200">
            <p>确定停止并删除该任务吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消</el-button>
              <el-button :loading="loading" type="primary" size="mini" @click="handleSubDelete(scope.row.id)">确定
              </el-button>
            </div>
            <el-button slot="reference" type="text" size="mini">删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 添加或修改任务配置对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="100px">
        <el-form-item label="任务名称" prop="jobName">
          <el-input v-model="form.jobName" style="width: 460px;"/>
        </el-form-item>
        <el-form-item label="Bean名称" prop="beanName">
          <el-input v-model="form.beanName" style="width: 460px;"/>
        </el-form-item>
        <el-form-item label="执行方法" prop="methodName">
          <el-input v-model="form.methodName" style="width: 460px;"/>
        </el-form-item>
        <el-form-item label="参数内容">
          <el-input v-model="form.params" style="width: 460px;"/>
        </el-form-item>
        <el-form-item label="Cron表达式" prop="cronExpression">
          <el-input v-model="form.cronExpression" style="width: 460px;"/>
        </el-form-item>
        <el-form-item label="任务状态">
          <el-radio v-model="form.isPause" label="false">启用</el-radio>
          <el-radio v-model="form.isPause" label="true">暂停</el-radio>
        </el-form-item>
        <el-form-item label="任务描述">
          <el-input v-model="form.remark" style="width: 460px;" rows="2" type="textarea"/>
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
  import {listQuartzJob, delQuartzJob, getQuartzJob, addQuartzJob, updateQuartzJob} from "@/api/tool/quartz";

  export default {
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 角色表格数据
        quartzJobList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 日期范围
        dateRange: [],
        // 状态数据字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          jobName: undefined,
          methodName: undefined,
          status: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          jobName: [
            {required: true, message: '请输入任务名称', trigger: 'blur'}
          ],
          beanName: [
            {required: true, message: '请输入Bean名称', trigger: 'blur'}
          ],
          methodName: [
            {required: true, message: '请输入方法名称', trigger: 'blur'}
          ],
          cronExpression: [
            {required: true, message: '请输入Cron表达式', trigger: 'blur'}
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 单条删除 */
      handleSubDelete(id) {
        this.loading = true;
        delQuartzJob(id).then((response) => {
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
        listQuartzJob(this.addDateRange(this.queryParams, this.dateRange)).then(
          response => {
            this.quartzJobList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      //执行任务
      execute(id) {
        execution(id).then(response => {
          if (response.code == 200) {
            this.msgSuccess("执行成功");
          } else {
            this.msgError("执行失败");
          }
        }).catch(err => {
          console.log(err.response.data.message)
        })
      },
      updateStatus(id, status) {
        updateIsPause(id).then(res => {
          this.getList();
          this.msgSuccess("更新成功");
        }).catch(err => {
          console.log(err.response.data.message)
        })
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
        this.open = true;
        this.title = "添加任务";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.$nextTick(() => {
          this.getRoleMenuTreeselect(row.id);
        });
        getQuartzJob(row.id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改任务";
        });
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              this.form.menuIds = this.getMenuAllCheckedKeys();
              updateQuartzJob(this.form).then(response => {
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
              addQuartzJob(this.form).then(response => {
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
    }
  };
</script>
