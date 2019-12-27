import {listBlogBaseInfo, listNoteBaseInfo, listBookBaseInfo} from '@/api'

export default {
    namespaced: true,
    state: {
        articles: [],
        books: [],
        notes: [],
    },
    mutations: {
        UPDATE_ARTICLES_BASE_INFO(state, articles) {
            state.articles = articles;
        },
        UPDATE_BOOKS_BASE_INFO(state, books) {
            state.books = books;
        },
        UPDATE_NOTES_BASE_INFO(state, notes) {
            state.notes = notes;
        },
    },
    actions: {
        // 获取文章基本信息
        GET_ARTICLES_BASE_INFO({state, commit}, params) {
            return new Promise((resolve, reject) => {
                listBlogBaseInfo(params).then((response) => {
                    commit('UPDATE_ARTICLES_BASE_INFO', response.rows);
                    resolve(response);
                }).catch((error) => {
                    reject(error);
                });
            });
        },
        // 获取图书基本信息
        GET_BOOKS_BASE_INFO({state, commit}, params) {
            return new Promise((resolve, reject) => {
                listBookBaseInfo(params).then((response) => {
                    commit('UPDATE_BOOKS_BASE_INFO', response.rows);
                    resolve(response);
                }).catch((error) => {
                    reject(error);
                });
            });
        },
        // 获取图书笔记基本信息
        GET_NOTES_BASE_INFO({state, commit}, params) {
            return new Promise((resolve, reject) => {
                listNoteBaseInfo(params).then((response) => {
                    commit('UPDATE_NOTES_BASE_INFO', response.data.results);
                    resolve(response);
                }).catch((error) => {
                    reject(error);
                });
            });
        },
    }
}
