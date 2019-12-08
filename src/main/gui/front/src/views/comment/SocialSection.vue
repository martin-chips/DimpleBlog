<template>
    <div class="social-section" id="comments" v-if="article != undefined" ref="socialSection">
        <i size="large" v-if="showSpin" fix style="z-index: 99;"></i>

        <div class="comment-area">
            <div class="editor" :class="{spread: spreadEditor}">
                <custom-mavon-editor :post="article"
                                     :replyId="undefined"
                                     :parentId="undefined"
                                     @valueChanged="valueChanged"
                                     @reloadCommentList="getCommentInfo"></custom-mavon-editor>
            </div>
        </div>

        <div class="comment-list" v-if="article.commentList.length>0">
            <div v-for="comment in article.commentList" :key="comment.id">
                <comment-cell-list :post="article"
                                   :commentLevel="1"
                                   @reloadCommentList="getCommentInfo"
                                   :comment="comment"></comment-cell-list>
                <comment-cell-list v-for="subComment in comment.subCommentList"
                                   :key="subComment.id"
                                   :post="article"
                                   @reloadCommentList="getCommentInfo"
                                   :commentLevel="2"
                                   :comment="subComment"></comment-cell-list>
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
        name: 'social-section',
        props: {
            article: {
                Type: Object,
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
                listComment(this.$route.params.id).then((response) => {
                    this.article.commentList = response.data;
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
            'custom-mavon-editor': CustomMavonEditor,
            'comment-cell-list': CommentListCell,
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
