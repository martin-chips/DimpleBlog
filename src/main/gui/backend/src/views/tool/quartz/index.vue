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
      <el-form-item>
        <router-link to="/log/log/quartz" class="link-type">
          <el-button type="info" icon="el-icon-tickets" size="mini">日志
          </el-button>
        </router-link>
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
      <el-table-column :show-overflow-tooltip="true" prop="jobName" label="任务名称"/>
      <el-table-column :show-overflow-tooltip="true" prop="beanName" label="Bean名称"/>
      <el-table-column :show-overflow-tooltip="true" prop="methodName" label="执行方法"/>
      <el-table-column :show-overflow-tooltip="true" prop="methodParams" label="参数"/>
      <el-table-column :show-overflow-tooltip="true" prop="cronExpression" label="cron表达式"/>
      <el-table-column :show-overflow-tooltip="true" prop="status" label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status ? 'success' : 'warning'">{{ scope.row.status ? '运行中' :'已暂停' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="remark" label="描述"/>
      <el-table-column :show-overflow-tooltip="true" prop="createTime" label="创建日期">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="300px">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit"
                     @click="handleUpdate(scope.row)">修改
          </el-button>
          <el-button type="text" size="mini" icon="el-icon-finished"
                     @click="execute(scope.row.id)">执行
          </el-button>
          <el-button type="text" size="mini" icon="el-icon-open"
                     @click="updateStatus(scope.row.id)">
            {{ scope.row.status ? '暂停' : '运行' }}
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
            <el-button slot="reference" type="text" size="mini" icon="el-icon-delete">删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="init"/>

    <!-- 添加或修改任务配置对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="600px">
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="10 0px">
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
          <el-input v-model="form.methodParams" style="width: 460px;"/>
        </el-form-item>
        <el-form-item label="Cron表达式" prop="cronExpression">
          <el-input v-model="form.cronExpression" style="width: 460px;"/>
        </el-form-item>
        <el-form-item label="任务状态">
          <el-radio-group v-model="form.status">
            <el-radio :label="false">暂停</el-radio>
            <el-radio :label="true">运行</el-radio>
          </el-radio-group>
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
  import {
    listQuartzJob,
    delQuartzJob,
    updateQuartzStatus,
    executeJob,
    getQuartzJob,
    addQuartzJob,
    updateQuartzJob
  } from "@/api/tool/quartz";
  import initData from '@/mixins/initData'

  export default {
    mixins: [initData],
    data() {
      return {
        // 状态数据字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          jobName: undefined,
          methodName: undefined,
          status: undefined
        },
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
      this.$nextTick(() => {
        this.init()
      })
    },
    methods: {
      beforeInit() {
        this.base = '/tool/quartz';
        this.modelName = '定时任务';
        return true
      },
      //执行任务
      execute(id) {
        executeJob(id).then(response => {
          if (response.code == 200) {
            this.msgSuccess("执行成功");
          } else {
            this.msgError("执行失败");
          }
        }).catch(err => {
          console.log(err.response.data.message)
        })
      },
      updateStatus(id) {
        updateQuartzStatus(id).then(res => {
          if (res.code == 200) {
            this.msgSuccess("更新成功");
            this.init();
          } else {
            this.msgError(res.msg);
          }
        }).catch(err => {
          console.log(err.response.data.message)
        })
      }
    }
  };
</script>
