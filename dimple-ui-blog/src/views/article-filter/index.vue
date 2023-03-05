<template>
    <div class="article-filter">
        <layout :_title="title" :cover="cover">
            <template slot="custom-body">
                <article-iterator :articles="articles"></article-iterator>
                <div class="article-filter__page">
                    <el-pagination
                            :total="total"
                            layout="prev, pager, next"
                            :page-size="pageSize"
                            @current-change="currentChange"
                    ></el-pagination>
                </div>
            </template>
        </layout>
    </div>
</template>
<script>
import api from "@/api/";
import articleIterator from "@/views/components/article-iterator";

import cover from "@/assets/img/cover/articles.jpeg";

// 筛选文章公共请求
async function fetchArticles(route, page) {
    const params = {...page}
    // 按标签筛选
    if (route.params.type === 'tag') params.tag = route.params.param
    // 按分类筛选
    if (route.params.type === 'category') params.categoryId = route.params.param

    const articleRes = await api.listArticle(params, {pageNum: 1, pageSize: 10})
    return articleRes
}

export default {
    name: 'articleFilter',
    metaInfo() {
        return {
            title: `文章筛选：${this.title}  - Dimple's Blog`,
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
            cover: cover,
            pageSize: 10,
            pageNum: 1,
            articles: [],
            total: []
        }
    },
    created() {
    },
    components: {articleIterator},
    async asyncData({route}) {
        const articleRes = await fetchArticles(route)
        if (articleRes.code === 200) return {articles: articleRes.data, total: articleRes.total}
    },
    computed: {
        title() {
            if (this.$route.params.type === 'category') return this.$route.query.name
            return this.$route.params.param
        }
    },
    methods: {
        currentChange(val) {
            this.pageNum = val
            this.getArticles()
        },
        async getArticles() {
            const articleRes = await fetchArticles(this.$route, {page: this.pageNum, limit: this.pageSize})
            if (articleRes.code === 200) {
                this.articles = articleRes.data
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
