<template>
  <div class="article-page-footer" v-if="article != undefined">
    <LicenseTag :license="article.license"/>
    <SocialSection :pageId="article.id"
                   :commentList="article.commentList"
                   :allowComment="article.comment"/>
  </div>
</template>

<script type="text/ecmascript-6">
  import LicenseTag from "../LicenseTag";
  import SocialSection from "../comment/SocialSection";

  export default {
    name: 'ArticlePageFooter',
    props: {
      article: {
        Type: Object,
        default: undefined
      }
    },
    components: {
      LicenseTag, SocialSection
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";
  .article-page-footer
    text-align left

    .operate_menu
      margin-top 30px

    .comment-menu
      margin-top 30px

      .comment-menu-item
        margin-bottom 20px

      p.comment-menu-item
        a
          font-size 14px

          &:hover
            color $default-link-hover-color
</style>
