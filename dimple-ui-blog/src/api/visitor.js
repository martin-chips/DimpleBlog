import request from "@/utils/request";
import {getIp, getReferer, getUserAgent} from "@/utils/dimple";

export const Enum = {
  LIST_ARCHIVES: "LIST_ARCHIVES",
  LIST_ARTICLE: "LIST_ARTICLE",
  GET_ARTICLE: "GET_ARTICLE",
  LIST_CATEGORY: "LIST_CATEGORY",
  LIST_COMMENT: "LIST_COMMENT",
  LIKE_COMMENT: "LIKE_COMMENT",
  LIST_LINK: "LIST_LINK",
  LIST_MESSAGE_BOARD: "LIST_MESSAGE_BOARD",
  LIKE_MESSAGE_BOARD: "LIKE_MESSAGE_BOARD",
  LIST_TAG: "LIST_TAG",
  LIST_ABOUT: "LIST_ABOUT",
};

export default {
  getVisitLoginConfig() {
    return request({
      url: '/auth/visitor/config', method: 'get '
    })
  },
  getVisitorInfo(code) {
    return request({
      url: '/blog-front/visitor/github/code/' + code, method: 'get'
    })
  },
  getGithubClientId() {
    return request({
      url: '/blog-front/visitor/github/clientId/', method: 'get'
    })
  },
  saveVisitLog(title, pageId, requestUri, statusCode, _req) {
    var data = {
      title, pageId, requestUri, referer: getReferer(_req), statusCode,
      ip: getIp(_req),
      userAgent: getUserAgent(_req)
    }
    return request({
      url: '/blog-front/visitor/log', method: 'post',
      data: data
    })
  },
}
