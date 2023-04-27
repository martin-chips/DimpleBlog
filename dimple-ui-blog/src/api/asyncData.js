import request from "@/utils/request";
import {addXNodeHeaders} from "@/utils/dimple";

export default {
  getAsyncMessageBoard(query) {
    return request({
      url: "/blog-front/messageBoard/list",
      method: "get",
      params: query
    })
  },
  getAsyncAbout(req) {
    return request({
      url: '/blog-front/about',
      method: 'get',
      headers: addXNodeHeaders(req)
    })
  },
  getAsyncArchives(req) {
    return request({
      url: '/blog-front/archives',
      method: 'get',
      headers: addXNodeHeaders(req)
    })
  },
  getAsyncArticle(id, req) {
    return request({
      url: '/blog-front/article/' + id,
      method: 'get',
      headers: addXNodeHeaders(req)
    })
  },
  listAsyncComment(query, req) {
    return request({
      url: '/blog-front/comment/list',
      method: 'get',
      params: query,
      headers: addXNodeHeaders(req)
    })
  },
  listAsyncCategory(req) {
    return request({
      url: '/blog-front/category/list',
      method: 'get',
      headers: addXNodeHeaders(req)
    });
  },
  listAsyncArticle(query, req) {
    return request({
      url: '/blog-front/article/list',
      method: 'get',
      params: query,
      headers: addXNodeHeaders(req)
    })
  },
  listAsyncLink(query, req) {
    return request({
      url: '/blog-front/link/list',
      method: 'get',
      params: query,
      headers: addXNodeHeaders(req)
    })
  },
  listAsyncTag(query, req) {
    return request({
      url: '/blog-front/tag/list', method: 'get', params: query,
      headers: addXNodeHeaders(req)
    })
  },
}
