import request from '@/utils/request'

// 获取分类
export function listCategory(params) {
  return request({
    url: '/book/category/list',
    method: 'get',
    params: params
  })
}
