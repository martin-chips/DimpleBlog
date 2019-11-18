<template>
  <div class="side-bar" @touchmove.stop.prevent :class="{ open: show }">
    <div class="main-area">
      <div class="top-wrapper" @touchmove.stop>
        <div class="top-area">
          <img :src="siteInfo.background" alt="">
          <div class="site-info">
            <h1 class="site-name">{{$store.state.settings.F_Title}}}</h1>
            <h1 class="site-desc">{{ $store.state.settings.F_Title_Desc }}</h1>
          </div>
        </div>
        <div class="sidebar-menus">
          <div class="site-nav">
            <p>
              <i class="el-icon-map-location"></i>
              网站导航
            </p>
            <i-switch v-model="showNav">
              <span slot="open">开启</span>
              <span slot="close">关闭</span>
            </i-switch>
          </div>
          <ul class="nav-menu" :class="{'hide': !showNav}">
            <!-- 类别导航 -->
            <li class="nav-dropdown-container" v-for="category_level1 in allCategorysInfo">
              <i-icon type="minus-round"></i-icon>&nbsp;
              <router-link class="nav-link" :to="rootRouterLink(category_level1)">
                {{ category_level1[resolveI18N('name')] }} <span class="arrow"></span>
              </router-link>
              <ul class="nav-dropdown" v-if="category_level1.sub_category.length > 0">
                <li v-for="category_level2 in category_level1.sub_category">
                  <i-icon type="minus-round"></i-icon>
                  <router-link class="nav-link" :to="routerLink(category_level2)">
                    {{category_level2[resolveI18N('name')]}}
                  </router-link>
                </li>
              </ul>
            </li>
            <!-- 自定义的导航 -->
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
          <div class="sidebar-toc-list" ref="list" v-show="showToc">
            <div class="site-nav">
              <p>
                <i-icon type="ios-flower-outline"></i-icon>
                {{ $t('article.typeName') + $t('others.toc') }}
              </p>
            </div>
            <div id="sidebar-toc" class="list" @click.prevent></div>
          </div>
        </div>
      </div>
      <div class="sidebar-operate-area" @click.stop>
        <i-button-group size="small">
          <i-button style="width:30px;" type="ghost" :icon="isDark ? 'android-sunny' : 'ios-moon'" @click="toggleTheme">
          </i-button>
          <i-button style="width:40px;" type="ghost" @click="toggleLanguage">
            {{ $i18n.locale === 'EN' ? '中文' : 'EN' }}
          </i-button>
        </i-button-group>
      </div>
    </div>
    <div class="mask" @click.prevent="toggleSideBar"></div>
  </div>
</template>

<script type="text/ecmascript-6">
  import {
    mapState,
    mapMutations
  } from 'vuex';
  // mixin
  import {mixin} from '@/common/js/utils';

  export default {
    name: 'side-bar',
    props: {
      categorys: {
        Type: Array,
        default: []
      }
    },
    data() {
      return {
        show: false,
        showNav: false
      };
    },
    mixins: [mixin],
    beforeRouteUpdate(to, from, next) {
      next();
      console.log(this.$route.path);
    },
    computed: {
      ...mapState({
        siteInfo: state => state.base.siteInfo,
        bloggerInfo: state => state.base.bloggerInfo,
        siteTheme: state => state.base.siteTheme
      }),
      isDark: function () {
        return this.siteTheme === 'dark';
      },
      showToc: function () {
        return (this.$route.name === 'article' ||
          this.$route.name === 'book' ||
          this.$route.name === 'book/note' ||
          this.$route.name === 'movie' ||
          this.$route.name === 'album'
        );
      }
    },
    methods: {
      ...mapMutations({
        updateSiteTheme: 'base/UPDATE_SITE_THEME'
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
      toggleSideBar() {
        this.show = !this.show;
        this.showNav = !(this.$route.name === 'article' ||
          this.$route.name === 'book' ||
          this.$route.name === 'book/note' ||
          this.$route.name === 'movie' ||
          this.$route.name === 'album'
        );
      },
      toggleLanguage() {
        if (this.$i18n.locale === 'CN') {
          this.$i18n.locale = 'EN';
        } else if (this.$i18n.locale === 'EN') {
          this.$i18n.locale = 'CN';
        }
      },
      toggleTheme() {
        this.updateSiteTheme(this.siteTheme === 'dark' ? 'default' : 'dark');
      }
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "./stylus/sidebar.styl";
</style>
