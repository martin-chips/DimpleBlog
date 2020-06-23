<template>
    <div class="panel">
        <h4 :class="titleAlign">{{title}}</h4>
        <slot name="content"></slot>
    </div>
</template>

<script type="text/ecmascript-6">
    export default {
        name: 'panel',
        props: {
            title: {
                default: ''
            },
            titleAlign: {
                default: 'left'
            }
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../common/stylus/theme.styl";
    .panel
        position relative
        background $default-background-color

        h4
            font-size 18px
            padding 13px 20px 13px
            line-height 18px
            text-align left
            color $default-title-color
            border-left 5px solid $default-link-hover-color

            &.center
                text-align center
                border 1px solid $default-border-color

            &.left
                text-align left
                border-left 5px solid $default-link-hover-color
                border-right none

            &.right
                text-align right
                border-right 5px solid $default-link-hover-color
                border-left none
</style>
