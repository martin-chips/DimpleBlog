import request from "@/utils/request";

export default {
    // archive page used
    getArchives(query) {
        return request({
            url: '/blog-front/archives',
            method: 'get',
            params: query
        })
    }
}
