<template>
    <div class="comment-list-cell" :id="'comment_'+comment.id">
        <Row :style="{'margin-left':commentLevel==2?'4rem':''}">
            <Col :xs="cellSpan('xs')" :sm="cellSpan('sm')" :md="cellSpan('md')" :lg="cellSpan('lg')"
                 :xl="cellSpan('xl')">
                <div class="comment-main">
                    <Row :gutter="8">
                        <Col :xs="cellLeftSpan('xs')" :sm="cellLeftSpan('sm')" :md="cellLeftSpan('md')"
                             :lg="cellLeftSpan('lg')" :xl="cellLeftSpan('xl')">
                            <div class="avatar">
                                <img src="../../assets/default_head_img.gif" v-if="comment.avatar==undefined" alt="">
                                <img :src="comment.avatar" v-else alt="">
                            </div>
                        </Col>
                        <Col :xs="cellRightSpan('xs')" :sm="cellRightSpan('sm')" :md="cellRightSpan('md')"
                             :lg="cellRightSpan('lg')" :xl="cellRightSpan('xl')">
                            <div class="content">
                                <p class="title">
                  <span class="name">
                    <a>{{ comment.nickName }}</a>
                  </span>
                                    <span class="name-tag" v-if="comment.adminReply">
                    站长
                  </span>
                                    <span class="reply-icon" v-if="comment.replyNickName != undefined">
                    &nbsp;<Icon type="ios-at"></Icon>
                  </span>
                                    <span class="reply-name" v-if="comment.replyNickName != undefined">
                    <a>{{ comment.replyNickName }}</a>
                  </span>
                                    <span class="name-tag"
                                          v-if="comment.replyNickName != undefined && comment.adminReply==true">站长</span>
                                    <span class="time">{{ comment.createTime | socialDate }}</span>
                                </p>
                                <div class="comment-main-content" v-if="comment.content"
                                     v-html="comment.content"
                                     ref="content"></div>
                                <div class="operate-area">
                                  <span class="like" @click="likeComment(comment)" title="点赞">
                                   <a>
                                     <Icon type="ios-heart"/>  {{ comment.good }}
                                   </a>
                                  </span>
                                    <span class="unlike" @click="unlikeComment(comment)" title="踩">
                                 <a>
                                     <Icon type="ios-thumbs-down"/>    {{ comment.bad }}
                                   </a>
                                     </span>
                                    <span class="reply">
                                    <a @click="displayEditor" title="回复">
                                        <Icon type="ios-redo"/>  回复
                                    </a>
                                  </span>
                                </div>
                                <div class="comment-area" v-show="showEditor">
                                    <div class="reply-editor" :class="{spread: spreadEditor}">
                                        <CustomMavonEditor :pageId="pageId"
                                                           :allowComment="allowComment"
                                                           :replyId="comment.id"
                                                           :parentId="commentLevel==1?comment.id:comment.parentId"
                                                           :replyToComment="comment"
                                                           @valueChanged="valueChanged"
                                                           @reloadCommentList="reloadCommentList"></CustomMavonEditor>
                                    </div>
                                </div>
                            </div>
                        </Col>
                    </Row>
                </div>
            </Col>
        </Row>
    </div>
</template>

<script type="text/ecmascript-6">
    import CustomMavonEditor from '../CustomMavonEditor';
    // mixin
    import {mixin} from '@/utils';
    import {goodComment, badComment} from "@/api";

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
        name: 'CommentListCell',
        props: {
            pageId: {
                type: Number,
                default: undefined
            },
            allowComment: {
                type: Boolean,
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
        methods: {
            reloadCommentList() {
                this.$emit('reloadCommentList');
                this.showEditor = false;
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
            displayEditor() {
                this.showEditor = !this.showEditor;
            },
            likeComment(comment) {
                goodComment(comment.id).then(response => {
                    comment.good += 1;
                    this.$Message.success("点赞 +1");
                }).catch(error => {
                    console.log(error)
                });
            },
            unlikeComment(comment) {
                badComment(comment.id).then(response => {
                    comment.bad += 1;
                    this.$Message.success('鄙视+1');
                }).catch(error => {
                    console.log(error)
                });
            }
        },
        components: {
            CustomMavonEditor
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../../common/stylus/theme.styl";
    @import "../../common/stylus/comment.styl";
    .comment-list-cell
        position relative
        text-align left

        a
            color #7e8c8d
            text-decoration none
            -webkit-backface-visibility hidden

        .avatar
            text-align center

            img
                border-radius 100%
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
                height 250px
                transition height 0.9s

                &.spread
                    height 300px

            p.comment-tip
                a
                    font-size 14px

                    &:hover
                        color $default-link-hover-color
</style>
