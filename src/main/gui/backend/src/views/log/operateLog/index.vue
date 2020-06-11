<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="系统模块">
        <el-input v-model="queryParams.title" placeholder="请输入系统模块" clearable style="width: 240px;" size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="操作人员">
        <el-input v-model="queryParams.operName" placeholder="请输入操作人员" clearable style="width: 240px;" size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="queryParams.businessType" placeholder="操作类型" clearable size="small" style="width: 240px">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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

    <el-table v-loading="loading" :data="list" style="width: 100%;" @selection-change="handleSelectionChange">
      <el-table-column type="selection"/>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="URL">
              <span>{{ props.row.url}}</span>
            </el-form-item>
            <el-form-item label="请求方式">
              <span>{{ props.row.requestMethod}}</span>
            </el-form-item>
            <el-form-item label="操作方法">
              <span>{{ props.row.method}}</span>
            </el-form-item>
            <el-form-item label="请求参数">
              <span>{{ props.row.param }}</span>
            </el-form-item>
            <el-form-item label="返回参数">
              <span>{{ props.row.jsonResult }}</span>
            </el-form-item>
            <el-form-item label="登录信息">
              <span>{{ props.row.operateName }} / {{ props.row.ip }} / {{ props.row.location }}</span>
            </el-form-item>
            <el-form-item label="异常信息" v-if="props.row.status === false">
              <span>{{ props.row.errorMsg }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="系统模块" prop="title"/>
      <el-table-column label="操作类型" prop="businessType" :formatter="typeFormat"/>
      <el-table-column label="请求方式" prop="requestMethod"/>
      <el-table-column label="操作人员" prop="operateName"/>
      <el-table-column label="主机" prop="ip" :show-overflow-tooltip="true"/>
      <el-table-column label="操作地点" prop="location"/>
      <el-table-column prop="status" label="登录状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status ? 'success' : 'danger'">{{ scope.row.status ? '成功' : '失败' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="cost" label="请求耗时">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.cost ==null"><1ms</el-tag>
          <el-tag v-else-if="scope.row.cost <= 300">{{ scope.row.cost }}ms</el-tag>
          <el-tag v-else-if="scope.row.cost <= 1000" type="warning">{{ scope.row.cost }}ms</el-tag>
          <el-tag v-else type="danger">{{ scope.row.cost }}ms</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作日期" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
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

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="init"/>

  </div>
</template>

<script>
  import initData from '@/mixins/initData'

  export default {
    mixins: [initData],
    data() {
      return {
        // 类型数据字典
        typeOptions: [],
        // 类型数据字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          title: undefined,
          operName: undefined,
          businessType: undefined,
          status: undefined
        }
      };
    },
    created() {
      this.$nextTick(() => {
        this.init()
      });
      this.getDicts("sys_oper_type").then(response => {
        this.typeOptions = response.data;
      });
      this.getDicts("sys_common_status").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      beforeInit() {
        this.base = '/log/operateLog';
        this.modelName = '操作日志';
        return true
      },
      // 操作日志状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status);
      },
      // 操作日志类型字典翻译
      typeFormat(row, column) {
        return this.selectDictLabel(this.typeOptions, row.businessType);
      },
    }
  };
</script>


<style scoped>
  .demo-table-expand {
    font-size: 0;
  }

  .demo-table-expand label {
    width: 70px;
    color: #99a9bf !important;
  }

  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 100%;
  }

  .demo-table-expand .el-form-item__content {
    font-size: 12px;
  }

  /deep/ .el-dialog__body {
    padding: 0 20px 10px 20px !important;
  }

  .java.hljs {
    color: #444;
    background: #ffffff !important;
    height: 630px !important;
  }
</style>
