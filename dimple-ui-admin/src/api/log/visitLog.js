import request from '@/utils/request'

// 查询访问日志列表
export function list(query) {
  return request({
    url: '/log/visitlog/list',
    method: 'get',
    params: query
  })
}

// 删除访问日志
export function delVisitLog(operId) {
  return request({
    url: '/log/visitlog/' + operId,
    method: 'delete'
  })
}

// 清空访问日志
export function cleanVisitLog() {
  return request({
    url: '/log/visitlog/clean',
    method: 'delete'
  })
}
