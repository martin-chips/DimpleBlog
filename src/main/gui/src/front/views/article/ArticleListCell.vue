<template>
  <div class="article-list-cell">
    <el-row type="flex">
      <el-col :xs="24" :sm="textSpan" :md="textSpan" :lg="textSpan"
              style="padding-left: 0;padding-right: 0;">
        <div class="text-wrapper">
          <h4 class="title">
            <a @click.prevent="gotoPostDetail(article)" :href="`/article/${article.id}`">
              {{article.title}}</a>
            <span class="special" v-if="article.support">推荐</span>
          </h4>
          <div class="tags">
            <el-tag :type="tag.type" effect="plain" v-for="tag in tags" :key="tag.id" class="border-tag"
                    style="margin: 2px 4px 2px 0">
              {{tag.label}}
            </el-tag>
          </div>
          <p class="desc">{{article.summary | textLineBreak(90) }}
            <a @click.prevent="gotoPostDetail(article)" :href="`/article/${article.id}`"> 查看更多
              <i class="el-icon-caret-right"></i>
            </a>
          </p>
          <p class="operate_info">
            <span class="publish-time"><a>{{ article.createTime | socialDate }}</a></span>
            <span class="readings">
              <a><i class="el-icon-view"></i> {{article.click}} 阅读</a>
            </span>
            <span class="comments">
              <a><i class="el-icon-chat-dot-square"></i> {{article.commentCount}} 评论</a>
            </span>
            <span class="likes">
              <a @click="likePost(article)"><i class="el-icon-star-on"></i> {{article.like}} 赞</a>
            </span>
          </p>
        </div>
      </el-col>
      <el-col :xs="0" :sm="imgSpan" :md="imgSpan" :lg="imgSpan" style="padding-left: 0;padding-right: 0;">
        <div class="img-wrapper" :class="themeClass">
          <img :src="article.headerImg" alt="">
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
  import {mixin} from '@/utils/front/utils';
  import {LineBreakMode} from "@/utils/front/const.js";
  import {socialDateFormat} from "../../../utils/front/utils";

  const ARTICLE_TYPE_NO_IMAGE = 0;
  const ARTICLE_TYPE_NORMAL_IMAGE = 1;
  const ARTICLE_TYPE_BIG_IMAGE = 2;
  export default {
    name: 'article-list-cell',
    mixins: [mixin],
    props: {
      article: {
        Type: Object
      }
    },
    data() {
      return {
        // articleType: 2,
      }
    },
    computed: {
      articleType: function () {
        if (this.article.headerImg == null) {
          return 0;
        } else {
          return 1;
        }
      },
      textOrderType: function () {
        return parseInt(this.articleType) === ARTICLE_TYPE_BIG_IMAGE ? 2 : 1;
      },
      imgOrderType: function () {
        return parseInt(this.articleType) === ARTICLE_TYPE_BIG_IMAGE ? 1 : 2;
      },
      textSpan: function () {
        switch (parseInt(this.articleType)) {
          case ARTICLE_TYPE_NO_IMAGE:
            return 24;
          case ARTICLE_TYPE_NORMAL_IMAGE:
            return 17;
          case ARTICLE_TYPE_BIG_IMAGE:
            return 24;
          default:
            return 24;
        }
      },
      imgSpan: function () {
        switch (parseInt(this.articleType)) {
          case ARTICLE_TYPE_NO_IMAGE:
            return 0;
          case ARTICLE_TYPE_NORMAL_IMAGE:
            return 7;
          case ARTICLE_TYPE_BIG_IMAGE:
            return 24;
          default:
            return 0;
        }
      },
      themeClass: function () {
        if (parseInt(this.article.front_image_type) === ARTICLE_TYPE_BIG_IMAGE) {
          return 'big-image';
        } else {
          return '';
        }
      },
      tags: function () {
        let tags = this.article.tag.split(",");
        let tagArray = []
        for (let i = 0; i < tags.length; i++) {
          let temp = {};
          if (i % 5 == 0) {
            temp.type = '';
          } else if (i % 5 == 1) {
            temp.type = 'success';
          } else if (i % 5 == 2) {
            temp.type = 'info';
          } else if (i % 5 == 3) {
            temp.type = 'danger';
          } else if (i % 5 == 4) {
            temp.type = 'warning';
          }
          temp.label = tags[i];
          tagArray[i] = temp;
        }
        return tagArray;
      }
    },
    methods: {
      gotoPostDetail(post) {
        this.$router.push({
          name: post.post_type,
          params: {id: post.id},
        });
      },
      likePost(post) {

      }
    },
    filters: {
      // 用于格式化时间的过滤器
      socialDate: function (formattedDate) {
        return socialDateFormat(formattedDate);
      },
      // 用于处理行尾省略号的过滤器
      textLineBreak: function (text, maxLength, lineBreakMode) {
        if (text === undefined || text === null || text.length === 0) {
          return '';
        }
        if (lineBreakMode === null || lineBreakMode === undefined) {
          lineBreakMode = LineBreakMode.EllipsisTruncatingTail;
        }
        switch (lineBreakMode) {
          case LineBreakMode.WrappingTruncatingTail:
            return text.substr(0, maxLength);
          case LineBreakMode.WrappingTruncatingHead:
            return text.substr(-maxLength);
          case LineBreakMode.EllipsisTruncatingTail:
            return text.substr(0, maxLength) + (text.length > maxLength ? '...' : '');
          case LineBreakMode.EllipsisTruncatingMiddle:
            let resultText = text.substr(0, maxLength);
            if (text.length > maxLength) {
              return resultText.substr(0, parseInt(maxLength / 2)) + '...' + resultText.substr(parseInt(maxLength / 2));
            }
            return resultText;
          case LineBreakMode.EllipsisTruncatingHead:
            return (text.length > maxLength ? '...' : '') + text.substr(-maxLength);
        }
        return text;
      }
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";
  .article-list-cell
    margin-bottom 10px
    background-color $default-cell-background-color
    border 1px solid $default-border-color

    img
      width 100%
      transition: All 0.4s ease-in-out
      transform: scale(1.0)
      zoom: 1.0

    &:hover
      border 1px solid $default-border-hover-color

      img
        transform: scale(1.05)
        zoom: 1.02

    .text-wrapper
      padding 20px 20px 0 20px
      text-align left
      @media only screen and (max-width: 768px)
        padding 10px 10px 0 10px

      .title
        font-size 20px
        font-weight 100
        line-height 24px
        @media only screen and (max-width: 768px)
          font-size 17px
          line-height 23px
        word-wrap break-word

        a
          color $default-title-color
          cursor pointer

          &:hover
            color $default-title-hover-color
            text-decoration underline

        span.special
          border-radius $border-radius
          font-size 12px
          font-weight 100
          padding 3px 5px
          margin-left 1px
          vertical-align top
          color $default-background-color
          background $iview-secondary-warning-color
          cursor pointer

      .tags
        margin 8px 0

      .desc
        font-size 14px
        line-height 20px
        font-weight 200
        color $default-desc-color
        @media only screen and (max-width: 768px)
          font-size 13px

        a
          font-weight 500
          color $default-desc-hover-color
          cursor pointer

          &:hover
            text-decoration underline

      .operate_info
        font-size 14px
        margin 15px 0 20px

        span
          margin-right 8px

          a
            color $default-info-color
            cursor pointer

            &:hover
              color $default-info-hover-color

          + span
            margin-left 8px
        @media only screen and (max-width: 768px)
          font-size 13px
          margin 10px 0
          span
            margin-right 4px

            + span
              margin-left 4px

    .img-wrapper
      padding-bottom: 85%
      width 100%
      height 0
      overflow hidden

      &.big-image
        padding-bottom 26%
        box-shadow 1px 1px 1px $default-border-color
</style>
