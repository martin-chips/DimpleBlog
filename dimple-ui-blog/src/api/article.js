import request from "@/utils/request";

export default {
    listArticle(query) {
        return request({
            url: '/blog-front/article/list',
            method: 'get',
            params: query
        })
    },
    listLatestArticle(query) {
        return request({
            url: '/blog-front/article/list',
            method: 'get',
            params: query
        })
    },
    getPrevNextArticle(query) {
        return request({
            url: '/blog-front/article/prevNext',
            method: 'get',
            params: query
        })
    },
    getArticle(id) {
        return request({
            url: '/blog-front/article/' + id,
            method: 'get'
        })
    },
    likeArticle(id) {
        return request({
            url: '/blog-front/article/' + id+"/like",
            method: 'put'
        })
    },
    searchArticle(query) {
        return request({
            url: '/blog-front/article/list',
            method: 'get',
            params: query
        })
    }
}
