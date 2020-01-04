<template>
  <div class="app-container">
    <el-form :inline="true" label-width="68px">
      <el-form-item label="公告标题">
        <el-input v-model="queryParams.title" placeholder="请输入公告标题" clearable size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="操作人员">
        <el-input v-model="queryParams.createBy" placeholder="请输入操作人员" clearable size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="queryParams.type" placeholder="公告类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
      <el-table-column label="公告标题" align="center" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="公告类型" align="center" prop="type" :formatter="typeFormat"/>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="创建者" align="center" prop="createBy"/>
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
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

    <!-- 添加或修改公告对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="780px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="公告标题" prop="title">
              <el-input v-model="form.title" placeholder="请输入公告标题"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="公告类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择">
                <el-option
                  v-for="dict in typeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="内容" prop="content">
              <mavonEditor @change="contentChange" v-model="form.content" ref="editor" @imgAdd="handleEditorImgAdd" :subfield="false"
                           style="min-height: 200px;max-height: 400px"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer" style="padding-top:20px">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import initData from '@/mixins/initData'
  import marked from 'marked'
  import {uploadImgToQiNiu} from "@/api/common"
  import {mavonEditor} from 'mavon-editor'
  import 'mavon-editor/dist/css/index.css'

  export default {
    mixins: [initData],
    components: {
      mavonEditor
    },
    data() {
      return {
        // 类型数据字典
        statusOptions: [],
        // 状态数据字典
        typeOptions: [],
        // 查询参数
        queryParams: {
          title: undefined,
          createBy: undefined,
          status: undefined
        },
        // 表单校验
        rules: {
          title: [
            {required: true, message: "公告标题不能为空", trigger: "blur"},
            {min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'change'}
          ],
          content: [
            {required: true, message: "内容不能为空", trigger: "blur"},
            {min: 3, max: 500, message: '长度在 3 到 500 个字符', trigger: 'change'}
          ],
          type: [
            {required: true, message: "公告类型不能为空", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      this.$nextTick(() => {
        this.init()
      });
      this.getDicts("sys_notice_status").then(response => {
        this.statusOptions = response.data;
      });
      this.getDicts("sys_notice_type").then(response => {
        this.typeOptions = response.data;
      });
    },
    methods: {
      beforeInit() {
        this.base = '/system/notice';
        this.modelName = '公告';
        return true
      },
      handleEditorImgAdd(pos, $file) {
        // 第一步.将图片上传到服务器.
        var formdata = new FormData();
        formdata.append('file', $file);
        uploadImgToQiNiu(formdata).then((url) => {
          // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
          // $vm.$img2Url 详情见本页末尾
          $vm.$img2Url(pos, url);
        })
      },
      contentChange() {
        this.form.htmlContent = marked(this.form.content);
      },
      // 公告状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status);
      },
      // 公告状态字典翻译
      typeFormat(row, column) {
        return this.selectDictLabel(this.typeOptions, row.type);
      },
    }
  };
</script>
