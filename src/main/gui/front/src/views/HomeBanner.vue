<template>
    <div class="article-home-banner">
        <Row class="row">
            <Col :xs="24" :sm="24" :md="24" :lg="17" class="col">
                <swiper :options="leftSwiperOption" ref="leftSwiper" class="gallery">
                    <swiper-slide v-for="item in carouselList">
                        <a @click.prevent="gotoPostDetail(item)" :href="`article/${item.id}`">
                            <span class="title">{{ item.title}}</span>
                            <img :src="item.headerImg" :title="item.title">
                        </a>
                    </swiper-slide>
                    <div class="swiper-pagination" slot="pagination"></div>
                    <div class="swiper-button-prev" slot="button-prev">
                        <Icon type="ios-arrow-back"></Icon>
                    </div>
                    <div class="swiper-button-next" slot="button-next">
                        <Icon type="ios-arrow-forward"></Icon>
                    </div>
                </swiper>
            </Col>
            <Col :xs="0" :sm="0" :md="0" :lg="7" class="col">
                <swiper :options="rightSwiperOption" ref="rightSwiper" class="gallery right-gallery">
                    <swiper-slide v-for="item in carouselList">
                        <div class="carousel-infos">
                            <p class="title">{{ item.title | textLineBreak(35) }}</p>
                            <p class="desc">
                                {{ item.description | textLineBreak(70) }}
                            </p>
                            <Button size="large" type="primary" @click.prevent="gotoPostDetail(item)"
                                    :href="`article/${item.id}`">详情
                            </Button>
                        </div>
                    </swiper-slide>
                </swiper>
            </Col>
        </Row>
    </div>
</template>

<script type="text/ecmascript-6">
    import { mixin} from '@/utils';
    // require styles
    import 'swiper/dist/css/swiper.css'

    import { swiper, swiperSlide } from 'vue-awesome-swiper'

    export default {
        name: 'article-home-banner',
        components: {
            swiper,
            swiperSlide
        },
        props: {
            carouselList: {
                type: Array,
                default: undefined
            }
        },
        mixins: [mixin],
        data() {
            return {
                leftSwiperOption: {
                    lazy: true,
                    centeredSlides: true,
                    loop: true,
                    effect: 'fade',
                    autoplay: {
                        delay: 5000,
                        disableOnInteraction: false
                    },
                    pagination: {
                        el: '.swiper-pagination',
                        clickable: true
                    },
                    navigation: {
                        nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev'
                    }
                },
                rightSwiperOption: {
                    direction: 'vertical',
                    loop: true
                }
            };
        },
        mounted() {
            this.$nextTick(() => {
                const rightSwiper = this.$refs.rightSwiper.swiper;
                const leftSwiper = this.$refs.leftSwiper.swiper;
                rightSwiper.controller.control = leftSwiper;
                leftSwiper.controller.control = rightSwiper;
            })
        },
        methods:{
            gotoPostDetail(item) {
                if (item.target) {
                    window.open(item.url, '_blank')
                } else {
                    window.open(item.url)
                }
            }
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../common/stylus/theme.styl";
    .article-home-banner
        height 100%
        .row, .col
            height 100%
        .gallery
            width 100%
            height 100%
            border 1px solid $default-border-color
            a
                position relative
                display block
                height 100%
                overflow hidden
                img
                    height 100%
                    width 100%
                .title
                    position absolute
                    display none
                    top 5px
                    right 5px
                    padding 4px
                    background rgba($default-background-color, 0.6)
                    color $default-title-color
                    font-size 13px
                    max-width 90%
                    @media only screen and (max-width: $responsive-lg)
                        display block
            .carousel-infos
                height 100%
                width 100%
                padding 20px
                background-color $default-background-color
                .title
                    font-size 23px
                    line-height 31px
                    margin-bottom 10px
                    color $default-title-color
                .desc
                    font-size 15px
                    font-weight 300
                    line-height 20px
                    margin-bottom 10px
                    color $default-desc-color
            .swiper-pagination-bullet
                background $default-background-color
            .swiper-pagination-bullet-active
                background $default-background-color
            .swiper-button-prev, .swiper-button-next
                text-align center
                background-image none
                color $default-background-color
                > i
                    font-size 45px
        .right-gallery
            border-left none
</style>
