import {listBlogBaseInfo} from '@/api/front/front.js'

export default {
  namespaced: true,
  state: {
    articles: []
  },
  mutations: {
    UPDATE_ARTICLES_BASE_INFO(state, articles) {
      state.articles = articles;
    }
  },
  actions: {
    // 获取文章基本信息
    GET_ARTICLES_BASE_INFO({state, commit}, params) {
      return new Promise((resolve, reject) => {
        listBlogBaseInfo(params).then((response) => {
          commit('UPDATE_ARTICLES_BASE_INFO', response.data);
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    },
  }
}
