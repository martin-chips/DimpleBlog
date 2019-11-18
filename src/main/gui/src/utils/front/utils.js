import AES from 'crypto-js/aes';

/**
 * 按社交方式格式化时间
 * @param formatDate
 * @returns {string}
 */
export function socialDateFormat(formatDate) {
  let timestamp = Date.parse(new Date(formatDate));
  // 获取时间戳
  let time = new Date().getTime();
  // 去掉时间戳后三位，保持一致
  time = parseInt((time - timestamp) / 1000);

  // 存储转换值
  let s;
  if (time < 60 * 10) {
    // 十分钟内
    return '刚刚';
  } else if ((time < 60 * 60) && (time >= 60 * 10)) {
    // 超过十分钟少于1小时
    s = Math.floor(time / 60);
    return s + '分钟前';
  } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
    // 超过1小时少于24小时
    s = Math.floor(time / 60 / 60);
    return s + '小时前';
  } else if ((time < 60 * 60 * 24 * 3) && (time >= 60 * 60 * 24)) {
    // 超过1天少于3天内
    s = Math.floor(time / 60 / 60 / 24);
    return s + '天前';
  } else {
    // 超过3天
    let date = new Date(parseInt(timestamp));
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    month = month >= 10 ? month : '0' + month; // 不满10前面加0
    let day = date.getDate();
    day = day >= 10 ? day : '0' + day; // 不满10前面加0
    return year + '-' + month + '-' + day;
  }
}

/**
 * LocalStorage的操作
 * classify 分类
 * key 存储键
 * value 存储值
 * */
export function saveToLocal(classify, key, value) {
  try {
    let blog = window.localStorage.__blog__;
    if (!blog) {
      blog = {};
      blog[classify] = {};
    } else {
      blog = JSON.parse(AES.decrypt(blog, 'local storage').toString(ENCUTF8));
      if (!blog[classify]) {
        blog[classify] = {};
      }
    }
    blog[classify][key] = value;
    window.localStorage.__blog__ = AES.encrypt(JSON.stringify(blog), 'local storage').toString();
  } catch (exception) {
    console.log(exception);
  }
}

/**
 * 从LocalStorage中取
 * @param classify 分类
 * @param key 存储键
 * @param def 存储值
 * @returns {*} 获取的值
 */
export function loadFromLocal(classify, key, def) {
  try {
    let blog = window.localStorage.__blog__;
    if (!blog) {
      return def;
    }
    blog = AES.decrypt(blog, 'local storage').toString(ENCUTF8);
    blog = JSON.parse(blog)[classify];
    if (!blog) {
      return def;
    }
    let ret = blog[key];
    return ret || def;
  } catch (exception) {
    console.log(exception);
    return def;
  }
}

/**
 * 强制刷新LocalStorage，防止由于代码修改，用户遗留的本地配置无法正确读取
 * @param forceRefresh 是否强制刷新
 * @param refreshTime 上次刷新时间
 */
export function refreshLocal(forceRefresh, refreshTime) {
  if (forceRefresh === null || forceRefresh === undefined || !forceRefresh ||
    refreshTime === null || refreshTime === undefined) {
    return false;
  }
  // 判断时间
  refreshTime = Date.parse(new Date(refreshTime));
  let latestRefreshTime = loadFromLocal('siteConfig', 'refreshTime', undefined);
  if (refreshTime > latestRefreshTime || latestRefreshTime === undefined) {
    // 需要刷新
    try {
      // 清空，并只存储上次刷新时间
      let blog = {
        siteConfig: {
          refreshTime: refreshTime
        }
      };
      window.localStorage.__blog__ = AES.encrypt(JSON.stringify(blog), 'local storage').toString();
      return true;
    } catch (exception) {
      console.log(exception);
      return false;
    }
  }
}

// scrollTop animation
export function scrollTop(el, from = 0, to, duration = 500) {
  if (!window.requestAnimationFrame) {
    window.requestAnimationFrame = (
      window.webkitRequestAnimationFrame ||
      window.mozRequestAnimationFrame ||
      window.msRequestAnimationFrame ||
      function (callback) {
        return window.setTimeout(callback, 1000 / 60);
      }
    );
  }
  const difference = Math.abs(from - to);
  const step = Math.ceil(difference / duration * 50);

  function scroll(start, end, step) {
    if (start === end) return;
    let d = (start + step > end) ? end : start + step;
    if (start > end) {
      d = (start - step < end) ? end : start - step;
    }
    if (el === window) {
      window.scrollTo(d, d);
    } else {
      el.scrollTop = d;
    }
    window.requestAnimationFrame(() => scroll(d, end, step));
  }

  scroll(from, to, step);
}

