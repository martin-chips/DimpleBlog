import request from '@/utils/request'

// 查询列表
export function listContent(query) {
  return request({
    url: '/tool/qiNiu/list',
    method: 'get',
    params: query
  })
}

//删除
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


// listContent, delContent, uploadContent, downloadContent
