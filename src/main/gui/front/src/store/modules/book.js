import {BookDetail} from "@/api"

export default {
    namespaced: true,
    state: {
        book: {},
    },
    mutations: {
        UPDATE_BOOK_INFO(state, book) {
            state.book = book;
        },
        CLEAR_BOOK_INFO(state) {
            state.book = {};
        }
    },
    actions: {
        GET_BOOK_DETAIL_INFO({state, commit, dispatch}, params) {
            return new Promise((resolve, reject) => {
                BookDetail(params).then((response) => {
                    // 更新图书信息和权限信息
                    commit('UPDATE_BOOK_INFO', response.data);
                }).catch((error) => {
                    reject(error);
                });
            });
        },
    }
};
