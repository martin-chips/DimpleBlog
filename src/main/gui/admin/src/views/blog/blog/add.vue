<template>
  <blog-detail :is-edit="false"/>
</template>

<script>
  import BlogDetail from './BlogDetail'

  export default {
    name: 'AddBlog',
    components: {BlogDetail}
  }
</script>
