import {getScrollTop} from '@/utils/getScrollTop'

export default function scrollTo(pos = 0, duration = 250) {
  // 计算需要滚动的距离
  const distance = pos - getScrollTop()
  let scrollY = getScrollTop()
  let oldTimestamp = null
  if (distance == 0) return

  function step(newTimestamp) {
    if (oldTimestamp !== null) {
      // 上移
      if (distance < 0) {
        scrollY -= (Math.abs(distance) * (newTimestamp - oldTimestamp)) / duration
        if (scrollY <= pos) return (document.scrollingElement.scrollTop = pos)
      }
      // 下移
      else {
        scrollY += (Math.abs(distance) * (newTimestamp - oldTimestamp)) / duration
        if (scrollY >= pos) return (document.scrollingElement.scrollTop = pos)
      }
      document.scrollingElement.scrollTop = scrollY
    }
    oldTimestamp = newTimestamp
    window.requestAnimationFrame(step)
  }
  window.requestAnimationFrame(step)
}
