import request from '@/utils/request'

// 查询标签列表
export function listTag(query) {
  return request({
    url: '/blog/tag/list',
    method: 'get',
    params: query
  })
}

// 查询标签详细
export function getTag(id) {
  return request({
    url: '/blog/blog/' + id,
    method: 'get'
  })
}

// 新增标签配置
export function addTag(data) {
  return request({
    url: '/blog/blog',
    method: 'post',
    data: data
  })
}

// 修改标签配置
export function updateTag(data) {
  return request({
    url: '/blog/blog',
    method: 'put',
    data: data
  })
}

// 删除标签配置
export function delTag(id) {
  return request({
    url: '/blog/blog/' + id,
    method: 'delete'
  })
}

// 修改标签推荐
export function changeBlogSupport(id, support) {
  let data = {
    id: id,
    support: support
  };
  return request({
    url: '/blog/blog/support',
    method: 'put',
    data: data
  })
}


// 修改标签评论
export function changeBlogComment(id, comment) {
  let data = {
    id: id,
    comment: comment
  };
  return request({
    url: '/blog/blog/comment',
    method: 'put',
    data: data
  })
}

//获取Blog Tag
export function listBlogTagList(query) {
  return request({
    url: '/blog/blog/tag/' + query,
    method: 'get',
  })
}


// 新增标签草稿
export function addBlogDraft(data) {
  return request({
    url: '/blog/blog/draft',
    method: 'post',
    data: data
  })
}

// 修改标签草稿
export function updateBlogDraft(data) {
  return request({
    url: '/blog/blog/draft',
    method: 'put',
    data: data
  })
}
