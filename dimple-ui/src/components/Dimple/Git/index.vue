<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'DimpleGit',
  data() {
    return {
      url: 'https://github.com/martin-chips/DimpleBlog'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
