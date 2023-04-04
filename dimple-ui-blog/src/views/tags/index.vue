<template>
    <div class="tags">
        <layout title="标签" :cover="cover">
            <tags-iterator :tags="tags"></tags-iterator>
        </layout>
    </div>
</template>
<script>
import api from "@/api/";
import tagsIterator from "@/views/components/tags-iterator";
import cover from "@/assets/img/cover/tags.jpg";

export default {
    name: "tags",
    metaInfo() {
        return {
            title: `标签  - ` + (this.$store.state.globalConfig.siteConfig.siteName || "Dimple's Blog Inside"),
            meta: [
                {
                    name: "description",
                    content: "标签"
                },
                {
                    name: "keywords",
                    content: "标签页面"
                }
            ]
        };
    },
    components: {tagsIterator},
    data() {
        return {
            cover: cover,
            tags: []
        };
    },
    created() {
        this.getTagList();
    },
    methods: {
        async getTagList() {
            const tagRes = await api.listTag({});
            if (tagRes.code === 200) {
                this.tags = tagRes.rows, this.total = tagRes.total;
            }
        },
    },
};
</script>
<style lang="scss">
@import '~@/style/index.scss';
</style>
