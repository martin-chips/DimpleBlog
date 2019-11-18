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




