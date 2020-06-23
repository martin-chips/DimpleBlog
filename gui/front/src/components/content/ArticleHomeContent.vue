<template>
    <div class="article-home-content layout-content">
        <Row>
            <Col :xs="24" :sm="24" :md="24" :lg="17">
                <div class="layout-left">
                    <ClassifyMenu v-if="categories !=null " :categories="categoriesInfo"
                                  :defaultCategoryId="selected_category"
                                  @selectCategory="selectCategory"></ClassifyMenu>
                    <SectionTitle :mainTitle="'文章列表'"
                                  :subTitle="'Article List'"
                                  :menus="menus"
                                  :withRefresh="true"
                                  :withTimeSelect="true"
                                  @refresh="refresh"
                                  @menusControl="menusControl"
                                  @confirmDateSelect="confirmDateSelect">
                    </SectionTitle>
                    <article-list-cell v-for="article in articles" :article="article"
                                       :key="article.id"></article-list-cell>
                    <BrowseMore @browseMore="browseMore" :noMoreData="noMoreData" ref="browseMore"></BrowseMore>
                </div>
            </Col>
            <Col :xs="0" :sm="0" :md="0" :lg="7">
                <div class="layout-right">
                    <Recommend></Recommend>
                    <TagWall style="margin-top: 15px;"></TagWall>
                </div>
            </Col>
        </Row>
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
    import Recommend from "../../views/Recommend";
    import TagWall from "../../views/TagWall";

    import {
        DefaultLimitSize,
        MaxLimitSize,
        SectionTitleDefaultMenus,
    } from '@/utils/const';

    export default {
        name: 'ArticleHomeContent',
        components: {
            ClassifyMenu, ArticleListCell, SectionTitle, BrowseMore, Recommend, TagWall
        },
        data() {
            return {
                selected_category: undefined,
                timeSorted: false,
                mostComment: undefined,
                recommend: undefined,
                pageSize: DefaultLimitSize,
                pageNum: 1,
                menus: SectionTitleDefaultMenus,
                dateRange: []
            };
        },
        beforeRouteLeave(to, from, next) {
            // 导航离开时清空vuex中数据
            this.clearArticlesBaseInfo();
            next();
        },
        mounted() {
            this.selected_category = this.$route.params.id;
            if (this.$store.state.articleHome.articles.length === 0) {
                this.updateArticlesInfo(true);
            }
        },
        beforeRouteUpdate(to, from, next) {
            next();
            this.selected_category = this.$route.params.id;
            this.refresh();
        },
        computed: {
            ...mapState({
                // categories: state => state.base.categories,
                articles: state => state.articleHome.articles,
                noMoreData: state => state.articleHome.noMoreData,
                bannerArticles: state => state.articleHome.bannerArticles,
            }),
            categories() {
                return this.$store.state.base.categories;
            },
            categoriesInfo() {
                if (this.categories == null) {
                    return [];
                }
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
                if (this.mostComment !== undefined) {
                    orderings.push(this.mostComment ? 'commentCount' : '-commentCount');
                }
                let params = {
                    categoryId: this.selected_category,
                    orderByColumn: orderings.toString(),
                    isAsc: this.timeSorted ? 'asc' : 'desc',
                    support: this.recommend,
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    beginTime: this.dateRange[0],
                    endTime: this.dateRange[1]
                };
                this.getArticlesBaseInfo({params, reset}).then(response => {
                    this.$refs.browseMore.stopLoading();
                }).catch(error => {
                    this.$refs.browseMore.stopLoading();
                    console.log(error);
                });
            },
            browseMore() {
                this.pageNum++;
                this.updateArticlesInfo();
            },
            selectCategory(categoryId) {
                this.pageNum = 1;
                this.selected_category = categoryId;
                this.updateArticlesInfo(true);
            },
            refresh() {
                // this.selected_category = undefined;
                this.timeSorted = false;
                this.mostComment = undefined;
                this.recommend = undefined;
                this.pageNum = 1;
                this.dateRange = [];
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
                this.pageNum = 1;
                this.updateArticlesInfo(true);
            },
            confirmDateSelect(dateRange) {
                this.dateRange = dateRange;
                this.updateArticlesInfo(true);
            }
        },

    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../../common/stylus/theme.styl";
</style>
