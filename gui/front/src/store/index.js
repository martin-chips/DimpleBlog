import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'

//前台
import base from './modules/base';
import common from './modules/common';
import home from './modules/home';
import timeline from './modules/timeline';
import articleHome from './modules/articleHome';
import article from "./modules/article";
import settings from "./modules/settings";

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
        //前台
        base,
        home,
        common,
        timeline,
        articleHome,
        article,
        settings
    },
    getters
});

export default store
