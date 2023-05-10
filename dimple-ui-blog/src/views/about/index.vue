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
        <el-button type="primary" :plain="article.liked==0" @click="likeArticle">👍🏻 {{ likeText }}
        </el-button>
      </div>
      <div class="article-detail__copyright">
        <copyright :url="url"></copyright>
      </div>
      <div class="article-detail__share">
        <share :tags="article.blogTags" :summary="article.summary" :title="article.title"></share>
      </div>
    </layout>
  </div>
</template>
<script>
import {mapMutations} from "vuex";
import api from "@/api/";
import note from "@/components/note/";
import {generateTree} from "@/utils/generateTree";
import {getRandomCharacter} from "@/utils/getRandomCharacter";
import copyright from "../article/components/copyright";
import share from "../article/components/share";
import Prism from "@/assets/js/prism.js";
import axios from "axios";

function jumpAnchor(route) {
  if (route.query.anchor === 'a_cm') {
    const el = document.querySelector('#a_cm')
    el.scrollIntoView()
  }
}

export default {
  name: "articleDetail",
  components: {note, copyright, share},
  props: {},
  metaInfo() {
    return {
      title: `关于我 - ` + "Dimple's Blog",
      meta: [
        {
          name: "description",
          content: this.article.summary + ` - Dimple's Blog`
        },
        {
          name: "keywords",
          content: this.article.blogTags ? this.article.blogTags.join(",") : ""
        }
      ]
    };
  },
  // 动态属性
  data() {
    return {
      loading: true,
      pageNum: 1,
      pageSize: 10,
      total: 0,
      likeText: "赞",
      article: {},
      messages: [],
      flatTree: null,
      viewer: null
    };
  },
  computed: {
    tags() {
      if (this.article.blogTags) return this.article.blogTags.join(" ");
      return "";
    },
    url() {
      return `${process.env.VUE_APP_BASE_URL}/app/article/${this.article.id}`;
    },
  },
  async asyncData({store, route, isServer, _req}) {
    const articleRes = await api.getAsyncAbout(_req)
    if (articleRes.code === 200) {
      if (!isServer) setTimeout(() => jumpAnchor(route), 0);
      const response = await axios.post(process.env.BASE_URL + '/api/markdown/convert', {content: articleRes.data.content});
      articleRes.data.content = response.data;
      return {article: articleRes.data}
    }
  },
  mounted() {
    this.$nextTick(function () {
      Prism.highlightAll()
    })

    this.collectTitles()
    window.addEventListener('scroll', this.handleScroll, false)
  },
  methods: {
    ...mapMutations(["setCatalogs", "setActiveCatalog"]),
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
        this.likeText = this.article.liked === 1 ? "已赞" : "赞"
      }
    },
    collectTitles() {
      const selectors = ["h1", "h2", "h3", "h4", "h5", "h6"].map((et) => ".article-detail__body " + et).join(",");
      const nodeList = document.querySelectorAll(selectors);
      if (!nodeList) return;
      const flatTree = Array.from(nodeList).map((node) => {
        const a = document.createElement("a");
        const tempId = getRandomCharacter(4);
        const firstChild = node.firstChild;
        a.setAttribute("name", node.innerText);
        a.setAttribute("id", tempId);
        // node.appendChild(a)
        node.insertBefore(a, firstChild);
        return {
          level: parseInt(node.nodeName.substr(1)),
          name: node.innerText,
          tempId
        };
      });
      this.flatTree = [...flatTree];
      this.flatTree.reverse();
      const catalogs = generateTree(flatTree);
      this.addTreeLevel(catalogs);
      this.setCatalogs(catalogs);
    },
    addTreeLevel(catalogs, level, order) {
      catalogs.forEach((catalog, index) => {
        if (!level) level = 0;
        catalog.level_tree = level;
        catalog.order = order ? order + "." + (index + 1) : index + 1;
        const dom = document.getElementById(catalog.tempId);
        dom.removeAttribute("name");
        dom.setAttribute("name", catalog.order);
        if (catalog.children && catalog.children.length) {
          this.addTreeLevel(catalog.children, level + 1, catalog.order);
        }
      });
    },

    handleScroll() {
      if (!this.flatTree) return;
      this.flatTree.some((item) => {
        const node = document.getElementById(item.tempId);
        if (node.getBoundingClientRect().y < 5) {
          this.setActiveCatalog(item.tempId);
          return true;
        }
      });
    }
  },
  destroyed() {
    window.removeEventListener("scroll", this.handleScroll, false);
  }
};
</script>

<style lang="scss">
@import '~@/style/index.scss';
@import '~@/style/article.scss';

</style>
