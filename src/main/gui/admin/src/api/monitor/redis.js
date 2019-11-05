import request from '@/utils/request'

// 查询Redis Info列表
export function listRedisInfoList(query) {
  return request({
    url: '/monitor/redis/list',
    method: 'get',
    params: query
  })
}
