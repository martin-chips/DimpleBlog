<template>
  <div class="panel-category">
    <el-card>
      <div class="panel__item-title">
        <i class="el-icon-c-scale-to-original"></i>
        <span>文章分类</span>
      </div>
      <div class="panel__item-body">
        <ul>
          <li v-for="(item, index) in category" :key="index" @click="filterArticles(item.title, item.id)">
            <span>{{ item.title }}</span>
            <span>{{ item.articleCount }}</span>
          </li>
        </ul>
      </div>
    </el-card>
  </div>
</template>
<script>
import {mapState} from 'vuex'

export default {
  name: 'panelCategory',
  props: {},
  data() {
    return {}
  },
  computed: {
    ...mapState(['category'])
  },
  methods: {
    filterArticles(title, id) {
      this.$router.push({
        name: 'articleFilter',
        params: {
          type: 'category',
          param: id
        },
        query: {
          title
        }
      })
    }
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
@import '~@/views/panel/style/mixins';

.panel-category {
  @include panel-frame;

  .panel__item-body {
    li {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 8px;
      cursor: pointer;
      transition: all ease 0.38s;
    }

    li:hover {
      padding: 8px 12px;
      @include themeify() {
        background: themed('color-list-hover');
      }
    }
  }
}
</style>
