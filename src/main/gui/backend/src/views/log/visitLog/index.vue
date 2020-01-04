<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="登录地址">
        <el-input v-model="queryParams.location" placeholder="请输入登录地址" clearable style="width: 240px;" size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="用户名称">
        <el-input v-model="queryParams.userName" placeholder="请输入用户名称" clearable style="width: 240px;" size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryParams.status" placeholder="登录状态" clearable size="small" style="width: 240px">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="登录时间">
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
      <el-table-column label="访问模块" align="center" prop="title"/>
      <el-table-column label="IP" align="center" prop="ip"  :show-overflow-tooltip="true"/>
      <el-table-column label="登录地点" align="center" prop="location"/>
      <el-table-column label="浏览器" align="center" prop="browser"/>
      <el-table-column label="操作系统" align="center" prop="os"/>
      <el-table-column align="center" prop="status" label="访问状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status ? 'success' : 'danger'">{{ scope.row.status ? '成功' : '失败' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="异常信息" align="center" prop="errorMsg"/>
      <el-table-column label="登录日期" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
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

    <pagination
      v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="init"/>
  </div>
</template>

<script>
    import initData from '@/mixins/initData'

    export default {
        mixins: [initData],
        data() {
            return {
                // 状态数据字典
                statusOptions: [],
                // 查询参数
                queryParams: {
                    ip: undefined,
                    userName: undefined,
                    status: undefined
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
                this.base = '/log/visitLog';
                this.modelName = '访问日志';
                return true
            },
        }
    };
</script>

