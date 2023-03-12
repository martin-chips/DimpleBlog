<template>
  <div class="component-upload-image">
    <el-upload
      class="upload-btn"
      :action="uploadImgUrl"
      :on-success="handleUploadSuccess"
      :before-upload="handleBeforeUpload"
      :on-error="handleUploadError"
      :fileList="[]"
      ref="imageUpload"
      :headers="headers"
    >
      <el-button plain
                 icon="el-icon-upload2"
                 size="mini"
                 type="primary">点击上传
      </el-button>
    </el-upload>
  </div>
</template>

<script>
import {getToken} from "@/utils/auth";

export default {
  data() {
    return {
      fileType: ["png", "jpg", "jpeg"],
      fileSize: 5,
      dialogImageUrl: "",
      dialogVisible: false,
      hideUpload: false,
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/file/file/upload", // 上传的图片服务器地址
      headers: {
        Authorization: "Bearer " + getToken(),
      },
    };
  },
  methods: {
    // 上传前loading加载
    handleBeforeUpload(file) {
      let isImg = false;
      if (this.fileType.length) {
        let fileExtension = "";
        if (file.name.lastIndexOf(".") > -1) {
          fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1);
        }
        isImg = this.fileType.some(type => {
          if (file.type.indexOf(type) > -1) return true;
          return (fileExtension && fileExtension.indexOf(type) > -1);
        });
      } else {
        isImg = file.type.indexOf("image") > -1;
      }

      if (!isImg) {
        this.$modal.msgError(`文件格式不正确, 请上传${this.fileType.join("/")}图片格式文件!`);
        return false;
      }
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$modal.msgError(`上传头像图片大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
      this.$modal.loading("正在上传图片，请稍候...");
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      if (res.code === 200) {
        this.uploadedSuccessfully();
      } else {
        file.status='fail'
        this.$modal.closeLoading();
        this.$modal.msgError(res.msg);
        setTimeout(this.$refs.imageUpload.clearFiles(), 1000)
      }
    },
    // 上传失败
    handleUploadError() {
      this.$modal.msgError("上传图片失败，请重试");
      this.$modal.closeLoading();
    },
    // 上传结束处理
    uploadedSuccessfully() {
      this.$emit("uploadSuccess");
      this.$modal.closeLoading();
      setTimeout(this.$refs.imageUpload.clearFiles(), 1000)
    }
  }
};
</script>
<style lang="scss" scoped>
// .el-upload--picture-card 控制加号部分
::v-deep.hide .el-upload--picture-card {
  display: none;
}

// 去掉动画效果
::v-deep .el-list-enter-active,
::v-deep .el-list-leave-active {
  transition: all 0s;
}

::v-deep .el-list-enter, .el-list-leave-active {
  opacity: 0;
  transform: translateY(0);
}
</style>

