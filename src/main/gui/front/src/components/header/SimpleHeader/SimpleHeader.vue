<template>
    <div class="simple-header">
        <head-room :speed="600" :zIndex="1003" style="position: fixed;">
            <header>
                <div id="mobile-bar">
                    <router-link class="logo" to="/">
                        <img src="../../../assets/logo.png"/>
                    </router-link>
                    <a class="menu-button" @click="showMobileMenu"></a>
                </div>
                <div id="header" class="show">
                    <router-link id="logo" to="/">
                        <img src="../../../assets/logo.png">
                        <div class="info">
                            <span class="name">{{$store.state.settings.F_Title}}}</span>
                            <span class="desc">{{$store.state.settings.F_Title_Desc}}</span>
                        </div>
                    </router-link>
                    <ul id="nav">
                        <!-- 类别导航 -->
                        <li class="nav-dropdown-container">
                            <router-link class="nav-link" to="#">
                                分类 <span class="arrow"></span>
                            </router-link>
                            <ul class="nav-dropdown">
                                <li v-for="item in categories">
                                    <router-link class="nav-link" :to="routerLink(item)">
                                        {{item.title}}
                                    </router-link>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-dropdown-container" v-for="item in menus">
                            <a v-if="item.target ==true" class="nav-link" :href="item.url" :target="item.target">
                                {{item.title}}
                            </a>
                            <router-link v-if="item.target == false" class="nav-link" :to="item.url">
                                {{item.title}}
                            </router-link>
                        </li>
                        <li class="switches">
                            <ButtonGroup size="small">
                                <Button style="width:30px;" :icon="isDark ? 'ios-sunny' : 'ios-moon'" ghost
                                        @click="toggleTheme">
                                </Button>
                                <Button style="width:40px;" icon="ios-log-in" @click="toIndex" ghost>
                                </Button>
                            </ButtonGroup>
                        </li>
                    </ul>
                </div>
            </header>
        </head-room>
        <side-bar :categories="categories" :menus="menus" ref="sidebar"></side-bar>
    </div>
</template>

<script>
    import SideBar from "./SideBar";
    import {headroom} from 'vue-headroom';
    import {mapState, mapMutations, mapActions} from 'vuex';
    import {loadFromLocal} from '@/utils';

    export default {
        name: 'simple-header',
        data() {
            return {
                searchKeyWords: '',
                searchResult: [],
                showMobileSearchView: false
            };
        },
        mounted() {
            if (!this.$store.state.base.categories) this.getCategories();
            if (!this.$store.state.base.menus) this.getMenus();
            // 获取皮肤信息
            this.checkTheme();
        },
        computed: {
            ...mapState({
                categories: state => state.base.categories,
                menus: state => state.base.menus,
                siteTheme: state => state.base.siteTheme
            }),
            isDark: function () {
                return this.siteTheme === 'dark';
            }
        },
        methods: {
            ...mapMutations({
                updateSiteTheme: 'base/UPDATE_SITE_THEME'
            }),
            ...mapActions({
                getCategories: 'base/GET_CATEGORIES',
                getMenus: 'base/GET_MENUS'
            }),
            //跳转到后台首页
            toIndex() {
                window.open("http://admin.bianxiaofeng.com", '_blank')
            },
            //切换主题
            toggleTheme() {
                console.log(this.siteTheme);
                this.updateSiteTheme(this.siteTheme === 'dark' ? 'default' : 'dark');
            },
            checkTheme() {
                const theme = loadFromLocal('siteConfig', 'theme', 'default');
                this.updateSiteTheme(theme);
            },
            rootRouterLink(category) {
                let router = {};
                router.name = category.category_type;
                return router;
            },
            routerLink(category) {
                let router = {};
                router.name = "articles/category";
                router.params = {};
                router.params['id'] = category.id;
                return router;
            },
            showMobileMenu() {
                // 显示手机端的菜单
                let sidebar = this.$refs.sidebar;
                sidebar.toggleSideBar();
            },
            showSearchView() {
                this.showMobileSearchView = !this.showMobileSearchView;
            },
        },
        components: {
            'head-room': headroom,
            'side-bar': SideBar,
            // 'search-view': SearchView
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "stylus/header.styl";
</style>
