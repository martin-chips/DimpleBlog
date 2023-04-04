import request from '@/utils/request'

// 查询操作日志列表
export function list(query) {
  return request({
    url: '/log/operlog/list',
    method: 'get',
    params: query
  })
}

// 删除操作日志
export function delOperlog(id) {
  return request({
    url: '/log/operlog/' + id,
    method: 'delete'
  })
}

// 清空操作日志
export function cleanOperlog() {
  return request({
    url: '/log/operlog/clean',
    method: 'delete'
  })
}
