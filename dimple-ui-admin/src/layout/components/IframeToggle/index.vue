<template>
  <transition-group mode="out-in" name="fade-transform">
    <inner-link
      v-for="(item, index) in iframeViews"
      v-show="$route.path === item.path"
      :key="item.path"
      :iframeId="'iframe' + index"
      :src="item.meta.link"
    ></inner-link>
  </transition-group>
</template>

<script>
import InnerLink from "../InnerLink/index"

export default {
  components: {InnerLink},
  computed: {
    iframeViews() {
      return this.$store.state.tagsView.iframeViews
    }
  }
}
</script>
