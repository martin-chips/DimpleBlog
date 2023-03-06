import request from "@/utils/request";


export default {
  getMessageBoard(query) {
    return request({
      url: "/blog-front/comment/list",
      method: "get",
      params: query
    });
  },

  saveMessageBoard(data) {
    return request({
      url: "/blog-front/comment",
      method: "post",
      data: data
    });
  },

  likeMessageBoard(id) {
    return request({
      url: "/blog-front/comment/likeCount/"+id,
      method: "post",
    });
  }
};
