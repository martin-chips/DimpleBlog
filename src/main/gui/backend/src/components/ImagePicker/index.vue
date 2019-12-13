<template>
  <div>
    <el-tabs tab-position="left">
      <el-tab-pane label="七牛云服务器" style="height: 400px;overflow-y: auto">
        <QiNiuImageContent @onImgSelect="onImgSelect"/>
      </el-tab-pane>
      <el-tab-pane label="本地服务器">
        <LocalImageContent @onImgSelect="onImgSelect"/>
      </el-tab-pane>
      <el-tab-pane label="本地上传">
        <el-upload
          class="avatar-uploader"
          action="https://httpbin.org/post"
          :show-file-list="false"
          accept="image/*">
          <i class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-tab-pane>
      <el-tab-pane label="网络图片">网络地址</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import QiNiuImageContent from "./components/QiNiuImageContent";
  import LocalImageContent from "./components/LocalImageContent";

  export default {
    name: "ImagePicker",
    components: {
      QiNiuImageContent,LocalImageContent
    },
    data() {
      return {}
    },
    created() {
    },
    methods: {
      onImgSelect(url) {
        this.$emit('onImgSelect', url);
      },
    }
  }
</script>

<style scoped>
  .selectImg {
    /*filter: blur(1px);*/
    border-radius: 4px !important;
    border: 1px solid #67C23A !important;
    background-color: #DCDFE6 !important;
    overflow: hidden !important;
    color: #409EFF !important;
  }

  .selectImg .el-card__body {
    background-color: cornflowerblue;
  }

  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    height: 100px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100%;
    height: 224px;
    line-height: 224px;
    text-align: center;
  }
</style>
