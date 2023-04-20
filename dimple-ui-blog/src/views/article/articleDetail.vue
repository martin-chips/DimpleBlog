<template>
  <div class="article-detail">
    <layout :cover="article.headerImage">
      <div class="article-detail__header" slot="custom-header">
        <h1 class="article-detail__title">{{ article.title }}</h1>
        <div class="article-detail__info info-1">
          <span>
            <i class="el-icon-date"></i>
            发表于 {{ article.createTime | formatDate }}
          </span>
          <span>&nbsp;|&nbsp;</span>
          <span @click="toCategory(article.categoryTitle,article.categoryId)">
            <i class="el-icon-folder-opened"></i>
            {{ article.categoryTitle }}
          </span>
        </div>
        <div class="article-detail__info info-2">
          <span>
            <i class="el-icon-view"></i>
            阅读量 {{ article.pv }}
          </span>
          <span>&nbsp;|&nbsp;</span>
          <span>
            <i class="el-icon-timer"></i>
            阅读时长 {{ readingMinute }}分钟
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
      <div v-html="article.content" class="article-detail__body" id="article-detail__body"></div>
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
    readingMinute() {
      const wordsPerMinute = 200;
      return Math.ceil(this.article.content.length / wordsPerMinute);
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
    toCategory(title, id) {
      this.$router.push({
        name: 'articleFilter',
        params: {
          type: 'category',
          param: id
        },
        query: {
          title
        }
      })
    },
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


    img {
      width: 100%;
      height: 100%;
    }

    .table-wrap {
      overflow-x: scroll;
      margin: 0 0 20px
    }

    table {
      display: table;
      width: 100%;
      border-spacing: 0;
      border-collapse: collapse;
      empty-cells: show
    }

    table thead {
      background: rgba(18, 99, 208, 0.1)
    }

    table td, table th {
      padding: 6px 12px;
      @include themify() {
        border: 1px solid themed('light-grey');
      }
      vertical-align: middle
    }

    code {
      padding: 2px 4px;
      background: rgba(27, 31, 35, .05);
      color: #f47466
    }

    a {
      @include themify() {
        color: themed('a-color')
      }

    }

    a:hover {
      text-decoration: underline
    }

    img {
      display: block;
      margin: 0 auto 20px;
      max-width: 100%;
      -webkit-transition: filter 375ms ease-in .2s;
      -moz-transition: filter 375ms ease-in .2s;
      -o-transition: filter 375ms ease-in .2s;
      -ms-transition: filter 375ms ease-in .2s;
      transition: filter 375ms ease-in .2s
    }

    p {
      margin: 0 0 16px
    }


    blockquote {
      margin: 0 0 20px;
      padding: 12px 15px;
      @include themify() {
        border-left: 3px solid themed('default-bg-color');
        background-color: themed('blockquote-bg');
        color: themed('blockquote-color')
      }
    }

    blockquote footer cite:before {
      padding: 0 5px;
      font-family: "FontAwesome";
      content: '—'
    }

    blockquote > :last-child {
      margin-bottom: 0 !important
    }

    a:after {
      float: right;
      @include themify() {
        color: themed('headline-presudo');
      }
      font-family: "FontAwesome";
      content: '\f0c1';
      font-size: .95em;
      -webkit-transition: all .3s;
      -moz-transition: all .3s;
      -o-transition: all .3s;
      -ms-transition: all .3s;
      transition: all .3s
    }

    a:hover:after {
      @include themify() {
        color: themed('pseudo-hover')
      }
    }

    a:after {
      opacity: 0
    }

    h1:hover a.headerlink:after, h2:hover a.headerlink:after, h3:hover a.headerlink:after, h4:hover a.headerlink:after, h5:hover a.headerlink:after, h6:hover a.headerlink:after {
      opacity: 1;
      -ms-filter: none;
      filter: none
    }

    ol ol, ol ul, ul ol, ul ul {
      padding-left: 20px
    }

    ol li, ul li {
      margin: 4px 0
    }

    ol p, ul p {
      margin: 0 0 8px
    }

    hr {
      margin: 20px 0
    }

    h1, h2, h3, h4, h5, h6 {
      -webkit-transition: all .2s ease-out;
      -moz-transition: all .2s ease-out;
      -o-transition: all .2s ease-out;
      -ms-transition: all .2s ease-out;
      transition: all .2s ease-out
    }

    h1:before, h2:before, h3:before, h4:before, h5:before, h6:before {
      position: inherit;
      top: calc(50% - 7px);
      @include themify() {
        color: themed('pseudo-hover')
      }
      font-family: "FontAwesome";
      content: '\f0c1';
      line-height: 1;
      -webkit-transition: all .2s ease-out;
      -moz-transition: all .2s ease-out;
      -o-transition: all .2s ease-out;
      -ms-transition: all .2s ease-out;
      transition: all .2s ease-out
    }

    h1:hover:before, h2:hover:before, h3:hover:before, h4:hover:before, h5:hover:before, h6:hover:before {
      @include themify() {
        color: themed('default-bg-color')
      }
    }

    h1 {
      padding-left: 32px
    }

    h1:before {
      margin-left: -26px;
      font-size: 20px
    }

    h1:hover {
      padding-left: 38px
    }

    h2 {
      padding-left: 30px
    }

    h2:before {
      margin-left: -24px;
      font-size: 18px
    }

    h2:hover {
      padding-left: 36px
    }

    h3 {
      padding-left: 28px
    }

    h3:before {
      margin-left: -22px;
      font-size: 16px
    }

    h3:hover {
      padding-left: 34px
    }

    h4 {
      padding-left: 26px
    }

    h4:before {
      margin-left: -20px;
      font-size: 14px
    }

    h4:hover {
      padding-left: 32px
    }

    h5 {
      padding-left: 24px
    }

    h5:before {
      margin-left: -18px;
      font-size: 12px
    }

    h5:hover {
      padding-left: 30px
    }

    h6 {
      padding-left: 24px
    }

    h6:before {
      margin-left: -18px;
      font-size: 12px
    }

    h6:hover {
      padding-left: 30px
    }

    ol p, ul p {
      margin: 0 0 8px
    }

    li::marker {
      @include themify() {
        color: themed('default-bg-color')
      }
      font-weight: 600;
      font-size: 1.05em
    }

    li:hover::marker {
      @include themify() {
        color: themed('pseudo-hover')
      }
    }

    ul > li {
      list-style-type: circle
    }

    ol > li {
      list-style-type: decimal;
    }
  }

  &__title {
    line-height: 1.5;
    padding: 0 12px;
    text-align: center;
    @include themify() {
      color: themed('color-white');
    }
    @include respond-to(xs) {
      font-size: 18px;
    }
  }

  &__info {
    padding: 0 12px;
    @include themify() {
      color: themed('meta-grey');
    }
  }

  .info-2 {
    margin-top: 8px;
  }

  &__update {
    margin-top: 20px;
    padding: 14px;
    text-align: right;
    @include themify() {
      color: themed('headline-presudo');
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


}
</style>
