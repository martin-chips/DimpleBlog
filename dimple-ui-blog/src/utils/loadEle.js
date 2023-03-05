import Vue from "vue";
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