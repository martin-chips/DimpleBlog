import request from "@/utils/request";

export default {
    listLink(query) {
        return request({
            url: '/blog-front/link/list',
            method: 'get',
            params: query
        })
    },
    addLink(data) {
        return request({
            url: '/blog-front/link',
            method: 'post',
            data: data
        })
    },
    addLinkVisitCount(id) {
        return request({
            url: '/blog-front/link/'+id+"/visitCount",
            method: 'put',
        })
    }
}
