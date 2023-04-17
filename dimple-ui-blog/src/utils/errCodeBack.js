import {Message} from 'element-ui'

export function errorCode(code) {
  switch (code) {
    case 200:
      break
    case 100:
      _message('warning', '昵称已存在')
      break
    case 101:
      _message('warning', '您已经点过赞了 ~')
      break
    case 102:
      _message('warning', '文章不存在或已删除 ~')
      break
    case 429:
      _message('warning', '访问次数超限，请稍后再试 ~')
      break
  }
}

function _message(t, m) {
  Message({
    type: t,
    message: m,
    center: true
  })
}
