import request from "@/utils/request";


export default {
    listCategory(query) {
        return request({
            url: '/blog-front/category/list',
            method: 'get',
            params: query
        });
    },
    getCategory(id) {
        return request({
            url: '/blog-front/category/' + id,
            method: 'get'
        })
    }
}
