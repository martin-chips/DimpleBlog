<template>
  <div class="archives">
    <layout title="归档" cover="/img/cover/archive.jpg">
      <template v-if="archives.length">
        <div class="archives__year" v-for="(range, index) in archives" :key="index">
          <div class="year-text">{{ range.dateStr }}</div>
          <el-timeline>
            <el-timeline-item
              v-for="(article, mi) in range.items"
              :key="'art_' + mi"
              type="primary"
              :hide-timestamp="true"
            >
              <div class="archives__content">
                <div class="content-left">
                  <router-link :to="'/app/article/' + article.id">
                    <img v-lazy="article.headerImage" alt=""/>
                  </router-link>
                </div>
                <div class="content-right">
                  <div class="content-right__title">
                    <router-link :to="'/app/article/' + article.id">
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
      <ElEmpty v-else></ElEmpty>

      <el-pagination
        class="archives__page"
        :total="total"
        v-if="total>archives.length"
        layout="prev, pager, next"
        :page-size="pageSize"
        @current-change="currentChange"
      ></el-pagination>
    </layout>
  </div>
</template>
<script>
import api from "@/api/";
import {Enum} from "../../api/visitor";

export default {
  name: "archives",
  metaInfo() {
    return {
      title: `归档  - ` + "Dimple's Blog",
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
      pageNum: 1,
      pageSize: 10,
      total: 0,
      archives: []
    };
  },
  async asyncData({store, route, isServer, _req}) {
    const archiveRes = await api.getAsyncArchives(_req);
    await api.saveVisitLog(Enum.LIST_ARCHIVES, "", "/app/archives", archiveRes.code, _req);
    if (archiveRes.code === 200) {
      console.log(archiveRes.rows)
      return {
        archives: archiveRes.rows,
        total: archiveRes.total
      }
    }
  },
  methods: {
    currentChange(val) {
      this.pageNum = val;
      this.getArchiveRes();
    },
    async getArchiveRes() {
      const archiveRes = await api.getArchives({
        pageNum: this.pageNum, pageSize: this.pageSize,
      });
      if (archiveRes.code === 200) {
        this.archives = archiveRes.rows;
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
        @include themify() {
          color: themed('btn-bg');
        }
        transform: translateX(12px);
      }

      &__date {
        @include themify() {
          color: themed('meta-grey');
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
