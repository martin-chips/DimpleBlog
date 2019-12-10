<template>
    <div class="social-section" id="comments" v-if="pageId != undefined" ref="socialSection">
        <Spin size="large" v-if="!allowComment" fix style="z-index: 101;"><Slot></Slot></Spin>

        <div class="comment-area">
            <div class="editor" :class="{spread: spreadEditor}">
                <CustomMavonEditor :pageId="pageId"
                                   :replyId="undefined"
                                   :allowComment="allowComment"
                                   :parentId="undefined"
                                   @valueChanged="valueChanged"
                                   @reloadCommentList="getCommentInfo"/>
            </div>
        </div>

        <div class="comment-list" v-if="commentList.length>0">
            <div v-for="comment in commentList" :key="comment.id">
                <CommentListCell :pageId="pageId"
                                 :commentLevel="1"
                                 :allowComment="allowComment"
                                 @reloadCommentList="getCommentInfo"
                                 :comment="comment"/>
                <CommentListCell v-for="subComment in comment.subCommentList"
                                 :key="subComment.id"
                                 :allowComment="allowComment"
                                 :pageId="pageId"
                                 @reloadCommentList="getCommentInfo"
                                 :commentLevel="2"
                                 :comment="subComment"/>
            </div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">

    import CustomMavonEditor from '../CustomMavonEditor';
    import CommentListCell from './CommentListCell';
    import {listComment} from '@/api'

    const COMMENT_DEFAULT_LIMIT = 10;
    export default {
        name: 'SocialSection',
        props: {
            pageId: {
                Type: Number,
                default: undefined
            },
            commentList: {
                type: Array,
                default: undefined
            },
            allowComment: {
                type: Boolean,
                default: undefined
            }
        },
        data() {
            return {
                spreadEditor: false,
                showSpin: true,
            };
        },
        methods: {
            getCommentInfo() {
                listComment(this.pageId).then((response) => {
                    this.commentList = response.data;
                    this.showSpin = false;
                }).catch((error) => {
                    console.log(error);
                });
            },
            valueChanged(flag) {
                this.spreadEditor = flag;
            }
        },
        components: {
             CustomMavonEditor,CommentListCell,
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
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
                height 250px
                transition height 0.9s
                &.spread
                    height 350px
        > .ivu-menu
            z-index 0
        .comment-list
            padding 15px
            background $default-background-color
</style>
