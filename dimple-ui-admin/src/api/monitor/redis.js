import request from '@/utils/request'

// 获得 Redis 监控信息
export function getRedisMonitorInfo() {
  return request({
    url: '/system/redis/monitorInfo',
    method: 'get'
  })
}

// 获得 Redis Key 模板列表
export function getKeyDefineList() {
  return request({
    url: '/system/redis/keyDefines',
    method: 'get'
  })
}

// 获取键名列表
export function getKeyList(keyTemplate) {
  return request({
    url: '/system/redis/keys',
    method: 'get',
    params: {
      keyTemplate
    }
  })
}

// 获取缓存内容
export function getKeyValue(key) {
  return request({
    url: '/system/redis/key/' + key,
    method: 'get'
  })
}

// 根据键名删除缓存
export function deleteKey(key) {
  return request({
    url: '/system/redis/key/' + key,
    method: 'delete'
  })
}

export function deleteKeys(keyTemplate) {
  return request({
    url: '/system/redis/key/template',
    method: 'delete',
    params: {
      keyTemplate
    }
  })
}
