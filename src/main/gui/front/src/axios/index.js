import axios from 'axios'
import {Notice} from 'view-design'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';
// 创建axios实例
const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: process.env.VUE_APP_BASE_API,
    // 超时
    timeout: 120000
});
// request拦截器
service.interceptors.request.use(
    config => {
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
            Notice.error({
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
                Notice.error({
                    title: '网络请求超时',
                    duration: 2500
                });
                return Promise.reject(error)
            }
            if (error.toString().indexOf('Error: Network Error') !== -1) {
                Notice.error({
                    title: '网络请求错误',
                    duration: 2500
                });
                return Promise.reject(error)
            }
        }
        return Promise.reject(error)
    }
);

export default service
