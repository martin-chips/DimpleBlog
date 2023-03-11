import request from '@/utils/request'

// 查询评论列表
export function listLink(query) {
  return request({
    url: '/blog/link/list',
    method: 'get',
    params: query
  })
}

// 查询评论详细
export function getLink(id) {
  return request({
    url: '/blog/link/' + id,
    method: 'get'
  })
}

// 新增评论
export function addLink(data) {
  return request({
    url: '/blog/link',
    method: 'post',
    data: data
  })
}

// 修改评论
export function updateLink(data) {
  return request({
    url: '/blog/link/' + data.id,
    method: 'put',
    data: data
  })
}

// 删除评论
export function delLink(id) {
  return request({
    url: '/blog/link/' + id,
    method: 'delete'
  })
}
