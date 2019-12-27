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

//获取访问日志
export function listVisitLog(params) {
  return request({
    url: '/dashboard/visitLog',
    method: 'get',
    params: params
  })
}

//获取操作日志
export function listOperateLog(params) {
  return request({
    url: '/dashboard/operateLog',
    method: 'get',
    params: params
  })
}

//获取任务日志
export function listTaskLog(params) {
  return request({
    url: '/dashboard/taskLog',
    method: 'get',
    params: params
  })
}

//获取登录日志
export function listLoginLog(params) {
  return request({
    url: '/dashboard/loginLog',
    method: 'get',
    params: params
  })
}
