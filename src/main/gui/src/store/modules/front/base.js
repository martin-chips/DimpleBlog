import {saveToLocal} from '@/utils/front/utils';
import {getSiteInfo, getMenu} from '@/api/front/front.js'

export default {
  namespaced: true,
  state: {
    menus: {},
    categories: [],
    siteTheme: 'default',
    ExpandLeftColumn: false
  },
  mutations: {
    //更新菜单
    UPDATE_MENUS(state, menus) {
      state.menus = menus;
    },
    //更新分类
    UPDATE_CATEGORIES(state, categories) {
      state.categories = categories;
    },
    UPDATE_SITE_THEME(state, siteTheme) {
      if (siteTheme === state.siteTheme) return;
      try {
        // 更改皮肤
        document.body.classList.remove(state.siteTheme);
        document.body.classList.add(siteTheme);
        // 持久化
        saveToLocal('siteConfig', 'theme', siteTheme);
      } catch (exception) {
        console.log(exception);
      }
      state.siteTheme = siteTheme;
    },
    UPDATE_EXPAND_LEFT_COLUMN(state, ExpandLeftColumn) {
      state.ExpandLeftColumn = ExpandLeftColumn;
    },
    RESET_EXPAND_COLUMN(state) {
      state.ExpandLeftColumn = false;
    }
  },
  actions: {
    // 获取所有的分类
    GET_MENUS({state, commit}, params) {
      return new Promise((resolve, reject) => {
        getMenu().then((response) => {
          commit('UPDATE_MENUS', response.data);
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    },
    GET_CATEGORIES({state, commit}, params) {
      return new Promise((resolve, reject) => {
        getMenu().then((response) => {
          commit('UPDATE_CATEGORIES', response.data);
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    }
  }
}

