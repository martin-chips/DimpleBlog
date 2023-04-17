<template>
  <div class="panel-comments">
    <el-card>
      <div class="panel__item-content">
        <div class="panel__item-title">
          <i class="el-icon-chat-dot-square"></i>
          <span>最新评论</span>
        </div>
        <div class="panel__item-body">
          <template v-if="newComments.length">
            <div class="body-item" v-for="(comment, index) in newComments" :key="index">
              <div class="body-pic">
                <router-link :to="'/app/article/' + comment.articleId + '?anchor=a_cm'">
                  <img v-lazy="comment.avatars" alt="" />
                </router-link>
              </div>
              <div class="body-info">
                <div class="body-info__title">
                  <router-link :to="'/app/article/' + comment.articleId + '?anchor=a_cm'">
                    {{ comment.content }}
                  </router-link>
                </div>
                <div class="body-info__name">{{ comment.username }}</div>
                <div class="body-info__date">发表于：{{ comment.createTime | formatDate }}</div>
              </div>
            </div>
          </template>
          <ElEmpty v-else></ElEmpty>
        </div>
      </div>
    </el-card>
  </div>
</template>
<script>
import {mapState} from 'vuex'

export default {
  name: 'panelComments',
  props: {},
  data() {
    return {}
  },
  computed: {
    ...mapState(['newComments'])
  },
  methods: {}
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
@import '~@/views/panel/style/mixins';

.panel-comments {
  @include articles-comments;
  .body-info {
    &__title a {
      -webkit-line-clamp: 1 !important;
    }
    &__name {
      padding-top: 4px;
      @include clamp(1);
    }
    &__date {
      padding: 2px 0 !important;
    }
  }
}
</style>
