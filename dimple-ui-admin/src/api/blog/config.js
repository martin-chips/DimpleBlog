import request from '@/utils/request'


export function getConfig() {
  return request({
    url: '/blog/config/',
    method: 'get'
  })
}

export function updateConfig(data) {
  return request({
    url: '/blog/config/',
    method: 'put',
    data: data
  })
}

export function deleteCache() {
  return request({
    url: '/blog/config/',
    method: 'delete',
  })
}
