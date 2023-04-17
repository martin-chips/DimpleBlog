<template>
  <div class="article-detail">
    <layout :cover="article.headerPic">
      <div class="article-detail__header" slot="custom-header">
        <h1 class="article-detail__title">{{ article.title }}</h1>
        <div class="article-detail__info info-1">
          <span>
            <i class="el-icon-date"></i>
            发表时间 {{ article.createTime | formatDate }}
          </span>
          <span>&nbsp;|&nbsp;</span>
          <span>
            <i class="el-icon-price-tag"></i>
            标签 {{ tags }}
          </span>
        </div>
        <div class="article-detail__info info-2">
          <span>
            <i class="el-icon-chat-dot-round"></i>
            阅读量 {{ article.pv }}
          </span>
          <span>&nbsp;|&nbsp;</span>
          <span>
            <i class="el-icon-chat-dot-round"></i>
            评论数 {{ total }}
          </span>
          <span>&nbsp;|&nbsp;</span>
          <span>
            <i class="el-icon-star-off"></i>
            点赞 {{ article.likeCount }}
          </span>
        </div>
      </div>
      <note>
        <p>{{ article.summary }}</p>
      </note>
      <div v-html="article.content" class="article-detail__body ql-editor"></div>
      <div class="article-detail__update">
        <span>最后编辑于：{{ article.updateTime | formatDate }}</span>
      </div>
      <div class="article-detail__like">
        <el-button type="primary" :plain="article.liked === 0" @click="likeArticle">👍🏻 {{ likeText }}</el-button>
      </div>
      <div class="article-detail__copyright">
        <copyright :url="url"></copyright>
      </div>
      <div class="article-detail__share">
        <share :tags="article.tag" :abstract="article.summary" :title="article.title"></share>
      </div>
      <div class="article-detail__prevnext">
        <prevnext :article="article"></prevnext>
      </div>
      <div class="article-detail__comment">
        <a id="a_cm"></a>
        <div class="comment__title">
          <i class="el-icon-chat-dot-round"></i>
          <span>文章评论</span>
        </div>
        <div class="comment__submit">
          <submit @submitContent="submitContent"></submit>
        </div>
        <div class="comment__total">
          <span>{{ total }}条评论</span>
        </div>
        <div class="comment__list">
          <comments :messages="messages" @submitReply="submitReply" @addLike="addLike"></comments>
        </div>
        <div class="comment__page" v-if="total">
          <el-pagination
            :current-page.sync="currentPage"
            :total="total"
            layout="prev, pager, next"
            :page-size="pageSize"
            @current-change="currentChange"
          ></el-pagination>
        </div>
      </div>
    </layout>
  </div>
</template>
<script>
import {mapMutations, mapState} from 'vuex'
import api from '@/api/'
import note from '@/components/note/'
import {generateTree} from '@/utils/generateTree'
import {getRandomCharacter} from '@/utils/getRandomCharacter'
import comments from '@/views/components/comments'
import submit from '@/views/components/submit'
import copyright from './components/copyright'
import share from './components/share'
import prevnext from './components/prevnext'
import axios from 'axios';
import {storage} from "@/utils/storage";

function jumpAnchor(route) {
  if (route.query.anchor === 'a_cm') {
    const el = document.querySelector('#a_cm')
    el.scrollIntoView()
  }
}

export default {
  name: 'articleDetail',
  components: {note, comments, submit, copyright, share, prevnext},
  props: {},
  metaInfo() {
    return {
      title: `${this.article.title}  - Dimple's Blog`,
      meta: [
        {
          name: 'description',
          content: this.article.summary + ` - Dimple's Blog `
        },
        {
          name: 'keywords',
          content: this.article.tag?.join(',')
        }
      ]
    }
  },
  // 动态属性
  data() {
    return {
      currentPage: 1,
      pageSize: 10,
      total: 0,
      likeText: "赞",
      article: {},
      messages: [],
      flatTree: null,
      viewer: null,
    }
  },
  computed: {
    ...mapState(['visitorInfo']),
    tags() {
      if (this.article.blogTags) return this.article.blogTags.join(" ");
      return "";
    },
    url() {
      return `${process.env.BASE_URL}/app/article/${this.article.id}`
    }
  },
  watch: {
    $route(to, from) {
      if (to.params.id !== from.params.id) {
        this.$nextTick(() => {
          this.collectTitles()
        })
      }
    }
  },
  filters: {},
  mounted() {
    this.$nextTick(function () {
      Prism.highlightAll()
    })

    this.collectTitles()
    window.addEventListener('scroll', this.handleScroll, false)
  },
  async asyncData({route, isServer, _ip}) {
    const articleRes = await api.getArticle(route.params.id)
    const commentRes = await api.listComment({
      pageNum: 1,
      pageSize: 10,
      orderByColumn: "createTime",
      isAsc: "desc",
      articleId: route.params.id,
      _ip
    });

    if (articleRes.code === 200) {
      if (!isServer) {
        setTimeout(() => jumpAnchor(route), 0)
      }
      const response = await axios.post(process.env.BASE_URL + '/api/markdown/convert', {content: articleRes.data.content});
      articleRes.data.content = response.data;
      return {article: articleRes.data, messages: commentRes.rows, total: commentRes.total}
    }
  },
  methods: {
    ...mapMutations(['setCatalogs', 'setActiveCatalog']),
    async likeArticle() {
      if (this.article.liked == 1) {
        this.$message({
          type: "warning",
          message: "已经点过赞了哦!"
        });
        return
      }
      const likeRes = await this.$api.likeArticle(this.article.id);
      if (likeRes.code === 200) {
        this.$message({
          type: "success",
          message: "点赞成功!"
        });
        this.article.likeCount = this.article.likeCount + 1;
        this.article.liked = 1;
        this.likeText = this.article.liked == 1 ? "已赞" : "赞"
      }
    },
    async addLike(message) {
      message.liked = 1;
      const likeRes = await api.likeArticleComment(message.id)
      if (likeRes.code === 200) {
        message.likeCount += 1;
        this.$message({
          type: 'success',
          message: "点赞成功!"
        })
      }
    },
    submitContent(content, cb) {
      this.submit(content, null, cb)
    },
    submitReply(content, currentReplyComment, cb) {
      this.submit(content, currentReplyComment, cb)
    },
    async submit(content, currentReplyComment, cb) {
      let parentId;
      let replyId;
      if (currentReplyComment) {
        if (currentReplyComment.parentId && currentReplyComment.parentId > 0) {
          parentId = currentReplyComment.parentId;
          replyId = currentReplyComment.id;
        } else {
          parentId = currentReplyComment.id;
          replyId = currentReplyComment.id;
        }
      }
      var visitor = storage.getVisitor();
      const res = await api.addComment(Object.assign({
        content: content,
        replyId: replyId,
        parentId: parentId,
        articleId: this.$route.params.id,
      }, visitor));
      if (res.code === 200) {
        if (cb) cb();
        this.$message({
          type: "success",
          message: "评论成功"
        });
        await this.currentChange(this.pageNum);
      }
    },
    async currentChange(val) {
      this.pageNum = val;
      const commentRes = await api.listComment({
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        orderByColumn: "createTime",
        isAsc: "desc",
        articleId: this.article.id
      });
      if (commentRes.code === 200) {
        this.messages = commentRes.rows;
        this.total = commentRes.total;
      }
    },
    async getArticleComments() {
      const commentRes = await api.getArticleComments({
        pageNum: this.currentPage,
        pageSize: this.limit,
        articleId: this.$route.params.id
      })
      if (commentRes.code === 200) {
        this.total = commentRes.total
        this.messages = commentRes.rows
      }
    },
    collectTitles() {
      const selectors = ['h1', 'h2', 'h3', 'h4', 'h5', 'h6'].map((et) => '.article-detail__body ' + et).join(',')
      const nodeList = document.querySelectorAll(selectors)
      if (!nodeList) return

      const flatTree = Array.from(nodeList).map((node) => {
        const a = document.createElement('a')
        const tempId = getRandomCharacter(4)
        const firstChild = node.firstChild
        a.setAttribute('name', node.innerText)
        a.setAttribute('id', tempId)
        // node.appendChild(a)
        node.insertBefore(a, firstChild)
        return {
          level: parseInt(node.nodeName.substr(1)),
          name: node.innerText,
          tempId
        }
      })
      this.flatTree = [...flatTree]
      this.flatTree.reverse()
      const catalogs = generateTree(flatTree)
      this.addTreeLevel(catalogs)
      this.setCatalogs(catalogs)
    },
    addTreeLevel(catalogs, level, order) {
      catalogs.forEach((catalog, index) => {
        if (!level) level = 0
        catalog.level_tree = level
        catalog.order = order ? order + '.' + (index + 1) : index + 1
        const dom = document.getElementById(catalog.tempId)
        dom.removeAttribute('name')
        dom.setAttribute('name', catalog.order)
        if (catalog.children && catalog.children.length) {
          this.addTreeLevel(catalog.children, level + 1, catalog.order)
        }
      })
    },

    handleScroll() {
      if (!this.flatTree) return
      this.flatTree.some((item) => {
        const node = document.getElementById(item.tempId)
        if (node.getBoundingClientRect().y < 5) {
          this.setActiveCatalog(item.tempId)
          return true
        }
      })
    }
  },
  destroyed() {
    window.removeEventListener('scroll', this.handleScroll, false)
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import '~@/style/index.scss';

.article-detail {
  &__header {
    width: 100%;
    height: 100%;
    @include flex-box-center;
    flex-direction: column;
  }

  &__body {
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Helvetica Neue', Lato, Roboto, 'PingFang SC',
    'Microsoft JhengHei', 'Microsoft YaHei', sans-serif;
    line-height: 2;
    color: #4c4948;

    img {
      width: 100%;
      height: 100%;
    }
  }

  &__title {
    line-height: 1.5;
    padding: 0 12px;
    text-align: center;
    @include themeify() {
      color: themed('color-title');
    }
    @include respond-to(xs) {
      font-size: 18px;
    }
  }

  &__info {
    padding: 0 12px;
    @include themeify() {
      color: themed('color-navbar');
    }
  }

  .info-2 {
    margin-top: 8px;
  }

  &__update {
    margin-top: 20px;
    padding: 14px;
    text-align: right;
    @include themeify() {
      color: themed('color-ele-holder');
    }
  }

  &__like {
    margin-top: 20px;
    padding: 14px;
    text-align: center;
  }

  &__copyright {
    margin-top: 28px;
  }

  &__share {
    margin-top: 12px;
  }

  &__prevnext {
    margin-top: 28px;
  }

  &__comment {
    margin-top: 32px;

    .comment__title {
      padding: 16px 0;
      font-size: 20px;
      font-weight: 700;

      > [class^='el-icon-'] {
        font-weight: 700;
      }

      span {
        margin-left: 12px;
      }
    }

    .comment__total {
      color: #4c4948;
      font-size: 25px;
      font-weight: bold;
      margin-top: 28px;
    }

    .comment__list {
      margin-top: 28px;
    }

    .comment__page {
      @include flex-box-center;
      padding: 16px 0;
    }
  }

  // 覆盖 quill.js 中的部分css
  .ql-editor {
    padding: 0;
    line-height: 2;

    .code-toolbar {
      margin-top: 12px;
    }

    a {
      color: #409eff;
    }

    a:hover {
      text-decoration: underline;
    }

    ul,
    ol {
      padding-left: 0;
    }

    li.ql-indent-1:not(.ql-direction-rtl) {
      padding-left: 3.5em;
    }

    pre > code {
      background: 0 0 !important;
    }

    code:not([class*='language-']) {
      background-color: #f0f0f0;
      border-radius: 3px;
      font-size: 90%;
      padding: 3px 5px;
    }

    blockquote {
      border-left: 4px solid #ccc;
      margin-bottom: 5px;
      margin-top: 5px;
      padding-left: 16px;
    }
  }
}
</style>
