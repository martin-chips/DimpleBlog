import request from '@/utils/request'

// 查询操作日志列表
export function list(query) {
  return request({
    url: '/log/operateLog/list',
    method: 'get',
    params: query
  })
}
