<template>
    <div class="about">
        <img class="background" src="./../assets/background.jpg" alt="">
        <img class="avatar" src="./../assets/avatar.png" alt="">
        <p class="name">边小丰</p>
        <p class="desc">Dimple Blog</p>
        <!--    <ul class="social">-->
        <!--      <li v-for="social in bloggerInfo.socials">-->
        <!--        <a :href="social.url" :target="social.url.indexOf(siteImageBaseUrl) === 0 ? '_self' : '_blank'"><img-->
        <!--          :src="social.image" alt=""></a>-->
        <!--      </li>-->
        <!--    </ul>-->
        <!--    <div class="master" v-if="bloggerInfo.masters.length > 0">-->
        <!--      <div class="line"></div>-->
        <!--      <h4>{{ $t('others.master') }}</h4>-->
        <!--      <div class="progresses">-->
        <!--        <i-row v-for="master in bloggerInfo.masters" :gutter="20" :key="master.id">-->
        <!--          <i-col :span="6">-->
        <!--            <p class="title">{{ master.name }}</p>-->
        <!--          </i-col>-->
        <!--          <i-col :span="18">-->
        <!--            <i-progress status="normal" :hide-info="true" :stroke-width="8" :percent="master.experience"-->
        <!--                        class="bar"></i-progress>-->
        <!--          </i-col>-->
        <!--        </i-row>-->
        <!--      </div>-->
        <!--    </div>-->
    </div>
</template>

<script>
    export default {
        name: 'About',
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../common/stylus/theme.styl";
    .about
        position relative
        text-align center
        padding-bottom 20px
        margin-top 0
        background-color $default-background-color
        border 1px solid $default-border-color

        img.background
            position absolute
            top 0
            left 0
            right 0
            width 100%
            height 125px
            z-index 1

        img.avatar
            position relative
            margin 75px auto 15px
            width 100px
            height 100px
            border-radius 50%
            border 5px solid $default-border-color
            z-index 9

        .name
            font-size 22px
            color $default-title-color
            line-height 30px
            font-weight 700

        .desc
            font-size 15px
            color $default-desc-color
            line-height 30px
            font-weight 100

        .social
            text-align center
            display flex
            justify-content space-around
            padding 0 20px
            margin-top 15px

            > li
                flex 1
                padding 8px
                max-width 60px
                max-height 60px
                a
                    display block
                    img
                        width 100%

        .master
            .line
                height 1px
                background-color $default-border-color
                margin 10px 20px

            h4
                font-size 19px
                margin 30px 0 20px
                font-weight 600
                color $default-title-color

            .progresses
                padding 0 20px

                p.title
                    height 38px
                    line-height 38px
                    text-align right
                    color $default-title-color

                .bar
                    margin 10px 0
</style>
