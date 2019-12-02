<template>
    <div class="main-wrapper">
        <router-view name="header" keep-alive></router-view>
        <transition name="el-fade-in-linear">
            <router-view name="content" class="transition-box"></router-view>
        </transition>
        <router-view name="footer" keep-alive></router-view>
    </div>
</template>

<script>
    export default {
        name: 'index'
    };
</script>


<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../common/stylus/theme-config.styl";
    @import "../common/stylus/index.styl";
    .main-wrapper
        width 100%
        min-height 100vh
        margin 0 auto
</style>
