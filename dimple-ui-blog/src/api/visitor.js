import request from "@/utils/request";

export default {
    getVisitLoginConfig() {
        return request({
            url: '/auth/visitor/config', method: 'get '
        })
    },
    getVisitorInfo(code) {
        return request({
            url: '/blog-front/visitor/github/code/' + code, method: 'get'
        })
    },
    getGithubClientId() {
        return request({
            url: '/blog-front/visitor/github/clientId/', method: 'get'
        })
    },
}