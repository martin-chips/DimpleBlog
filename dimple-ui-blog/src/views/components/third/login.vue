<template>
  <div>
    <h3>数据传输中，请稍后...</h3>
  </div>
</template>
<script>
import api from "@/api/visitor";

export default {
  name: "thirdPartLogin",
  mounted() {
    this.getVisitInfo();
  },

  methods: {
    getVisitInfo() {
      console.log(window.location.href)
      if (window.location.href.indexOf("code=") != -1) {
        var code = window.location.href.substr(window.location.href.indexOf("code=") + 5);
        api.getVisitorInfo(code).then(response => {
          if (response.code == 200) {
            window.opener.postMessage(
              {
                type: 'github',
                data: response.data
              },
              '*'
            )
          }
          window.close();
        })
      }
    },
  }
};
</script>
<style lang="scss">
</style>
