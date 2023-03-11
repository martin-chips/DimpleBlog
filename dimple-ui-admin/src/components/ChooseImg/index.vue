<template>
  <div class="app-container">
    <div class="header-img-box" @click="openDrawer">从媒体库选择</div>
    <el-drawer :visible.sync="drawerVisible"
               :append-to-body="true"
               title="媒体库" size="650px">
      <el-alert
        title="点击“文件名/备注”可以编辑文件名或者备注内容。"/>
      <div class="gva-btn-list">
        <el-form ref="searchForm" :inline="true" :model="queryParams">
          <el-form-item>
          <ImageUpload class="upload-btn-media-library" :isShowTip="false"/>
          </el-form-item>
          <el-form-item label="文件名">
            <el-input v-model="queryParams.title" class="keyword" placeholder="请输入文件名或备注"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="search" @click="getList">查询</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="media">
        <div v-for="(item,key) in picList" :key="key" class="media-box">
          <div class="header-img-box-list">
            <el-image
              :key="key"
              :src="item.url"
              @click="chooseImg(item.url,item.title)"
            >
              <template #error>
                <div class="header-img-box-list">
                  <el-icon>
                    <picture/>
                  </el-icon>
                </div>
              </template>
            </el-image>
          </div>
          <div @click="editFileName(item)" class="img-title">{{ item.name }}</div>
        </div>
      </div>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-drawer>
  </div>
</template>

<script>
import {MessageBox} from "element-ui";
import ImageUpload from "@/components/ImageUpload";

export default {
  name: "chooseImg",
  components: {ImageUpload},
  data() {
    return {
      drawerVisible: false,
      // 总条数
      total: 0,
      drawer: "",
      imageUrl: "",
      imageCommon: "",
      picList: [],
      queryParams: {
        pageNum: 1,
        title: "",
        pageSize: 10,
      },
    }
  },
  created() {
    this.getList();
  },
  methods: {
    editFileName(row) {
      MessageBox.prompt('请输入文件名或者备注', '编辑', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /\S/,
        inputErrorMessage: '不能为空',
        inputValue: row.name
      }).then(async ({value}) => {
        // row.name = value
        // const res = await editFileName(row)
        // if (res.code === 0) {
        //   this.$notify({
        //     type: 'success',
        //     message: '编辑成功!',
        //   })
        // }
      }).catch(() => {
        this.$notify({
          type: 'info',
          message: '取消修改'
        })
      })
    },
    openDrawer() {
      this.drawerVisible = true;
    },
    chooseImg(url) {
      this.$emit('onChooseImg', url);
      this.drawerVisible = false;
    },
    getList() {
      this.picList = [{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/gvalogo.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      },{
        name: "test",
        url: "https://qmplusimg.henrongyi.top/1576554439myAvatar.png",
      }]
      this.total = 12
    },

  }
}
</script>
<style lang="scss">
.upload-btn-media-library {
  margin-left: 20px;
}
.media {
  display: flex;
  flex-wrap: wrap;
  .media-box {
    width: 120px;
    margin-left: 20px;
    .img-title {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      line-height: 36px;
      text-align: center;
      cursor: pointer;
    }
    .header-img-box-list {
      width: 120px;
      height: 120px;
      border: 1px dashed #ccc;
      border-radius: 8px;
      text-align: center;
      line-height: 120px;
      cursor: pointer;
      overflow: hidden;
      .el-image__inner {
        max-width: 120px;
        max-height: 120px;
        vertical-align: middle;
        width: unset;
        height: unset;
      }
    }
  }
}
</style>
