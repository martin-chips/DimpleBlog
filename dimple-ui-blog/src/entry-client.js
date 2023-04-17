import {createApp} from './app'
import {storage} from '@/utils/storage'
import R_O_P from 'resize-observer-polyfill'
import Vue from 'vue'
import VueLazyload from '@/utils/lazyLoad'

const loading = require('@/assets/img/loading.gif')
if (!window.ResizeObserver) {
  window.ResizeObserver = R_O_P
}
// eslint-disable-next-line no-undef
Vue.use(VueLazyload, {
  loading: loading
})

Vue.mixin({
  beforeRouteUpdate(to, from, next) {
    const { asyncData } = this.$options
    if (asyncData) {
      asyncData({
        store: this.$store,
        route: to
      })
        .then((res) => {
          Object.assign(this.$data, res)
          next()
        })
        .catch(next)
    } else {
      next()
    }
  }
})

const { app, router, store } = createApp()
Prism.plugins.toolbar.registerButton('macostyle', function () {
  const content = document.createElement('div')
  content.setAttribute('class', 'toolbar-item__content')
  content.innerHTML = '<span class="toolbar-item__icon"></span>'
  return content
})
// 将服务端渲染时的状态写入vuex中
if (window.__INITIAL_STATE__) {
  store.replaceState(window.__INITIAL_STATE__.state)
}
// 同步访客登录信息
if (storage.getVisitor()) store.commit('setVisitor', storage.getVisitor())
router.onReady(() => {
  router.beforeResolve(async (to, from, next) => {
    const matched = router.getMatchedComponents(to)
    const prevMatched = router.getMatchedComponents(from)
    // 我们只关心之前没有渲染的组件
    // 所以我们对比它们，找出两个匹配列表的差异组件
    let diffed = false
    const activated = matched.filter((c, i) => {
      return diffed || (diffed = prevMatched[i] !== c)
    })
    if (!activated.length) {
      return next()
    }
    try {
      // 这里如果有加载指示器(loading indicator)，就触发
      await Promise.all(
        activated.map(async (Component) => {
          if (Component.asyncData) {
            const res = await Component.asyncData({ store, route: to })
            Component.__COMPONENT_ASYNCDATA__ = res || {}
          }
        })
      )
      // 停止加载指示器(loading indicator)
      next()
    } catch (e) {
      console.error(`[entry-client]: async data fetch error -> ${e}`)
      next(e)
    }
  })

  app.$mount('#app')
})
