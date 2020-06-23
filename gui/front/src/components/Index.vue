<template>
    <div class="main-wrapper">
        <router-view name="header" keep-alive></router-view>
        <transition name="fade" :duration="100">
            <router-view name="content"></router-view>
        </transition>
        <router-view name="footer" keep-alive></router-view>
    </div>
</template>

<script>
    import {listNotice} from "@/api"
    export default {
        name: 'index',

        created() {
            listNotice().then(response => {
                let list = response.data;
                for (let i = 0; i < list.length; i++) {
                    let obj = list[i];
                    //通知
                    if (obj.type === "1") {
                        this.$Notice.info({
                            title: obj.title,
                            desc: obj.htmlContent
                        });
                    } else {
                        //公告
                        this.$Modal.info({
                            title: obj.title,
                            content: obj.htmlContent,
                            okText:'我知道了',
                            closeOnClickModal:true,
                            scrollable:true,
                        })
                    }
                }
            });
        },
        //创建前设置
        beforeCreate () {
            document.body.classList.add("custom-scrollbar")
        },
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
