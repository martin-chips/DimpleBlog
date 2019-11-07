import request from '@/utils/request'

// 查询列表
export function listQuartzJob(query) {
  return request({
    url: '/tool/quartz/list',
    method: 'get',
    params: query
  })
}

//删除
export function delQuartzJob(id) {
  return request({
    url: '/tool/quartz/' + id,
    method: 'delete'
  })
}


//获取任务信息
export function getQuartzJob(id) {
  return request({
    url: '/tool/quartz/' + id,
    method: 'get'
  })
}

//更新任务信息
export function updateQuartzJob(data) {
  return request({
    url: '/tool/quartz',
    method: 'put',
    data: data
  })
}

//添加任务
export function addQuartzJob(data) {
  return request({
    url: '/tool/quartz',
    method: 'post',
    data: data
  })
}

//执行任务
export function executeJob(id) {
  return request({
    url: '/tool/quartz/exe/' + id,
    method: 'put',
  })
}

//更新任务状态
export function updateQuartzStatus(id) {
  return request({
    url: '/tool/quartz/status/' + id,
    method: 'put',
  })
}

