<template>
  <div class="prev-next">
    <div class="prev-next__content">
      <div class="content__prev" v-if="prevnext.prev">
        <a :href="getHref('prev')">
          <img :src="prevnext.prev.headImage" alt=""/>
          <div class="content__info">
            <div>上一篇</div>
            <div class="info-title">{{ this.prevnext.prev.title }}</div>
          </div>
        </a>
      </div>
      <div class="content__next" v-if="prevnext.next">
        <a :href="getHref('next')">
          <img :src="prevnext.next.headImage" alt=""/>
          <div class="content__info">
            <div>下一篇</div>
            <div class="info-title">{{ this.prevnext.next.title }}</div>
          </div>
        </a>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "prevnext",
  props: {
    article: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  data() {
    return {
      prevnext: {}
    };
  },
  mounted() {
    this.getPrevNextArticle()
  },
  components: {},
  methods: {
    async getPrevNextArticle() {
      console.log("getPrevNextArticle")
      const prevnextRes = await this.$api.getPrevNextArticle({
        id: this.article.id
      });
      if (prevnextRes.code === 200) {
        this.prevnext = prevnextRes.data;
      }
    },
    getHref(type) {
      if (type === "prev") {
        return `/app/article/${this.prevnext.prev.id}`;
      } else {
        return `/app/article/${this.prevnext.next.id}`;
      }
    }
  }
};
</script>
<style lang="scss">
@import '~@/style/index.scss';

.prev-next {
  &__content {
    position: relative;
    height: 150px;
    @include respond-to(xs) {
      height: 100px;
    }
    @include flex-box-space;

    .content__prev,
    .content__next {
      width: 50%;
      height: 100%;
      flex-grow: 1;

      a {
        position: relative;
        display: inline-block;
        width: 100%;
        height: 100%;
        overflow: hidden;
      }

      a:before {
        content: '';
        position: absolute;
        z-index: 5;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.4);
        transition: all ease-in 0.38s;
      }

      img {
        position: absolute;
        z-index: 0;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        object-fit: cover;
        transition: all ease 0.38s;
      }

      a:hover:before {
        background: rgba(0, 0, 0, 0.2);
      }

      a:hover img {
        transform: scale(1.2);
      }
    }

    .content__prev .content__info {
      text-align: left;
    }

    .content__next .content__info {
      text-align: right;
    }

    .content__info {
      width: 100%;
      height: 100%;
      position: absolute;
      z-index: 10;
      display: flex;
      flex-direction: column;
      justify-content: center;
      padding: 0 38px;

      .info-title {
        @include clamp(2);
      }

      @include respond-to(xs) {
        padding: 0 12px;
      }
      @include themeify() {
        color: themed('color-title');
      }
    }
  }
}
</style>
