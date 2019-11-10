import request from '@/utils/request'

//通用的获取表格数据
export function list(url, params) {
  return request({
    url: url + "/list",
    method: 'get',
    params: params
  })
}

//通用的表格单条数据删除
export function del(url, id) {
  return request({
    url: url + "/" + id,
    method: 'delete',
  })
}

//通用数据添加
export function add(url, data) {
  return request({
    url: url,
    method: 'post',
    data: data
  })
}

//通用数据修改
export function update(url, data) {
  return request({
    url: url,
    method: 'put',
    data: data
  })
}

//通用单条数据获取
export function get(url, id) {
  return request({
    url: url + "/" + id,
    method: 'get',
  })
}


