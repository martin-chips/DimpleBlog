<template>
  <div class="article-content layout-content" v-if="article">
    <el-row>
      <el-col :xs="24" :sm="ExpandLeftColumn ? 24 : 17" :md="ExpandLeftColumn ? 24 : 17"
              :lg="ExpandLeftColumn ? 24 : 17">
        <div class="layout-left" v-if="article">
          <article-page-header :article="article"></article-page-header>
          <article-page-content>
            <div class="article-details" id="article-main-page" slot="content" ref="article">
              <div class="detail" v-if="article">
                <article class="typo container article-main-content" v-html="article.content">
                </article>
                <div class="detail-footer">
                  以上内容添加于 {{ article.createTime | socialDate }} &nbsp;&nbsp;&nbsp;
                  更新于 {{ article.updateTime | socialDate }}
                </div>
              </div>
            </div>
          </article-page-content>
          <article-page-footer :article="article"></article-page-footer>
        </div>
      </el-col>
      <el-col :xs="0" :sm="ExpandLeftColumn ? 0 : 7" :md="ExpandLeftColumn ? 0 : 7" :lg="ExpandLeftColumn ? 0 : 7">
        <div class="layout-right">
          <recommend></recommend>
          <div :offset-top="60">
            <side-toc style="margin-top: 15px;" ref="sideToc"></side-toc>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
    import {
        mapState,
        mapMutations,
    } from 'vuex';
    import ArticlePageHeader from "../../views/article/ArticlePageHeader";
    import ArticlePageContent from "../../views/article/ArticlePageContent";
    import ArticlePageFooter from "../../views/article/ArticlePageFooter";
    import about from "../../../views/front/about";
    import SideToc from "../../views/SideToc";
    import {BlogDetail} from "@/api/front/front"
    import Recommend from "../../views/Recommend";
    import {mixin} from '@/utils/front/utils';
    import tocbot from 'tocbot';

    export default {
        name: 'article-content',
        data() {
            return {
                id: undefined,
                article: undefined,
                tocbotControl: undefined
            };
        },
        mixins: [mixin],
        mounted() {
            this.refreshData();
        },
        beforeRouteUpdate(to, from, next) {
            next();
            console.log('beforeRouteUpdate');
            this.id = this.$route.params.id;
            this.refreshData();
        },
        beforeDestroy() {
            // 恢复通栏
            this.resetExpandColumn();
        },
        computed: {
            ...mapState({
                ExpandLeftColumn: state => state.base.ExpandLeftColumn
            })
        },
        methods: {
            ...mapMutations({
                resetExpandColumn: 'base/RESET_EXPAND_COLUMN'
            }),
            refreshData() {
                this.id = this.$route.params.id;
                BlogDetail(this.id).then(response => {
                    if (response.code === 200) {
                        this.article = response.data;
                    }
                });
                this.refreshContent();
            },
            // 更新文章图片、目录
            refreshContent() {
                this.$nextTick(() => {
                    // 添加图片前缀
                    // this.resolveImageTagsUrl(this.$refs.article.querySelectorAll('img'));
                    this.addTocScrollSpy();
                });
            },
            addTocScrollSpy() {
                /* eslint-disable */
                // if (!this.$refs.article) return;
                console.log('addTocScrollSpy');
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
            'article-page-header': ArticlePageHeader,
            'article-page-content': ArticlePageContent,
            'article-page-footer': ArticlePageFooter,
            'about': about,
            'recommend': Recommend,
            'side-toc': SideToc
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
</style>
