import request from '@/utils/request'

// 查询笔记详细
export function getNote(id) {
  return request({
    url: '/book/note/' + id,
    method: 'get'
  })
}

// 新增笔记配置
export function addNote(data) {
  return request({
    url: '/book/note',
    method: 'post',
    data: data
  })
}

// 修改笔记配置
export function updateNote(data) {
  return request({
    url: '/book/note',
    method: 'put',
    data: data
  })
}

// 删除笔记配置
export function delNote(id) {
  return request({
    url: '/book/note/' + id,
    method: 'delete'
  })
}

// 修改笔记推荐
export function changeNoteSupport(id, support) {
  return request({
    url: '/book/note/support/' + id + "/" + support,
    method: 'put',
  })
}


// 修改笔记评论
export function changeNoteComment(id, comment) {
  return request({
    url: '/book/note/comment/' + id + "/" + comment,
    method: 'put'
  })
}

//获取Blog Tag
export function listNoteTagList(query) {
  return request({
    url: '/book/note/tag/' + query,
    method: 'get',
  })
}


// 新增笔记草稿
export function addNoteDraft(data) {
  return request({
    url: '/book/note/draft',
    method: 'post',
    data: data
  })
}

// 修改笔记草稿
export function updateNoteDraft(data) {
  return request({
    url: '/book/note/draft',
    method: 'put',
    data: data
  })
}
