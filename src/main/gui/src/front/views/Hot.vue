<template>
  <div class="hot" v-if="hots.length > 0">
    <panel title="最热阅读">
      <div slot="content" class="content">
        <div class="top">
          <p class="title">
            <el-tool-tip placement="right" :content="routerInfos(articleSlice(0, 1)[0]).message"
                        v-if="articleSlice(0, 1)[0].need_auth">
              <i-icon type="android-lock" color="#FA5555" v-if="articleSlice(0, 1)[0].need_auth"></i-icon>
            </el-tool-tip>
            <a @click.prevent="gotoPostDetail(articleSlice(0, 1)[0])"
               :href="`${articleSlice(0, 1)[0].post_type}/${articleSlice(0, 1)[0].id}`">
              {{ articleSlice(0, 1)[0][resolveI18N('title')] }}
            </a>
          </p>
          <div class="tags">
            <i-tag :color="tag.color" type="border" v-for="tag in articleSlice(0, 1)[0].tags" :key="tag.id"
                   class="border-tag">{{tag[resolveI18N('name')]}}
            </i-tag>
          </div>
          <p class="info">
            <span class="time"><a>{{ articleSlice(0, 1)[0].add_time | socialDate }}</a></span>
            <span class="likes"><a @click="likePost(articleSlice(0, 1)[0])"><i-icon
              type="heart"></i-icon> {{ articleSlice(0, 1)[0].like_num }} </a></span>
            <span class="comments"><a><i-icon
              type="compose"></i-icon> {{ articleSlice(0, 1)[0].comment_num }} </a></span>
            <span class="readings"><a><i-icon type="eye"></i-icon> {{ articleSlice(0, 1)[0].click_num }} </a></span>
          </p>
          <div class="img" v-if="articleSlice(0, 1)[0].front_image">
            <img :src="articleSlice(0, 1)[0].front_image" :alt="articleSlice(0, 1)[0].title">
          </div>
          <p class="desc" v-if="articleSlice(0, 1)[0].desc">{{ articleSlice(0, 1)[0][resolveI18N('desc')] |
            textLineBreak(60) }}</p>
        </div>
        <ul class="others">
          <li v-for="article in articleSlice(1)">
            <p class="title">
              <i-tool-tip placement="right" :content="routerInfos(article).message" v-if="article.need_auth">
                <i-icon type="android-lock" color="#FA5555" v-if="article.need_auth"></i-icon>
              </i-tool-tip>
              <a @click.prevent="gotoPostDetail(article)" :href="`${article.post_type}/${article.id}`">{{article[resolveI18N('title')]}}</a>
            </p>
            <p class="info">
              <span class="time">{{ article.add_time | socialDate }}</span>
              <span class="likes"><a @click="likePost(article)"><i-icon
                type="heart"></i-icon> {{ article.like_num}} </a></span>
              <span class="comments"><a><i-icon type="compose"></i-icon> {{ article.comment_num}} </a></span>
              <span class="readings"><a><i-icon type="eye"></i-icon> {{ article.click_num}} </a></span>
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

  export default {
    name: 'hot',
    mounted() {
      if (!this.$store.state.common.hots || this.$store.state.common.hots.length === 0) {
        console.log('hot');
        this['common/GET_HOTS']();
      }
    },
    computed: {
      ...mapState({
        hots: state => state.common.hots
      })
    },
    methods: {
      ...mapActions(['common/GET_HOTS']),
      gotoPostDetail(post) {
        // let routerInfos = this.routerInfos(post);
        // checkPostAuth.call(this, post, '提示', routerInfos.message, () => {
        //   this.$router.push({name: post.post_type, params: {id: post.id}});
        // }, (encryptedBrowseAuth) => {
        //   this.$router.push({
        //     name: post.post_type,
        //     params: {id: post.id},
        //     query: {browse_auth: encryptedBrowseAuth}
        //   });
        // }, (error) => {
        //   console.log(error);
        //
        // });
      },
      likePost(post) {
        // API.addPostLike({
        //   post_id: post.id
        // }).then((response) => {
        //   post.like_num += 1;
        //   this.$Message.success('点赞成功');
        // }).catch((error) => {
        //   console.log(error);
        // });
      },
      routerInfos(post) {
        // let router = {};
        // switch (post.post_type) {
        //   case 'article':
        //     router.message = '该文章已加密，您需要输入访问密码';
        //     break;
        //   case 'album':
        //     router.message = '该图集已加密，您需要输入访问密码';
        //     break;
        //   case 'movie':
        //     router.message = '该文章已加密，您需要输入访问密码';
        //     break;
        //   default:
        //     router.message = '该文章已加密，您需要输入访问密码';
        // }
        // return router;
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
