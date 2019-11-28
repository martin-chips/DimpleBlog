<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="图书名">
        <el-input v-model="queryParams.title" placeholder="请输入图书名" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="图书描述">
        <el-input v-model="queryParams.description" placeholder="请输入图书描述" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
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
      <el-table-column label="图书主键" align="center" prop="id"/>
      <el-table-column label="图书名" align="center" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="封面" prop="headerImg" width="120">
        <template slot-scope="scope">
          <el-image
            style="width: 30px; height: 30px"
            :src="scope.row.headerImg"
            :preview-src-list="[scope.row.headerImg]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="图书描述" align="center" prop="description" :show-overflow-tooltip="true"/>
      <el-table-column label="图书作者" align="center" prop="author" :show-overflow-tooltip="true"/>
      <el-table-column label="出版社" align="center" prop="publisher" :show-overflow-tooltip="true"/>
      <el-table-column label="浏览量" align="center" prop="click" :show-overflow-tooltip="true"/>
      <el-table-column label="点赞量" align="center" prop="like" :show-overflow-tooltip="true"/>
      <el-table-column label="推荐" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.support" active-color="#13ce66" inactive-color="#ff4949"
                     @change="handleSupportChange(scope.row)"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
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

    <!-- 添加或修改分类对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图书名" prop="title">
          <el-input v-model="form.title" placeholder="请输入图书名"/>
        </el-form-item>
        <el-form-item label="图书描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入图书描述"/>
        </el-form-item>
        <el-form-item label="推荐" prop="support">
          <el-switch v-model="form.support" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
        </el-form-item>
        <el-form-item label="推荐" prop="support">
          <el-switch v-model="form.support" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
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
    getCategory,
    changeCategorySupport,
  } from "@/api/blog/category";
  import initData from '@/mixins/initData'

  export default {
    mixins: [initData],
    data() {
      return {
        // 查询参数
        queryParams: {
          title: undefined,
          description: undefined,
          support: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          title: [
            {required: true, message: "分类名称不能为空", trigger: "blur"},
            {min: 3, max: 50, message: '长度在 5 到 120 个字符', trigger: 'change'}
          ],
          description: [
            {required: true, message: "分类描述不能为空", trigger: "blur"},
            {min: 10, max: 150, message: '长度在 10 到 256 个字符', trigger: 'change'}
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
        this.base = '/blog/category';
        this.modelName = '分类'
        return true
      },
      handleSupportChange(row) {
        let text = row.support ? "启用" : "停用";
        this.$confirm('确认要' + text + '"' + row.title + '"分类吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeCategorySupport(row.id, row.support);
        }).then(() => {
          this.msgSuccess(text + "成功");
        }).catch(function () {
          row.support = row.support === false ? true : false;
        });
      },
    }
  };
</script>
