/**
 * @desc 获取随机色
 * @returns
 * @author justJokee
 */

export function getRandomColor() {
  const r = Math.floor(Math.random() * 255)
  const g = Math.floor(Math.random() * 255)
  const b = Math.floor(Math.random() * 255)
  const rgb = `rgb(${r},${g},${b})`
  return rgb
}
