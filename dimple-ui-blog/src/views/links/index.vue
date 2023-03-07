<template>
    <div>
        <layout title="友链" :cover="cover">
            <template v-if="links.length">
                <el-row :gutter="20">
                    <div v-for="(link, index) in links" :key="index">
                        <el-col el-col :span="8">
                            <el-card :title="link.description" shadow="hover" :body-style="{ padding: '0px' }">
                                <a :href="link.url">
                                    <img v-lazy="link.headerImage" class="link-image">
                                </a>
                                <div style="padding: 14px;">
                                    <span class="link-title">{{ link.title }}</span>
                                    <div>
                                        <div class="link-description">{{ link.description }}</div>
                                    </div>
                                </div>
                            </el-card>
                        </el-col>
                    </div>
                </el-row>
            </template>
            <ElEmpty v-else></ElEmpty>

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
            links: [{
                title: "Dimple",
                description: "this is a description this is a descriptionthis is a descriptionthis is a descriptionthis is a descriptionthis is a descriptionthis is a description",
                url: "https://www.bianxiaofeng.com",
                headerImage: "https://qiniu.mapblog.cn/images/article-cover/1649764408751.deploy-guide.jpg"
            }, {
                title: "Dimple",
                description: "this is a description",
                url: "https://www.bianxiaofeng.com",
                headerImage: "https://qiniu.mapblog.cn/images/article-cover/1649764408751.deploy-guide.jpg"
            }, {
                title: "Dimple",
                description: "this is a description",
                url: "https://www.bianxiaofeng.com",
                headerImage: "https://qiniu.mapblog.cn/images/article-cover/1649764408751.deploy-guide.jpg"
            }]
        };
    },
    watch: {},
    created() {
        this.getArchiveRes()
    },
    methods: {
        currentChange(val) {
            this.pageNum = val;
            this.getArchiveRes();
        },
        async getLinks() {
            // const archiveRes = await getArchiveRes(this.pageNum, this.pageSize);
            // if (archiveRes.code === 200) {
            //     this.archives = archiveRes.rows;
            //     this.total = archiveRes.total;
            // }
        }
    }
};
</script>
<style lang="scss">
@import '~@/style/index.scss';

.link-image {
  border-radius: 4px;
  width: 100%;
  height: 100%;
  -o-object-fit: cover;
  object-fit: cover
}

.link-title:hover {
    cursor: pointer;
    @include themeify() {
        color: themed('color-ele-primary');
    }
}
.link-description{
    white-space: nowrap;
    text-overflow:ellipsis;
    overflow:hidden;
}
</style>
