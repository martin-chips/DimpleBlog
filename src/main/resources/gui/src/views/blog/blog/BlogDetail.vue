<template>
  <div class="createPost-container">
    <el-form ref="form" :model="form" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar '+form.status">
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitBlog">
          发布
        </el-button>
        <el-button v-loading="loading" type="warning" @click="draftBlog">
          保存草稿
        </el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-row>
          <el-row>
            <el-col :span="16">
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
                    <el-form-item label-width="60px" label="标签: " prop="tag">
                      <el-select
                        style="width:100%" v-model="form.tag" multiple default-first-option filterable remote
                        :loading="loading"
                        :remote-method="getRemoteTagList" allow-create placeholder="请选择文章标签">
                        <el-option v-for="item in blogTagOptions" :key="item" :label="item" :value="item"></el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="headerImg" label-width="60px" label="封面" class="postInfo-container-item">
                <drop-zone id="myVueDropzone" url="https://httpbin.org/post" @dropzone-removedFile="dropzoneR"
                           @dropzone-success="dropzoneS"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-row>

        <el-form-item prop="content" style="margin-bottom: 30px;">
          <Tinymce ref="editor" v-model="form.content" :height="400"/>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
  import Sticky from '@/components/Sticky' // 粘性header组件
  import Tinymce from '@/components/Tinymce' //富文本
  import DropZone from '@/components/DropZone' // 上传文件组件
  import {listBlogTagList, getBlog, addBlog, updateBlog} from "@/api/blog/blog";
  import {listCategory} from "@/api/blog/category";

  export default {
    name: 'BlogDetail',
    components: {Tinymce, DropZone, Sticky},
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        form: {
          weight: 1,
          tag: [],
          comment: true
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
    },
    methods: {
      //获取标签集合
      getRemoteTagList(query) {
        if (query !== '') {
          this.loading = true;
          setTimeout(() => {
            this.loading = false;
            listBlogTagList(query).then(
              response => {
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
        listCategory().then(
          response => {
            this.categoryOptions = response.rows;
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
          response.data.tag = response.data.tag.split(",");
          this.form = response.data;
          this.setPageTitle();
        })
      },
      setPageTitle() {
        const title = '编辑文章'
        document.title = `${title} - ${this.form.id}`
      },
      submitBlog() {
        this.$refs.form.validate(valid => {
          if (valid) {
            this.loading = true
            this.form.status = true
            this.form.tag = this.form.tag.join(",");
            console.log(this.form);
            if (this.form.id == undefined) {
              addBlog(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("发布成功");
                } else {
                  this.msgError(response.msg);
                }
                this.loading = false;
                this.form.tag = this.form.tag.split(",");
              });
            } else {
              updateBlog(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("发布成功");
                } else {
                  this.msgError(response.msg);
                }
                this.loading = false;
                this.form.tag = this.form.tag.split(",");
              });
            }

          }
        })
      },
      draftBlog() {
        if (this.form.content.length === 0 || this.form.title.length === 0) {
          this.$message({
            message: '请填写必要的标题和内容',
            type: 'warning'
          })
          return
        }
        this.form.status = false;
        if (this.form.id == undefined) {
          addBlog(this.form).then(response => {
            if (response.code === 200) {
              this.msgSuccess("发布成功");
            } else {
              this.msgError(response.msg);
            }
          });
        } else {
          updateBlog(this.form).then(response => {
            if (response.code === 200) {
              this.msgSuccess("发布成功");
            } else {
              this.msgError(response.msg);
            }
          });
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
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

  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }

  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>
