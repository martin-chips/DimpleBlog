<template>
  <div class="article-page-header" v-if="article !== undefined">
    <div class="status">
      <div class="tags">
        <a v-for="tag in article.tagList">
          <el-tag :style="{borderColor:tag.color,color:tag.color} " effect="plain" size="small" :hit="true"
                  :key="tag.id" style="margin: 2px 4px 2px 0" class="border-tag">{{tag.title}}
          </el-tag>
        </a>
      </div>
    </div>
    <p class="title">{{article.title}}</p>
    <el-row>
      <el-col :xs="24" :sm="10" :md="10" :lg="10" style="padding-left: 0;padding-right: 0;">
        <p class="info">
          <span class="author">作者 / <a href="">Dimple</a></span>
          <span class="publish-time">  发布于 / <a
            href="">{{ article.createTime | socialDate }}</a></span>
        </p>
      </el-col>
      <el-col :xs="24" :sm="14" :md="14" :lg="14" style="padding-left: 0;padding-right: 0;">
        <p class="operate_info">
          <span class="readings">
            <a>
              <i class="el-icon-view"></i> {{article.click}} 阅读
            </a>
          </span>
          <span class="comments">
            <a @click="scrollToComments">
              <i class="el-icon-edit-outline"></i> {{article.commentList.length}} 评论
            </a>
          </span>
          <span class="likes">
            <a @click="likePost(article)">
              <svg-icon icon-class="like" /> {{article.like}} 赞
            </a>
          </span>
        </p>
      </el-col>
    </el-row>
    <p class="abstract" v-if="article.summary">
      摘要：{{article.summary}}
    </p>
  </div>
</template>

<script type="text/ecmascript-6">
  // mixin
  import {mixin} from '@/utils/front/utils';
  import {LikeBlog} from '@/api/front/front';

  export default {
    name: 'ArticlePageHeader',
    props: {
      article: {
        Type: Object,
        default: undefined
      },
      languages: {
        Type: []
      }
    },
    mixins: [mixin],
    methods: {
      likePost(post) {
        LikeBlog(post.id).then((response) => {
          post.like += 1;
          this.msgSuccess("点赞 +1");
        }).catch((error) => {
          console.log(error);
        });
      }
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";
  .article-page-header
    padding-bottom 10px
    text-align left

    .status
      display flex
      margin-bottom 10px
      line-height 100%

      .switches
        flex 1
        display flex
        justify-content flex-end
        cursor pointer

        .ivu-btn-ghost
          i:

          :before, span
          color $default-desc-color
          &:hover
            i:

            :before, span
              color $default-desc-hover-color

        .ivu-btn-ghost[disabled]
          i:

          :before, span
          color $default-info-color
          &:hover
            i:

            :before, span
              color $default-info-color

    .title
      font-weight 500
      color $default-title-color
      @media only screen and (max-width: $responsive-sm)
        font-size 24px
        line-height 27px
      @media screen and (min-width: $responsive-sm)
        font-size 25px
        line-height 35px
      @media screen and (min-width: $responsive-md)
        font-size 26px
        line-height 35px
      @media screen and (min-width: $responsive-lg)
        font-size 27px
        line-height 35px

    .info, .operate_info
      margin-top 10px
      font-size 14px
      font-weight 200
      line-height 18px
      color $default-desc-color
      @media only screen and (max-width: 768px)
        margin-top 7px
        font-size 12px
        line-height 15px

      a
        color: $default-link-color
        cursor pointer

        &:hover
          color $default-link-hover-color

      .publish-time
        margin-left 20px

    .operate_info
      text-align right
      @media only screen and (max-width: 768px)
        text-align left

      span
        + span
          margin-left 10px

        a
          cursor pointer

          &:hover
            color $default-link-hover-color

    .abstract
      color $default-desc-color
      border 1px dashed $default-border-color
      background $default-border-color
      @media only screen and (max-width: $responsive-sm)
        padding 8px
        margin-top 8px
        font-size 13px
        line-height 18px
      @media screen and (min-width: $responsive-sm)
        padding 15px
        margin-top 8px
        font-size 14px
        line-height 24px
      @media screen and (min-width: $responsive-md)
        padding 20px
        margin-top 15px
        line-height 28px
      @media screen and (min-width: $responsive-lg)
        padding 20px
        margin-top 15px
        line-height 28px
</style>
