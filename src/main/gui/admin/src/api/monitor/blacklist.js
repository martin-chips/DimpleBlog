import request from '@/utils/request'

// 查询黑名单列表
export function listBlacklist(query) {
  return request({
    url: '/monitor/blacklist/list',
    method: 'get',
    params: query
  })
}

// 查询黑名单详细
export function getBlacklist(id) {
  return request({
    url: '/monitor/blacklist/' + id,
    method: 'get'
  })
}

// 新增黑名单
export function addBlacklist(data) {
  return request({
    url: '/monitor/blacklist',
    method: 'post',
    data: data
  })
}

// 修改黑名单
export function updateBlacklist(data) {
  return request({
    url: '/monitor/blacklist',
    method: 'put',
    data: data
  })
}

// 删除黑名单
export function delBlacklist(id) {
  return request({
    url: '/monitor/blacklist/' + id,
    method: 'delete'
  })
}
