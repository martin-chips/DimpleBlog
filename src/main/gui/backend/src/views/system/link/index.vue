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
      <el-table-column label="友链名称" align="center" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="友链描述" align="center" prop="description" :show-overflow-tooltip="true"/>
      <el-table-column label="显示" align="center">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.display" @change="handleDisplayChange(scope.row)" active-color="#13ce66"
                     inactive-color="#ff4949"/>
        </template>
      </el-table-column>
      <el-table-column label="权重" align="center" prop="weight" :show-overflow-tooltip="true"/>
      <el-table-column label="站长邮箱" align="center" prop="email" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <div v-if="scope.row.status">
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
          </div>
          <div v-else>
            <el-button size="mini" type="text" icon="el-icon-check"
                       @click="handleStatus(scope.row,true)">通过
            </el-button>
            <el-button size="mini" type="text" icon="el-icon-close"
                       @click="handleStatus(scope.row,false)">拒绝
            </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="init"/>

    <!-- 添加或修改分类对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="友链名称" prop="title">
          <el-input maxlength="128" show-word-limit v-model="form.title" placeholder="请输入友链名称"/>
        </el-form-item>
        <el-form-item label="友链描述" prop="description">
          <el-input maxlength="512" show-word-limit v-model="form.description" placeholder="请输入友链描述"/>
        </el-form-item>
        <el-form-item label="友链地址" prop="url">
          <el-input maxlength="128" show-word-limit v-model="form.url" placeholder="请输入友链地址"/>
        </el-form-item>
        <el-row>
          <el-col :span="8">
            <el-form-item label="显示" prop="display">
              <el-switch v-model="form.display" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="权重" prop="weight">
              <el-input v-model.number="form.weight" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="网站图标" prop="headerImg">
          <el-input maxlength="128" show-word-limit v-model="form.headerImg" placeholder="请输入网站图标地址"/>
        </el-form-item>
        <el-form-item label="站长邮箱" prop="email">
          <el-input maxlength="128" show-word-limit v-model="form.email" placeholder="请输入站长邮箱地址"/>
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
    listLink,
    getLink,
    changeLinkDisplay,
    delLink,
    changeLinkStatus,
    addLink,
    updateLink
  } from "@/api/system/link";
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
        // 表单校验
        rules: {
          title: [
            {required: true, message: "网站名称不能为空", trigger: "blur"},
            {min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'change'}
          ],
          url: [
            {required: true, message: "网站地址不能为空", trigger: "blur"},
            {type: 'url', message: '请输入正确的URL地址', trigger: ['blur', 'change']}
          ],
          headerImg: [
            {required: true, message: "网站图标不能为空", trigger: "blur"},
            {type: 'url', message: '请输入正确的图片地址', trigger: ['blur', 'change']}
          ],
          description: [
            {required: true, message: "描述不能为空", trigger: "blur"},
            {min: 3, max: 500, message: '长度在 3 到 500 个字符', trigger: 'change'}
          ],
          weight: [
            {required: true, message: "权重不能为空", trigger: "blur"}
          ],
          email: [
            {required: true, message: "邮箱不能为空", trigger: "blur"},
            {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
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
        this.base = '/system/link';
        this.modelName = '友链';
        return true
      },
      handleStatus(row, pass) {
        let text = pass ? "通过" : "拒绝";
        this.$confirm('确认要' + text + '"' + row.title + '"申请吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeLinkStatus(row.id, pass);
        }).then((response) => {
          if (response.code == 200) {
            this.msgSuccess(text + "成功");
          } else {
            this.msgError(text + "失败");
          }
          this.getList();
        }).catch(function () {
          row.status = row.status === false ? true : false;
        });
      },
      handleDisplayChange(row) {
        let text = row.support ? "显示" : "隐藏";
        this.$confirm('确认要' + text + '"' + row.title + '"数据项吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return changeLinkDisplay(row.id, row.display);
        }).then((response) => {
          if (response.code == 200) {
            this.msgSuccess(text + "成功");
          } else {
            this.msgError(text + "失败");
          }
        }).catch(function () {
          row.display = row.display === false ? true : false;
        });
      }
    }
  };
</script>
