<template>
  <div id="app" :class="{ grayedOut: mask }">
    <router-view/>
    <theme-picker/>
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";

export default {
  name: "App",
  data() {
    return {
      mask: false,
    };
  },
  created() {
    this.getMarkConfig()
  },
  components: {ThemePicker},
  methods:{
    async getMarkConfig() {
      var res = await this.getConfigKey("sys.index.mask");
      if (res.code == 200) {
        this.mask = res.msg == 'false' ? false : true;
      }
    }
  },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: title => {
        return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
      }
    }
  }
};
</script>
<style scoped>
#app .theme-picker {
  display: none;
}
.grayedOut {
  filter: grayscale(100%);
}
</style>
