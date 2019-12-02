import {saveToLocal} from '@/utils';
import {listCategories, listMenus} from '@/api'

export default {
  namespaced: true,
  state: {
    menus: null,
    categories: null,
    bloggerInfo: null,
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
    //更新主题
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
    // 获取所有的菜单
    GET_MENUS({state, commit}, params) {
      return new Promise((resolve, reject) => {
        listMenus().then((response) => {
          commit('UPDATE_MENUS', response.data);
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    },
    //获取分类
    GET_CATEGORIES({state, commit}, params) {
      return new Promise((resolve, reject) => {
        listCategories().then((response) => {
          commit('UPDATE_CATEGORIES', response.data);
          resolve(response);
        }).catch((error) => {
          reject(error);
        });
      });
    },
  }
}

