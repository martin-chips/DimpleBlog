<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="评论地点">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入评论地点"
          clearable
          style="width: 240px;"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="昵称">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入昵称"
          clearable
          style="width: 240px;"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论时间">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="list" style="width: 100%;">
      <el-table-column label="评论编号" align="center" prop="id"/>
      <el-table-column label="昵称" align="center" prop="nickName"/>
      <el-table-column label="主机" align="center" prop="ip" width="130" :show-overflow-tooltip="true"/>
      <el-table-column label="操作地点" align="center" prop="location"/>
      <el-table-column label="显示" align="center" prop="display"/>
      <el-table-column label="内容" align="center" prop="content" :show-overflow-tooltip="true"/>
      <el-table-column label="点赞" align="center" prop="good"/>
      <el-table-column label="踩" align="center" prop="bad"/>
      <el-table-column label="评论日期" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row,scope.index)"
          >详细
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 评论详细 -->
    <el-dialog title="评论详细" :visible.sync="open" width="700px">
      <el-form ref="form" :model="form" label-width="100px" size="mini">
        <el-row>
          <el-col :span="12">
            <el-form-item label="评论URL：">{{ form.url }}</el-form-item>
            <el-form-item label="昵称：">{{ form.nickName }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="IP："> {{ form.ip }}</el-form-item>
            <el-form-item label="地理位置：">{{ form.location }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="浏览器：">{{ form.browser }}</el-form-item>
            <el-form-item label="OS: "> {{ form.os }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="点赞：">{{ form.good }}</el-form-item>
            <el-form-item label="踩：">{{ form.bad }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="父评论ID：">{{ form.parentId }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示：">
              <div v-if="form.display === true">显示</div>
              <div v-else-if="form.display === false">隐藏</div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="评论内容：">{{ form.content }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="评论时间: ">{{ parseTime(form.createTime) }}</el-form-item>
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
  import {
    listComment,
    getComment,
    delComment,
    updateComment
  } from "@/api/blog/comment";

  export default {
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 表格数据
        list: [],
        // 是否显示弹出层
        open: false,
        // 日期范围
        dateRange: [],
        // 表单参数
        form: {},
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          nickName: undefined,
          display: undefined,
          location: undefined,
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询登录日志 */
      getList() {
        this.loading = true;
        listComment(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
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
      /** 详细按钮操作 */
      handleView(row) {
        this.open = true;
        this.form = row;
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        getComment(row.id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改分类";
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$confirm('是否确认删除名称为"' + row.nickName + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delComment(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      }
    }
  };
</script>

