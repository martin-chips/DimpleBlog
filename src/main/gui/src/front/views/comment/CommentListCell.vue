<template>
  <div class="comment-list-cell" :id="'comment_'+comment.id">
    <el-row :style="{'margin-left':commentLevel==2?'4rem':''}">
      <el-col :xs="cellSpan('xs')" :sm="cellSpan('sm')" :md="cellSpan('md')" :lg="cellSpan('lg')" :xl="cellSpan('xl')">
        <div class="comment-main">
          <el-row :gutter="8">
            <el-col :xs="cellLeftSpan('xs')" :sm="cellLeftSpan('sm')" :md="cellLeftSpan('md')"
                    :lg="cellLeftSpan('lg')" :xl="cellLeftSpan('xl')">
              <div class="avatar">
                <img :src="comment.avatar" alt="">
              </div>
            </el-col>
            <el-col :xs="cellRightSpan('xs')" :sm="cellRightSpan('sm')" :md="cellRightSpan('md')"
                    :lg="cellRightSpan('lg')" :xl="cellRightSpan('xl')">
              <div class="content">
                <p class="title">
                  <span class="name" :class="theme">
                    <a>{{ comment.nickName }}</a>
                  </span>
                  <span class="name-tag"
                        v-if="comment.nickName !== undefined && comment.nickName=='Dimple'">作者Dimple回复
                  </span>
                  <span class="reply-icon" :class="theme" v-if="comment.replyNickName != undefined">
                    <svg-icon icon-class="forward"></svg-icon>
                  </span>
                  <span class="reply-name" :class="theme" v-if="comment.replyNickName != undefined">
                    <a @click="scrollToComments('#comment_'+comment.id)">{{ comment.replyNickName }}</a>
                  </span>
                  <span class="name-tag"
                        v-if="comment.replyNickName != undefined && comment.replyNickName==='Dimple'">作者回复</span>
                  <span class="time">{{ comment.createTime | socialDate }}</span>
                </p>
                <div class="comment-main-content" :class="theme" v-if="comment.content"
                     v-html="comment.content"
                     ref="content"></div>
                <div class="operate-area" :class="theme">
                  <span class="like" @click="likeComment(comment)">
                   <a>
                      <svg-icon icon-class="like"/>  {{ comment.good }}
                   </a>
                  </span>
                  <span class="unlike" @click="unlikeComment(comment)">
                   <a>
                     <svg-icon icon-class="unlike"/>  {{ comment.bad }}
                   </a>
                  </span>
                  <span class="reply">
                    <a @click="displayEditor">
                      <i class="el-icon-chat-dot-square"></i> 回复
                    </a>
                  </span>
                </div>
                <div class="comment-area" v-show="showEditor">
                  <div class="reply-editor" :class="{spread: spreadEditor}">
                    <custom-mavon-editor :post="post"
                                         :replyId="comment.id"
                                         :parentId="commentLevel==1?comment.id:comment.parentId"
                                         :replyToComment="comment"
                                         @valueChanged="valueChanged"
                                         @publishedComment="publishedComment"></custom-mavon-editor>
                  </div>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script type="text/ecmascript-6">
  import CustomMavonEditor from '../CustomMavonEditor';
  // mixin
  import {mixin} from '@/utils/front/utils';
  import {goodComment, badComment} from "@/api/front/front";

  const CELL_LEFT_SPAN = {
    'xs': 3,
    'sm': 3,
    'md': 2,
    'lg': 2
  };
  const CELL_RIGHT_SPAN = {
    'xs': 24 - CELL_LEFT_SPAN['xs'],
    'sm': 24 - CELL_LEFT_SPAN['sm'],
    'md': 24 - CELL_LEFT_SPAN['md'],
    'lg': 24 - CELL_LEFT_SPAN['lg']
  };
  export default {
    name: 'comment-list-cell',
    props: {
      post: {
        Type: Object,
        default: undefined
      },
      comment: {
        Type: Object,
        default: undefined
      },
      commentLevel: {
        type: Number,
        default: 0
      }
    },
    mixins: [mixin],
    data() {
      return {
        showEditor: false,
        spreadEditor: false
      };
    },
    computed: {
      showEditor() {

      }
    },
    methods: {
      cellSpan(size) {
        var span = {};
        span['offset'] = CELL_LEFT_SPAN[size] * parseInt(this.comment.comment_level);
        span['span'] = 24 - span['offset'];
        return span;
      },
      cellLeftSpan(size) {
        var span = {};
        span['span'] = CELL_LEFT_SPAN[size];
        return span;
      },
      cellRightSpan(size) {
        var span = {};
        span['span'] = CELL_RIGHT_SPAN[size];
        return span;
      },
      valueChanged(flag) {
        this.spreadEditor = flag;
      },
      displayEditor() {
        this.showEditor = !this.showEditor;
      },
      publishedComment(comment) {
        this.$emit('publishedComment', comment);
      },
      likeComment(comment) {
        goodComment(comment.id).then(response => {
          comment.good += 1;
          this.msgSuccess("点赞 +1");
        }).catch(error => {
          console.log(error)
        });
      },
      unlikeComment(comment) {
        badComment(comment.id).then(response => {
          comment.bad += 1;
          this.msgSuccess("鄙视 +1");
        }).catch(error => {
          console.log(error)
        });
      }
    },
    components: {
      'custom-mavon-editor': CustomMavonEditor
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";
  @import "../../common/stylus/comment.styl";
  .comment-list-cell
    position relative
    text-align left

    .avatar
      text-align center

      img
        border-radius $border-radius
        width 100%

    .content
      margin 5px 0 20px

      .title
        font-size 0
        margin-bottom 5px
        line-height 18px

        .name
          a
            font-size 15px
            color $default-title-hover-color
            font-weight 700

            &:hover
              text-decoration underline

          &.dark-theme
            a
              color $iview-secondary-warning-color

        .name-tag
          font-size 10px
          background-color $default-info-color
          padding 2px 5px
          margin 0 5px
          color #fff
          border-radius $border-radius

        .reply-icon
          font-size 15px
          color $default-info-color

        .reply-name
          font-size 15px
          margin 0 5px

          a
            color $default-link-color

            &:hover
              color $default-link-hover-color

          &.dark-theme
            a
              color $color-gradually-gray-71

              &:hover
                color $iview-secondary-warning-color
                text-decoration underline

        .time
          font-size 13px
          color $default-info-color
          margin-left 8px

      .comment-main-content
        font-size 16px
        line-height 24px
        margin 10px 0 15px
        word-wrap break-word
        word-break break-all

        &.dark-theme
          color $color-gradually-gray-71

    .operate-area
      margin-top 8px
      font-size 14px

      span
        margin-right 10px

      .i-dropdown-link
        cursor pointer

      .like, .unlike
        color $default-info-color
        font-weight 300
        cursor pointer

      .reply
        cursor pointer

      &.dark-theme
        .i-dropdown-link
          &:hover
            color $iview-secondary-warning-color

        .reply
          a
            color $iview-secondary-warning-color

    .comment-area
      margin-bottom 10px

      .reply-editor
        margin-top 15px
        height 200px
        transition height 0.7s

        &.spread
          height 300px

      p.comment-tip
        a
          font-size 14px

          &:hover
            color $default-link-hover-color
</style>
