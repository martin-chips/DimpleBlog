<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">

      <sticky :z-index="10" :class-name="'sub-navbar '+postForm.articleStatus">
        <OriginalDropdown v-model="postForm.original"/>
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm">
          发布
        </el-button>
        <el-button v-loading="loading" type="warning" @click="draftForm">
          暂存
        </el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-row>
          <el-row>
            <el-form-item style="margin-bottom: 40px;" prop="title">
              <MDinput v-model="postForm.title" :maxlength="100" name="name" required>
                标题
              </MDinput>
            </el-form-item>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="摘要:">
                <el-input v-model="postForm.summary" :rows="1" type="textarea" class="article-textarea" autosize
                          placeholder="请输入摘要"/>
                <span v-show="contentShortLength" class="word-counter">{{ contentShortLength }}字符</span>
              </el-form-item>
              <el-col :span="12">
                <el-form-item label="标签">
                  <el-select
                    v-model="postForm.tags"
                    multiple
                    filterable
                    remote
                    collapse-tags
                    :remote-method="getTagOptions"
                    allow-create
                    default-first-option
                    placeholder="请选择文章标签">
                    <el-option
                      v-for="item in tagOptions"
                      :key="item.title"
                      :label="item.title"
                      :value="item.title">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="分类">
                  <el-select
                    v-model="postForm.categoryId"
                    filterable
                    remote
                    :remote-method="getCategoryOptions"
                    default-first-option
                    placeholder="请选择文章分类">
                    <el-option
                      v-for="item in categoryOptions"
                      :key="item.id"
                      :label="item.title"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-col>
            <el-col :span="12">
              <el-result icon="success" title="成功提示" subTitle="请根据提示进行操作">
                <template slot="extra">
                  <el-button type="primary" size="medium">返回</el-button>
                </template>
              </el-result>
            </el-col>
          </el-row>
          <el-col :span="24">
            <el-form-item label="内容" prop="content" style="margin-bottom: 30px;">
              <MarkdownEditor v-model="postForm.content" :markdown="postForm.content"/>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

    </el-form>
  </div>
</template>

<script>
import MarkdownEditor from '@/components/MarkdownEditor'
import MDinput from '@/components/MDinput'
import Sticky from '@/components/Sticky' // 粘性header组件
import {addArticle, getArticle, updateArticle} from '@/api/blog/article'
import {listTag} from '@/api/blog/tag'
import {listCategory} from '@/api/blog/category'
import {OriginalDropdown} from './dropdown'

const defaultForm = {
  articleStatus: '2',
  title: '', // 文章题目
  content: '', // 文章内容
  summary: '', // 文章摘要
  headerImage: '', // 文章图片
  id: undefined,
  categoryId: null,
  enableComment: false,
  original: true,
  tags: []
};
export default {
  name: 'ArticleDetail',
  components: {MarkdownEditor, MDinput, Sticky, OriginalDropdown},
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      postForm: Object.assign({}, defaultForm),
      tagOptions: [],
      categoryOptions: [],
      loading: false,
      userListOptions: [],
      rules: {
        title: [{required: true, message: '标题为必填项', trigger: 'blur'}],
        summary: [{required: true, message: '标题为必填项', trigger: 'blur'}],
        tags: [{required: true, message: '标题为必填项', trigger: 'blur'}],
        categoryId: [{required: true, message: '标题为必填项', trigger: 'blur'}],
        content: [{required: true, message: '内容为必填项', trigger: 'blur'}],
      },
      tempRoute: {}
    }
  },
  computed: {
    contentShortLength() {
      return this.postForm.summary.length
    }
  },
  created() {
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id;
      this.fetchData(id);
    } else {
      if (window.localStorage && localStorage.getItem("md")) {
        this.postForm.content = localStorage.getItem("md");
      }
    }
    this.tempRoute = Object.assign({}, this.$route);
    this.getTagOptions();
    this.getCategoryOptions();
  },
  watch: {
    'postForm.content': {
      handler(content, preContent) {
        if (window.localStorage && preContent && preContent != '') {
          localStorage.setItem("md", content);
        }
        this.postForm.content = content;
      },
      immediate: true,
    }
  },
  methods: {
    async getTagOptions(query) {
      listTag({title: query}).then(response => {
        this.tagOptions = response.rows
      })
    },
    getCategoryOptions(query) {
      listCategory({title: query}).then(response => {
        this.categoryOptions = response.rows
      })
    },
    fetchData(id) {
      getArticle(id).then(response => {
        this.postForm = response.data
        // set tagsview title
        this.setTagsViewTitle();
      }).catch(err => {
        console.log(err)
      })
    },
    setTagsViewTitle() {
      const title = '编辑文章'
      const route = Object.assign({}, this.tempRoute, {title: `${title}-${this.postForm.title}`})
      this.$store.dispatch('tagsView/updateVisitedView', route)
    },
    submitForm() {
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.postForm.articleStatus = 1
          if (!this.postForm.id) {
            addArticle(this.postForm).then(response => {
              if (response.code != 200) {
                return false;
              }
              this.$notify({
                title: '成功',
                message: '发布文章成功',
                type: 'success',
                duration: 2000
              })
              this.loading = false;
              localStorage.removeItem("md");
              this.$store.dispatch('tagsView/delView', this.$route);
              this.$router.push({path: '/blog/article'})
            });
          } else {
            updateArticle(this.postForm).then(response => {
              this.loading = true;
              if (response.code != 200) {
                return false
              }
              this.$notify({
                title: '成功',
                message: '修改文章成功',
                type: 'success',
                duration: 2000
              });
              this.loading = false;
              localStorage.removeItem("md");
              this.$store.dispatch('tagsView/delView', this.$route);
              this.$router.push({path: '/blog/article'})
            });
          }

        } else {
          return false
        }
      })
    },
    draftForm() {
      if (this.postForm.content.length === 0 || this.postForm.title.length === 0) {
        this.$message({
          message: '请填写必要的标题和内容',
          type: 'warning'
        })
        return
      }
      this.postForm.articleStatus = 2;
      if (!this.postForm.id) {
        addArticle(this.postForm).then(response => {
          this.$message({
            message: '保存成功',
            type: 'success',
            showClose: true,
            duration: 1000
          });
          this.postForm.id = response.data;
        });
      } else {
        updateArticle(this.postForm).then(response => {
          this.$message({
            message: '保存成功',
            type: 'success',
            showClose: true,
            duration: 1000
          });
        });
      }
      localStorage.removeItem("md")
    },
  }
}
</script>

<style lang="scss" scoped>
@import "@/assets/styles/mixin.scss";

.createPost-container {
  position: relative;

  .createPost-main-container {
    padding: 40px 45px 20px 50px;

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
    width: 60px;
    position: absolute;
    right: 10px;
    top: 0px;
  }
}

.article-textarea ::v-deep {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
}
</style>
