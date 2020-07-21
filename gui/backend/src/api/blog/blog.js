import request from '@/utils/request'

export function get(data) {
  return request({
    url: 'api/blog',
    method: 'post',
    data
  })
}
