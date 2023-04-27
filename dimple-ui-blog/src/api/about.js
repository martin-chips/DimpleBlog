import request from "@/utils/request";

export default {
  getAbout() {
    return request({
      url: '/blog-front/about',
      method: 'get'
    })
  }
}
