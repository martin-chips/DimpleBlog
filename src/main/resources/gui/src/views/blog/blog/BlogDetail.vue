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
            <el-col :span="12">
              <el-form-item prop="title" label="标题: " label-width="60px">
                <el-input placeholder="请输入标题" v-model="form.title" clearable></el-input>
              </el-form-item>
              <el-form-item label-width="60px" label="摘要: " prop="summary">
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 2, maxRows: 3}"
                  placeholder="请输入摘要"
                  v-model="form.summary">
                </el-input>
              </el-form-item>
              <el-row>
                <el-col :span="8">
                  <el-form-item prop="summary" label-width="60px" label="分类: ">
                    <el-select
                      v-model="form.summary"
                      filterable
                      allow-create
                      default-first-option
                      placeholder="请选择文章分类">
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label-width="60px" label="推荐: " class="postInfo-container-item" prop="support">
                    <el-switch
                      v-model="form.support"
                      active-color="#13ce66"
                      inactive-color="#ff4949">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label-width="60px" label="评论: " class="postInfo-container-item" prop="comment">
                    <el-radio-group v-model="form.comment">
                      <el-radio label="true">开启</el-radio>
                      <el-radio label="false">关闭</el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>
              <div class="postInfo-container">
                <el-row>
                  <el-col :span="8">
                    <el-form-item label-width="60px" label="权重: " prop="weight">
                      <el-rate
                        v-model="form.weight"
                        :max="5"
                        :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                        :low-threshold="1"
                        :high-threshold="3"
                        style="display:inline-block"
                      />
                    </el-form-item>
                  </el-col>
                  <el-col :span="16">
                    <el-form-item label-width="60px" label="标签: " prop="tag">
                      <el-tag
                        :key="tag"
                        v-for="tag in dynamicTags"
                        closable
                        :disable-transitions="false"
                        @close="handleClose(tag)">
                        {{tag}}
                      </el-tag>
                      <el-input
                        class="input-new-tag"
                        v-if="inputVisible"
                        v-model="inputValue"
                        ref="saveTagInput"
                        size="small"
                        @keyup.enter.native="handleInputConfirm"
                        @blur="handleInputConfirm"
                      >
                      </el-input>
                      <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
                    </el-form-item>
                  </el-col>
                </el-row>
              </div>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="headerImg" label-width="60px" label="封面"
                            class="postInfo-container-item">
                <Upload v-model="form.headerImg"/>
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
  import Upload from '@/components/Upload/SingleImage3'
  import MDinput from '@/components/MDinput'
  import Sticky from '@/components/Sticky' // 粘性header组件
  import Tinymce from '@/components/Tinymce' // 粘性header组件

  const defaultForm = {
    status: 'draft',
    title: '', // 文章题目
    content: '', // 文章内容
    summary: '', // 文章摘要
    headerImg: '', // 文章图片
    weight: 1, // 前台展示时间
    id: undefined,
    category: 6,
    comment: false,
    support: false
  }

  export default {
    name: 'BlogDetail',
    components: {MDinput, Tinymce, Sticky, Upload},
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        form: Object.assign({}, defaultForm),
        loading: false,
        userListOptions: [],
        tempRoute: {},
        dynamicTags: ['标签一', '标签二', '标签三'],
        inputVisible: false,
        inputValue: ''
      }
    },
    computed: {
      contentShortLength() {
        return this.form.summary.length
      }
    },
    created() {
      if (this.isEdit) {
        const id = this.$route.params && this.$route.params.id
        this.fetchData(id)
      }
      this.tempRoute = Object.assign({}, this.$route)
    },
    methods: {
      handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      },

      showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },

      handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      },
      fetchData(id) {
        fetchArticle(id).then(response => {
          this.form = response.data

          // just for test
          this.form.title += `   Article Id:${this.form.id}`
          this.form.content_short += `   Article Id:${this.form.id}`

          // set tagsview title
          this.setTagsViewTitle()

          // set page title
          this.setPageTitle()
        }).catch(err => {
          console.log(err)
        })
      },
      setTagsViewTitle() {
        const title = 'Edit Article'
        const route = Object.assign({}, this.tempRoute, {title: `${title}-${this.form.id}`})
        this.$store.dispatch('tagsView/updateVisitedView', route)
      },
      setPageTitle() {
        const title = 'Edit Article'
        document.title = `${title} - ${this.form.id}`
      },
      submitBlog() {
        this.$refs.postForm.validate(valid => {
          if (valid) {
            this.loading = true
            this.$notify({
              title: '成功',
              message: '发布文章成功',
              type: 'success',
              duration: 2000
            })
            this.form.status = 'published'
            this.loading = false
          } else {
            console.log('error submit!!')
            return false
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
        this.$message({
          message: '保存成功',
          type: 'success',
          showClose: true,
          duration: 1000
        })
        this.form.status = 'draft'
      },
      getRemoteUserList(query) {
        searchUser(query).then(response => {
          if (!response.data.items) return
          this.userListOptions = response.data.items.map(v => v.name)
        })
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
