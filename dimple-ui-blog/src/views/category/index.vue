<template>
  <div class="category">
    <layout title="分类" cover="/img/cover/category.jpg">
      <div class="category__content">
        <ul>
          <li v-for="(item, index) in categories" :key="index" @click="filterArticles(item.title, item.id)">
            <div>
              <dot>
                <span>{{ item.title }}</span>
                <span>（{{ item.articleCount }}）</span>
              </dot>
            </div>
          </li>
        </ul>
      </div>
    </layout>
  </div>
</template>
<script>
import api from '@/api/'
import dot from '@/components/dot'

export default {
  name: 'category',
  metaInfo() {
    return {
      title: `文章分类  - Dimple's Blog`,
      meta: [
        {
          name: 'description',
          content: '文章分类'
        },
        {
          name: 'keywords',
          content: '技术文档，生活感悟'
        }
      ]
    }
  },
  data() {
    return {
      categories: []
    }
  },
  components: {dot},
  async asyncData({store, route, isServer, _req}) {
    const categoryRes = await api.listAsyncCategory(_req)
    if (categoryRes.code == 200) return {categories: categoryRes.rows}
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

.category {
  &__content {
    li {
      padding: 6px 0;
      transition: all ease 0.38s;
    }

    li:hover {
      cursor: pointer;
      @include themify() {
        color: themed('btn-bg');
      }
    }
  }
}
</style>
