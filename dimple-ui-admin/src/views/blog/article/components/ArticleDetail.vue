<template>
  <div class="app-container">
    <el-card style="height: 100%">
      <el-steps :active="active" finish-status="success">
        <el-step title="基本信息"></el-step>
        <el-step title="文章内容"></el-step>
        <el-step title="完成"></el-step>
      </el-steps>
      <el-form :style="active==0?'width: 450px;margin: 0 auto;':''"
               ref="postForm" :model="postForm" :rules="rules" class="form-container">
        <el-form-item v-show="active==0" label="标题" prop="title">
          <el-input v-model="postForm.title" placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item v-show="active==0" label="摘要:">
          <el-input
            type="textarea"
            placeholder="请输入摘要"
            v-model="postForm.summary"
            maxlength="300"
            show-word-limit
          />
        </el-form-item>
        <el-form-item v-show="active==0" label="原创">
          <el-switch :active-value="true" :inactive-value="false" v-model="postForm.original"></el-switch>
        </el-form-item>
        <el-form-item v-show="active==0" label="文章标图">
          <div style="display: inherit;margin-top: 40px;" @click="openHeaderChange">
            <img style="width: 100%" v-if="postForm.headerImage" alt="文章标图" class="header-img-box"
                 :src="postForm.headerImage">
            <div style="width: 100%" v-else class="header-img-box">从媒体库选择</div>
          </div>
        </el-form-item>
        <el-form-item v-show="active==0" label="分类">
          <el-select style="width: 100%"
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
        <el-form-item v-show="active==0" label="标签">
          <el-select style="width: 100%"
                     v-model="postForm.tags"
                     multiple
                     filterable
                     remote
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
        <el-form-item v-show="active==1" label="内容" prop="content">
          <MarkdownEditor v-model="postForm.content" :markdown="postForm.content"/>
        </el-form-item>

        <el-form-item v-show="active==2">
          <el-result icon="success" title="操作成功" subTitle="可能存在系统缓存,博客页面需要等待缓存刷新!">
            <template slot="extra">
              <el-button type="primary" @click="toArticleList" size="medium">返回</el-button>
            </template>
          </el-result>
        </el-form-item>

        <el-form-item style="margin: auto;text-align: center;">
          <el-button v-if="active==1" @click="active--">上一步</el-button>
          <el-button v-if="active==0" @click="active++">下一步</el-button>

          <el-button v-if="active==1" v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm">
            发布
          </el-button>
          <el-button v-if="active==1" v-loading="loading" type="warning" @click="draftForm">
            暂存
          </el-button>
        </el-form-item>

      </el-form>

    </el-card>
      <ChooseImg hidden v-on:onChooseImg="onChooseImg" ref="chooseImg"></ChooseImg>
  </div>
</template>

<script>
import MarkdownEditor from '@/components/MarkdownEditor'
import {addArticle, getArticle, updateArticle} from '@/api/blog/article'
import {listTag} from '@/api/blog/tag'
import {listCategory} from '@/api/blog/category'
import ChooseImg from "@/components/ChooseImg/index.vue";

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
  components: {MarkdownEditor, ChooseImg},
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      active: 0,
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
    onChooseImg(value) {
      this.postForm.headerImage = value;
    },
    openHeaderChange() {
      this.$refs.chooseImg.openDrawer();
    },
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
    toArticleList() {
      this.$store.dispatch('tagsView/delView', this.$route);
      this.$router.push({path: '/blog/article'})
    },
    submitForm() {
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.postForm.articleStatus = 1;
          this.loading = false;
          if (!this.postForm.id) {
            addArticle(this.postForm).then(response => {
              if (response.code != 200) {
                this.$notify({
                  title: '失败',
                  message: '发布文章失败',
                  type: 'fail',
                  duration: 2000
                })
                return false;
              }
              localStorage.removeItem("md");
              this.active++;
            });
          } else {
            updateArticle(this.postForm).then(response => {
              this.loading = true;
              if (response.code != 200) {
                this.$notify({
                  title: '失败',
                  message: '修改文章失败',
                  type: 'fail',
                  duration: 2000
                });
                return false
              }
              localStorage.removeItem("md");
              this.active++;
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
      localStorage.removeItem("md");
      this.active++;
    }
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
