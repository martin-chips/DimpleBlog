<template>
  <div class="article-iterator">
    <template v-if="articles.length">
      <el-card class="article-iterator__item" v-for="(article, index) in articles" :key="index">
        <div class="item-content">
          <div class="item-content__pic">
            <img  :src="article.headerImage" alt="" />
          </div>
          <div class="item-content__info">
            <div class="item-content__link">
              <router-link :to="'/app/article/' + article.id">{{ article.title }}</router-link>
            </div>
            <div class="item-content__detail">
              <span>
                <i class="el-icon-date"></i>
                发表时间 {{ article.createTime | formatDate }}
              </span>
              <span>&nbsp;|&nbsp;</span>
              <span>
                <i class="el-icon-chat-dot-round"></i>
                评论数 {{ article.commentCount }}
              </span>
              <span>&nbsp;|&nbsp;</span>
              <span>
                <i class="el-icon-star-off"></i>
                点赞 {{ article.likeCount }}
              </span>
            </div>
            <div class="item-content__abstract">{{ article.summary }}</div>
          </div>
        </div>
      </el-card>
    </template>
    <el-card class="article-iterator__item" v-else>
      <empty text="您还没有文章，赶快去创建吧~"></empty>
    </el-card>
  </div>
</template>
<script>
export default {
  name: 'articleIterator',
  props: {
    articles: {
      type: Array,
      default() {
        return []
      }
    }
  },
  data() {
    return {}
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
.article-iterator {
  &__item {
    height: 280px;
    @include respond-to(xs) {
      height: auto;
    }
    > .el-card__body {
      width: 100%;
      height: 100%;
      padding: 0;
    }
    .item-content {
      display: flex;
      width: 100%;
      height: 100%;
      @include respond-to(xs) {
        flex-direction: column;
      }
      &__pic {
        width: 45%;
        height: 100%;
        @include respond-to(xs) {
          width: 100%;
          height: 230px;
        }
        flex: 0 0 auto;
        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
        }
      }
      &__info {
        width: 55%;
        padding: 20px 30px;
        flex: 0 0 auto;
        display: flex;
        flex-direction: column;
        justify-content: center;
        @include respond-to(xs) {
          width: 100%;
          justify-content: normal;
          padding: 16px;
        }
      }
      &__link {
        flex: 0 0 auto;
        @include clamp(2);
        a {
          font-size: 24px;
          line-height: 1.5;
          transition: all ease-in-out 0.25s;
          @include respond-to(xs) {
            font-size: 18px;
          }
        }
        a:hover {
          @include themeify() {
            color: themed('color-ele-primary');
          }
        }
      }
      &__detail {
        @include themeify() {
          color: themed('color-home-article-detail');
        }
        font-size: 12px;
        padding: 12px 0;
      }
      &__abstract {
        line-height: 2;
        flex: 0 0 auto;
        @include clamp(2);
      }
    }
  }
  &__item:not(:first-child) {
    margin-top: 20px;
  }
  &__item:nth-child(even) .item-content__pic {
    order: 1;
    @include respond-to(xs) {
      order: 0;
    }
  }
}
</style>
