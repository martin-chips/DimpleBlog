import Vue from 'vue'
import Vuex from 'vuex'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)
export function createStore() {
  return new Vuex.Store({
    state: {
      activeCatalog: '',
      rollBack: false,
      // 访客信息
      visitorInfo: {},
      // 文章目录树
      catalogs: [],
      // 文章归档 按月统计
      archives: [],
      // 文章分类
      category: [],
      // 文章标签
      tags: [],
      // 最新评论
      newComments: [],
      // 最新文章
      newArticles: [],
      totals: {
        article: 0,
        tag: 0,
        category: 0
      }
    },
    mutations,
    actions
  })
}
