import request from "@/utils/request";


export default {
  getMessageBoard(query) {
    return request({
      url: "/blog-front/messageBoard/list",
      method: "get",
      params: query
    });
  },

  saveMessageBoard(data) {
    return request({
      url: "/blog-front/messageBoard",
      method: "post",
      data: data
    });
  },

  likeMessageBoard(id) {
    return request({
      url: "/blog-front/messageBoard/likeCount/"+id,
      method: "post",
    });
  }
};
