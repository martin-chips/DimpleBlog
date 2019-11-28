<template>
  <div class="social-section" id="comments" v-if="article != undefined" ref="socialSection">
    <i-spin size="large" v-if="showSpin" fix style="z-index: 99;"></i-spin>
    <div class="comment-area">
      <div class="editor" :class="{spread: spreadEditor}">
        <custom-mavon-editor :post="article"
                             :theme="theme"
                             @valueChanged="valueChanged"
                             @publishedComment="publishedComment"></custom-mavon-editor>
      </div>
    </div>

    <div class="comment-list" v-if="comments.length > 0">
      <div v-for="comment_level1 in comments" :key="comment_level1.id">
        <comment-cell-list :theme="theme"
                           :post="article"
                           :commentLevel="comment_level1.comment_level"
                           :comment="comment_level1"
                           @publishedComment="publishedComment"></comment-cell-list>
        <comment-cell-list v-for="comment_level2 in comment_level1.sub_comment"
                           :key="comment_level2.id"
                           :theme="theme"
                           :post="article"
                           :commentLevel="comment_level2.comment_level"
                           :comment="comment_level2"
                           @publishedComment="publishedComment"></comment-cell-list>
        <browse-more @browseMore="showMoreSubComments(comment_level1)"
                     v-if="comment_level1.sub_comment && comment_level1.comment_num > comment_level1.sub_comment.length"></browse-more>
      </div>
      <browse-more @browseMore="getCommentInfo" ref="browseMore"></browse-more>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import CustomMavonEditor from '@/components/views/CustomMavonEditor';
  import CommentListCell from '@/components/views/Comment/CommentListCell';
  import BrowseMore from '@/components/views/BrowseMore';
  // API
  import API from 'API';

  const COMMENT_DEFAULT_LIMIT = 10;
  export default {
    name: 'social-section',
    props: {
      article: {
        Type: Object,
        default: undefined
      },
      theme: {
        Type: String,
        default: ''
      }
    },
    data() {
      return {
        comments: [],
        totalCount: 0,
        noMoreData: false,
        spreadEditor: false,
        name: '',
        select: 'email',
        email: '',
        mobile: '',
        showSpin: true,
        dataLoaded: false
      };
    },
    created() {
      this.getCommentInfo();
    },
    methods: {
      getCommentInfo() {
        API.getCommentInfo({
          params: {
            post_id: this.article.id,
            comment_level: 0,
            limit: COMMENT_DEFAULT_LIMIT,
            offset: this.comments.length
          }
        }).then((response) => {
          this.totalCount += response.data.results.length;
          this.noMoreData = this.totalCount >= response.data.count;
          this.comments = this.comments.concat(response.data.results);
          this.$nextTick(() => {
            if (this.$refs.browseMore) {
              this.$refs.browseMore.stopLoading(this.noMoreData);
            }
          });
          this.showSpin = false;
        }).catch((error) => {
          console.log(error);
        });
      },
      likePost(post) {
        API.addPostLike({
          post_id: post.id
        }).then((response) => {
          post.like_num += 1;
          this.$Message.success('点赞成功');
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
      showMoreSubComments(parentComment) {
        API.getCommentInfo({
          params: {
            post_id: this.article.id,
            parent_comment: parentComment.id,
            limit: COMMENT_DEFAULT_LIMIT,
            offset: parentComment.sub_comment.length,
            ordering: 'add_time'
          }
        }).then((response) => {
          parentComment.sub_comment = parentComment.sub_comment.concat(response.data.results);
        }).catch((error) => {
          console.log(error);
        });
      }
    },
    components: {
      'custom-mavon-editor': CustomMavonEditor,
      'comment-cell-list': CommentListCell,
      'browse-more': BrowseMore
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus" scoped>
  @import "../.././../common/stylus/theme.styl";
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
        height 200px
        transition height 0.7s

        &.spread
          height 450px

    > .ivu-menu
      z-index 0

    .comment-list
      padding 15px
      background $default-background-color
</style>
