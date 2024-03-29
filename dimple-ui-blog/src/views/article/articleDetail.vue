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
          <router-link :to="'/app/articles/category/'+article.categoryId+'?title='+article.categoryTitle">
            <i class="el-icon-folder-opened"></i>
            {{ article.categoryTitle }}
          </router-link>
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
        <share :tags="article.blogTags" :abstract="article.summary" :title="article.title"></share>
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
import {Enum} from "../../api/visitor";

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
      var html = this.article.content;
      var text = html.replace(/<[^>]+>/g, '');
      var wordCount = text.trim().split(/\s+/).length;
      return Math.ceil(wordCount / wordsPerMinute);
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
  async asyncData({store, route, isServer, _req}) {
    const articleRes = await api.getAsyncArticle(route.params.id, _req);
    await api.saveVisitLog(Enum.GET_ARTICLE, route.params.id, "/app/article/" + route.params.id, articleRes.code, _req);
    if (articleRes.code === 200) {
      if (!isServer) {
        setTimeout(() => jumpAnchor(route), 0)
      }
      const response = await axios.post(process.env.BASE_URL + '/api/markdown/convert', {content: articleRes.data.content});
      articleRes.data.content = response.data;
      return {article: articleRes.data, messages: articleRes.data.comments, total: articleRes.data.commentCount}
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
@import '~@/style/article.scss';
</style>
