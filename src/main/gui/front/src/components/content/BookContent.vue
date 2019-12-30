<template>
    <div class="book-overview-content layout-content" v-if="Object.keys(bookDoubanInfo).length > 0">
        <Row>
            <Col :xs="24" :sm="24" :md="24" :lg="17">
                <div class="layout-left">
                    <div class="book-infos">
                        <div class="book-base-info">
                            <div class="img">
                                <div class="container">
                                    <div class="bracket"></div>
                                    <div class="target">
                                        <img :src="book.headerImg" alt="">
                                    </div>
                                </div>
                            </div>
                            <div class="info">
                                <p class="title">{{book.title}}</p>
                                <p class="desc"><span>作者：</span>{{ book.author.join(' ') }}</p>
                                <p class="desc"><span>出版社：</span>{{ book.publisher }}</p>
                                <p class="desc"><span>出版日期：</span>{{ book.publishDate }}</p>
                                <p class="desc"><span>页数：</span>{{ book.pageNum }}</p>
                                <p class="desc"><span>评分：</span>
                                    <i-rate v-model="book.grade" :allowHalf="true"
                                            :disabled="true"></i-rate>
                                </p>
                                <Tag type="border" v-for="tag in book.tags" :key="tag.name"
                                     class="border-tag">{{ tag.name }}
                                </Tag>
                            </div>
                            <div class="rating"></div>
                        </div>
                        <vue-tabs class="book-detail-info" @tab-change="handleTabChange">
                            <v-tab title="简介">
                                <p class="summary" v-html="book.summary"></p>
                            </v-tab>
                            <v-tab title="读书笔记">
                                <book-catalog :book="book"></book-catalog>
                            </v-tab>
                            <v-tab title="原书目录">
                                <p class="catalog" v-html="book.catalog"></p>
                            </v-tab>
                            <v-tab title="读后感" v-if="book !== undefined">
                                <div class="article-details" id="article-main-page" ref="book" v-viewer>
                                    <div class="detail" v-for="detail in book.details">
                                        <article class="typo container article-main-content"
                                                 v-html="detail.content">
                                        </article>
                                        <div class="detail-footer">
                                            以上内容添加于： {{ detail.createTime | socialDate }} &nbsp;&nbsp;&nbsp;
                                            更新于： {{ detail.updateTime | socialDate }}
                                        </div>
                                    </div>
                                </div>
                                <!--<p class="summary" v-html="book.detail.formatted_content"></p>-->
                            </v-tab>
                        </vue-tabs>
                    </div>
                    <social-section :article="book"></social-section>
                </div>
            </Col>
            <Col :xs="0" :sm="0" :md="0" :lg="7">
                <div class="layout-right">
                    <recommend></recommend>
                    <Affix :offset-top="60" v-show="showToc">
                        <side-toc style="margin-top: 15px;" ref="sideToc"></side-toc>
                    </Affix>
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
    import {VueTabs, VTab} from 'vue-nav-tabs';
    import 'vue-nav-tabs/themes/paper.css';
    import BookCatalog from "../../views/book/BookCatalog";
    import Recommend from "../../views/Recommend";
    import SideToc from "../../views/SideToc";
    import SocialSection from "../../views/comment/SocialSection";
    // TOC
    import tocbot from 'tocbot';
    // mixin
    import {mixin} from '@/utils';

    export default {
        name: 'book-content',
        data() {
            return {
                id: 0,
                showToc: false,
                tocbotControl: undefined
            };
        },
        mixins: [mixin],
        beforeRouteLeave(to, from, next) {
            // 导航离开时清空vuex中文章数据
            this.clearBookInfo();
            next();
        },
        beforeRouteUpdate(to, from, next) {
            next();
            this.id = this.$route.params.id;
            this.browse_auth = this.$route.query.browse_auth;
            this.refreshData();
        },
        computed: {
            ...mapState({
                book: state => state.book.book,
            })
        },
        mounted() {
            this.id = this.$route.params.id;
            this.browse_auth = this.$route.query.browse_auth;
            let that = this;
            // 未SSR的情况
            this.refreshData();
        },
        beforeDestroy() {
            // 销毁时清空vuex中数据
            this.clearBookInfo();
            if (this.tocbotControl !== undefined) {
                this.tocbotControl.destroy();
            }
        },
        methods: {
            ...mapMutations({
                clearBookInfo: 'book/CLEAR_BOOK_DETAIL_INFO'
            }),
            ...mapActions({
                getBookDetailInfo: 'book/GET_BOOK_DETAIL_INFO'
            }),
            refreshData() {
                this.id = this.$route.params.id;
                this.browse_auth = this.$route.query.browse_auth;
                let that = this;
                this.getBookDetailInfo({
                    params: {
                        browse_auth: this.browse_auth
                    },
                    id: this.id
                }).then(response => {
                    console.log('book refreshContent');
                    this.refreshContent();
                }).catch((error) => {
                });
            },
            checkPassword(message) {
                let checkAuth = (browseAuthInput, isAutoRemove) => {
                    this.$router.push({
                        name: this.$router.name,
                        params: {id: this.id},
                        query: {browse_auth: this.browse_auth}
                    });
                    if (isAutoRemove) {
                        this.$Modal.remove();
                    }
                };
            },
            // 更新文章图片、目录
            refreshContent() {
                this.$nextTick(() => {
                    this.addTocScrollSpy();
                });
            },
            handleTabChange(tabIndex, newTab, oldTab) {
                this.showToc = tabIndex === 3;
            },
            addTocScrollSpy() {
                /* eslint-disable */
                if (!this.$refs.book) return;
                let tocSelector = '#side-toc';
                if (document.body.clientWidth <= 1200) {
                    tocSelector = '#sidebar-toc';
                }
                this.tocbotControl = tocbot.init({
                    tocSelector,
                    contentSelector: '#article-main-page',
                    headingSelector: 'h1, h2, h3, h4, h5',
                    linkClass: 'toc-link',
                    activeLinkClass: 'is-active-link',
                    listClass: 'toc-list',
                    isCollapsedClass: 'is-collapsed',
                    collapsibleClass: 'is-collapsible',
                    listItemClass: 'toc-list-item',
                    collapseDepth: 3,
                    scrollSmooth: true,
                    scrollSmoothDuration: 420,
                    headingsOffset: 1,
                    throttleTimeout: 50,
                    positionFixedClass: 'is-position-fixed',
                    fixedSidebarOffset: 'auto',
                    includeHtml: true,
                    onClick: false
                });
            }
        },
        components: {
            'vue-tabs': VueTabs,
            'v-tab': VTab,
            'book-catalog': BookCatalog,
            'social-section': SocialSection,
            'recommend': Recommend,
            'side-toc': SideToc,
        },
        watch: {
            showToc: function (newShowToc) {
                if (newShowToc) {
                    this.$nextTick(() => {
                        this.addTocScrollSpy();
                    });
                }
            }
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../../common/stylus/theme.styl";
    @import "../../common/stylus/article.styl";
    .book-overview-content
        .book-infos
            .book-base-info
                display flex
                margin-bottom 15px

                .img
                    position relative
                    flex 0 0 140px
                    width 140px
                    overflow hidden

                    .container
                        width 100%
                        position relative
                        overflow hidden

                        .bracket
                            margin-top 140%

                        .target
                            position absolute
                            top 0
                            bottom 0
                            left 0
                            right 0
                            border 1px solid $default-border-color

                            > img
                                width 100%
                                height 100%
                                transition All 0.4s ease-in-out
                                transform scale(1.0)
                                zoom 1.0

                .info
                    padding-left 15px

                    .title
                        font-size 20px
                        line-height 28px
                        font-weight 500
                        color $default-title-color
                        margin-bottom 5px
                        text-align justify

                    .desc
                        font-size 14px
                        font-weight 100
                        line-height 20px
                        color $default-desc-color
                        text-align justify
                        margin-bottom 3px

                        > span
                            color $default-desc-color
                            font-weight 700

            .book-detail-info
                p.summary, p.author, p.catalog
                    font-size 14px
                    font-weight 200
                    line-height 23px
                    text-align justify
                    color $default-desc-color

                .nav-tabs-navigation
                    border-bottom 1px solid $default-border-color
                    margin-bottom 20px

                    .nav-tabs
                        li
                            margin-bottom 0

                            a
                                color $default-info-color

                                &.active_tab
                                    color $default-info-hover-color

                                &::before
                                    bottom 2px
                                    background transparent
                                    border-bottom 11px solid $default-border-color

                                &::after
                                    bottom 1px
                                    background transparent
                                    border-bottom 11px solid $default-body-background-color
</style>
