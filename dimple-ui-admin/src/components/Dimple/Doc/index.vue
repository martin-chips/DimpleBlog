<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'DimpleDoc',
  data() {
    return {
      url: 'http://doc.bianxf.com'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
