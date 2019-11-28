<template>
  <div class="comment-list-cell">
    <i-row>
      <i-col :xs="cellSpan('xs')" :sm="cellSpan('sm')" :md="cellSpan('md')" :lg="cellSpan('lg')" :xl="cellSpan('xl')">
        <div class="comment-main">
          <i-row :gutter="8">
            <i-col :xs="cellLeftSpan('xs')" :sm="cellLeftSpan('sm')" :md="cellLeftSpan('md')"
                   :lg="cellLeftSpan('lg')" :xl="cellLeftSpan('xl')">
              <div class="avatar">
                <img :src="siteImageBaseUrl + '/comment/avatar/' + avatarImage(comment.author)" alt="">
              </div>
            </i-col>
            <i-col :xs="cellRightSpan('xs')" :sm="cellRightSpan('sm')" :md="cellRightSpan('md')"
                   :lg="cellRightSpan('lg')" :xl="cellRightSpan('xl')">
              <div class="content">
                <p class="title">
                  <span class="name" :class="theme" v-if="comment.author !== null"><a>{{ comment.author.nick_name }}</a></span>
                  <span class="name-tag" v-if="comment.author !== null && comment.author.is_blogger">{{ $t('comments.blogger') }}</span>
                  <span class="reply-icon" :class="theme" v-if="comment.reply_to_author !== null"><i-icon
                    type="forward"></i-icon></span>
                  <span class="reply-name" :class="theme"
                        v-if="comment.reply_to_author !== null"><a>{{ comment.reply_to_author.nick_name }}</a></span>
                  <span class="name-tag" v-if="comment.reply_to_author !== null && comment.reply_to_author.is_blogger">{{ $t('comments.blogger') }}</span>
                  <span class="time">{{ comment.add_time | socialDate }}</span>
                </p>
                <div class="comment-main-content" :class="theme" v-if="comment.detail" v-html="comment.detail.formatted_content"
                     ref="content"></div>
                <div class="operate-area" :class="theme">
                  <span class="like" @click="likeComment(comment)"><i-icon type="thumbsup"></i-icon> {{ comment.like_num }}</span>
                  <span class="unlike" @click="unlikeComment(comment)"><i-icon type="thumbsdown"></i-icon> {{ comment.unlike_num }}</span>
                  <span class="reply"><a @click="showEditor = !showEditor"><i-icon
                    type="forward"></i-icon> {{ $t('comments.reply') }}</a></span>
                  <!--<i-dropdown>-->
                  <!--<span class="i-dropdown-link">-->
                  <!--<i-icon type="android-share-alt"></i-icon> 分享 <i-icon type="arrow-down-b"></i-icon>-->
                  <!--</span>-->
                  <!--<i-dropdown-menu slot="list">-->
                  <!--<i-dropdown-item>菜单</i-dropdown-item>-->
                  <!--<i-dropdown-item>菜单</i-dropdown-item>-->
                  <!--<i-dropdown-item>菜单</i-dropdown-item>-->
                  <!--<i-dropdown-item disabled>菜单</i-dropdown-item>-->
                  <!--<i-dropdown-item divided>菜单</i-dropdown-item>-->
                  <!--</i-dropdown-menu>-->
                  <!--</i-dropdown>-->
                  <!--<span class="reply"><a>查看评论列表</a></span>-->
                </div>
                <div class="comment-area" v-show="showEditor">
                  <div class="reply-editor" :class="{spread: spreadEditor}">
                    <custom-mavon-editor :post="post"
                                         :replyToComment="comment"
                                         :theme="theme"
                                         @valueChanged="valueChanged"
                                         @publishedComment="publishedComment"></custom-mavon-editor>
                  </div>
                </div>
              </div>
            </i-col>
          </i-row>
        </div>
      </i-col>
    </i-row>
  </div>
</template>

<script type="text/ecmascript-6">
  import CustomMavonEditor from '@/components/views/CustomMavonEditor';
  // Api
  import API from 'API';
  // utils
  import MD5 from 'crypto-js/md5';
  // mixin
  import {mixin} from '@/common/js/utils';
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
      theme: {
        Type: String,
        default: ''
      }
    },
    mixins: [mixin],
    data() {
      return {
        showEditor: false,
        spreadEditor: false
      };
    },
    methods: {
      avatarImage(author) {
        // 随机固定的头像图片名
        let idStr = author.id + '';
        let start = author.nick_name.length + idStr.length > 31 ? 31 : author.nick_name.length + idStr.length;
        return MD5((author.nick_name + idStr)).toString().slice(start, start + 1) + '.png';
      },
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
      publishedComment(comment) {
        console.log(comment);
        this.$emit('publishedComment', comment);
      },
      likeComment(comment) {
        API.likeOrUnlikeComment({
          comment_id: comment.id,
          operation: true
        }).then((response) => {
          comment.like_num += 1;
          this.$Message.success('点赞成功');
        }).catch((error) => {
          console.log(error);
        });
      },
      unlikeComment(comment) {
        API.likeOrUnlikeComment({
          comment_id: comment.id,
          operation: false
        }).then((response) => {
          comment.unlike_num += 1;
          this.$Message.success('吐槽成功');
        }).catch((error) => {
          console.log(error);
        });
      }
    },
    mounted() {
      this.$nextTick(() => {
        // 添加图片前缀
        if (this.$refs.content) {
          this.resolveImageTagsUrl(this.$refs.content.querySelectorAll('img'));
        }
      });
    },
    components: {
      'custom-mavon-editor': CustomMavonEditor
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";
  @import "../.././../common/stylus/comment.styl";
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
