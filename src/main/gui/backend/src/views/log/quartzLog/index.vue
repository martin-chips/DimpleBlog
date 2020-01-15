<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="任务名">
        <el-input v-model="queryParams.title" placeholder="请输入任务名" clearable style="width: 240px;" size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="方法名">
        <el-input v-model="queryParams.operName" placeholder="请输入方法名" clearable style="width: 240px;" size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryParams.status" placeholder="操作状态" clearable size="small" style="width: 240px">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="操作时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :loading="delLoading" :disabled="multiple"
                   @click="handleDelete">删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :loading="delLoading"
                   @click="handleClean">清空
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
      <el-table-column align="center" prop="cost" label="耗时">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.cost ==null"><1ms</el-tag>
          <el-tag v-else-if="scope.row.cost <= 300">{{ scope.row.cost }}ms</el-tag>
          <el-tag v-else-if="scope.row.cost <= 1000" type="warning">{{ scope.row.cost }}ms</el-tag>
          <el-tag v-else type="danger">{{ scope.row.cost }}ms</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="isSuccess" label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status ? 'success' : 'danger'">{{ scope.row.status ? '成功' : '失败' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="createTime" label="创建日期">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view"
                     @click="handleDetail(scope.row.id)">详细
          </el-button>
          <el-popover :ref="scope.row.id" placement="top" width="180">
            <p>确定删除本条数据吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消
              </el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="handleSubDelete(scope.row.id)">确定
              </el-button>
            </div>
            <el-button slot="reference" type="text" icon="el-icon-delete" size="mini">删除
            </el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="任务日志详细" :visible.sync="open" width="700px">
      <el-form ref="form" :model="detail" label-width="100px" size="mini">
        <el-row>
          <el-col :span="12">
            <el-form-item label="任务名称：">{{ detail.jobName }}</el-form-item>
            <el-form-item label="Bean名称：">{{ detail.beanName }}</el-form-item>
            <el-form-item label="Cron表达式：">{{ detail.cronExpression }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="方法名称：">{{ detail.methodName }}</el-form-item>
            <el-form-item label="方法参数：">{{ detail.methodParams }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作状态：">
              <el-tag :type="detail.status ? 'success' : 'danger'">{{ detail.status ? '成功' : '失败' }}</el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作时间：">{{ parseTime(form.createTime) }}</el-form-item>
          </el-col>
          <el-col :span="24" v-if="detail.result!=null">
            <el-form-item label="结果：">{{ detail.result }}</el-form-item>
          </el-col>
          <el-col :span="24" v-if="detail.status === false">
            <el-form-item label="异常信息：">{{ detail.exception }}</el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">关 闭</el-button>
      </div>
    </el-dialog>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                style="margin-top: 8px;"
                @pagination="init"/>

  </div>
</template>

<script>
  import {getQuartzDetail} from "@/api/log/quartzLog";
  import initData from '@/mixins/initData'

  export default {
    mixins: [initData],
    data() {
      return {
        detail: {},
        // 类型数据字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          jobName: undefined,
          methodName: undefined,
          status: undefined
        }
      };
    },
    created() {
      this.$nextTick(() => {
        this.init()
      });
      this.getDicts("sys_common_status").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      beforeInit() {
        this.base = '/log/quartzLog';
        this.modelName = '任务日志';
        return true
      },
      handleDetail(id) {
        getQuartzDetail(id).then(response => {
          this.detail = response.data;
          this.open = true;
        });
      }
    }
  };
</script>

