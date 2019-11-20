<template>
  <div class="article-home-content layout-content">
    <el-row>
      <el-col :xs="24" :sm="17" :md="17" :lg="17">
        <div class="layout-left">
          <classify-menu :categorys="categoriesInfo" @selectCategory="selectCategory"
                         :defaultCategory="selected_category"></classify-menu>
          <section-title :mainTitle="'文章列表'"
                         :subTitle="'Article List'"
                         :menus="menus"
                         :withRefresh="true"
                         :withTimeSelect="true"
                         @refresh="refresh"
                         @menusControl="menusControl"
                         @comfirmDateSelect="dateSelect"
                         @clearDateSelect="dateSelectClear">
          </section-title>
          <article-list-cell v-for="article in articles" :article="article" :key="article.id"></article-list-cell>
          <BrowseMore @browseMore="browseMore" :noMoreData="noMoreData" ref="browseMore"></BrowseMore>
        </div>
      </el-col>
      <el-col :xs="0" :sm="7" :md="7" :lg="7">
        <div class="layout-right">
          <!--          <recommend></recommend>-->
          <!--          <tag-wall style="margin-top: 15px;"></tag-wall>-->
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
  import {
    mapState,
    mapMutations,
    mapActions
  } from 'vuex';

  import ArticleListCell from "../../views/article/ArticleListCell";
  import ClassifyMenu from "../../views/classify/ClassifyMenu";
  import SectionTitle from "../../views/SectionTitle";
  import BrowseMore from "../../views/BrowseMore";

  import {
    DefaultLimitSize,
    MaxLimitSize,
    SectionTitleDefaultMenus,
  } from '@/utils/front/const';

  // mixin
  // import {mixin} from '@/utils/front/utils';

  export default {
    name: 'ArticleHomeContent',
    components: {
      ClassifyMenu, ArticleListCell, SectionTitle, BrowseMore
    },
    data() {
      return {
        selected_category: undefined,
        timeSorted: false,
        mostComment: undefined,
        recommend: undefined,
        pageSize: DefaultLimitSize,
        pageNum: 0,
        menus: SectionTitleDefaultMenus,
        selectedDateRange: []
      };
    },
    beforeRouteLeave(to, from, next) {
      // 导航离开时清空vuex中数据
      this.clearArticlesBaseInfo();
      next();
    },
    created() {
      // 设置默认的分类id
      this.selected_category = this.categoriesInfo[0].id;
    },
    mounted() {
      if (this.$store.state.articleHome.articles.length === 0) {
        this.updateArticlesInfo(true);
      }
    },
    computed: {
      ...mapState({
        categories: state => state.base.categories,
        articles: state => state.articleHome.articles,
        noMoreData: state => state.articleHome.noMoreData
      }),
      categoriesInfo: function () {
        return this.categories.filter((category) => {
          return 1 == 1;
        });
      }
    },
    beforeDestroy() {
      // 导航离开时清空vuex中数据
      this.clearArticlesBaseInfo();
    },
    methods: {
      ...mapMutations({
        clearArticlesBaseInfo: 'articleHome/CLEAR_ARTICLES_BASE_INFO'
      }),
      ...mapActions({
        getArticlesBaseInfo: 'articleHome/GET_ARTICLES_BASE_INFO'
      }),
      updateArticlesInfo(reset) {
        // 排序条件
        let orderings = [];
        if (this.timeSorted) {
          orderings.push('add_time');
        } else {
          orderings.push('-add_time');
        }
        if (this.mostComment !== undefined) {
          if (this.mostComment) {
            orderings.push('comment_num');
          } else {
            orderings.push('-comment_num');
          }
        }
        this.getArticlesBaseInfo({
          params: {
            params: {
              top_category: this.selected_category,
              ordering: orderings.toString(),
              is_recommend: this.recommend,
              time_min: this.selectedDateRange[0],
              time_max: this.selectedDateRange[1],
              limit: this.limit_size,
              offset: this.page * this.limit_size
            }
          },
          reset
        }).then(response => {
          this.$refs.browseMore.stopLoading();
        }).catch(error => {
          this.$refs.browseMore.stopLoading();
          console.log(error);
        });
      },
      browseMore() {
        this.page++;
        this.updateArticlesInfo();
      },
      selectCategory(categoryId) {
        this.page = 0;
        this.selected_category = categoryId;
        this.updateArticlesInfo(true);
      },
      refresh() {
        this.selected_category = undefined;
        this.timeSorted = false;
        this.mostComment = undefined;
        this.recommend = undefined;
        this.page = 0;
        this.selectedDateRange = [];
        this.updateArticlesInfo(true);
      },
      menusControl(params) {
        switch (params[0]) {
          case 'timeSorted':
            this.timeSorted = !params[1];
            break;
          case 'mostComment':
            this.mostComment = params[1] ? true : undefined;
            break;
          case 'recommend':
            this.recommend = params[1] ? true : undefined;
            break;
        }
        // 清空原数据
        this.page = 0;
        this.updateArticlesInfo(true);
      },
      dateSelect(dateRange) {
        this.selectedDateRange = [dateRange[0], dateAdd(dateRange[1], 60 * 60 * 24 * 1000)];
        this.page = 0;
        this.limit_size = MaxLimitSize;
        this.updateArticlesInfo(true);
      },
      dateSelectClear() {
        this.selectedDateRange = [];
        this.page = 0;
        this.limit_size = DefaultLimitSize;
        this.updateArticlesInfo(true);
      }
    },

  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";
  .article-home-content
    .banner
      position relative
      width 100%
      overflow hidden

      .bracket
        margin-top 25%
        @media only screen and (max-width: $responsive-sm)
          margin-top 38%

      .target
        position absolute
        top 0
        bottom 0
        left 0
        right 0

    .thumb-cards
      margin-top 15px
</style>
