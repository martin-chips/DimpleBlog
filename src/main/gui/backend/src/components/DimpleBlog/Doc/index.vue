<template>
  <div>
    <svg-icon icon-class="skill" @click="goto"/>
  </div>
</template>

<script>
  export default {
    name: 'DimpleBlogDoc',
    data() {
      return {
        url: 'https://bianxiaofeng.com/article/84'
      }
    },
    methods: {
      goto() {
        window.open(this.url)
      }
    }
  }
</script>
