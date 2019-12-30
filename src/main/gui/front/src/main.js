import Vue from 'vue'
import App from './App.vue'
import Vuex from 'vuex'

import store from './store'
import Router from '@/router/index.js';

import 'view-design/dist/styles/iview.css';
// iView UI 组件引入
import {
    Row,
    Col,
    Button,
    Progress,
    Tag,
    Icon,
    Affix,
    Rate,
    Modal,
    Message,
    Notice,
    Tooltip,
    ButtonGroup,
    Switch,
    BackTop,
    Spin,
    Checkbox,
    FormItem,
    Form,
    Input,
    Avatar,
    Card,
    Divider,
    DatePicker,
} from 'view-design'

Vue.component('Row', Row);
Vue.component('Col', Col);
Vue.component('Button', Button);
Vue.component('Progress', Progress);
Vue.component('Tag', Tag);
Vue.component('Icon', Icon);
Vue.component('Affix', Affix);
Vue.component('Rate', Rate);
Vue.component('Tooltip', Tooltip);
Vue.component('ButtonGroup', ButtonGroup);
Vue.component('i-switch', Switch);
Vue.component('BackTop', BackTop);
Vue.component('Spin', Spin);
Vue.component('Checkbox', Checkbox);
Vue.component('FormItem', FormItem);
Vue.component('Form', Form);
Vue.component('Input', Input);
Vue.component('Avatar', Avatar);
Vue.component('Card', Card);
Vue.component('Divider', Divider);
Vue.component('DatePicker', DatePicker);


Vue.use(Vuex);

Vue.config.productionTip = false;

Vue.prototype.$Modal = Modal;
Vue.prototype.$Message = Message;
Vue.prototype.$Notice = Notice;

import hljs from 'highlight.js' //导入代码高亮文件

//自定义一个代码高亮指令
Vue.directive('highlight',function (el) {
    let highlight = el.querySelectorAll('pre code');
    highlight.forEach((block)=>{
        hljs.highlightBlock(block);
        block.classList.add("custom-scrollbar")
    })
});


new Vue({
    render: h => h(App),
    router: Router, store
}).$mount('#app');
