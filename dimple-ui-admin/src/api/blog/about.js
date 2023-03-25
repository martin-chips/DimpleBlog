import request from '@/utils/request'


export function getAbout(id) {
  return request({
    url: '/blog/about/',
    method: 'get'
  })
}

export function updateAbout(data) {
  return request({
    url: '/blog/about/',
    method: 'put',
    data: data
  })
}

