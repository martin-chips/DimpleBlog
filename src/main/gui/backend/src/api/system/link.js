import request from '@/utils/request'

// 查询友链列表
export function listLink(query) {
  return request({
    url: '/system/link/list',
    method: 'get',
    params: query
  })
}

// 查询友链详细
export function getLink(id) {
  return request({
    url: '/system/link/' + id,
    method: 'get'
  })
}

// 新增友链配置
export function addLink(data) {
  return request({
    url: '/system/link',
    method: 'post',
    data: data
  })
}

// 修改友链配置
export function updateLink(data) {
  return request({
    url: '/system/link',
    method: 'put',
    data: data
  })
}

// 删除友链配置
export function delLink(id) {
  return request({
    url: '/system/link/' + id,
    method: 'delete'
  })
}

// 修改友链推荐
export function changeLinkDisplay(id, display) {
  let data = {
    id: id,
    display: display
  };
  updateLink(data);
}

// 通过或者拒绝友链申请
export function changeLinkStatus(id, pass) {

  return request({
    url: '/system/link/pass/' + id + "/" + pass,
    method: 'put',
  })
}

