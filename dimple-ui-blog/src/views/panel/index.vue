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
    <div v-if="btnFlag" id="rightside" style="opacity: 0.8; transform: translateX(-58px);">
      <div id="rightside-config-show">
        <button id="darkmode" @click="changeTheme" type="button" title="浅色和深色模式转换"><i
          class="fa fa-adjust"></i></button>
        <button @click="backTop" id="go-up" type="button" title="回到顶部" class="show-percent"><span
          class="scroll-percent">{{ backupValue }}</span><i
          class="fa fa-arrow-up"></i></button>
      </div>
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
import {storage} from "@/utils/storage";

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
      backupValue: 0,
      btnFlag: false,
      showSide: true,
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
    this.initStickyBehavior();
    window.addEventListener('scroll', this.scrollToTop)
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
    ...mapState(['rollBack','panelShow']),
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
      return def;
    }
  },
  methods: {
    // 点击图片回到顶部方法，加计时器是为了过渡顺滑
    backTop() {
      const that = this
      let timer = setInterval(() => {
        let ispeed = Math.floor(-that.scrollTop / 5)
        document.documentElement.scrollTop = document.body.scrollTop = that.scrollTop + ispeed
        if (that.scrollTop === 0) {
          clearInterval(timer)
        }
      }, 16)
    },

    // 为了计算距离顶部的高度，当高度大于60显示回顶部图标，小于60则隐藏
    scrollToTop() {
      const that = this
      let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      that.scrollTop = scrollTop;
      if (that.scrollTop > 200) {
        that.btnFlag = true
      } else {
        that.btnFlag = false
      }
      // 可视区高度
      var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
      // 滚动条总高度
      var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
      this.backupValue = parseInt(+(scrollTop / (scrollHeight - clientHeight)).toFixed(2) * 100);
    },
    changeTheme() {
      var currentTheme = document.getElementsByTagName("body")[0].getAttribute("class");
      if (currentTheme === 'data-theme-dark') {
        storage.setTheme("data-theme-light");
        document.getElementsByTagName("body")[0].setAttribute("class", 'data-theme-light');
      } else {
        storage.setTheme("data-theme-dark");
        document.getElementsByTagName("body")[0].setAttribute("class", 'data-theme-dark');
      }
    },
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
    window.removeEventListener('scroll', this.scrollToTop)
    this.removeEffect()
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';

#rightside {
  position: fixed;
  right: -48px;
  bottom: 40px;
  z-index: 100;
  opacity: 0;
  -webkit-transition: all .5s;
  -moz-transition: all .5s;
  -o-transition: all .5s;
  -ms-transition: all .5s;
  transition: all .5s
}

#rightside > div > a, #rightside > div > button {
  display: block;
  margin-bottom: 5px;
  width: 35px;
  height: 35px;
  border: none;
  border-radius: 5px;
  @include themify() {
    background-color: themed('btn-bg');
    color: themed('btn-color');
  }
  text-align: center;
  font-size: 16px;
  line-height: 35px
}

#rightside > div > a:hover, #rightside > div > button:hover {
  @include themify() {
    background-color: themed('btn-hover-color')
  }
}

#rightside #go-up .scroll-percent {
  display: none
}

#rightside #go-up.show-percent .scroll-percent {
  display: block
}

#rightside #go-up.show-percent .scroll-percent + i {
  display: none
}

#rightside #go-up:hover .scroll-percent {
  display: none
}

#rightside #go-up:hover .scroll-percent + i {
  display: block
}


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
