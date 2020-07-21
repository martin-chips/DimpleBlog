import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/bgBlog',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/bgBlog/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/bgBlog',
    method: 'put',
    data
  })
}

export default { add, edit, del }
