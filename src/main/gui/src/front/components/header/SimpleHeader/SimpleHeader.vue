<template>
  <div class="simple-header">
    <head-room :speed="600" :zIndex="1003" style="width: 100%;">
      <header>
        <div id="mobile-bar">
          <router-link class="logo" to="/">
            <img src="https://bianxiaofeng.com/front/images/logo.png"/>
          </router-link>
          <!--<transition name="fade">-->
          <!--<div class="search-area" v-show="showMobileSearchView">-->
          <!--<search-view></search-view>-->
          <!--</div>-->
          <!--</transition>-->
          <a class="menu-button" @click="showMobileMenu"></a>
          <!--<a class="search-button" @click="showSearchView"></a>-->
        </div>
        <div id="header" class="show">
          <router-link id="logo" to="/">
            <img src="https://bianxiaofeng.com/front/images/logo.png">
            <div class="info">
              <span class="name">{{$store.state.settings.F_Title}}}</span>
              <span class="desc">{{$store.state.settings.F_Title_Desc}}</span>
            </div>
          </router-link>
          <ul id="nav">
            <!-- 搜索框 -->
            <!--<li class="search-area">-->
            <!--<search-view></search-view>-->
            <!--</li>-->
            <!-- 类别导航 -->
            <li class="nav-dropdown-container">
              <router-link class="nav-link" to="/">
                分类 <span class="arrow"></span>
              </router-link>
              <ul class="nav-dropdown">
                <li>
                  <router-link class="nav-link" to="/">
                    分类1
                  </router-link>
                </li>
              </ul>
            </li>
            <li class="nav-dropdown-container">
              <router-link class="nav-link" to="/">
                时光轴
              </router-link>
            </li>
            <li class="nav-dropdown-container">
              <router-link class="nav-link" to="/">
                友链
              </router-link>
            </li>
            <li class="nav-dropdown-container">
              <router-link class="nav-link" to="/">
                留言
              </router-link>
            </li>
            <li class="nav-dropdown-container">
              <router-link class="nav-link" to="/">
                关于
              </router-link>
            </li>
          </ul>
        </div>
      </header>
    </head-room>
    <side-bar :categorys="allCategorysInfo" ref="sidebar"></side-bar>
  </div>
</template>

<script type="text/ecmascript-6">
  import {
    mapState,
    mapMutations,
    mapActions
  } from 'vuex';
  // import SideBar from '@/components/header/SimpleHeader/SideBar';
  // import SearchView from '@/components/views/Search/SearchView';
  import {headroom} from 'vue-headroom';
  import {loadFromLocal, saveToLocal} from '@/utils/front/utils';

  export default {
    name: 'simple-header',
    data() {
      return {
        searchKeyWords: '',
        searchResult: [],
        showMobileSearchView: false
      };
    },
    asyncData({store}) {
      return Promise.all([
        store.dispatch('base/GET_SITE_INFO'),
        store.dispatch('base/GET_ALL_CATEGORY')
      ]);
    },
    mounted() {
      if (!this.$store.state.base.allCategoryInfo) this.getAllCategory();
    },
    computed: {
      ...mapState({
        siteTheme: state => state.base.siteTheme
      }),
    },
    methods: {
      ...mapMutations({
        updateSiteTheme: 'base/UPDATE_SITE_THEME'
      }),
      ...mapActions({
        getAllCategory: 'base/GET_ALL_CATEGORY'
      }),
      rootRouterLink(category) {
        let router = {};
        router.name = category.category_type;
        return router;
      },
      routerLink(category) {
        let router = {};
        router.name = category.category_type;
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
      // 'side-bar': SideBar,
      // 'search-view': SearchView
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "stylus/header.styl";
</style>
