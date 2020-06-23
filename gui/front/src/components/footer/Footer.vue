<template>
    <div class="common-footer">
        <BackTop :height="300" :bottom="bottom" :right="right">
            <div class="top">
                <Tooltip :content="ExpandLeftColumn ? '退出通栏' : '通栏阅读'"
                         placement="left"
                         v-show="showExpandMenu">
                    <Icon :type="ExpandLeftColumn ? 'ios-contract' : 'ios-expand'" class="icon"
                          @click.native.stop.prevent="toggleExpand">
                    </Icon>
                </Tooltip>

                <Icon type="md-arrow-dropup" class="icon"
                      @mouseover.native="setButtonState('top', true, true)"
                      @mouseleave.native="setButtonState('top', false)"></Icon>
                <Icon type="md-arrow-dropdown" class="icon"
                      @click.native.stop.prevent="scrollDown"
                      @mouseover.native="setButtonState('bottom', true, true)"
                      @mouseleave.native="setButtonState('bottom', false)"></Icon>
            </div>
        </BackTop>

        <p class="copyright">
            <a target="_blank" href="http://www.miit.gov.cn">{{ $store.state.settings.F_ICP }}</a>
            <span>|</span>
            {{ $store.state.settings.F_Copyright }}
            <span>|</span>
            <span v-html="$store.state.settings.F_Copyright_Desc"></span>
        </p>
        <p class="copyright-english" v-html="$store.state.settings.F_Copyright_Desc_En"></p>
    </div>
</template>

<script type="text/ecmascript-6">
    import {
        mapState,
        mapMutations
    } from 'vuex';
    import {scrollTop} from '@/utils';

    export default {
        name: 'Footer',
        data() {
            return {
                bottom: 120,
                right: 20,
                topBtnMouseOver: false,
                bottomBtnMouseOver: false,
                animationFrameId: undefined
            };
        },
        mounted() {
            if (document.body.clientWidth <= 1200) {
                this.bottom = 20;
            }
        },
        computed: {
            ...mapState({
                ExpandLeftColumn: state => state.base.ExpandLeftColumn
            }),
            showExpandMenu: function () {
                try {
                    return (this.$route.name === 'article' ||
                        this.$route.name === 'book' ||
                        this.$route.name === 'book/note' ||
                        this.$route.name === 'movie' ||
                        this.$route.name === 'album'
                    ) && document.body.clientWidth >= 1200;
                } catch (error) {
                    return true;
                }
            }
        },
        methods: {
            ...mapMutations({
                updateExpandLeftColumn: 'base/UPDATE_EXPAND_LEFT_COLUMN'
            }),
            setButtonState(position, state, start) {
                this[(Object.is(position, 'bottom') ? 'bottomBtnMouseOver' : 'topBtnMouseOver')] = state;
                window.cancelAnimationFrame(this.animationFrameId);
                start && this.slowMoveToAnyWhere();
            },
            slowMoveToAnyWhere() {
                const step = () => {
                    let targetScrollY = window.scrollY;
                    const currentScrollY = document.body.scrollHeight - window.innerHeight;
                    if (this.bottomBtnMouseOver) targetScrollY += 1;
                    if (this.topBtnMouseOver) targetScrollY -= 1;
                    if (targetScrollY < 0) {
                        targetScrollY = 0;
                    } else if (targetScrollY >= currentScrollY) {
                        targetScrollY = currentScrollY;
                    }
                    const canScrollTo = targetScrollY > 0 && targetScrollY < currentScrollY;
                    if (!canScrollTo) return false;
                    window.scrollTo(0, targetScrollY);
                    if (this.bottomBtnMouseOver || this.topBtnMouseOver) {
                        this.animationFrameId = window.requestAnimationFrame(step);
                    } else {
                        window.cancelAnimationFrame(this.animationFrameId);
                        return false;
                    }
                };
                this.animationFrameId = window.requestAnimationFrame(step);
            },
            scrollDown() {
                if (document.body.clientWidth >= 1200) {
                    const sTop = document.documentElement.scrollTop || document.body.scrollTop;
                    scrollTop(window, sTop, sTop + 500, 1000);
                }
            },
            toggleExpand() {
                this.updateExpandLeftColumn(!this.ExpandLeftColumn);
            }
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../../common/stylus/theme.styl";
    .common-footer
        padding 15px 5px
        margin-top 50px
        text-align center
        background $default-background-color
        border-top 1px solid $default-border-color
        z-index 99
        .top
            text-align center
            border-radius 2px
            i.icon
                display block
                margin-bottom 5px
                padding 6px 12px
                color $default-background-color
                background rgba($default-link-color, .7)
                cursor pointer
                &:hover
                    background rgba($default-link-hover-color, .7)
                &::before
                    display inline-block
                    font-size 20px
                    width 15px
        .side-menu
            right 20px
        p
            margin 0 auto
            font-size 15px
            line-height 22px
            font-weight 100
            span
                padding 0 10px
        .copyright, .copyright-english
            margin 10px
            color $default-title-color
            a
                color $default-link-color
                &:hover
                    color $default-link-hover-color
</style>
