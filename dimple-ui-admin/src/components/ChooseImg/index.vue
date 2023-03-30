<template>
  <div class="app-container">
    <div class="header-img-box" @click="openDrawer">从媒体库选择</div>
    <el-drawer :visible.sync="drawerVisible"
               :append-to-body="true"
               title="媒体库" size="650px">
      <el-alert class="chooseImage-alert">
        请上传 大小不超过 <b style="color: #f56c6c">5MB.</b>格式为 <b style="color: #f56c6c">"png", "jpg", "jpeg"</b>的文件
      </el-alert>
      <div class="gva-btn-list">
        <el-form ref="searchForm" :inline="true" :model="queryParams">
          <el-form-item>
            <UploadImage class="upload-btn-media-library" v-on:uploadSuccess="getList"/>
          </el-form-item>
          <el-form-item label="文件名">
            <el-input v-model="queryParams.name" class="keyword" placeholder="请输入文件名"/>
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
              <template>
                <div class="header-img-box-list">
                  <el-icon>
                    <picture/>
                  </el-icon>
                </div>
              </template>
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
          </div>
          <div :title="item.name" @click="editName(item)" class="img-title">{{ item.name }}</div>
        </div>
      </div>
      <pagination
        v-show="total>0"
        :total="total"
        :smail="true"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-drawer>
  </div>
</template>

<script>
import UploadImage from "@/components/ChooseImg/components/UploadImage";
import {editFileName, listFile} from "@/api/file/file"

export default {
  name: "chooseImg",
  components: {UploadImage},
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
        name: "",
        orderByColumn: "createTime",
        isAsc: "desc",
        type: "image",
        pageSize: 10,
      },
    }
  },
  created() {
    this.getList();
  },
  methods: {
    editName(row) {
      this.$prompt('请输入文件名', '编辑', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputErrorMessage: '不能为空',
        inputValue: row.name
      }).then(({value}) => {
        row.name = value
        console.log(12121)
        editFileName(row).then(response => {
          this.$notify({
            type: 'success',
            message: '编辑成功!',
          })
        })
      }).catch((reason) => {
        console.log(reason)
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
      this.loading = true;
      listFile(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.picList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },

  }
}
</script>
<style lang="scss">
.chooseImage-alert {
  margin-bottom: 5px;
}

.gva-btn-list {
  margin-top: 10px;
}

.header-img-box {
  width: 200px;
  height: 200px;
  border: 1px dashed #ccc;
  border-radius: 20px;
  text-align: center;
  line-height: 200px;
  cursor: pointer;
}

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
