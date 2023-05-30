// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// eslint-disable-next-line no-unused-vars
import App from './App.vue'
import moment from 'moment'
import api from '@/api/'
import Vue from 'vue'
import {
  Button,
  Card,
  Col,
  Dialog,
  Drawer,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  Empty,
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
  Row,
  Scrollbar,
  Select,
  Skeleton,
  SkeletonItem,
  Submenu,
  TabPane,
  Tabs,
  Tag,
  Timeline,
  TimelineItem
} from "element-ui";
import layout from '@/views/layout/'
import {createRouter} from './router'
import {createStore} from './store'
import {sync} from 'vuex-router-sync'

import mergeAsyncData from '@/mixins/mergeAsyncData'

Vue.component('layout', layout)
Vue.use(Skeleton)
Vue.use(Col)
Vue.use(Drawer)
Vue.use(Empty)
Vue.use(SkeletonItem)
Vue.use(Row)
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
Vue.prototype.$message = Message

// Vue.config.productionTip = false
Vue.filter('formatDate', (val) => {
  if (val) {
    return moment(val).format('YYYY-MM-DD HH:mm')
  }
  return "N/A";
})
Vue.mixin(mergeAsyncData)
Vue.prototype.$moment = moment;
Vue.prototype.$api = api
Vue.config.devtools = true

/* eslint-disable no-new */
export function createApp() {
  const router = createRouter()
  const store = createStore()
  sync(store, router)
  const app = new Vue({
    router,
    store,
    render: (h) => h(App)
  })

  return {app, router, store}
}
