import Vue from "vue";
import App from "./App.vue";
import Vuex from "vuex";
import "./style/index.scss";
import api from "@/api/";
import moment from "moment";
import empty from "@/components/empty";
import {createStore} from "./store";
import {createRouter} from "@/router";
import VueLazyload from "@/utils/lazyLoad";
import "element-ui/lib/theme-chalk/index.css";
import R_O_P from "resize-observer-polyfill";
import {
  Button,
  Card,
  Dialog,
  Drawer,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  Form,
  FormItem,
  Input,
  Menu,
  MenuItem,
  Message,
  Pagination,
  Popover,
  RadioButton,
  RadioGroup,
  Scrollbar,
  Select,
  Submenu,
  TabPane,
  Tabs,
  Tag,
  Timeline,
  TimelineItem
} from "element-ui";
import layout from "@/views/layout/";

Vue.component('layout', layout)
Vue.use(Button)
Vue.use(Select)
Vue.use(Tag)
Vue.use(Card)
Vue.use(Dropdown)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Pagination)
Vue.use(Input)
Vue.use(Dialog)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Popover)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Scrollbar)
Vue.use(Timeline)
Vue.use(TimelineItem)
Vue.use(RadioGroup)
Vue.use(RadioButton)
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Submenu)
Vue.use(Drawer)
Vue.prototype.$message = Message

const loading = require("@img/loading.gif");
Vue.component("empty", empty);
Vue.use(Vuex);
Vue.prototype.$api = api;
Vue.prototype.$moment = moment;

if (!window.ResizeObserver) {
  window.ResizeObserver = R_O_P;
}

Vue.filter("formatDate", (val) => {
  return moment(val).format("YYYY-MM-DD HH:mm");
});

Vue.use(VueLazyload, {
  loading: loading
});



new Vue({
  render: h => h(App),
  router: createRouter(),
  store: createStore()
}).$mount("#app");