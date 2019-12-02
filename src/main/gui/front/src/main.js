import Vue from 'vue'
import App from './App.vue'
import Vuex from 'vuex'

import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
// import '@/assets/iview.min.css';

import store from './store'

import Router from '@/router/index.js';

Vue.use(Vuex);
Vue.use(ViewUI);

Vue.config.productionTip = false

Vue.prototype.$Modal = ViewUI.Modal;
Vue.prototype.$Message = ViewUI.Message;
Vue.prototype.$Notice = ViewUI.Notice;

import hljs from 'highlight.js' //导入代码高亮文件
// import 'highlight.js/styles/monokai-sublime.css'  //导入代码高亮样式

//自定义一个代码高亮指令
Vue.directive('highlight',function (el) {
    let highlight = el.querySelectorAll('pre code');
    highlight.forEach((block)=>{
        hljs.highlightBlock(block)
    })
})


new Vue({
    render: h => h(App),
    router: Router, store
}).$mount('#app')
