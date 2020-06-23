import {BlogDetail} from "@/api"
export default {
    namespaced: true,
    state: {
      article: {},
    },
    mutations: {
      UPDATE_ARTICLE_DETAIL_INFO(state, article) {
        state.article = article;
      },
      CLEAR_ARTICLE_DETAIL_INFO(state) {
        state.article = {};
      }
    },
    actions: {
      // 获取文章详细信息
      GET_ARTICLE_DETAIL_INFO({state, commit}, id) {
        return new Promise((resolve, reject) => {
          BlogDetail(id).then((response) => {
            // 更新文章信息和权限信息
            commit('UPDATE_ARTICLE_DETAIL_INFO', response.data);
            // 更新文章页的meta信息
            // commit('UPDATE_DOCUMENT_TITLE', response.data.title, { root: true });
            // commit('UPDATE_DOCUMENT_DESCRIPTION', response.data.desc, { root: true });
            // commit('UPDATE_DOCUMENT_KEYWORDS', response.data.tags.map((tags) => {
            //   return tags.name;
            // }).join(','), { root: true });
            resolve(response);
          }).catch((error) => {
            reject(error);
          });
        });
      }
    }
};
