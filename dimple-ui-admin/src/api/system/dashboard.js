import request from '@/utils/request'

export function getDashboardCount(query) {
  return request({
    url: '/system/dashboard/count',
    method: 'get',
    params: query
  })
}

export function getDashboardCountItem() {
  return request({
    url: '/system/dashboard/count/item',
    method: 'get',
  })
}

export function getDashboardSpider() {
  return request({
    url: '/system/dashboard/spider',
    method: 'get',
  })
}

export function getDashboardReferer() {
  return request({
    url: '/system/dashboard/referer',
    method: 'get',
  })
}
