import request from '@/utils/request'


// 修改网站设置
export function updateSiteSetting(data) {
  return request({
    url: '/system/setting/siteSetting',
    method: 'put',
    data: data
  })
}

//修改about
export function updateAbout(data) {
  return request({
    url: '/system/setting/about',
    method: 'put',
    data: data
  })
}
