<template>
  <div>
    <el-tabs tab-position="left">
      <el-tab-pane label="七牛云服务器">
        <el-row :gutter="12">
          <el-col :span="8" v-for="item in qiNiuContent.rows">
            <div @click="onImgSelect(item)">
              <el-card shadow="hover" :class="{'selectImg':item.id==index}">
                <img :src="item.url" class="image">
                <div style="padding: 14px;">
                  <span>{{item.name}}</span>
                  <div class="bottom clearfix">
                    <time class="time">{{ item.createTime }}</time>
                  </div>
                </div>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="本地服务器">本地服务器</el-tab-pane>
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
  import {listQiNiuImage, listLocalImage} from "@/api/common"

  export default {
    name: "ImagePicker",
    data() {
      return {
        index: 0,
        QiNiuPageNum: 1,
        QiNiuPageSize: 10,
        LocalPageNum: 1,
        LocalPageSize: 10,
        qiNiuContent: {},
        localContent: {}
      }
    },
    created() {
      listQiNiuImage({pageNum: this.QiNiuPageNum, pageSize: this.QiNiuPageSize}).then(response => {
        this.qiNiuContent = response;
      });
      listLocalImage({pageNum: this.LocalPageNum, pageSize: this.LocalPageSize}).then(response => {
        this.localContent = response;
      });
    },
    methods: {
      onImgSelect(item) {
        this.index = item.id;
        console.log(item)
        this.$emit('onImgSelect', item.url);
      },
      test() {
        console.log(1111)
      }
    }
  }
</script>

<style scoped>
  .selectImg {
    /*filter: blur(1px);*/
    border-radius: 4px!important;
    border: 1px solid #67C23A!important;
    background-color: #DCDFE6!important;
    overflow: hidden!important;
    color: #409EFF!important;
  }

  .selectImg .el-card__body{
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
