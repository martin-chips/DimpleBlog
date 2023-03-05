/**
 * @desc 获取search & hash路由上的参数
 * @param {*} urlStr
 * @param {*} field
 * @returns string | Object
 * @author Justjokee
 */
export function getUrlParams(urlStr, field) {
  let searchParams = {}
  let hashParams = {}
  if (window.location.hash) hashParams = this.getSearchParams(window.location.hash)
  searchParams = getSearchParams(urlStr)
  if (field) return Object.assign(hashParams, searchParams)[field]
  return Object.assign(hashParams, searchParams)
}

function getSearchParams(urlStr) {
  let search = urlStr || document.location.search
  search = search.split('?')[1]
  let params = {}
  if (search) {
    if (search.lastIndexOf('/') == search.length - 1) search = search.substr(0, search.length - 1)
    let eps = search.split('&')
    eps.forEach((ep) => {
      let k_v = ep.split('=')
      params[k_v[0]] = k_v[1]
    })
  }
  return params
}
