import request from '@/utils/request'

// 查询分类列表
export function listFile(query) {
  return request({
    url: '/file/file/list',
    method: 'get',
    params: query
  })
}
