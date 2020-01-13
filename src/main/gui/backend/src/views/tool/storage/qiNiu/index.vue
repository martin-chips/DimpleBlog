<template>
  <div class="app-container" style="padding: 8px;">
    <QiNiuConfig ref="config"/>

    <el-form :inline="true" label-width="68px">
      <el-form-item label="文件名称">
        <el-input v-model="queryParams.name" placeholder="请输入文件名称" clearable size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="上传时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini"
                   @click="handleQuery">搜索
        </el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button size="mini" type="primary" icon="el-icon-upload"
                   @click="open = true">上传文件
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single"
                   @click="handleUpdate(null)">修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button :icon="icon" class="filter-item" size="mini" type="warning"
                   @click="handleSynchronize">{{buttonName}}
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button class="filter-item" size="mini" type="success" icon="el-icon-s-tools"
                   @click="handleConfig">七牛配置
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
      <el-table-column :show-overflow-tooltip="true" label="文件名">
        <template slot-scope="scope">
          <a href="JavaScript:" class="el-link el-link--primary" target="_blank" type="primary"
             @click="download(scope.row.id)">{{ scope.row.name }}</a>
        </template>
      </el-table-column>
      <el-table-column prop="path" label="预览图">
        <template slot-scope="{row}">
          <el-image :src="row.url" :preview-src-list="[row.url]" fit="contain" lazy class="el-avatar">
            <div slot="error">
              <i class="el-icon-document"/>
            </div>
          </el-image>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="suffix" label="文件类型"/>
      <el-table-column prop="bucket" label="空间名称"/>
      <el-table-column prop="size" label="文件大小"/>
      <el-table-column prop="type" label="空间类型"/>
      <el-table-column width="180px" prop="updateTime" label="上传时间">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100px" align="center">
        <template slot-scope="scope">
          <el-popover :ref="scope.row.id" placement="top" width="180">
            <p>删除该文件会导致所有引用该文件的博客图片失效!确定删除本条数据吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消</el-button>
              <el-button :loading="loading" type="primary" size="mini" @click="handleSubDelete(scope.row.id)">确定
              </el-button>
            </div>
            <el-button slot="reference" type="text" icon="el-icon-delete" size="mini">删除
            </el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>

    <!--分页组件-->
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="init"/>

    <!--上传图片-->
    <el-dialog :visible.sync="open" :close-on-click-modal="false" append-to-body width="600px" @close="doSubmit"
               title="上传图片">
      <el-upload
        drag
        :action="imagesUploadApi"
        :headers="headers"
        :on-remove="handleRemove"
        :file-list="fileList"
        list-type="picture"
        multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传图片文件，且不超过500kb</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="doSubmit">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import QiNiuConfig from "./QiNiuConfig";
  import {downloadContent, syncContent} from "@/api/tool/qiNiu";
  import {getToken} from '@/utils/auth'
  import initData from '@/mixins/initData'

  export default {
    components: {QiNiuConfig},
    mixins: [initData],
    data() {
      return {
        imageDialog: false,
        imagesUploadApi: '',
        buttonName: '同步数据',
        //文件列表
        fileList: [],
        //上传图片Dialog
        open: false,
        //是否已经加载过了
        ready: false,
        //上传文件地址
        uploadUrl: '',
        //上传文件需要用到的token
        headers: {'Authorization': 'Bearer ' + getToken()},
        // 查询参数
        queryParams: {
          name: undefined,
          bucket: undefined,
          type: undefined
        },
        icon: 'el-icon-refresh',
      }
    },
    created() {
      this.$nextTick(() => {
        this.init();
        this.imagesUploadApi = process.env.VUE_APP_BASE_API + "/tool/qiNiu"
      })
    },
    methods: {
      beforeInit() {
        this.base = '/tool/qiNiu';
        this.modelName = '七牛云';
        return true
      },
      /** 下载 */
      download(id) {
        downloadContent(id);
      },
      /**刷新*/
      handleSynchronize() {
        this.icon = 'el-icon-loading';
        this.buttonName = '同步中';
        syncContent().then(res => {
          this.icon = 'el-icon-refresh';
          this.buttonName = '同步数据';
          this.msgSuccess("同步成功");
          this.init();
        }).catch(err => {
          this.icon = 'el-icon-refresh';
          this.buttonName = '同步数据';
          this.msgError("同步失败");
          console.log(err.response.msg)
        })
      },
      handleSuccess(response, file, fileList) {
        if (response.code == 200) {
          this.msgSuccess("上传成功");
        } else {
          this.msgError(response.msg)
        }
      },
      handleRemove(file, fileList) {
        let id = this.file.response.data.id;
        handleSubDelete(id);
      },
      handleConfig() {
        const _this = this.$refs.config;
        _this.getConfig();
        _this.dialog = true;
      },
      doSubmit() {
        this.fileList = [];
        this.open = false;
        this.dialogImageUrl = '';
        this.dialog = false;
        this.init()
      },
    }
  }
</script>
<style scoped>
  /deep/ .el-image__error, .el-image__placeholder {
    background: none;
  }
</style>
