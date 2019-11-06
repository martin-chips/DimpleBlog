<template>
  <div class="app-container" style="padding: 8px;">
    <qi-niu-config/>

    <el-form :inline="true" label-width="68px">
      <el-form-item label="公告标题">
        <el-input v-model="queryParams.name" placeholder="请输入公告标题" clearable size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <div style="display: inline-block;margin: 0px 2px;">
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-upload" @click="dialog = true">上传文件
      </el-button>
    </div>
    <!-- 同步 -->
    <el-button :icon="icon" class="filter-item" size="mini" type="warning" @click="synchronize">同步数据</el-button>
    <!-- 配置 -->
    <div style="display: inline-block;margin: 0px 2px;">
      <el-button class="filter-item" size="mini" type="success" icon="el-icon-s-tools"
                 @click="doConfig">七牛配置
      </el-button>
    </div>
    <!-- 文件上传 -->
    <el-dialog :visible.sync="dialog" :close-on-click-modal="false" append-to-body width="500px" @close="doSubmit">
      <el-upload
        :before-remove="handleBeforeRemove"
        :on-success="handleSuccess"
        :on-error="handleError"
        :file-list="fileList"
        :headers="headers"
        :action="qiNiuUploadApi"
        class="upload-demo"
        multiple>
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" style="display: block;" class="el-upload__tip">请勿上传违法文件，且文件不超过15M</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="doSubmit">确认</el-button>
      </div>
    </el-dialog>

    <el-table v-loading="loading" ref="table" :data="data" size="small" style="width: 100%;">
      <el-table-column :show-overflow-tooltip="true" label="文件名">
        <template slot-scope="scope">
          <a href="JavaScript:;" class="el-link el-link--primary" target="_blank" type="primary"
             @click="download(scope.row.id)">{{ scope.row.key }}</a>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" prop="suffix" label="文件类型"/>
      <el-table-column prop="bucket" label="空间名称"/>
      <el-table-column prop="size" label="文件大小"/>
      <el-table-column prop="type" label="空间类型"/>
      <el-table-column width="180px" prop="updateTime" label="创建日期">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100px" align="center" fixed="right">
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
            <el-button slot="reference" type="danger" icon="el-icon-delete" size="mini"/>
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
  import {listContent, delContent, uploadContent, downloadContent, syncContent} from "@/api/tool/qiNiu";

  export default {
    components: {QiNiuConfig},
    data() {
      return {
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
        // 表单参数
        form: {}
      }
    },
    created() {
      this.getList();
    },
    methods: {
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 下载 */
      download(id) {
        downloadContent(id);
      },
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
    }
  }
</script>

<style scoped>
</style>
