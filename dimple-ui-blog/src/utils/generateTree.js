export function generateTree(flatList, options = { level: 'level' }) {
  const result = []
  let stack = []
  flatList.forEach((item) => {
    // 栈为空，则直接入栈
    if (!stack.length) {
      item.children = []
      stack.push(item)
      result.push(item)
    } else {
      // 当前元素级别大于栈底元素，则倒序遍历栈，找到其父级并挂载
      if (item[options.level] > stack[0][options.level]) {
        stack.reverse().some((el) => {
          if (el[options.level] < item[options.level]) {
            if (!el.children) el.children = []
            el.children.push(item)
            return true
          }
        })
        stack.reverse()
        stack.push(item)
      }
      // 完成子树收集
      else {
        // 清空栈
        stack = []
        item.children = []
        stack.push(item)
        result.push(item)
      }
    }
  })
  return result
}
