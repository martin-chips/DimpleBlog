import Vue from "vue";
import App from "./App.vue";
import Vuex from "vuex";
import "./style/index.scss";
import api from "@/api/";
import moment from "moment";
import empty from "@/components/empty";
import { createStore } from "./store";
import { createRouter } from "@/router";
import VueLazyload from "@/utils/lazyLoad";
import "element-ui/lib/theme-chalk/index.css";
import R_O_P from "resize-observer-polyfill";

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