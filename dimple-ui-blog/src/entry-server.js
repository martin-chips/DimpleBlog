import {createApp} from './app'

export default (context) => {
  const { app, router, store } = createApp()
  const meta = app.$meta()
  return new Promise((resolve, reject) => {
    router.push(context.url)
    context.meta = meta
    router.onReady(async () => {
      const matchedComponents = app.$router.getMatchedComponents()
      if (!matchedComponents.length) {
        return reject({ code: 404 })
      }
      // 对所有匹配的路由组件调用 `asyncData()`
      try {
        const componentRes = await Promise.all(
          matchedComponents.map(async (Component) => {
            if (Component.asyncData) {
              const res = await Component.asyncData({
                store,
                route: router.currentRoute,
                isServer: true,
                _ip: context._ip
              })
              return { res, Component }
            }
          })
        )
        const __COMPONENT_ASYNCDATA__ = componentRes.map((eRes) => {
          if (eRes) {
            const { res, Component } = eRes
            // 将路由组件的 asyncData 返回值挂载到组件实例的构造项上
            // 用于 data & asyncData 的合并策略
            Component.__COMPONENT_ASYNCDATA__ = res
            return res || {}
          }
        })

        // 在所有预取钩子(preFetch hook) resolve 后，
        // 我们的 store 现在已经填充入渲染应用程序所需的状态。
        // 当我们将状态附加到上下文，
        // 并且 `template` 选项用于 renderer 时，
        // 状态将自动序列化为 `window.__INITIAL_STATE__`，并注入 HTML。
        context.state = {
          state: store.state,
          __COMPONENT_ASYNCDATA__
        }
        resolve(app)
      } catch (e) {
        console.error(`[entry-server]: async data fetch error -> ${e}`)
        reject(e)
      }
    }, reject)
  })
}
