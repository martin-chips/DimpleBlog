import request from '@/utils/request'

//获取本地存储列表
export function listLocalStorage(query) {
  return request({
    url: '/tool/localStorage/list',
    method: 'get',
    params: query
  })
}

//删除本地存储
export function delLocalStorage(id) {
  return request({
    url: '/tool/localStorage/' + id,
    method: 'delete',
  })
}

//修改本地存储
export function updateLocalStorage(data) {
  return request({
    url: '/tool/localStorage',
    method: 'put',
    data: data
  })
}




