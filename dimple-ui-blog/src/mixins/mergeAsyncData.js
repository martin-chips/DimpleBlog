export default {
  created() {
    // client 端做同步
    if (
      !this.$isServer &&
      window.__INITIAL_STATE__ &&
      window.__INITIAL_STATE__.__COMPONENT_ASYNCDATA__ &&
      this.$router &&
      this.$router.getMatchedComponents().length
    ) {
      const matcheComps = this.$router.getMatchedComponents()
      matcheComps.forEach((ec, i) => (ec.__COMPONENT_ASYNCDATA__ = window.__INITIAL_STATE__.__COMPONENT_ASYNCDATA__[i]))
      window.__INITIAL_STATE__.__COMPONENT_ASYNCDATA__ = null
    }
    // 保证client端在开发模式下的热更新正常
    if (!this.$isServer) HMR(this)
    // server 端合并策略 & client 端的首次合并 & client 端接管后的合并
    if (this.constructor && this.constructor.extendOptions && this.constructor.extendOptions.__COMPONENT_ASYNCDATA__) {
      const data = this.constructor.extendOptions.__COMPONENT_ASYNCDATA__
      Object.assign(this.$data, data)
      this.constructor.extendOptions.__COMPONENT_ASYNCDATA__ = null
      if (process.env.NODE_ENV === 'development') console.log('[data merge]: merge asyncData success')
    }
  }
}

async function HMR(vm) {
  if (
    process.env.NODE_ENV === 'development' &&
    vm.constructor &&
    vm.constructor.extendOptions &&
    vm.constructor.extendOptions.asyncData &&
    !vm.constructor.extendOptions.__COMPONENT_ASYNCDATA__
  ) {
    const matched = vm.$router.getMatchedComponents()
    await Promise.all(
      matched.map(async (Component) => {
        if (Component.asyncData) {
          const res = await Component.asyncData({ store: vm.$store, route: vm.$route })
          Object.assign(vm.$data, res)
        }
      })
    )
  }
  return
}
