<template>
  <blog-detail :is-edit="true"/>
</template>

<script>
  import BlogDetail from './BlogDetail'

  export default {
    name: 'EditBlog',
    components: {BlogDetail}
  }
</script>
