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
        <el-select v-model="queryParams.status" placeholder="推荐" clearable size="small">
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
        <router-link to="blog/add" class="link-type">
          <el-button type="primary" icon="el-icon-plus" size="mini"
          >新增
          </el-button>
        </router-link>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="blogList">
      <el-table-column label="博客主键" prop="id"/>
      <el-table-column label="标题" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="分类" prop="category.title">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="right">
            <p>名称: {{ scope.row.category.title }}</p>
            <p>描述: {{ scope.row.category.description }}</p>
            <p>推荐:
              <el-switch
                v-model="scope.row.category.support"
                active-color="#13ce66"
                disabled
                inactive-color="#ff4949">
              </el-switch>
            </p>
            <p>
              创建时间:<span>{{ parseTime(scope.row.category.createTime) }}</span>
            </p>
            <div slot="reference" class="name-wrapper">
              <el-tag effect="plain">{{ scope.row.category.title }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="摘要" prop="summary" :show-overflow-tooltip="true" width="180"/>
      <el-table-column label="封面" prop="headerImg" width="120">
        <template slot-scope="scope">
          <el-image
            style="width: 30px; height: 30px"
            :src="scope.row.headerImg"
            :preview-src-list="[scope.row.headerImg]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="评论" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.comment"
                     @change="handleCommentChange(scope.row)"/>
        </template>
      </el-table-column>
      <el-table-column label="推荐" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.support" @change="handleSupportChange(scope.row)" active-color="#13ce66"
                     inactive-color="#ff4949"/>
        </template>
      </el-table-column>
      <el-table-column label="权重" prop="weight" width="150" align="center">
        <template slot-scope="scope">
          <el-rate v-model="scope.row.weight" :max="5" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" disabled
                   :low-threshold="1" :high-threshold="5" style="display:inline-block"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="text" size="mini" icon="el-icon-edit">
            <router-link :to="'blog/edit/'+scope.row.id">编辑</router-link>
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>
  </div>
</template>

<script>
  import {listBlog, delBlog, changeBlogSupport, changeBlogComment} from "@/api/blog/blog";

  export default {
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 博客表格数据
        blogList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 日期范围
        dateRange: [],
        // 状态数据字典
        statusOptions: [],
        //推荐数据字典
        supportOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          title: undefined,
          summary: undefined,
          status: undefined,
          comment: undefined
        },
        // 表单参数
        form: {},
        defaultProps: {
          children: "children",
          label: "label"
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
      this.getList();
      this.getDicts("bg_blog_status").then(response => {
        this.statusOptions = response.data;
      });
      this.getDicts("bg_blog_support").then(response => {
        this.supportOptions = response.data;
      });
    },
    methods: {
      /** 查询博客列表 */
      getList() {
        this.loading = true;
        listBlog(this.addDateRange(this.queryParams, this.dateRange)).then(
          response => {
            this.blogList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      // 博客状态修改
      handleCommentChange(row) {
        console.log(row.comment);
        let text = row.comment ? "开启评论" : "关闭评论";
        this.$confirm('确认要"' + text + '""' + row.title + '"博客吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeBlogComment(row.id, row.comment);
        }).then((response) => {
          console.log(response)
          if (response.code == 200) {
            this.msgSuccess(text + "成功");
          } else {
            this.msgError(text + "失败");
          }
        }).catch(function () {
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
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$confirm('是否确认删除名称为"' + row.title + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delBlog(row.id);
        }).then((response) => {
          if (response.code == 200) {
            this.msgSuccess("删除成功");
          } else {
            this.msgError("删除失败");
          }
          this.getList();
        }).catch(function () {
        });
      }
    }
  };
</script>
