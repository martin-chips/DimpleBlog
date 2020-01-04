<template>
    <div class="home-content layout-content">
        <div class="banner" style="margin-bottom:20px;" v-if="carouselList.length>0">
            <div class="bracket"></div>
            <div class="target">
                <HomeBanner :carouselList="carouselList"></HomeBanner>
            </div>
        </div>
        <Row>
            <Col :xs="24" :sm="24" :md="24" :lg="24" :xl="17">
                <div class="layout-left">
                    <!-- 文章 -->
                    <SectionTitle mainTitle="文章" subTitle="Article" :menus="articlesTitleMenus" :to="'/articles'"
                                  :withRefresh="true"
                                  :withTimeSelect="false"
                                  @refresh="refreshArticles"
                                  @menusControl="articlesMenusControl">
                    </SectionTitle>
                    <ArticleListCell v-for="article in articles" :article="article" :key="article.id"></ArticleListCell>
                    <SectionTitle mainTitle="阅读" subTitle="Reading" :menus="booksTitleMenus" :to="'/articles'" v-if="books.length>0"
                                  :withRefresh="true"
                                  :withTimeSelect="false"
                                  @refresh="refreshBooks"
                                  @menusControl="booksMenusControl">
                    </SectionTitle>
                    <div class="books">
                        <BookCell :book="book" v-for="book in books" :key="book.id"></BookCell>
                    </div>
                </div>
            </Col>
            <Col :xs="24" :sm="24" :md="24" :lg="24" :xl="7">
                <div class="layout-right" v-if="showPage">
                    <About/>
                    <Recommend style="margin-top:15px;"/>
                    <Hot style="margin-top:15px;"/>
                    <TagWall style="margin-top:15px;"/>
                    <FriendLinks style="margin-top:15px;"/>
                </div>
            </Col>
        </Row>
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
    import HomeBanner from "../../views/HomeBanner";
    import {listCarousel} from '@/api'
    import BookCell from "../../views/book/BookCell";


    export default {
        name: 'home-content',
        data() {
            return {
                carouselList: [],
                // 文章
                mostComment: undefined,
                hot: undefined,
                recommend: undefined,
                articlesTitleMenus: [
                    {title: "最多评论", selected: false, method: 'mostComment'},
                    {title: "最热", selected: false, method: 'hot'},
                    {title: "推荐", selected: false, method: 'recommend'}
                ],
                mostCommentBooks: undefined,
                hotBooks: undefined,
                recommendBooks: undefined,
                booksTitleMenus: [
                    {title: '最多评论', selected: false, method: 'mostComment'},
                    {title: '最热', selected: false, method: 'hot'},
                    {title: '推荐', selected: false, method: 'recommend'}
                ],
            };
        },
        computed: {
            ...mapState({
                articles: state => state.home.articles,
                books: state => state.home.books,
            }),
            showPage: function () {
                return this.$store.state.home.articles.length > 0;
            }
        },
        beforeMount() {
            listCarousel().then(response => {
                this.carouselList = response.data;
            });
            if (this.$store.state.home.articles.length === 0) {
                this.getArticlesBaseInfo({
                    is_recommend: this.recommend,
                    is_hot: this.hot,
                    ordering: this.mostComment,
                    pageSize: 10,
                    pageNum: 1
                });
            }
            if (this.$store.state.home.books.length === 0) {
                this.getBooksBaseInfo({
                    is_recommend: this.recommend,
                    is_hot: this.hot,
                    ordering: this.mostComment,
                    pageSize: 10,
                    pageNum: 1
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
                getBooksBaseInfo: 'home/GET_BOOKS_BASE_INFO',
            }),
            refreshArticles() {
                this.mostComment = undefined;
                this.hot = undefined;
                this.recommend = undefined;
                this.getArticlesBaseInfo();
            },
            articlesMenusControl(params) {
                switch (params[0]) {
                    case 'mostComment':
                        this.mostComment = params[1] ? 'commentCount' : undefined;
                        break;
                    case 'hot':
                        this.hot = params[1] ? 'click' : undefined;
                        break;
                    case 'recommend':
                        this.recommend = params[1] ? true : undefined;
                        break;
                }
                // 排序条件
                let orderings = [];
                if (this.mostComment !== undefined) {
                    orderings.push(this.mostComment);
                }
                if (this.hot !== undefined) {
                    orderings.push(this.hot);
                }
                this.getArticlesBaseInfo({
                    support: this.recommend,
                    orderByColumn: orderings.toString(),
                    isAsc: this.timeSorted ? 'asc' : 'desc',
                    pageNum: 1,
                    pageSize: 10
                });
            },
            refreshBooks() {
                this.mostCommentBooks = undefined;
                this.hotBooks = undefined;
                this.recommendBooks = undefined;
                this.getBooksBaseInfo({
                    params: {
                        is_recommend: this.recommendBooks,
                        is_hot: this.hotBooks,
                        ordering: this.mostCommentBooks,
                        limit: 10,
                        offset: 0
                    }
                });
            },
            booksMenusControl(params) {
                switch (params[0]) {
                    case 'mostComment':
                        this.mostCommentBooks = params[1] ? 'commentCount' : undefined;
                        break;
                    case 'hot':
                        this.hotBooks = params[1] ? 'click' : undefined;
                        break;
                    case 'recommend':
                        this.recommendBooks = params[1] ? true : undefined;
                        break;
                }
                // 排序条件
                let orderings = [];
                if (this.mostCommentBooks !== undefined) {
                    orderings.push(this.mostCommentBooks);
                }
                if (this.hot !== undefined) {
                    orderings.push(this.hotBooks);
                }
                this.getBooksBaseInfo({
                    params: {
                        support: this.recommend,
                        orderByColumn: orderings.toString(),
                        isAsc: this.timeSorted ? 'asc' : 'desc',
                        pageNum: 1,
                        pageSize: 10,
                        orderByColumn:'b.create_time'
                    }
                });
            }
        },
        components: {
            SectionTitle, ArticleListCell, About, TagWall, FriendLinks, Hot, Recommend, HomeBanner, BookCell
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    .home-content
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
