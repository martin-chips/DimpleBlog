export function siblings(ele) {
  let arr_siblings = []
  let ele_par = ele.parentNode
  let children = ele_par.childNodes
  for (let i = 0; i < children.length; i++) {
    if (children[i].nodeType == 1 && children[i] !== ele) {
      arr_siblings.push(children[i])
    }
  }
  return arr_siblings
}
