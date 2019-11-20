<template>
  <div class="home-content layout-content">
    <el-row>
      <el-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <!-- 文章 -->
          <SectionTitle
            mainTitle="文章"
            subTitle="Article"
            :menus="articlesTitleMenus"
            to="/"
            :withRefresh="true"
            :withTimeSelect="false"
            @refresh="refreshArticles"
            @menusControl="artclesMenusControl">
          </SectionTitle>
          <ArticleListCell v-for="article in articles" :article="article" :key="article.id"></ArticleListCell>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="7">
        <div class="layout-right" v-if="showPage">
          <About/>
          <Recommend style="margin-top:15px;"/>
          <Hot style="margin-top:15px;"/>
          <TagWall style="margin-top:15px;"/>
          <FriendLinks style="margin-top:15px;"/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
  import {
    mapState,
    mapActions
  } from 'vuex';

  import SectionTitle from "../../views/SectionTitle";
  import ArticleListCell from "../../views/article/ArticleListCell";
  import About from "../../views/About";
  import TagWall from "../../views/TagWall";
  import FriendLinks from "../../views/FriendLinks";
  import Hot from "../../views/Hot";
  import Recommend from "../../views/Recommend";

  export default {
    name: 'home-content',
    data() {
      return {
        // 文章
        mostCommentArticles: undefined,
        hotArticles: undefined,
        recommendArticles: undefined,
        articlesTitleMenus: [
          {title: "最多评论", selected: false, method: 'mostComment'},
          {title: "最热", selected: false, method: 'hot'},
          {title: "推荐", selected: false, method: 'recommend'}
        ]
      };
    },
    computed: {
      ...mapState({
        articles: state => state.home.articles
      }),
      showPage: function () {
        return this.$store.state.home.articles.length > 0;
      }
    },
    beforeMount() {
      if (this.$store.state.home.articles.length === 0) {
        this.getArticlesBaseInfo({
          params: {
            is_recommend: this.recommendArticles,
            is_hot: this.hotArticles,
            ordering: this.mostCommentArticles,
            limit: 30,
            offset: 0
          }
        });
      }
    },
    mounted() {
      // 更新首页meta信息
      // this.updateHomeMeta();
    },
    methods: {
      ...mapActions({
        getArticlesBaseInfo: 'home/GET_ARTICLES_BASE_INFO',
      }),
      refreshArticles() {
        this.mostCommentArticles = undefined;
        this.hotArticles = undefined;
        this.recommendArticles = undefined;
        this.getArticlesBaseInfo({
          params: {
            is_recommend: this.recommendArticles,
            is_hot: this.hotArticles,
            ordering: this.mostCommentArticles,
            limit: 30,
            offset: 0
          }
        });
      },
      artclesMenusControl(params) {
        switch (params[0]) {
          case 'mostComment':
            this.mostCommentArticles = params[1] ? '-comment_num' : undefined;
            break;
          case 'hot':
            this.hotArticles = params[1] ? true : undefined;
            break;
          case 'recommend':
            this.recommendArticles = params[1] ? true : undefined;
            break;
        }
        this.getArticlesBaseInfo({
          params: {
            is_recommend: this.recommendArticles,
            is_hot: this.hotArticles,
            ordering: this.mostCommentArticles,
            limit: 5,
            offset: 0
          }
        });
      },
    },
    components: {
      SectionTitle, ArticleListCell, About, TagWall, FriendLinks, Hot, Recommend
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
</style>
