import request from '@/utils/request'

export function listFile(query) {
  return request({
    url: '/file/file/list',
    method: 'get',
    params: query
  })
}


export function editFileName(params) {
  return request({
    url: '/file/file/' + params.id + '/name/',
    method: 'put',
    data: {name: params.name}
  })
}
