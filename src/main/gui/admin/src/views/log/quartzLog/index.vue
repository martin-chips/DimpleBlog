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

    <el-table v-loading="loading" :data="list" style="width: 100%;">
      <el-table-column :show-overflow-tooltip="true" prop="jobName" label="任务名称"/>
      <el-table-column :show-overflow-tooltip="true" prop="beanName" label="Bean名称"/>
      <el-table-column :show-overflow-tooltip="true" prop="methodName" label="执行方法"/>
      <el-table-column :show-overflow-tooltip="true" prop="methodParams" label="参数"/>
      <el-table-column :show-overflow-tooltip="true" prop="cronExpression" label="cron表达式"/>
      <el-table-column prop="createTime" label="异常详情">
        <template slot-scope="scope">
          <el-button v-show="scope.row.exception" size="mini" type="text"
                     @click="getExceptionInfo(scope.row.exception)">查看详情
          </el-button>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" align="center" prop="cost" label="耗时(毫秒)"/>
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
    </el-table>

    <el-dialog :close-on-click-modal="false" title="操作日志详细" :visible.sync="open" width="700px">
      <span>
        {{ exception }}
      </span>
    </el-dialog>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

  </div>
</template>

<script>
  import {listQuartzJobLog} from "@/api/log/quartzLog";

  export default {
    data() {
      return {
        // 遮罩层
        loading: true,
        exception: '',
        // 总条数
        total: 0,
        // 表格数据
        list: [],
        // 是否显示弹出层
        open: false,
        // 类型数据字典
        statusOptions: [],
        // 日期范围
        dateRange: [],
        // 表单参数
        form: {},
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          jobName: undefined,
          methodName: undefined,
          status: undefined
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_common_status").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      /** 查询登录日志 */
      getList() {
        this.loading = true;
        listQuartzJobLog(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            this.list = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      getExceptionInfo(exception) {
        this.exception = exception;
        this.open = true;
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
    }
  };
</script>

