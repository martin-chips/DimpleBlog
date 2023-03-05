<template>
  <div class="archives">
    <layout title="归档" :cover="cover">
      <template v-if="archives.length">
        <div class="archives__year" v-for="(range, index) in archives" :key="index">
          <div class="year-text">{{ range.year || range.month }}</div>
          <el-timeline>
            <el-timeline-item
              v-for="(article, mi) in range.months"
              :key="'art_' + mi"
              type="primary"
              :hide-timestamp="true"
            >
              <div class="archives__content">
                <div class="content-left">
                  <router-link :to="'/app/article/' + article.articleId">
                    <img v-lazy="article.headerImage" alt="" />
                  </router-link>
                </div>
                <div class="content-right">
                  <div class="content-right__title">
                    <router-link :to="'/app/article/' + article.articleId">
                      {{ article.title }}
                    </router-link>
                  </div>
                  <div class="content-right__date">
                    <i class="el-icon-date"></i>
                    {{ article.createTime | formatDate }}
                  </div>
                </div>
              </div>
            </el-timeline-item>
          </el-timeline>
        </div>
      </template>
      <empty v-else></empty>

      <div class="archives__page" v-if="archives.length">
        <el-pagination
          :total="total"
          layout="prev, pager, next"
          :page-size="pageSize"
          @current-change="currentChange"
        ></el-pagination>
      </div>
    </layout>
  </div>
</template>
<script>
import api from "@/api/";
import cover from "@/assets/img/cover/archive.jpeg";

async function getArchiveRes(route, pageNum = 1, pageSize = 10) {
  const params = {
    pageSize,
    pageNum
  };
  if (route.query.filter && /(\d+)-(\d+)/.test(route.query.filter)) {
    params.filter = 1;
    params.month = route.query.filter;
  }

  const archiveRes = await api.listArticle(params);
  return archiveRes;
}

export default {
  name: "archives",
  metaInfo() {
    return {
      title: `归档  - Dimple's Blog`,
      meta: [
        {
          name: "description",
          content: "文章归档"
        },
        {
          name: "keywords",
          content: "文章归档"
        }
      ]
    };
  },
  data() {
    return {
      cover: cover,
      pageNum: 1,
      pageSize: 20,
      total: 0,
      archives: []
    };
  },
  async asyncData({ route }) {
    const archiveRes = await getArchiveRes(route);
    if (archiveRes.code === 200) return { archives: archiveRes.data, total: archiveRes.total };
  },
  watch: {
    $route() {
      this.getArchiveRes();
    }
  },
  methods: {
    currentChange(val) {
      this.pageNum = val;
      this.getArchiveRes();
    },
    async getArchiveRes() {
      const archiveRes = await getArchiveRes(this.$route, this.pageNum, this.pageSize);
      if (archiveRes.code === 200) {
        this.archives = archiveRes.data;
        this.total = archiveRes.total;
      }
    }
  }
};
</script>
<style lang="scss">
@import '~@/style/index.scss';

.archives {
  &__year {
    padding: 12px;

    .year-text {
      font-size: 22px;
      padding: 16px 0 28px 0;
    }
  }

  &__content {
    display: flex;
    align-items: center;

    .content-left {
      width: 80px;
      height: 80px;
      flex: 0 0 auto;
      overflow: hidden;

      a {
        display: inline-block;
        width: 80px;
        height: 80px;
      }

      img {
        border-radius: 4px;
        width: 100%;
        height: 100%;
        object-fit: cover;
      }

      img[src=''],
      img:not([src]) {
        opacity: 0;
      }
    }

    .content-right {
      margin-left: 24px;

      &__title,
      &__title a {
        transition: all 0.38s ease;
        @include clamp(2);
      }

      &__title {
        font-size: 16px;
        padding: 8px 0;
      }

      &__title:hover a,
      &__title:hover {
        @include themeify() {
          color: themed('color-ele-primary');
        }
        transform: translateX(12px);
      }

      &__date {
        @include themeify() {
          color: themed('color-home-article-detail');
        }
        padding: 8px 0;
      }
    }
  }

  &__page {
    padding: 16px;
    @include flex-box-center;
  }
}
</style>
