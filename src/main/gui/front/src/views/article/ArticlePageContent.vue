<template>
  <div id="article-page-content">
    <slot name="content"></slot>
  </div>
</template>

<script type="text/ecmascript-6">
    export default {
        name: 'article-page-content'
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/article.styl";
  a
    color #7e8c8d
    text-decoration none
    -webkit-backface-visibility hidden
</style>
