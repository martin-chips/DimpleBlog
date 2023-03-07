import request from "@/utils/request";

export default {
    listComment(query) {
        return request({
            url: '/blog-front/comment/list',
            method: 'get',
            params: query
        })
    },
    listLatestComment(query) {
        return request({
            url: '/blog-front/comment/list',
            method: 'get',
            params: query
        })
    },
    likeArticleComment(id){
        return request({
            url: "/blog-front/comment/likeCount/"+id,
            method: "post",
        });
    },

    getComment(id) {
        return request({
            url: '/blog-front/comment/' + id,
            method: 'get'
        })
    },
    addComment(data) {
        return request({
            url: '/blog-front/comment',
            method: 'post',
            data: data
        })
    }
}
