import request from '@/utils/request'

// 查询博客列表
export function listBlog(query) {
  return request({
    url: '/blog/blog/list',
    method: 'get',
    params: query
  })
}

// 查询博客详细
export function getBlog(id) {
  return request({
    url: '/blog/blog/' + id,
    method: 'get'
  })
}

// 新增博客配置
export function addBlog(data) {
  return request({
    url: '/blog/blog',
    method: 'post',
    data: data
  })
}

// 修改博客配置
export function updateBlog(data) {
  return request({
    url: '/blog/blog',
    method: 'put',
    data: data
  })
}

// 删除博客配置
export function delBlog(id) {
  return request({
    url: '/blog/blog/' + id,
    method: 'delete'
  })
}

// 修改博客推荐
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


// 修改博客评论
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


// 新增博客草稿
export function addBlogDraft(data) {
  return request({
    url: '/blog/blog/draft',
    method: 'post',
    data: data
  })
}

// 修改博客草稿
export function updateBlogDraft(data) {
  return request({
    url: '/blog/blog/draft',
    method: 'put',
    data: data
  })
}
