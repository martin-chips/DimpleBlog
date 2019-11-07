import request from '@/utils/request'

// 查询列表
export function listContent(query) {
  return request({
    url: '/tool/qiNiu',
    method: 'get',
    params: query
  })
}

//删除qi
export function delContent(id) {
  return request({
    url: '/tool/qiNiu/' + id,
    method: 'delete'
  })
}

//下载
export function downloadContent(id) {
  return request({
    url: '/tool/qiNiu/download/' + id,
    method: 'get'
  })
}

//同步
export function syncContent() {
  return request({
    url: '/tool/qiNiu/synchronize',
    method: 'post'
  })
}

//获取配置文件
export function getQiNiuConfig() {
  return request({
    url: '/tool/qiNiu/config',
    method: 'get'
  })
}

//更新配置文件
export function updateQiNiuConfig(data) {
  return request({
    url: '/tool/qiNiu/config',
    method: 'put',
    data: data
  })
}


// listContent, delContent, uploadContent, downloadContent
