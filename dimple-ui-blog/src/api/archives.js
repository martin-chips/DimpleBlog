import request from "@/utils/request";

export default {
    listArchive(query) {
        return request({
            url: '/blog-front/archives/list',
            method: 'get',
            params: query
        })
    },
    // archive page used
    getArchives(query) {
        return request({
            url: '/blog-front/archives',
            method: 'get',
            params: query
        })
    }
}
