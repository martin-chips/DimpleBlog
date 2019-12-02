<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: 'app'
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "./common/stylus/reset.styl";
    #app
        -webkit-font-smoothing antialiased
        -moz-osx-font-smoothing grayscale
        color: #2c3e50
</style>