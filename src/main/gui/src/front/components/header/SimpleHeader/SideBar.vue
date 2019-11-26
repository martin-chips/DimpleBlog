<template>
  <div class="side-bar" @touchmove.stop.prevent :class="{ open: show }">
    <div class="main-area">
      <div class="top-wrapper" @touchmove.stop>
        <div class="top-area">
          <img src="https://www.bianxiaofeng.com/front/images/leaveComment-bg.png" alt="">
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
            <el-switch v-model="showNav">
              <span slot="open">开启</span>
              <span slot="close">关闭</span>
            </el-switch>
          </div>
          <ul class="nav-menu" :class="{'hide': !showNav}">
            <!-- 类别导航 -->
            <li class="nav-dropdown-container">
              <router-link class="nav-link" to="#">
                分类<span class="arrow"></span>
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
          </ul>
          <div class="sidebar-toc-list" ref="list" v-show="showToc">
            <div class="site-nav">
              <p>
                <i class="el-icon-tickets"></i>
                文章目录
              </p>
            </div>
            <div id="sidebar-toc" class="list" @click.prevent></div>
          </div>
        </div>
      </div>
      <div class="sidebar-operate-area" @click.stop>
        <el-button-group>
          <el-button :icon="isDark?'el-icon-cloudy-and-sunny':'el-icon-sunny'" plain class="ivu-btn-ghost"
                     size="mini" @click="toggleTheme"></el-button>
          <el-button class="ivu-btn-ghost" icon="el-icon-monitor" plain @click="toIndex"
                     size="mini">
          </el-button>
        </el-button-group>
      </div>
    </div>
    <div class="mask" @click.prevent="toggleSideBar"></div>
  </div>
</template>

<script>
  import {mapState, mapMutations, mapActions} from 'vuex';

  export default {
    name: 'side-bar',
    props: {
      categories: {
        Type: Array,
        default: []
      },
      menus: {
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
    beforeRouteUpdate(to, from, next) {
      next();
      console.log(this.$route.path);
    },
    computed: {
      ...mapState({
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
      //跳转到后台首页
      toIndex() {
        this.$router.push({path: '/index'});
      },
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
      toggleTheme() {
        this.updateSiteTheme(this.siteTheme === 'dark' ? 'default' : 'dark');
      }
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "./stylus/sidebar.styl";
</style>
