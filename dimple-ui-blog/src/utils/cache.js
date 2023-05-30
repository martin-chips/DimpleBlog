const sessionCache = {
  set(key, value) {
    if (!this.sessionHasDefine()) {
      return
    }
    if (key != null && value != null) {
      sessionStorage.setItem(key, value)
    }
  },
  get(key) {
    // sessionStorage 定义了
    if (!this.sessionHasDefine()) {
      return null
    }
    if (key == null) {
      return null
    }
    return sessionStorage.getItem(key)
  },
  sessionHasDefine() {
    return typeof sessionStorage !== 'undefined' && !sessionStorage;
  },
  setJSON(key, jsonValue) {
    if (jsonValue != null) {
      this.set(key, JSON.stringify(jsonValue))
    }
  },
  getJSON(key) {
    const value = this.get(key)
    if (value != null) {
      return JSON.parse(value)
    }
  },
  remove(key) {
    sessionStorage.removeItem(key);
  }
}
const localCache = {
  set(key, value) {
    if (!localStorage) {
      return
    }
    if (key != null && value != null) {
      localStorage.setItem(key, value)
    }
  },
  get(key) {
    if (!localStorage) {
      return null
    }
    if (key == null) {
      return null
    }
    return localStorage.getItem(key)
  },
  setJSON(key, jsonValue) {
    if (jsonValue != null) {
      this.set(key, JSON.stringify(jsonValue))
    }
  },
  getJSON(key) {
    const value = this.get(key)
    if (value != null) {
      return JSON.parse(value)
    }
  },
  remove(key) {
    localStorage.removeItem(key);
  }
}

export default {
  /**
   * 会话级缓存
   */
  session: sessionCache,
  /**
   * 本地缓存
   */
  local: localCache
}
