<template>
  <div class="hot" v-if="hots.length > 0">
    <panel title="最热阅读">
      <div slot="content" class="content">
        <div class="top">
          <p class="title">
            <a @click.prevent="gotoPostDetail(first)"
               :href="`article/${first.id}`">
              {{first.title }}
            </a>
          </p>
          <div class="tags">
            <a v-for="tag in first.tagList">
              <el-tag :style="{borderColor:tag.color,color:tag.color} " effect="plain" size="small" :key="tag.id" :hit="true"
                      style="margin: 2px 4px 2px 0" class="border-tag">{{tag.title}}
              </el-tag>
            </a>
          </div>
          <p class="info">
            <span class="time"><a>{{ first.createTime | socialDate }}</a></span>
            <span class="likes">
              <a @click="likePost(first)"><svg-icon icon-class="like" /> {{ first.like }} </a>
            </span>
            <span class="comments">
              <a><i class="el-icon-edit-outline"></i>{{ first.commentCount }} </a>
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
                <a @click="likePost(article)"><svg-icon icon-class="like" />  {{ article.like}} </a>
              </span>
              <span class="comments">
                <a><i class="el-icon-edit-outline"></i> {{ article.commentCount}} </a>
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
  import {mixin} from '@/utils/front/utils';
  import {LikeBlog} from "@/api/front/front";

  export default {
    name: 'hot',
    mixins: [mixin],
    mounted() {
      if (!this.$store.state.common.hots || this.$store.state.common.hots.length === 0) {
        this['common/GET_HOTS']();
      }
    },
    computed: {
      ...mapState({
        hots: state => state.common.hots
      }),
      first: {
        get() {
          return this.articleSlice(0, 1)[0];
        },
        set(val) {

        }
      }
    },
    updated() {
      this.first = this.articleSlice(0, 1)[0];
    },
    methods: {
      ...mapActions(['common/GET_HOTS']),
      gotoPostDetail(post) {
        this.$router.push({
          name: "article",
          params: {id: post.id},
        });
      },
      likePost(post) {
        LikeBlog(post.id).then((response) => {
          post.like += 1;
          this.msgSuccess("点赞 +1");
        }).catch((error) => {
          console.log(error);
        });
      },
      articleSlice(start, end) {
        return this.hots.slice(start, end);
      }
    },
    components: {
      'panel': Panel
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../common/stylus/theme.styl";
  .hot
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
