// 访客信息key
const visitorKey = 'v_k_i'
const themeKey = 'global_theme'

export const storage = {
  getVisitor() {
    if (localStorage.getItem(visitorKey)) {
      return JSON.parse(localStorage.getItem(visitorKey))
    }
  },
  setVisitor(info) {
    localStorage.setItem(visitorKey, JSON.stringify(info))
  },
  removeVisitor() {
    localStorage.removeItem(visitorKey)
  },
  getTheme() {
    return localStorage.getItem(themeKey);
  },
  setTheme(theme) {
    localStorage.setItem(themeKey, theme)
  },
  removeTheme() {
    localStorage.removeItem(themeKey)
  },
}
