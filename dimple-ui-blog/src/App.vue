<template>
    <div id="app" :class="{ grayedOut: mask }">
        <router-view></router-view>
    </div>
</template>

<script>
import {mapMutations, mapState} from "vuex";
import Ribbons from '@/assets/js/ribbon.js'

export default {
    Ribbons,
    components: {},
    data() {
        return {
            location: [],
            timer: "",
            showBackTop: true,
            loading: true,
            mask: false,
        };
    },
    watch: {
        'globalConfig.siteConfig.mask': {
            handler(newValue, oldValue) {
                if (newValue) {
                    this.mask = newValue;
                }
                if (newValue && newValue) {
                    Ribbons.stop();
                }
            },
            immediate: true,
        },
    },
    mounted() {
        this.initPanel();
        Ribbons.start()
    },
    computed: {
        ...mapState(['globalConfig'])
    },
    methods: {
        ...mapMutations(["setArchives", "setCategory", "setTags", "setNewComments", "setGlobalConfig", "setNewArticles", "setTotals"]),
        initPanel() {
            this.setGlobalConfig({
                "siteConfig": {
                    "mask": false,
                    "infoPanel": " Hi，欢迎来到 Dimple's Blog，一个记录学习与生活的个人博客，你可以在<a href=\"https://bianxf.com\">这里</a> 了解到关于本站的更多技术细节。",
                    "siteName": "Dimple Blog",
                    "avatars": "https://avatars.githubusercontent.com/u/33685170?s=48&v=4",
                    "icp": "蜀备-12345",
                    "githubUrl": "https://github.com/martin-chips",
                    "copyright": "©2018 - 2024&nbsp;&nbsp;&nbsp;",
                    "githubUserName": "Martin Chips"
                },
                "commentConfig": {
                    "enableLocalLogin": false,
                    "enableGithubLogin": true,
                    "enableQQLogin": false,
                    "githubLoginConfig": {
                        "adminId": null,
                        "clientId": "",
                        "clientSecrets": "",
                        "accessTokenUrl": "",
                        "loginUrl": "",
                        "userInfoUrl": ""
                    }
                }
            })
            this.$api.getSiteConfig().then((res) => {
                if (res.code === 200) {
                    var data = res.data;
                    data.siteConfig.infoPanel = data.siteConfig.infoPanel.replace(/&lt;/g, '<').replace(/&gt;/g, '>');
                    data.siteConfig.copyright = data.siteConfig.copyright.replace(/&lt;/g, '<').replace(/&gt;/g, '>');
                    this.setGlobalConfig(data);
                }
            });
            this.$api.listPanelArchive({countType: "month", pageNum: 1, pageSize: 5}).then((res) => {
                if (res.code === 200) {
                    this.setArchives(res.rows);
                }
            });
            this.$api.listPanelCategory().then((res) => {
                if (res.code === 200) {
                    this.setTotals({key: "category", value: res.total});
                    this.setCategory(res.rows);
                }
            });
            this.$api.listPanelTag().then((res) => {
                if (res.code === 200) {
                    this.setTotals({key: "tag", value: res.total});
                    this.setTags(res.rows);
                }
            });
            this.$api.listPanelComment({
                pageNum: 1,
                pageSize: 5,
                orderByColumn: "createTime",
                isAsc: "desc",
            }).then((res) => {
                if (res.code === 200) this.setNewComments(res.rows);
            });
            this.$api.listPanelArticle({
                pageNum: 1,
                pageSize: 5,
                orderByColumn: "createTime",
                isAsc: "desc",
            }).then((res) => {
                if (res.code === 200) {
                    this.setTotals({key: "article", value: res.total});
                    this.setNewArticles(res.rows);
                }
            });
        }
    }
};
</script>

<style lang="scss">
@import '~@/style/reset.scss';
@import './assets/css/prism.css';
@import './assets/css/emoji-sprite.css';

.grayedOut {
  filter: grayscale(100%);
}
</style>
