import request from '@/utils/request'

// 查询任务日志列表
export function listQuartzJobLog(query) {
  return request({
    url: '/log/quartzLog/list',
    method: 'get',
    params: query
  })
}
