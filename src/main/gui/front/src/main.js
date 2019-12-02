import Vue from 'vue'
import App from './App.vue'
import Vuex from 'vuex'

import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';

import store from './store'

import Router from '@/router/index.js';

Vue.use(Vuex);
Vue.use(ViewUI);

Vue.config.productionTip = false

Vue.prototype.$Modal = ViewUI.Modal;
Vue.prototype.$Message = ViewUI.Message;
Vue.prototype.$Notice = ViewUI.Notice;


new Vue({
    render: h => h(App),
    router: Router, store
}).$mount('#app')
