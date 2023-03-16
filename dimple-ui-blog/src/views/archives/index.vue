<template>
    <div class="archives">
        <layout title="归档" :cover="cover">
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
import cover from "@/assets/img/cover/archive.jpeg";

async function getArchiveRes(pageNum = 1, pageSize = 10) {
    const params = {
        pageSize,
        pageNum
    };
    const archiveRes = await api.getArchives(params);
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
            pageSize: 10,
            total: 0,
            archives: []
        };
    },
    watch: {
        $route() {
            this.getArchiveRes();
        }
    },
    created() {
        this.getArchiveRes()
    },
    methods: {
        currentChange(val) {
            this.pageNum = val;
            this.getArchiveRes();
        },
        async getArchiveRes() {
            const archiveRes = await getArchiveRes(this.pageNum, this.pageSize);
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
