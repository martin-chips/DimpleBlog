<template>
  <div class="article-filter">
    <layout :title="title" cover="/img/cover/articles.jpg">
      <template slot="custom-body">
        <article-iterator :articles="articles"></article-iterator>
        <ElPagination
          class="article-filter__page"
          :total="total"
          v-if="total>articles.length"
          layout="prev, pager, next"
          :page-size="pageSize"
          @current-change="currentChange"
        ></ElPagination>
      </template>
    </layout>
  </div>
</template>
<script>
import api from "@/api/";
import articleIterator from "@/views/components/article-iterator";


// 筛选文章公共请求
async function fetchArticles(route, page) {
  const params = {...page}
  // 按标签筛选
  if (route.params.type === 'tag') {
    params.tagId = route.params.param
  }
  // 按分类筛选
  if (route.params.type === 'category') {
    params.categoryId = route.params.param
  }
  const articleRes = await api.listArticle(params, {pageNum: 1, pageSize: 10})
  return articleRes
}

export default {
  name: 'articleFilter',
  metaInfo() {
    return {
      title: `文章筛选：${this.title}  - ` + `Dimple's Blog`,
      meta: [
        {
          name: 'description',
          content: `${this.title}类文章`
        },
        {
          name: 'keywords',
          content: `${this.title}`
        }
      ]
    }
  },
  data() {
    return {
      pageSize: 10,
      pageNum: 1,
      articles: [],
      total: 0
    }
  },
  watch: {
    title() {
      this.getArticles();
    }
  },
  created() {
    this.getArticles()
  },
  components: {articleIterator},
  computed: {
    title() {
      return this.$route.query.title
    }
  },
  methods: {
    currentChange(val) {
      this.pageNum = val
      this.getArticles()
    },
    async getArticles() {
      const articleRes = await fetchArticles(this.$route, {pageNum: this.pageNum, pageSize: this.pageSize})
      if (articleRes.code === 200) {
        this.articles = articleRes.rows
        this.total = articleRes.total
      }
    }
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';

.article-filter {
  &__page {
    padding: 12px;
    @include flex-box-center;
  }
}
</style>
