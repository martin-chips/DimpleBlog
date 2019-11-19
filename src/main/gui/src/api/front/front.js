import request from '@/utils/request'

// 获取网站信息
export function getSiteInfo(query) {
  console.log('1111111111111111111111')
  return request({
    url: '/f/siteInfo',
    method: 'get',
    params: query
  })
}

//获取所有分类
export function listCategories() {
  return request({
    url: '/f/categories',
    method: 'get',
  })
}

//获取所有的菜单
export function listMenus() {
  return request({
    url: '/f/menus',
    method: 'get',
  })
}

//获取网站信息
export function getBloggerInfo() {
  return request({
    url: '/f/bloggerInfo',
    method: 'get',
  })
}

//获取推荐内容
export function listRecommend() {
  return request({
    url: '/f/support',
    method: 'get',
  })
}

//获取最热内容
export function listHot() {
  return request({
    url: '/f/hot',
    method: 'get',
  })
}

//获取友链
export function listFriendLinks() {
  return request({
    url: '/f/link',
    method: 'get',
  })
}

//获取标签
export function listTag() {
  return request({
    url: '/f/tag',
    method: 'get',
  })
}

// 查询博客列表
export function listBlog(query) {
  return request({
    url: '/f/blog',
    method: 'get',
    params: query
  })
}



