import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import tagsView from './modules/tagsView'
import permission from './modules/permission'
import settings from './modules/settings'
import getters from './getters'

//前台
import base from './modules/front/base';
import common from './modules/front/common';
import home from './modules/front/home';
import timeline from './modules/front/timeline';
import articleHome from './modules/front/articleHome';

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    tagsView,
    permission,
    settings,
    //前台
    base,
    home,
    common,
    timeline,
    articleHome
  },
  getters
})

export default store
