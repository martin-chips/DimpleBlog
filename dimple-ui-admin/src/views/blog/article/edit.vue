<template>
  <ArticleDetail :is-edit="true"/>
</template>

<script>
import ArticleDetail from './components/ArticleDetail.vue'

export default {
  name: 'EditForm',
  components: {ArticleDetail}
}
</script>
