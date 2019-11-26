import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect           // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    roles: ['admin','editor']    // 设置该路由进入的权限，支持多个权限叠加
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             // 设置该路由的图标，对应路径src/icons/svg
    breadcrumb: false            // 如果设置为false，则不会在breadcrumb面包屑中显示
  }
 */

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error/401'),
    hidden: true
  },
  {
    path: '/index',
    component: Layout,
    children: [
      {
        path: '',
        component: () => import('@/views/index'),
        name: '首页',
        meta: {title: '首页', icon: 'dashboard', noCache: true, affix: true}
      },
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noRedirect',
    children: [
      {
        path: 'profile',
        component: () => import('@/views/profile/index'),
        name: '个人中心',
        meta: {title: '个人中心', icon: 'user'}
      }
    ]
  },
  {
    path: '/dict',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'type/data/:dictId(\\d+)',
        component: () => import('@/views/system/dict/data'),
        name: '字典数据',
        meta: {title: '字典数据', icon: ''}
      }
    ]
  },
  {
    path: '/blog',
    component: Layout,
    hidden: true,
    redirect: 'noRedirect',
    children: [
      {
        path: 'blog/add',
        component: () => import('@/views/blog/blog/add'),
        name: '新增博文',
        meta: {title: '新增文章', noCache: true, activeMenu: '/blog/blog'},
      }, {
        path: 'blog/edit/:id(\\d+)',
        component: () => import('@/views/blog/blog/edit'),
        name: '修改博文',
        meta: {title: '修改文章', activeMenu: '/blog/blog'},
      }
    ]
  }
]

import SimpleHeader from "../front/components/header/SimpleHeader/SimpleHeader";
import footer from "../front/components/footer/footer";

//组件
import HomeContent from "../front/components/content/HomeContent";
import TimeLineContent from "../front/components/content/TimeLineContent";
import ArticleHomeContent from "../front/components/content/ArticleHomeContent";

//前台路由
export const frontRoutes = [
  {
    path: '/',
    component: () => import('@/front/components/index'),
    children: [
      {
        path: '',
        components: {
          header: SimpleHeader,
          content: HomeContent,
          footer: footer
        },
      },
      {
        path: '/articles',
        name: 'articles',
        components: {
          header: SimpleHeader,
          content: ArticleHomeContent,
          footer: footer
        },
      },
      {
        path: '/articles/category/:id',
        name: 'articles/category',
        components: {
          header: SimpleHeader,
          content: ArticleHomeContent,
          footer: footer
        },
        meta: {
          title: '文章列表',
        }
      },
      {
        path: '/link',
        component: () => import('@/views/front/link'),
      },
      {
        path: '/leaveComment',
        component: () => import('@/views/front/leaveComment'),
      },
      {
        path: '/about',
        component: () => import('@/views/front/about'),
      },
      {
        path: '/archive',
        components: {
          header: SimpleHeader,
          content: TimeLineContent,
          footer: footer
        },
      },
    ]
  },
]

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({y: 0}),
  routes: frontRoutes.concat(constantRoutes)
})
