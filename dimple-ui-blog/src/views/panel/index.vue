<template>
    <div class="panel">
        <panel-introduction :style="{ width: stickyOffsetWidth }"></panel-introduction>
        <div class="panel__sticky"
             :class="{
        'panel--sticky-top': sticky,
        'panel--sticky-rollback': rollBack && !stickyBottom,
        'panel--sticky-bottom': stickyBottom
      }"
             :style="{ width: stickyOffsetWidth }">
            <component v-for="(panel, index) in enums" class="panel__item" :is="panel" :key="index"></component>
        </div>
    </div>
</template>
<script>
import {mapState} from "vuex";
import panelIntroduction from "./components/panel-introduction";
import panelCatalog from "./components/panel-catalog";
import panelArticles from "./components/panel-articles";
import panelComments from "./components/panel-comments";
import panelCategory from "./components/panel-category";
import panelTags from "./components/panel-tags.vue";
import panelArchives from "./components/panel-archives";
import {getScrollTop} from "@/utils/getScrollTop";
import {getElementTop} from "@/utils/getElementTop";
import debounce from "lodash/debounce";

export default {
    name: 'panel',
    components: {
        panelIntroduction,
        panelCatalog,
        panelArticles,
        panelComments,
        panelCategory,
        panelTags,
        panelArchives
    },
    inject: {
        panels: {
            from: 'panels',
            default: () => ({})
        }
    },
    props: {},
    data() {
        return {
            sticky: false,
            stickyBottom: false,
            panelOffsetTop: 0,
            stickyOffsetTop: 0,
            bodyPageWrapperTop: 0,
            panelOffsetHeight: 0,
            stickyOffsetWidth: 'auto',
            stickyOffsetHeight: 0,
            bodyPageWrapperHeight: 0,
            observer: null
        }
    },
    mounted() {
        this.initStickyBehavior()
    },
    watch: {
        $route(to, from) {
            //  锚点跳转防止routeUpdate
            if (JSON.stringify(to.params) !== JSON.stringify(from.params)) {
                this.removeEffect()
                this.initStickyBehavior()
            }
        }
    },
    computed: {
        ...mapState(['rollBack']),
        enums() {
            // 路由进行自定义看板、顺序
            const def = [
                'panel-catalog',
                'panel-articles',
                'panel-comments',
                'panel-tags',
                'panel-category',
                'panel-archives'
            ]
            if (this.panels) {
                if (this.panels.includes && Array.isArray(this.panels.includes)) return this.panels.includes
                if (this.panels.excludes) {
                    this.panels.excludes.forEach((item) => {
                        if (def.includes(item)) {
                            const index = def.indexOf(item)
                            if (index !== -1) def.splice(index, 1)
                        }
                    })
                }
            }
            if (this.$router.currentRoute.name !== 'articleDetail' && this.$router.currentRoute.name !== 'about') {
                const index = def.indexOf('panel-catalog');
                if (index !== -1) {
                }
                def.splice(index, 1);
            }
            return def
        }
    },
    methods: {
        initStickyBehavior() {
            window.addEventListener('scroll', this.stickyHandler, false)
            window.addEventListener('resize', this.resizeHandler, false)
            const bodyPageWrapperNode = document.querySelector('.body-page__wrapper')
            const panelNode = document.querySelector('.panel')
            const stickNode = document.querySelector('.panel__sticky')
            this.observer = new ResizeObserver(() => {
                if (document.documentElement.clientWidth <= 768) return
                this.panelOffsetHeight = panelNode.offsetHeight
                this.stickyOffsetHeight = stickNode.offsetHeight
                this.bodyPageWrapperHeight = bodyPageWrapperNode.offsetHeight

                // 尺寸变化后，主动触发一次计算，避免手动滚动页面时才进行重新布局
                this.stickyHandler()
            })
            this.observer.observe(panelNode, {attributes: true, childList: false, subtree: false})
            this.observer.observe(stickNode, {attributes: true, childList: false, subtree: false})
            this.$nextTick(() => {
                this.stickyOffsetTop = getElementTop(stickNode)
                this.panelOffsetTop = getElementTop(panelNode)
                this.bodyPageWrapperTop = getElementTop(bodyPageWrapperNode)
                this.stickyOffsetHeight = stickNode.offsetHeight
                this.stickyOffsetWidth = stickNode.offsetWidth + 'px'
                this.panelOffsetHeight = panelNode.offsetHeight
                this.bodyPageWrapperHeight = bodyPageWrapperNode.offsetHeight
            })
        },
        stickyHandler() {
            // 视口尺寸小于等于768px，则默认手机状态布局，无需处理sticky逻辑
            if (document.documentElement.clientWidth <= 768) return
            if (this.stickyOffsetTop + this.stickyOffsetHeight > this.bodyPageWrapperTop + this.bodyPageWrapperHeight) {
                // 此时应该清空状态
                this.sticky = false
                this.stickyBottom = false
                return
            }
            const scrollTop = getScrollTop()
            const distance = this.rollBack ? 70 : 20

            if (this.stickyOffsetTop - scrollTop <= distance) {
                this.sticky = true
                this.stickyBottom = false
                // 当页面回滚，sticky容器将固定与top：70px位置，正常向下滚动，位于top：20px位置
                // 避免像素跳转，产生闪烁问题
                const threshold = this.rollBack ? 70 : 20

                if (this.panelOffsetHeight + this.panelOffsetTop - scrollTop - threshold <= this.stickyOffsetHeight) {
                    this.sticky = false
                    this.stickyBottom = true
                }
            } else {
                this.sticky = false
            }
        },
        resizeHandler: debounce(function () {
            const panelNode = document.querySelector('.panel')
            const layoutBody = document.querySelector('.layout__body-content')
            this.stickyOffsetWidth = panelNode.offsetWidth + 'px'
            const maxWidth = window.getComputedStyle(layoutBody)['max-width']
            // 切换至手机尺寸后，将sticky相关状态全部置空
            if (maxWidth === '768px') {
                this.sticky = false
                this.stickyBottom = false
            }
        }, 200),
        removeEffect() {
            this.sticky = false
            this.stickyBottom = false
            this.observer.disconnect()
            window.removeEventListener('scroll', this.stickyHandler, false)
            window.removeEventListener('resize', this.resizeHandler, false)
        }
    },

    destroyed() {
        this.removeEffect()
    }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';

.panel {
  position: relative;
  height: 100%;

  &__sticky {
    transition: all ease 0.38s;
  }

  &__item:not(:first-child) {
    margin-top: 16px;
  }
}

.panel--sticky-top {
  position: fixed;
  top: 20px;
}

.panel--sticky-bottom {
  position: absolute;
  bottom: 0;
}

.panel--sticky-rollback {
  top: 70px;
}

@include respond-to(xs) {
  .panel {
    margin-top: 16px;
  }
  .panel__sticky {
    position: relative !important;
    top: 0 !important;
  }
}
</style>
