<template>
  <div class="panel-articles">
    <el-card>
      <div class="panel__item-content">
        <div class="panel__item-title">
          <i class="el-icon-document"></i>
          <span>最新文章</span>
        </div>
        <div class="panel__item-body">
          <template v-if="newArticles.length">
            <div class="body-item" v-for="(article, index) in newArticles" :key="index">
              <div class="body-pic">
                <router-link :to="'/app/article/' + article.id">
                  <img v-lazy="article.headerImage" alt="" />
                </router-link>
              </div>
              <div class="body-info">
                <div class="body-info__title">
                  <router-link :to="'/app/article/' + article.id">
                    {{ article.title }}
                  </router-link>
                </div>
                <div class="body-info__date">{{ article.createTime | formatDate }}</div>
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
import {mapState} from "vuex";

export default {
  name: 'panelArticles',
  props: {},
  data() {
    return {}
  },
  computed: {
    ...mapState(['newArticles'])
  },
  methods: {}
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
@import '~@/views/panel/style/mixins';

.panel-articles {
  @include articles-comments;
}
</style>
