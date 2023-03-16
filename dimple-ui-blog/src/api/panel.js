import request from "@/utils/request";

export default {
    listPanelArchive(query) {
        return request({
            url: '/blog-front/panel/archives',
            method: 'get',
            params: query
        })
    },
    listPanelCategory(query) {
        return request({
            url: '/blog-front/panel/category',
            method: 'get',
            params: query
        })
    },
    listPanelTag(query) {
        return request({
            url: '/blog-front/panel/tag',
            method: 'get',
            params: query
        })
    },
    listPanelComment(query) {
        return request({
            url: '/blog-front/panel/comment',
            method: 'get',
            params: query
        })
    },
    listPanelArticle(query) {
        return request({
            url: '/blog-front/panel/article',
            method: 'get',
            params: query
        })
    },
}
