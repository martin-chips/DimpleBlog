import request from '@/utils/request'

// 查询Blog article列表
export function listArticle(query) {
  return request({
    url: '/blog/article/list',
    method: 'get',
    params: query
  })
}

// 查询Blog article详细
export function getArticle(id) {
  return request({
    url: '/blog/article/' + id,
    method: 'get'
  })
}

// 新增Blog article
export function addArticle(data) {
  return request({
    url: '/blog/article',
    method: 'post',
    data: data
  })
}

// 修改Blog article
export function updateArticle(data) {
  return request({
    url: '/blog/article',
    method: 'put',
    data: data
  })
}

// 删除Blog article
export function delArticle(id) {
  return request({
    url: '/blog/article/' + id,
    method: 'delete'
  })
}
