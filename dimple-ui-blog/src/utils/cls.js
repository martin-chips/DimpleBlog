export function cls(cls) {
  if (document.getElementsByClassName) {
    return document.getElementsByClassName(cls)[0]
  } else {
    let arr = document.getElementsByTagName('*')
    let tempArr = []
    for (let i = 0; i < arr.length; i++) {
      let clsArr = arr[i].className.split(' ')
      for (let k = 0; k < clsArr.length; k++) {
        if (clsArr[k] === cls) {
          tempArr.push(arr[i])
        }
      }
    }
    return tempArr[0]
  }
}
