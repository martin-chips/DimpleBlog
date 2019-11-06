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
      <el-form-item>
        <el-button size="mini" type="primary" icon="el-icon-upload"
                   @click="open = true">上传文件
        </el-button>
      </el-form-item>
      <!-- 同步 -->
      <el-form-item>
        <el-button :icon="icon" class="filter-item" size="mini" type="warning"
                   @click="synchronize">同步数据
        </el-button>
      </el-form-item>
      <!-- 配置 -->
      <el-form-item>
        <el-button class="filter-item" size="mini" type="success" icon="el-icon-s-tools"
                   @click="doConfig">七牛配置
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 文件上传 -->
    <el-dialog :visible.sync="open" :close-on-click-modal="false" append-to-body width="500px" @close="doSubmit">
      <el-upload
        :before-remove="handleBeforeRemove"
        :on-success="handleSuccess"
        :on-error="handleError"
        :file-list="fileList"
        :headers="headers"
        :action="uploadUrl"
        class="upload-demo"
        multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" style="display: block;" class="el-upload__tip">请勿上传违法文件，且文件不超过15M</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="doSubmit">确认</el-button>
      </div>
    </el-dialog>

    <el-table v-loading="loading" ref="table" :data="contentList" size="small" style="width: 100%;">
      <el-table-column :show-overflow-tooltip="true" label="文件名">
        <template slot-scope="scope">
          <a href="JavaScript:;" class="el-link el-link--primary" target="_blank" type="primary"
             @click="download(scope.row.id)">{{ scope.row.name }}</a>
        </template>
      </el-table-column>
      <el-table-column prop="path" label="预览图">
        <template slot-scope="{row}">
          <el-image
            :src="row.url"
            :preview-src-list="[row.url]"
            fit="contain"
            lazy
            class="el-avatar">
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
      <el-table-column width="180px" prop="updateTime" label="创建日期">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100px" align="center">
        <template slot-scope="scope">
          <el-popover
            :ref="scope.row.id"
            placement="top"
            width="180">
            <p>确定删除本条数据吗？</p>
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
                @pagination="getList"/>
  </div>
</template>

<script>
  import QiNiuConfig from "./QiNiuConfig";
  import {listContent, delContent, downloadContent, syncContent} from "@/api/tool/qiNiu";
  import {getToken} from '@/utils/auth'

  export default {
    components: {QiNiuConfig},
    data() {
      return {
        //是否已经加载过了
        ready: false,
        //上传文件地址
        uploadUrl: '',
        //上传文件需要用到的token
        headers: {'Authorization': 'Bearer ' + getToken()},
        //上传文件的FileList
        fileList: [],
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 表格数据
        contentList: [],
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
          name: undefined,
          bucket: undefined,
          type: undefined
        },
        icon: 'el-icon-refresh',
        // 表单参数
        form: {}
      }
    },
    methods: {
      init() {
        if (!this.ready) {
          this.uploadUrl = process.env.VUE_APP_BASE_API + "/tool/qiNiu"
          // this.header = {'Authorization': 'Bearer ' + getToken()};
          console.log(this.header)
          this.getList();
          this.ready = true;
        }
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 下载 */
      download(id) {
        downloadContent(id);
      },
      /**刷新*/
      synchronize() {
        this.icon = 'el-icon-loading'
        this.buttonName = '同步中'
        syncContent().then(res => {
          this.icon = 'el-icon-refresh'
          this.buttonName = '同步数据'
          this.msgSuccess("同步成功")
          this.getList()
        }).catch(err => {
          this.icon = 'el-icon-refresh'
          this.buttonName = '同步数据'
          console.log(err.response.data.message)
        })
      },
      /**获取表格数据*/
      getList() {
        this.loading = true;
        listContent(this.queryParams).then(response => {
          this.contentList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 单条删除 */
      handleSubDelete(id) {
        this.loading = true;
        delContent(id).then((response) => {
          this.$refs[id].doClose()
          this.loading = false;
          if (response.code == 200) {
            this.msgSuccess("删除成功");
          } else {
            this.msgError("删除失败");
          }
          this.getList();
        }).catch(err => {
          this.msgError("删除失败");
          this.$refs[id].doClose()
          this.loading = false;
        });
      },
      handleSuccess(response, file, fileList) {
        this.getList();
        console.log(response)
        const uid = file.uid
        const id = response.id
        this.files.push({uid, id})
      },
      handleBeforeRemove(file, fileList) {
        for (let i = 0; i < this.files.length; i++) {
          if (this.files[i].uid === file.uid) {
            del(this.files[i].id).then(res => {
            })
            return true
          }
        }
      },
      // 监听上传失败
      handleError(e, file, fileList) {
        console.log(e);
        const msg = JSON.parse(e.message)
        this.$notify({
          title: msg.message,
          type: 'error',
          duration: 2500
        })
      },
      doConfig() {
        const _this = this.$refs.config
        _this.getConfig()
        _this.dialog = true
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url
        this.dialogVisible = true
      },
      doSubmit() {
        this.fileList = []
        this.open = false
        this.dialogImageUrl = ''
        this.dialog = false
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
