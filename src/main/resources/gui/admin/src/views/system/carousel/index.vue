<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="友链名称">
        <el-input v-model="queryParams.title" placeholder="请输入友链名称" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="友链描述">
        <el-input v-model="queryParams.description" placeholder="请输入友链描述" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索
        </el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增
        </el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="carouselList">
      <el-table-column label="轮播组件" align="center" prop="id"/>
      <el-table-column label="链接地址" align="center" prop="url" :show-overflow-tooltip="true"/>
      <el-table-column label="描述内容" align="center" prop="description" :show-overflow-tooltip="true"/>
      <el-table-column label="点击次数" align="center" prop="click"/>
      <el-table-column label="显示" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.display" @change="handleDisplayChange(scope.row)" active-color="#13ce66"
                     inactive-color="#ff4949"/>
        </template>
      </el-table-column>
      <el-table-column label="图片" align="center" prop="imgUrl">
        <template slot-scope="scope">
          <el-image
            style="width: 30px; height: 30px"
            :src="scope.row.imgUrl"
            :preview-src-list="previewImg(scope.row.imgUrl)">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="当前窗口打开" align="center" prop="target">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.target"
                     @change="handleCommentChange(scope.row)"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text"
                     @click="handleUpdate(scope.row)">修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete"
                     @click="handleDelete(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 添加或修改分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="链接地址" prop="url">
          <el-input maxlength="128" show-word-limit v-model="form.title" placeholder="请输入链接地址"/>
        </el-form-item>
        <el-form-item label="图片地址" prop="url">
          <el-input maxlength="128" show-word-limit v-model="form.title" placeholder="请输入图片地址"/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input maxlength="512" show-word-limit v-model="form.description" placeholder="请输入描述"/>
        </el-form-item>
        <el-row>
          <el-col :span="8">
            <el-form-item label="显示" prop="display">
              <el-switch v-model="form.display" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="当前窗口打开" prop="target">
              <el-switch v-model="form.target" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="网站图标" prop="headerImg">
          <el-input maxlength="128" show-word-limit v-model="form.headerImg" placeholder="请输入网站图标地址"/>
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
    listCarsousel,
    getCarsousel,
    changeCarsouselDisplay,
    delCarsousel,
    addCarsousel,
    updateCarsousel
  } from "@/api/system/carousel";

  export default {
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 参数表格数据
        carouselList: [],
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
          url: undefined,
          description: undefined,
          display: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          url: [
            {required: true, message: "链接地址不能为空", trigger: "blur"}
          ],
          description: [
            {required: true, message: "描述不能为空", trigger: "blur"}
          ],
          imgUrl: [
            {required: true, message: "图片地址不能为空", trigger: "blur"},
            {type: 'url', message: '请输入正确的图片地址', trigger: ['blur', 'change']}
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      previewImg(url) {
        return [url];
      },
      /** 查询参数列表 */
      getList() {
        this.loading = true;
        listCarsousel(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
            this.carouselList = response.rows;
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
          url: undefined,
          description: undefined,
          display: true,
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
        getCarsousel(row.id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改分类";
        });
      },
      handleDisplayChange(row) {
        let text = row.support ? "显示" : "隐藏";
        this.$confirm('确认要' + text + '"' + row.title + '"数据项吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeCarsouselDisplay(row.id, row.display);
        }).then((response) => {
          if (response.code == 200) {
            this.msgSuccess(text + "成功");
          } else {
            this.msgError(text + "失败");
          }
        }).catch(function () {
          row.display = row.display === false ? true : false;
        });
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateCarsousel(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addCarsousel(this.form).then(response => {
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
          return delLink(row.id);
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
