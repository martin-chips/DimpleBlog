import Router from 'vue-router';
import Vue from 'vue';
import routes from './routers';
import {LoadingBar} from 'view-design';

Vue.use(Router);

// 路由配置
const RouterConfig = {
    mode: 'history',
    routes: routes,
    linkActiveClass: 'active-link',
    linkExactActiveClass: 'exact-active-link',
    scrollBehavior(to, from, savedPosition) {
        if (to.hash) {
            // 先判断目标路由有没有hash值
            return {selector: to.hash};
        }
        if (savedPosition) {
            return savedPosition;
        } else {
            return {x: 0, y: 0};
        }
    }
};

// 配置加载进度条
LoadingBar.config({
    color: '#5cb85c',
    failedColor: '#f0ad4e',
    height: 2
});

const router = new Router(RouterConfig);

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title + ' | DimpleBlog';
    }
    if (to.meta.content) {

    }
    try {
        LoadingBar.start();
    } catch (err) {
    }
    next();
});
router.afterEach((to, from) => {
    try {
        LoadingBar.finish();
    } catch (err) {
    }
});
export default router;
