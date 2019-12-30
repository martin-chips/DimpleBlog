import router from '@/router/routers'
import store from '@/store'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import {getToken} from '@/utils/auth'
import config from '@/config'

NProgress.configure({showSpinner: false});

const whiteList = [
  '/login', '/auth-redirect', '/bind', '/register', '/',
];

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title + ' - ' + config.title
  }
  NProgress.start();
  if (getToken()) {
    /* has token*/
    if (to.path === '/login') {
      next({path: '/index'});
      NProgress.done()
    } else {
      if (store.getters.roles.length === 0) {
        // 判断当前用户是否已拉取完user_info信息
        store.dispatch('GetInfo').then(res => {
          // 拉取user_info
          const roles = res.roles;
          store.dispatch('GenerateRoutes', {roles}).then(accessRoutes => {
            accessRoutes.push({path: '*', redirect: '/404', hidden: true});
            router.addRoutes(accessRoutes); // 动态添加可访问路由表
            next({...to, replace: true}) // hack方法 确保addRoutes已完成
          })
        }).catch(err => {
          store.dispatch('FedLogOut').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        })
      } else {
        next()
      }
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    }  else {
      next(`/login?redirect=${to.path}`); // 否则全部重定向到登录页
      NProgress.done();
    }
  }
});

router.afterEach(() => {
  NProgress.done()
});
