<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="分类名称">
        <el-input v-model="queryParams.title" placeholder="请输入分类名称" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="分类描述">
        <el-input v-model="queryParams.description" placeholder="请输入分类描述" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="categoryList">
      <el-table-column label="分类主键" align="center" prop="id"/>
      <el-table-column label="分类名称" align="center" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="分类描述" align="center" prop="description" :show-overflow-tooltip="true"/>
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
      <el-table-column label="关联博客" align="center">
        <template slot-scope="scope">
          <el-popover
            placement="left"
            width="600"
            trigger="click">
            <el-table :data="scope.row.blogList">
              <el-table-column label="博客主键" prop="id"/>
              <el-table-column label="标题" prop="title" :show-overflow-tooltip="true"/>
              <el-table-column label="摘要" prop="summary" :show-overflow-tooltip="true"/>
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
                  <el-switch v-model="scope.row.comment" disabled/>
                </template>
              </el-table-column>
              <el-table-column label="推荐" align="center">
                <template slot-scope="scope">
                  <el-switch v-model="scope.row.support" disabled active-color="#13ce66"
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
            </el-table>
            <el-button size="mini" type="text" icon="el-icon-tickets" slot="reference">共 {{scope.row.blogList.length}}
              条数据
            </el-button>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit"
                     @click="handleUpdate(scope.row)">修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete"
                     @click="handleDelete(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList"/>

    <!-- 添加或修改分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入分类名称"/>
        </el-form-item>
        <el-form-item label="分类描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入分类描述"/>
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
    listCategory,
    getCategory,
    changeCategorySupport,
    delCategory,
    addCategory,
    updateCategory
  } from "@/api/blog/category";

  export default {
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 参数表格数据
        categoryList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 日期范围
        dateRange: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          title: undefined,
          description: undefined,
          support: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          title: [
            {required: true, message: "分类名称不能为空", trigger: "blur"}
          ],
          description: [
            {required: true, message: "分类描述不能为空", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询参数列表 */
      getList() {
        this.loading = true;
        listCategory(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            this.categoryList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          title: undefined,
          description: undefined,
          support: 1,
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加分类";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        getCategory(row.id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改分类";
        });
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
          row.status = row.status === false ? true : false;
        });
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateCategory(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addCategory(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        this.$confirm('是否确认删除名称为"' + row.title + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delCategory(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      }
    }
  };
</script>
