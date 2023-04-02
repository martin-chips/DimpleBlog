import request from '@/utils/request'

// 查询调度日志列表
export function listJobLog(query) {
  return request({
    url: '/schedule/job/log/list',
    method: 'get',
    params: query
  })
}

// 删除调度日志
export function delJobLog(id) {
  return request({
    url: '/schedule/job/log/' + id,
    method: 'delete'
  })
}

// 清空调度日志
export function cleanJobLog() {
  return request({
    url: '/schedule/job/log/clean',
    method: 'delete'
  })
}
