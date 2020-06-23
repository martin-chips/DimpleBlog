import {listBlogArchive} from '@/api'

export default {
  namespaced: true,
  state: {
    timeline: {},
    totalCount: 0,
    noMoreData: false
  },
  mutations: {
    UPDATE_TIMELINE_INFO(state, datas) {
      // 记录已有的post数量
      state.totalCount += datas.rows.length;
      // 根据年月来分发更新timeline
      datas.rows.map((post) => {
        // 按年月分批
        let addYear = new Date(post.createTime.replace(/\-/g, "/") ).getFullYear();
        let addMonth = new Date(post.createTime.replace(/\-/g, "/")).getMonth();
        if (!state.timeline.hasOwnProperty(addYear)) {
          state.timeline[addYear] = {};
          state.timeline[addYear]['months'] = {};
          state.timeline[addYear]['count'] = 0;
        }
        if (!state.timeline[addYear]['months'].hasOwnProperty(addMonth + 1)) {
          state.timeline[addYear]['months'][addMonth + 1] = [];
        }
        state.timeline[addYear]['months'][addMonth + 1].push(post);
        state.timeline[addYear]['count'] += 1;
      });
      state.timeline = Object.assign({}, state.timeline);
      // 判断是否还有更多
      state.noMoreData = state.totalCount >= datas.total;
    },
    CLEAR_TIMELINE_INFO(state) {
      state.timeline = {};
      state.totalCount = 0;
      state.noMoreData = false;
    }
  },
  actions: {
    // 获取文章详细信息
    GET_TIMELINE_INFO({state, commit}, {params, reset}) {
      return new Promise((resolve, reject) => {
        listBlogArchive(params).then((response) => {
          // 更新timeline
          if (reset) {
            // 如果是重置，则先删除原有数据
            commit('CLEAR_TIMELINE_INFO');
          }
          commit('UPDATE_TIMELINE_INFO', response);
          // commit('UPDATE_DOCUMENT_TITLE', '时间轴', {root: true});
          // commit('UPDATE_DOCUMENT_DESCRIPTION', '时间轴', {root: true});
          // commit('UPDATE_DOCUMENT_KEYWORDS', '时间轴', {root: true});
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    }
  }
}
