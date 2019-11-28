<template>
  <div class="createPost-container">
    <el-form ref="form" :model="form" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+(form.status==null?'publish':'draft')">
        <el-button :loading="loading" style="margin-left: 10px;" icon="el-icon-check" type="success" plain
                   @click="submitBlog">发布
        </el-button>
        <el-button :loading="loading" icon="el-icon-message" type="warning" plain
                   @click="draftBlog">保存草稿
        </el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-row>
          <el-row>
            <el-col :span="18">
              <el-form-item prop="title" label="标题: " label-width="60px">
                <el-input placeholder="请输入标题" v-model="form.title" clearable></el-input>
              </el-form-item>
              <el-form-item label-width="60px" label="摘要: " prop="summary">
                <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 3}" placeholder="请输入摘要"
                          v-model="form.summary"></el-input>
              </el-form-item>
              <el-row>
                <el-col :span="8">
                  <el-form-item prop="categoryId" label-width="60px" label="分类: ">
                    <el-select v-model="form.categoryId" filterable allow-create default-first-option
                               placeholder="请选择文章分类">
                      <el-option v-for="item in categoryOptions" :key="item.id" :label="item.title"
                                 :value="item.id"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label-width="60px" label="推荐: " class="postInfo-container-item" prop="support">
                    <el-switch v-model="form.support" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label-width="60px" label="评论: " class="postInfo-container-item" prop="comment">
                    <el-radio-group v-model="form.comment">
                      <el-radio :label="true">开启</el-radio>
                      <el-radio :label="false">关闭</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>
              <div class="postInfo-container">
                <el-row>
                  <el-col :span="8">
                    <el-form-item label-width="60px" label="权重: " prop="weight">
                      <el-rate v-model="form.weight" :max="5" :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                               :low-threshold="1" :high-threshold="5" style="display:inline-block"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="16">
                    <el-form-item label-width="60px" label="标签: " prop="tagTitleList">
                      <el-select
                        style="width:100%" v-model="form.tagTitleList" multiple default-first-option filterable remote
                        :loading="loading"
                        :remote-method="getRemoteTagList" allow-create placeholder="请选择文章标签">
                        <el-option v-for="item in blogTagOptions" :key="item" :label="item"
                                   :value="item"/>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
              </div>
            </el-col>
            <el-col :span="6">
              <el-form-item prop="headerImg" label-width="60px" label="封面">
                <el-upload
                  class="avatar-uploader"
                  action="https://httpbin.org/post"
                  :show-file-list="false"
                  accept="image/*"
                  :on-success="handleUploadSuccess">
                  <img v-if="form.headerImg" :src="form.headerImg" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>
        </el-row>

        <el-form-item prop="content" style="margin-bottom: 30px;">
          <mavonEditor v-model="form.content" ref="editor" @change="mavonChangeHandle"
                       style="min-height: 500px;"/>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
  import Sticky from '@/components/Sticky' // 粘性header组件
  import {mavonEditor} from 'mavon-editor'
  import 'mavon-editor/dist/css/index.css'
  import DropZone from '@/components/DropZone' // 上传文件组件
  import {listBlogTagList, getBlog, addBlog, updateBlog, addBlogDraft, updateBlogDraft} from "@/api/blog/blog";
  import {listCategory} from "@/api/blog/category";
  import {getToken} from '@/utils/auth'
  import marked from 'marked'

  export default {
    name: 'BlogDetail',
    components: {DropZone, Sticky, mavonEditor},
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        //上传图片的地址
        imagesUploadApi: '',
        //上传文件需要用到的token
        headers: {'Authorization': 'Bearer ' + getToken()},
        tags: '',
        form: {
          weight: 1,
          tagTitleList: [],
          comment: true,
          support: false
        },
        loading: false,
        tempRoute: {},
        categoryOptions: [],
        blogTagOptions: [],
        // 表单校验
        rules: {
          title: [
            {required: true, message: "文章标题不能为空", trigger: "blur"},
            {min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'change'}
          ],
          summary: [
            {required: true, message: "文章摘要不能为空", trigger: "blur"},
            {min: 10, max: 150, message: '长度在 10 到 150 个字符', trigger: 'change'}
          ],
          categoryId: [
            {required: true, message: "文章分类不能为空", trigger: "change"}
          ],
          comment: [
            {required: true, message: "评论不能为空", trigger: "change"}
          ],
          tag: [
            {required: true, message: "至少选择一个标签", trigger: "change"}
          ]
        }
      }
    },
    created() {
      if (this.isEdit) {
        const id = this.$route.params && this.$route.params.id
        this.fetchData(id)
      }
      this.tempRoute = Object.assign({}, this.$route);
      //设置category
      this.getCategory();
      this.imagesUploadApi = process.env.VUE_APP_BASE_API + "/tool/qiNiu"
    },
    methods: {
      //上传文件成功回调方法
      handleUploadSuccess(res, file) {
        this.form.headerImg = res.data.url;
      },
      //查询标签
      getRemoteTagList(query) {
        if (query !== '') {
          this.loading = true;
          setTimeout(() => {
            this.loading = false;
            listBlogTagList(query).then(response => {
                this.blogTagOptions = response.rows;
                this.loading = false;
              }
            );
          }, 200);
        } else {
          this.blogTagOptions = []
        }
      },
      //获取文章分类
      getCategory() {
        listCategory().then(response => {
            if (response.code == 200) {
              this.categoryOptions = response.rows;
            } else {
              this.msgError(response.msg);
            }
          }
        );
      },
      dropzoneS(file) {
        console.log(file)
        this.$message({message: '上传成功', type: 'success'})
      },
      dropzoneR(file) {
        console.log(file)
        this.$message({message: '删除成功', type: 'success'})
      },
      fetchData(id) {
        getBlog(id).then(response => {
          if (response.code != 200) {
            this.msgError(response.msg);
            return;
          }
          this.form = response.data;
        })
      },
      mavonChangeHandle(context, render) {
        console.log(marked(context))
      },
      submitBlog() {
        this.form.htmlContent = marked(this.form.content);
        this.$refs.form.validate(valid => {
          if (valid) {
            this.loading = true
            this.form.status = true
            let obj = JSON.parse(JSON.stringify(this.form));
            if (obj.id == undefined) {
              addBlog(obj).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("发布成功");
                  this.$store.dispatch('tagsView/delView', this.$route)
                  this.$router.push({path: '/blog/blog'})
                } else {
                  this.msgError(response.msg);
                }
                this.loading = false;
              }).catch(error => {
                this.loading = false;
              });
            } else {
              updateBlog(obj).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("发布成功");
                  this.$store.dispatch('tagsView/delView', this.$route)
                  this.$router.push({path: '/blog/blog'})
                } else {
                  this.msgError(response.msg);
                }
                this.loading = false;
              }).catch(error => {
                this.loading = false;
              });
            }
          }
        })
      },
      draftBlog() {
        this.form.htmlContent = marked(this.form.content);
        if (this.form.content.length === 0 || this.form.title.length === 0) {
          this.$message({
            message: '请填写必要的标题和内容',
            type: 'warning'
          })
          return
        }
        let obj = JSON.parse(JSON.stringify(this.form));
        obj.status = false;
        if (obj.id == undefined) {
          addBlogDraft(obj).then(response => {
            if (response.code === 200) {
              this.msgSuccess("保存草稿成功");
            } else {
              this.msgError(response.msg);
            }
          });
        } else {
          updateBlogDraft(obj).then(response => {
            if (response.code === 200) {
              this.msgSuccess("保存草稿成功");
            } else {
              this.msgError(response.msg);
            }
          });
        }
      }
    }
  }
</script>

<style lang="scss">
  @import "~@/assets/styles/mixin.scss";

  .createPost-container {
    position: relative;

  .createPost-main-container {
    padding: 30px 45px 20px 50px;

  .postInfo-container {
    position: relative;
  @include clearfix;
    margin-bottom: 10px;

  .postInfo-container-item {
    float: left;
  }

  }
  }

  .word-counter {
    width: 40px;
    position: absolute;
    right: 10px;
    top: 0px;
  }

  }

  .article-textarea /deep/ {

  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }

  }

  .el-tag + .el-tag {
    margin-left: 10px;
  }


  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 100%;
    height: 224px;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100%;
    height: 224px;
    line-height: 224px;
    text-align: center;
  }

  .avatar {
    width: 100%;
    height: 100%;
    display: block;
  }
</style>
