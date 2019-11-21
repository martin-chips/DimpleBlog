<template>
  <div class="recommend" v-if="recommends.length > 0">
    <panel title="推荐阅读">
      <div slot="content" class="content">
        <div class="top">
          <p class="title">
            <a @click.prevent="gotoPostDetail(first)"
               :href="`article/${first.id}`">
              {{ first.title }}
            </a>
          </p>
          <div class="tags">
            <i-tag :color="tag.color" type="border" v-for="tag in first.tags" :key="tag.id"
                   class="border-tag"> {{tag.title}}
            </i-tag>
          </div>
          <p class="info">
            <span class="time"><a>{{ articleSlice(0, 1)[0].createTime | socialDate }}</a></span>
            <span class="likes">
              <a @click="likePost(first)"><i class="el-icon-star-on"></i> {{ first.like }} </a>
            </span>
            <span class="comments"><a><i
              class="el-icon-chat-dot-square"></i>{{ first.commentCount }} </a>
            </span>
            <span class="readings"><a><i class="el-icon-view"></i> {{ first.click }} </a></span>
          </p>
          <!--          <div class="img" v-if="articleSlice(0, 1)[0].front_image">-->
          <!--            <img :src="articleSlice(0, 1)[0].front_image" :alt="articleSlice(0, 1)[0].title">-->
          <!--          </div>-->
          <p class="desc" v-if="articleSlice(0, 1)[0].summary">
            {{ first.summary |textLineBreak(60) }}</p>
        </div>
        <ul class="others">
          <li v-for="article in articleSlice(1)">
            <p class="title">
              <a @click.prevent="gotoPostDetail(article)" :href="`article/${article.id}`">{{article.title}}</a>
            </p>
            <p class="info">
              <span class="time">{{ article.createTime | socialDate }}</span>
              <span class="likes">
                <a @click="likePost(article)"><i class="el-icon-star-on"></i>  {{ article.like}} </a>
              </span>
              <span class="comments">
                <a><i class="el-icon-chat-dot-square"></i> {{ article.commentCount}} </a>
              </span>
              <span class="readings">
                <a><i class="el-icon-view"></i> {{ article.click}} </a>
              </span>
            </p>
          </li>
        </ul>
      </div>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
  import {
    mapState,
    mapActions
  } from 'vuex';
  import Panel from "./Panel";

  import {LineBreakMode} from "@/utils/front/const.js";
  import {socialDateFormat} from "@/utils/front/utils";
  import {mixin} from '@/utils/front/utils';

  export default {
    name: 'recommend',
    mixins: [mixin],
    mounted() {
      if (!this.$store.state.common.recommends || this.$store.state.common.recommends.length === 0) {
        this['common/GET_RECOMMENDS']();
      }
    },
    computed: {
      ...mapState({
        recommends: state => state.common.recommends
      }),
      first: {
        get() {
          return this.articleSlice(0, 1)[0];
        },
        set(val) {

        }
      }
    },
    methods: {
      ...mapActions(['common/GET_RECOMMENDS']),
      gotoPostDetail(post) {

      },
      likePost(post) {

      },
      routerInfos(post) {

      },
      articleSlice(start, end) {
        return this.recommends.slice(start, end);
      }
    },
    components: {
      'panel': Panel
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../common/stylus/theme.styl";
  .recommend
    background $default-background-color

    .content
      padding 5px 20px 15px
      border-left 1px solid $default-border-color

    .top, .others
      display block
      overflow hidden

      .tags
        margin-bottom 10px

      .title
        text-align left
        font-size 16px
        line-height 23px
        margin-bottom 5px

        > a
          display inline
          color $default-title-color

          &:hover
            text-decoration underline
            color $default-title-hover-color

      .info
        margin 5px 0 0

        span
          font-size 13px
          line-height 18px
          font-weight 100
          color $default-info-color

          + span
            float right
            margin-left 10px

        a
          display inline-block
          color: #777
          cursor pointer

          &:hover
            color $default-link-hover-color

      .img
        padding-bottom: 30%
        width: 100%
        height: 0
        margin 5px 0
        overflow hidden

        img
          width 100%
          transition: All 0.4s ease-in-out
          transform: scale(1.0)
          zoom: 1.0

      .desc
        text-align justify
        color $default-info-color
        font-size 13px
        line-height 20px
        margin 5px 0 0

      &:hover
        img
          transition: All 0.4s ease-in-out
          transform: scale(1.05)
          zoom: 1.05

    .others
      li
        margin-top 10px
        padding-top 10px
        border-top 1px solid $default-border-color
</style>
