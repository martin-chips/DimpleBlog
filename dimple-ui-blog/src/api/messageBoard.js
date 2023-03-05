import request from "@/utils/request";


export default {
  getMessageBoard(query) {
    return request({
      url: "/blog-front/category/list",
      method: "get",
      params: query
    });
  },

  likeMessageBoard(query) {
    return request({
      url: "/blog-front/category/list",
      method: "get",
      params: query
    });
  }
};
