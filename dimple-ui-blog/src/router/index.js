import Vue from "vue";
import Router from "vue-router";
import Meta from "vue-meta";

const miss = () => import("@/components/base/miss");
const home = () => import("@/views/home/index.vue");
const articleDetail = () => import("@/views/article/articleDetail");
const messageBoard = () => import("@/views/messageBoard/");
const archives = () => import("@/views/archives/");
const tags = () => import("@/views/tags/");
const articleFilter = () => import("@/views/article-filter/");
const category = () => import("@/views/category/");

Vue.use(Router);
Vue.use(Meta);
// 避免重复点击相同路由 报错问题
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err);
};

export function createRouter() {
  return new Router({
    mode: "history",
    routes: [
      {
        path: "*",
        name: "miss",
        component: miss
      },
      {
        path: "/",
        name: "home",
        component: home
      },
      {
        path: "/app/article/:id",
        name: "articleDetail",
        component: articleDetail
      },
      {
        path: "/app/messageBoard",
        name: "messageBoard",
        component: messageBoard
      },
      {
        path: "/app/archives",
        name: "archives",
        component: archives
      },
      {
        path: "/app/tags",
        name: "tags",
        component: tags
      },
      {
        path: "/app/category",
        name: "category",
        component: category
      },
      {
        path: "/app/articles/:type/:param",
        name: "articleFilter",
        component: articleFilter
      }
    ],
    scrollBehavior(to, from, savedPosition) {
      if (to.hash || to.query.anchor) return false;
      if (savedPosition) {
        return savedPosition;
      } else {
        return { x: 0, y: 0 };
      }
    }
  });
}
