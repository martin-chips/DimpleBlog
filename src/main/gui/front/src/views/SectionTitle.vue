<template>
    <div class="section-title">
        <div class="title">
            <span class="square"></span>
            <span class="main-title">{{mainTitle}}</span>
            <span class="vertical-line"></span>
            <span class="sub-title">{{subTitle}}</span>
            <span class="view-more" v-if="to !== undefined">
                 <router-link :to="to">
                     {{tipText ? tipText : "查看更多"}}<Icon type="md-arrow-dropright"/>
                 </router-link>
             </span>
        </div>
        <div class="menu">
            <ul class="list clearfix" v-if="controlMenus.length > 0">
                <li v-for="menu in controlMenus" :key="menu.title">
                    <a :class="{ active: menu.selected }" @click="menusControl(menu)">{{menuTitle(menu)}}</a>
                </li>
            </ul>
            <div class="date-picker" v-if="withTimeSelect">
                <DatePicker type="daterange" :options="datePickerOptions" confirm :split-panels="true"
                            placement="bottom-end" placeholder="请选择日期" @on-clear="clearDateSelect"
                            @on-ok="confirmDateSelect" @on-change="changeDate"
                            style="width: 180px;"></DatePicker>
            </div>
            <div class="refresh" v-if="withRefresh">
                <a @click="refresh" title="刷新">
                    <Icon type="md-refresh"/>
                </a>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'SectionTitle',
        props: {
            mainTitle: '',
            subTitle: '',
            tipText: {
                default: undefined
            },
            menus: {
                Type: Array,
                default: undefined
            },
            to: {
                Type: Object,
                default: undefined
            },
            withRefresh: {
                Type: Boolean,
                default: false
            },
            withTimeSelect: {
                Type: Boolean,
                default: false
            },
            datePickerOptions: undefined
        },
        data() {
            return {
                copiedMenus: undefined,
                selectedDateRange: []
            };
        },
        computed: {
            controlMenus: function () {
                // 复制一份内部menus使用
                if (this.copiedMenus === undefined || this.menus !== undefined) {
                    this.copiedMenus = [].concat(JSON.parse(JSON.stringify(this.menus)));
                    return this.copiedMenus;
                } else {
                    return [];
                }
            }
        },
        methods: {
            menuTitle(menu) {
                return menu.selected ? (menu.selectedTitle !== undefined ? menu.selectedTitle : menu.title) : menu.title;
            },
            //刷新
            refresh() {
                this.copiedMenus = undefined;
                this.$emit('refresh');
            },
            //跳转
            tipAction() {
                this.$emit('tipAction');
                this.$router.push(this.to);
            },
            menusControl(menu) {
                menu.selected = !menu.selected;
                this.$emit('menusControl', [menu.method, menu.selected]);
            },
            clearDateSelect() {
                this.$emit('clearDateSelect');
            },
            changeDate(dateRange) {
                this.selectedDateRange = dateRange;
            },
            confirmDateSelect() {
                this.$emit('confirmDateSelect', this.selectedDateRange);
            }
        }
    }
    ;
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../common/stylus/theme.styl";
    .section-title
        display flex
        justify-content space-between
        align-items flex-start
        height 60px
        line-height 40px
        padding 10px 0
        margin-bottom 10px
        box-sizing border-box
        @media only screen and (max-width: $responsive-sm)
            padding 5px 0
            margin-bottom 5px
            height 50px

        .title
            .square
                display inline-block
                height 30px
                width 30px
                background $default-sectionTitle-square-color
                vertical-align middle

            .main-title
                display inline-block
                vertical-align middle
                font-size 22px
                padding 0 10px
                color $default-sectionTitle-title-color

            .vertical-line
                display inline-block
                height 20px
                width 1px
                background-color $default-sectionTitle-line-color
                vertical-align middle

            .sub-title
                display inline-block
                vertical-align middle
                font-size 18px
                padding-left 10px
                color $default-sectionTitle-subtitle-color

            .view-more
                display inline-block
                vertical-align middle
                font-size 14px
                padding-left 10px

                a
                    font-weight 500
                    color $default-title-hover-color
                    cursor pointer

                    &:hover
                        text-decoration underline

        .menu
            display flex
            height 100%
            line-height inherit
            float right
            @media only screen and (max-width: 768px)
                display none

            ul.list
                li
                    float left
                    margin 0 6px

                    a
                        color $default-link-color
                        padding 6px 0

                        &:hover, &.active
                            color $default-link-hover-color
                            border-bottom 2px solid $default-link-hover-color

            .date-picker
                width 180px
                margin-left 15px
                font-size 13px

            .refresh
                margin-left 15px
                line-height inherit

                a
                    font-size 20px
                    color $default-link-color
                    cursor pointer

                    &:hover
                        color $default-link-hover-color
</style>
