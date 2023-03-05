<template>
  <div class="tags-iterator">
    <div class="tags-iterator__content" v-if="tags.length">
      <span
        class="tags-iterator__item"
        v-for="(tag, index) in tags"
        :key="index"
        :style="{ color: color(), fontSize: fontSize(), padding: tagPadding }"
        @click="filterArticles(tag.id)"
      >
        {{ tag.title }}
      </span>
    </div>
    <empty v-else></empty>
  </div>
</template>
<script>
import { getRandomColor } from "@/utils/getRandomColor";

export default {
  name: 'tagsIterator',
  data() {
    return {}
  },
  props: {
    tags: {
      type: Array,
      default() {
        return []
      }
    },
    size: {
      type: String,
      default: 'big'
    }
  },
  computed: {
    tagPadding() {
      if (this.size === 'small') return '6px'
      return '12px'
    }
  },
  methods: {
    filterArticles(tag) {
      this.$router.push({
        name: 'articleFilter',
        params: {
          type: 'tag',
          param: tag
        }
      })
    },
    color() {
      return getRandomColor()
    },
    fontSize() {
      const sizes = [26, 24, 22, 20, 18, 16, 14]
      const index = (parseFloat(Math.random().toFixed(2)) * 100) % sizes.length
      return sizes[index] + 'px'
    }
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.tags-iterator {
  &__item {
    display: inline-block;
    cursor: pointer;
    transition: all ease 0.38s;
  }
  &__item:hover {
    transform: scale(1.2);
  }
}
</style>
