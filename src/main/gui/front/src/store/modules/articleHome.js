import {listBlogBaseInfo} from '@/api';

export default {
  namespaced: true,
  state: {
    articles: [],
    totalCount: 0,
    noMoreData: false,
    bannerArticles: [],
  },
  mutations: {
    UPDATE_ARTICLES_BASE_INFO(state, datas) {
      // 记录已有的post数量
      state.totalCount += datas.rows.length;
      state.articles = state.articles.concat(datas.rows.filter((article) => {
        return true;
      }));
      // 判断是否还有更多
      state.noMoreData = state.totalCount >= datas.total;
      state.bannerArticles = state.bannerArticles.concat(datas.rows.filter((article) => {
        return article.support;
      }));
    },
    CLEAR_ARTICLES_BASE_INFO(state) {
      state.articles = [];
      state.totalCount = 0;
      state.noMoreData = false;
      state.bannerArticles = [];
    }
  },
  actions: {
    // 获取列表详细信息
    GET_ARTICLES_BASE_INFO({state, commit}, {params, reset}) {
      return new Promise((resolve, reject) => {
        listBlogBaseInfo(params).then((response) => {
          // 更新文章
          if (reset) {
            // 如果是重置，则先删除原有数据
            commit('CLEAR_ARTICLES_BASE_INFO');
          }
          commit('UPDATE_ARTICLES_BASE_INFO', response);
          // commit('UPDATE_DOCUMENT_TITLE', '文章列表', {root: true});
          // commit('UPDATE_DOCUMENT_DESCRIPTION', '文章列表', {root: true});
          // commit('UPDATE_DOCUMENT_KEYWORDS', '文章列表', {root: true});
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    }
  }
}
