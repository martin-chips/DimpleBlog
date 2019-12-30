import axios from 'axios'
import {Notification, MessageBox} from 'element-ui'
import store from '@/store'
import {getToken} from '@/utils/auth'
import config from '@/config'
import router from '@/router'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: process.env.VUE_APP_BASE_API,
  // 超时
  timeout: config.timeout
});
// request拦截器
service.interceptors.request.use(
  config => {
    if (getToken()) {
      config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
  },
  error => {
    console.log(error);
    Promise.reject(error)
  }
);

// 响应拦截器
service.interceptors.response.use(response => {
    const code = response.data.code;
    if (code < 200 || code > 300) {
      Notification.error({
        title: response.data.msg
      });
      return Promise.reject('error')
    } else {
      return response.data
    }
  }, error => {
    let code = 0;
    try {
      code = error.response.data.code
    } catch (e) {
      if (error.toString().indexOf('Error: timeout') !== -1) {
        Notification.error({
          title: '网络请求超时',
          duration: 2500
        });
        return Promise.reject(error)
      }
      if (error.toString().indexOf('Error: Network Error') !== -1) {
        Notification.error({
          title: '网络请求错误',
          duration: 2500
        });
        return Promise.reject(error)
      }
    }
    if (code === 401) {
      MessageBox.confirm(
        '登录状态已过期，您可以继续留在该页面，或者重新登录',
        '系统提示',
        {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        store.dispatch('LogOut').then(() => {
          location.reload() // 为了重新实例化vue-router对象 避免bug
        })
      })
    } else if (code === 403) {
      router.push({path: '/401'})
    } else {
      const errorMsg = error.response.data.msg;
      if (errorMsg !== undefined) {
        Notification.error({
          title: errorMsg,
          duration: 3000
        })
      }
    }
    return Promise.reject(error)
  }
);

export default service
