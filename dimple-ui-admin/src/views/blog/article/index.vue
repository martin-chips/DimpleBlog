<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入文章标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="articleStatus">
        <el-select
          v-model="queryParams.articleStatus"
          clearable
          placeholder="状态"
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.blog_article_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="分类" prop="categoryId">
        <el-select
          v-model="queryParams.categoryId"
          filterable
          remote
          clearable
          :remote-method="getCategoryOptions"
          placeholder="请选择文章分类">
          <el-option
            v-for="item in categoryOptions"
            :key="item.id"
            :label="item.title"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
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
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <router-link to="article/add">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            v-hasPermi="['blog:article:add']"
          >新增
          </el-button>
        </router-link>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['blog:article:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['blog:article:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['blog:article:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" align="center"/>
      <el-table-column label="" align="center" prop="id"/>
      <el-table-column :show-overflow-tooltip="true" label="标题" align="center" prop="title"/>
      <el-table-column label="文章标图" align="center" prop="headerImage">
        <template slot-scope="scope">
          <image-preview :src="scope.row.headerImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="原创" align="center" prop="original">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.original?'Y':'N'"/>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="摘要" align="center" prop="summary"/>
      <el-table-column :show-overflow-tooltip="true" label="点赞次数" align="center" prop="likeCount">
        <template slot-scope="scope">
          <el-badge type="primary" :value="scope.row.likeCount"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="articleStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.blog_article_status" :value="scope.row.articleStatus"/>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" label="分类名称" align="center" prop="categoryTitle">
        <template slot-scope="scope">
          <el-tag effect="plain" type="info">
            {{ scope.row.categoryTitle }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column :sort-orders="['descending', 'ascending']" align="center" label="创建日期" prop="createTime"
                       sortable="custom" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column :sort-orders="['descending', 'ascending']" align="center" label="最后修改日期" prop="updateTime"
                       sortable="custom" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-hasPermi="['blog:article:edit']"
          >
            <router-link :to="'article/edit/'+scope.row.id">修改
            </router-link>
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['blog:article:remove']"
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
  </div>
</template>

<script>
import {listArticle} from "@/api/blog/article";
import {listCategory} from '@/api/blog/category'

export default {
  dicts: ['blog_article_status', 'sys_yes_no'],
  name: "Article",
  data() {
    return {
      categoryOptions: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 日期范围
      dateRange: [],
      // 默认排序
      defaultSort: {prop: 'createTime', order: 'descending'},
      // 文章表格数据
      articleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        headerImage: null,
        content: null,
        categoryId: null,
        original: null,
        summary: null,
        articleStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          {required: true, message: "标题不能为空", trigger: "blur"}
        ],
      }
    };
  },
  activated() {
    this.getList();
  },
  created() {
    this.getList();
    this.getCategoryOptions();
  },
  mounted() {
    this.getList();
  },
  methods: {
    /** 查询文章列表 */
    getList() {
      this.loading = true;
      listArticle(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.articleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        headerImage: null,
        content: null,
        categoryId: null,
        original: null,
        summary: null,
        articleStatus: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    getCategoryOptions(query) {
      listCategory({title: query}).then(response => {
        this.categoryOptions = response.rows
      })
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      this.$router.push({path: 'article/edit/' + id})
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateArticle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticle(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除文章编号为"' + ids + '"的数据项？').then(function () {
        return delArticle(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('blog/article/export', {
        ...this.queryParams
      }, `article_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
