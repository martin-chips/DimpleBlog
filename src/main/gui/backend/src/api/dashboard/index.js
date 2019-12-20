import request from '@/utils/request'

export function listPanelGroup() {
  return request({
    url: '/dashboard/panelGroup',
    method: 'get',
  })
}

//获取折线图数据
export function listLineChartData(type) {
  return request({
    url: '/dashboard/lineChartData/' + type,
    method: 'get',
  })
}

//获取爬虫访问数据
export function listSpiderData() {
  return request({
    url: '/dashboard/spiderData',
    method: 'get',
  })
}
