<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px" size="small">
      <el-form-item label="模块名" prop="title">
        <el-input
          v-model="queryParams.title"
          clearable
          placeholder="请输入模块名称"
          style="width: 240px;"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="title">
        <el-select
          v-model="queryParams.title"
          clearable
          placeholder="操作类型"
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.blog_log_title"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          clearable
          placeholder="操作状态"
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_common_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="操作时间">
        <el-date-picker
          v-model="dateRange"
          end-placeholder="结束日期"
          range-separator="-"
          start-placeholder="开始日期"
          style="width: 240px"
          type="daterange"
          value-format="yyyy-MM-dd"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['blog:visitlog:remove']"
          :disabled="multiple"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['blog:visitlog:remove']"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleClean"
        >清空
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['blog:visitlog:export']"
          icon="el-icon-download"
          plain
          size="mini"
          type="warning"
          @click="handleExport"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table ref="tables" v-loading="loading" :data="list" :default-sort="defaultSort"
              @selection-change="handleSelectionChange" @sort-change="handleSortChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="日志编号" prop="id"/>
      <el-table-column align="center" label="访问未知" prop="title">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.blog_log_title" :value="scope.row.title"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="请求方式" prop="requestMethod"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="请求地址" prop="requestUri" width="130"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="操作系统" prop="os" width="130"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="浏览器" prop="browser" width="130"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="主机" prop="ip" width="130"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="地理位置" prop="location" width="130"/>
      <el-table-column align="center" label="访问状态" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_common_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column :sort-orders="['descending', 'ascending']" align="center" label="访问日期" prop="createTime"
                       sortable="custom" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['blog:visitlog:query']"
            icon="el-icon-view"
            size="mini"
            type="text"
            @click="handleView(scope.row,scope.index)"
          >详细
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 访问日志详细 -->
    <el-dialog :visible.sync="open" append-to-body title="访问日志详细" width="700px">
      <el-form ref="form" :model="form" label-width="100px" size="mini">
        <el-row>
          <el-col :span="12">
            <el-form-item label="访问未知：">{{ form.title }} / {{ titleFormat(form) }}</el-form-item>
            <el-form-item
              label="登录信息："
            >{{ form.location }} / {{ form.ip }} / {{ form.os }} / {{ form.browser }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="请求地址：">{{ form.requestUri }}</el-form-item>
            <el-form-item label="请求方式：">{{ form.requestMethod }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="访问方法：">{{ form.methodName }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="请求参数：">{{ form.requestParams }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="返回参数：">{{ form.responseParams }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="访问状态：">
              <div v-if="form.status === 0">正常</div>
              <div v-else-if="form.status === 1">失败</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="访问时间：">{{ parseTime(form.createTime) }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item v-if="form.status === 1" label="异常信息：">{{ form.exception }}</el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {cleanVisitLog, delVisitLog, list} from "@/api/log/visitLog";

export default {
  name: "VisitLog",
  dicts: ['sys_common_status', 'blog_log_title'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 表格数据
      list: [],
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],
      // 默认排序
      defaultSort: {prop: 'createTime', order: 'descending'},
      // 表单参数
      form: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: undefined,
        status: undefined
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 字典翻译
    titleFormat(row, column) {
      return this.selectDictLabel(this.dict.type.blog_log_title, row.title);
    },
    /** 查询登录日志 */
    getList() {
      this.loading = true;
      list(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.list = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.queryParams.pageNum = 1;
      this.$refs.tables.sort(this.defaultSort.prop, this.defaultSort.order)
    },
    /** 多选框选中数据 */
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.multiple = !selection.length
    },
    /** 排序触发事件 */
    handleSortChange(column, prop, order) {
      this.queryParams.orderByColumn = column.prop;
      this.queryParams.isAsc = column.order;
      this.getList();
    },
    /** 详细按钮操作 */
    handleView(row) {
      this.open = true;
      this.form = row;
    },
    /** 删除按钮访问 */
    handleDelete(row) {
      const visitLogIds = row.id || this.ids;
      this.$modal.confirm('是否确认删除日志编号为"' + visitLogIds + '"的数据项？').then(function () {
        return delVisitLog(visitLogIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 清空按钮操作 */
    handleClean() {
      this.$modal.confirm('是否确认清空所有操作日志数据项？').then(function () {
        return cleanVisitLog();
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("清空成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('log/visitlog/export', {
        ...this.queryParams
      }, `visitLog_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

