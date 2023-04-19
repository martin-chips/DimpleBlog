<template>
  <ol class="tree-folder">
    <li v-for="(catalog, index) in catalogs" :key="catalog.level + '_' + index">
      <div
        class="tree-folder__content"
        :class="{ 'tree-folder__content--active': catalog.tempId === activeCatalog }"
        @click="jumpAnchor(catalog)"
      >
        <div class="tree-folder__order">
          <!-- <span v-if="catalog.level_tree">{{ curIndex + 1 + '.' + (index + 1) }}</span>
          <span v-else>{{ index + 1 }}.</span> -->
          <span v-if="(catalog.order + '').includes('.')">{{ catalog.order }}</span>
          <span v-else>{{ catalog.order }}.</span>
        </div>
        <span>{{ catalog.name }}</span>
      </div>

      <tree-folder
        v-if="catalog.children && catalog.children.length"
        :catalogs="catalog.children"
        :curIndex="index"
      ></tree-folder>
    </li>
    <div class="tree-folder__empty" v-if="!catalogs.length">暂未匹配到目录</div>
  </ol>
</template>
<script>
import {mapState} from 'vuex'

export default {
  name: 'tree-folder',
  props: {
    curIndex: {
      type: Number,
      default: 0
    },
    catalogs: {
      type: Array,
      default() {
        return []
      }
    }
  },
  data() {
    return {}
  },
  computed: {
    ...mapState(['activeCatalog'])
  },
  methods: {
    jumpAnchor(catalog) {
      const a = document.createElement('a')
      a.href = `${location.href.replace(location.hash, '')}#${catalog.tempId}`
      document.body.appendChild(a)
      a.click()
      document.body.removeChild(a)
    }
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.tree-folder {
  padding-left: 12px;
  &__content {
    transition: all ease 0.38s;
    cursor: pointer;
    padding: 6px;
    &--active {
      color: #fff;
      @include themify() {
        background: themed('color-catalog-active');
      }
    }
  }
  &__content:hover {
    @include themify() {
      color: themed('color-catalog-active');
    }
  }
  &__order {
    display: inline-block;
    margin-right: 8px;
  }
  &__empty {
    font-size: 12px;
    text-align: center;
    @include themify() {
      color: themed('color-ele-holder');
    }
  }
}
</style>
