import {listRecommend, listHot, listFriendLinks, listTag} from '@/api'

export default {
  namespaced: true,
  state: {
    recommends: [],
    hots: [],
    friendLinks: [],
    tags: []
  },
  mutations: {
    UPDATE_RECOMMENDS(state, recommends) {
      state.recommends = recommends;
    },
    UPDATE_HOTS(state, hots) {
      state.hots = hots;
    },
    UPDATE_FRIEND_LINKS(state, friendLinks) {
      state.friendLinks = friendLinks;
    },
    UPDATE_TAGS(state, tags) {
      state.tags = tags;
    }
  },
  actions: {
    // 获取推荐内容
    GET_RECOMMENDS(store, params) {
      return new Promise((resolve, reject) => {
        listRecommend(params).then((response) => {
          store.commit('UPDATE_RECOMMENDS', response.data);
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    },
    // 获取热门内容
    GET_HOTS(store, params) {
      params = {
        pageSize: 5,
        pageNum: 1,
        orderByColumn: "b.click,b.createTime,b.weight",
        isAsc: 'desc'
      };
      return new Promise((resolve, reject) => {
        listHot(params).then((response) => {
          store.commit('UPDATE_HOTS', response.data);
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    },
    // 获取友情链接
    GET_FRIEND_LINKS(store, params) {
      return new Promise((resolve, reject) => {
        listFriendLinks().then((response) => {
          store.commit('UPDATE_FRIEND_LINKS', response.data);
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    },
    // 获取标签墙
    GET_TAGS(store, params) {
      return new Promise((resolve, reject) => {
        listTag().then((response) => {
          store.commit('UPDATE_TAGS', response.data);
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    }
  }
}
