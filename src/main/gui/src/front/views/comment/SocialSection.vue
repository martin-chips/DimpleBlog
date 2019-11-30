<template>
  <div class="social-section" id="comments" v-if="article != undefined" ref="socialSection">
    <i size="large" v-if="showSpin" fix style="z-index: 99;"></i>

    <div class="comment-area">
      <div class="editor" :class="{spread: spreadEditor}">
        <custom-mavon-editor :post="article"
                             :replyId="undefined"
                             :parentId="undefined"
                             @valueChanged="valueChanged"
                             @publishedComment="publishedComment"></custom-mavon-editor>
      </div>
    </div>

    <div class="comment-list" v-if="comments.length > 0">
      <div v-for="comment in comments" :key="comment.id">
        <comment-cell-list :post="article"
                           :commentLevel="1"
                           :comment="comment"
                           @publishedComment="publishedComment"></comment-cell-list>
        <comment-cell-list v-for="subComment in comment.subCommentList"
                           :key="subComment.id"
                           style="border-left:0.2rem solid #dadada"
                           :post="article"
                           :commentLevel="2"
                           :comment="subComment"
                           @publishedComment="publishedComment"></comment-cell-list>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">

  import CustomMavonEditor from '../CustomMavonEditor';
  import CommentListCell from './CommentListCell';
  import {listComment, goodComment, badComment} from '@/api/front/front'

  const COMMENT_DEFAULT_LIMIT = 10;
  export default {
    name: 'social-section',
    props: {
      article: {
        Type: Object,
        default: undefined
      }
    },
    data() {
      return {
        comments: [],
        spreadEditor: false,
        showSpin: true,
      };
    },
    watch: {
      article: function (val) {
        if (val != undefined) {
          this.$nextTick(() => {
            this.getCommentInfo()
          });
        }
      }
    },
    methods: {
      getCommentInfo() {
        listComment(this.article.id).then((response) => {
          this.comments = response.data;
          this.showSpin = false;
        }).catch((error) => {
          console.log(error);
        });
      },
      valueChanged(flag) {
        console.log(flag);
        this.spreadEditor = flag;
      },
      publishedComment(comment) {
        console.log(comment);
        if (comment.parent_comment === null) {
          // 根评论为空,表示是一级评论
          this.comments.unshift(comment);
        } else {
          // 否则是子级评论
          let parentComment = this.getParentComment(comment.parent_comment);
          if (parentComment !== null) {
            if (parentComment.sub_comment === null || parentComment.sub_comment === undefined) {
              parentComment.sub_comment = [];
            }
            parentComment.sub_comment.push(comment);
          }
        }
      },
      getParentComment(parentCommentId) {
        let recursiveComments = [];
        let recursiveCommentyIds = [];
        var recursiveComment = function (comments, parentCommentId) {
          if (parentCommentId === null || parentCommentId === undefined) return null;
          for (let index = 0; index < comments.length; index++) {
            let comment = comments[index];
            if (comment.id === parentCommentId) {
              recursiveComments.push(comment);
              recursiveCommentyIds.push(comment.id);
              return comment;
            } else if (comment.sub_comment && comment.sub_comment.length > 0) {
              let result = recursiveComment(comment.sub_comment, parentCommentId);
              if (result) {
                recursiveComments.push(comment);
                recursiveCommentyIds.push(comment.id);
                return result;
              }
            }
          }
        };
        let parentComment = recursiveComment(this.comments, parentCommentId);
        recursiveComments = recursiveComments.reverse();
        recursiveCommentyIds = recursiveCommentyIds.reverse();
        return parentComment;
      },
    },
    components: {
      'custom-mavon-editor': CustomMavonEditor,
      'comment-cell-list': CommentListCell,
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus" scoped>
  @import "../../common/stylus/theme.styl";
  .social-section
    position relative
    margin-top 10px

    .dark-theme
      background #000

      &::after
        display none

      .ivu-menu-item
        &:hover
          color $iview-secondary-warning-color
          border-bottom 2px solid $iview-secondary-warning-color

      .ivu-menu-item-active
        color $iview-secondary-warning-color
        border-bottom 2px solid $iview-secondary-warning-color

      .ivu-menu-submenu
        &:hover
          color $iview-secondary-warning-color
          border-bottom 2px solid $iview-secondary-warning-color

    .content
      margin 15px 0

      .likes
        margin-bottom 20px

        a
          display inline-block
          margin-right 10px

          img
            border-radius $border-radius
            width 40px
            height 40px

    .comment-area
      .editor
        margin 15px 0 10px
        transition height 0.7s

        &.spread
          height 300px

    > .ivu-menu
      z-index 0

    .comment-list
      padding 15px
      background $default-background-color
</style>
