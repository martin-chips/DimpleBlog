<template>
  <div class="rating">
    <i :class="star.icon" v-for="(star, index) in crating" :key="index"></i>
  </div>
</template>
<script>
export default {
  name: 'rating',
  props: {
    rating: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {}
  },
  computed: {
    crating() {
      this.rating
      const arr = []
      for (let i = 0; i < 5; i++) arr.push({ icon: 'el-icon-star-off' })
      arr.some((item, index) => {
        if (index + 1 <= this.rating) {
          item.icon = 'el-icon-star-on'
        } else return true
      })
      return arr
    }
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.rating {
  .el-icon-star-on {
    font-size: 17px;
    transform: translateY(0.5px);
    color: rgb(236, 169, 68);
  }
}
</style>
