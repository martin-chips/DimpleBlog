import request from '@/utils/request'

// 查询轮播图列表
export function listCarousel(query) {
  return request({
    url: '/system/carousel/list',
    method: 'get',
    params: query
  })
}

// 查询轮播图详细
export function getCarousel(id) {
  return request({
    url: '/system/carousel/' + id,
    method: 'get'
  })
}

// 新增轮播图配置
export function addCarousel(data) {
  return request({
    url: '/system/carousel',
    method: 'post',
    data: data
  })
}

// 修改轮播图配置
export function updateCarousel(data) {
  return request({
    url: '/system/carousel',
    method: 'put',
    data: data
  })
}

// 删除轮播图配置
export function delCarousel(id) {
  return request({
    url: '/system/carousel/' + id,
    method: 'delete'
  })
}

// 修改轮播图显示
export function changeCarouselDisplay(id, display) {
  let data = {
    id: id,
    display: display
  }
  updateCarousel(data);
}
