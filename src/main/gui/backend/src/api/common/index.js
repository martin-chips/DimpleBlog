import request from '@/utils/request'

// 查询七牛云图片
export function listQiNiuImage(query) {
  return request({
    url: '/tool/qiNiu/list',
    method: 'get',
    params: query
  })
}

// 查询本地图片
export function listLocalImage(query) {
  return request({
    url: '/tool/localStorage/list',
    method: 'get',
    params: query
  })
}
