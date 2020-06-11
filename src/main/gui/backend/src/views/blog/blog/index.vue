<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label="文章标题">
        <el-input v-model="queryParams.roleName" placeholder="请输入文章标题" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="摘要">
        <el-input v-model="queryParams.roleKey" placeholder="请输入摘要" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryParams.status" placeholder="博客状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="推荐">
        <el-select v-model="queryParams.support" placeholder="推荐" clearable size="small">
          <el-option
            v-for="dict in supportOptions"
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
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <router-link to="blog/add" class="link-type">
          <el-button type="primary" icon="el-icon-plus" size="mini">新增
          </el-button>
        </router-link>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :loading="delLoading"
          :disabled="multiple"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出
        </el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection"/>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="分类名称">
              <span>{{ props.row.category.title }}</span>
            </el-form-item>
            <el-form-item label="分类描述">
              <span>{{ props.row.category.description }}</span>
            </el-form-item>
            <el-form-item label="摘要">
              <span>{{ props.row.summary }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="标题" min-width="300px" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="分类" prop="category.title"/>
      <el-table-column label="封面" prop="headerImg">
        <template slot-scope="scope">
          <el-image
            style="width: 30px; height: 30px"
            :src="scope.row.headerImg"
            :preview-src-list="[scope.row.headerImg]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="允许评论">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.comment"
                     @change="handleCommentChange(scope.row)"/>
        </template>
      </el-table-column>
      <el-table-column label="推荐">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.support" @change="handleSupportChange(scope.row)" active-color="#13ce66"
                     inactive-color="#ff4949"/>
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status ==true">发布</el-tag>
          <el-tag v-else type="warning">草稿</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="权重" prop="weight">
        <template slot-scope="scope">
          <el-rate v-model="scope.row.weight" :max="5" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" disabled
                   :low-threshold="1" :high-threshold="5" style="display:inline-block"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" min-width="100px" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="text" size="mini" icon="el-icon-edit">
            <router-link :to="'blog/edit/'+scope.row.id">编辑</router-link>
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

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="init"/>
  </div>
</template>

<script>
  import {listBlog, delBlog, changeBlogSupport, changeBlogComment} from "@/api/blog/blog";
  import initData from '@/mixins/initData'

  export default {
    mixins: [initData],
    data() {
      return {
        // 状态数据字典
        statusOptions: [],
        //推荐数据字典
        supportOptions: [],
        // 查询参数
        queryParams: {
          title: undefined,
          summary: undefined,
          status: undefined,
          comment: undefined
        },
        // 表单校验
        rules: {
          roleName: [
            {required: true, message: "博客名称不能为空", trigger: "blur"}
          ],
          roleKey: [
            {required: true, message: "权限字符不能为空", trigger: "blur"}
          ],
          roleSort: [
            {required: true, message: "博客顺序不能为空", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      this.$nextTick(() => {
        this.init();
        this.getDicts("bg_blog_status").then(response => {
          this.statusOptions = response.data;
        });
        this.getDicts("bg_blog_support").then(response => {
          this.supportOptions = response.data;
        });
      })
    },
    methods: {
      beforeInit() {
        this.base = '/blog/blog';
        this.modelName = '博客';
        return true
      },
      // 博客状态修改
      handleCommentChange(row) {
        let text = row.comment ? "开启评论" : "关闭评论";
        this.$confirm('确认要"' + text + '""' + row.title + '"博客吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeBlogComment(row.id, row.comment);
        }).then((response) => {
          if (response.code == 200) {
            this.msgSuccess(text + "成功");
          } else {
            this.msgError(text + "失败");
          }
        }).catch(function () {
          row.comment = row.comment ? false : true;
          this.msgError(text + "失败");
        });
      },
      handleSupportChange(row) {
        let text = row.support ? "推荐" : "取消推荐";
        this.$confirm('确认要' + text + '"' + row.title + '"博客吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeBlogSupport(row.id, row.support);
        }).then((response) => {
          if (response.code == 200) {
            this.msgSuccess(text + "成功");
          } else {
            this.msgError(text + "失败");
          }
        }).catch(function () {
          row.support = row.support === false ? true : false;
        });
      }
    }
  };
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }

  .demo-table-expand label {
    width: 70px;
    color: #99a9bf;
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
