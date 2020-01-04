<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="标题">
        <el-input v-model="queryParams.title" placeholder="请输入轮播图标题" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="queryParams.description" placeholder="请输入轮播图描述" clearable size="small" style="width: 240px"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索
        </el-button>
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
      <el-table-column label="轮播标题" align="center" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="链接地址" align="center" prop="url" :show-overflow-tooltip="true"/>
      <el-table-column label="描述内容" align="center" prop="description" :show-overflow-tooltip="true"/>
      <el-table-column label="点击次数" align="center" prop="click"/>
      <el-table-column label="显示" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.display" @change="handleDisplayChange(scope.row)" active-color="#13ce66"
                     inactive-color="#ff4949"/>
        </template>
      </el-table-column>
      <el-table-column label="图片" align="center" prop="headerImg">
        <template slot-scope="scope">
          <el-image
            style="width: 30px; height: 30px"
            :src="scope.row.headerImg"
            :preview-src-list="[scope.row.headerImg]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="新窗口打开" align="center" prop="target">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.target"
                     @change="handleTargetChange(scope.row)"/>
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
          <el-popover :ref="scope.row.id" placement="top" width="180">
            <p>确定删除本条数据吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消
              </el-button>
              <el-button :loading="loading" type="primary" size="mini" @click="handleSubDelete(scope.row.id)">确定
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

    <!-- 添加或修改分类对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item label="链接地址" prop="url">
          <el-input v-model="form.url" placeholder="请输入链接地址"/>
        </el-form-item>
        <el-form-item label="图片地址" prop="headerImg">
          <el-input v-model="form.headerImg" placeholder="请输入图片地址"/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" v-model="form.description"
                    placeholder="请输入描述"/>
        </el-form-item>
        <el-row>
          <el-col :span="8">
            <el-form-item label="显示" prop="display">
              <el-switch v-model="form.display" active-color="#13ce66"
                         inactive-color="#ff4949"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="新窗口打开" prop="target">
              <el-switch v-model="form.target" active-color="#13ce66"
                         inactive-color="#ff4949"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
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
    changeCarouselDisplay,
    changeCarouselTarget
  } from "@/api/system/carousel";
  import initData from '@/mixins/initData'

  export default {
    mixins: [initData],
    data() {
      return {
        formReset: {
          target: false,
          display: true
        },
        // 查询参数
        queryParams: {
          url: undefined,
          description: undefined,
          display: undefined
        },
        // 表单校验
        rules: {
          url: [
            {required: true, message: "链接地址不能为空", trigger: "blur"}
          ],
          title: [
            {required: true, message: "标题不能为空", trigger: "blur"},
            {min: 2, max: 60, message: '长度在 5 到 60 个字符', trigger: 'change'}
          ],
          description: [
            {required: true, message: "描述不能为空", trigger: "blur"},
            {min: 5, max: 150, message: '长度在 5 到 150 个字符', trigger: 'change'}
          ],
          headerImg: [
            {required: true, message: "图片地址不能为空", trigger: "blur"},
            {type: 'url', message: '请输入正确的图片地址', trigger: ['blur', 'change']}
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
        this.base = '/system/carousel';
        this.modelName = '轮播图';
        return true
      },
      handleDisplayChange(row) {
        let text = row.display ? "显示" : "隐藏";
        this.$confirm('确认要' + text + '"' + row.title + '"数据项吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeCarouselDisplay(row.id, row.display);
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
      handleTargetChange(row) {
        let text = row.target ? "当前窗口打开" : "新窗口打开";
        this.$confirm('确认要' + text + '"' + row.title + '"数据项吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeCarouselTarget(row.id, row.target);
        }).then((response) => {
          if (response.code == 200) {
            this.msgSuccess(text + "成功");
          } else {
            this.msgError(text + "失败");
          }
        }).catch(function () {
          row.target = row.target === false ? true : false;
        });
      },
    }
  };
</script>
