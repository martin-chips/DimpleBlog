import request from "@/utils/request";

export default {
    listTag(query) {
        return request({
            url: '/blog-front/tag/list', method: 'get', params: query
        })
    },

// 查询标签详细
    getTag(id) {
        return request({
            url: '/blog-front/tag/' + id, method: 'get'
        })
    }
}