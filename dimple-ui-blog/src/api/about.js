import request from "@/utils/request";

export default {
  getAbout(req) {
    console.log("Current req===========>" + req)
    return request({
      url: '/blog-front/about',
      method: 'get',
      headers: {
        'x-node-real-ip': getIp(req),
        'x-node-user-agent': getUserAgent(req)
      }
    })
  }
}

const getIp = function (req) {
  if (!req) {
    return "";
  }
  let ip = req.get('X-Real-IP') || req.get('X-Forwarded-For') || req.ip;
  if (ip.split(',').length > 0) {
    ip = ip.split(',')[0]
  }
  return ip
}

const getUserAgent = function (req) {
  if (!req) {
    return "";
  }
  return req.headers['user-agent'];
}
