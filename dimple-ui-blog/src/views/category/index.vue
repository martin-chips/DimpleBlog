<template>
    <div class="category">
        <layout title="分类" :cover="cover">
            <div class="category__content">
                <ul>
                    <li v-for="(item, index) in categories" :key="index" @click="filterArticles(item.title, item.id)">
                        <div>
                            <dot>
                                <span>{{ item.name }}</span>
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
import api from "@/api/";
import dot from "@/components/dot";

import cover from "@/assets/img/cover/category.jpg";

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
            cover:cover,
            categories: []
        }
    },
    components: {dot},
    async asyncData() {
        const categoryRes = await api.getCategory({})
        if (categoryRes.code === 200) return {categories: categoryRes.data}
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
      @include themeify() {
        color: themed('color-ele-primary');
      }
    }
  }
}
</style>
