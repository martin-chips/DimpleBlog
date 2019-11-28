<template>
  <div class="article-page-footer" v-if="article != undefined">
    <license-tag :license="article.license"></license-tag>
    <social-section :article="article"></social-section>
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
    methods: {
      onSubmit() {
        console.log('submit!');
      }
    },
    components: {
      'license-tag': LicenseTag,
      'social-section': SocialSection
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
