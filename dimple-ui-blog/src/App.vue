<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
import {mapMutations} from "vuex";
import Ribbons from '@/assets/js/ribbon.js'

export default {
    Ribbons,
    components: {},
    data() {
        return {
            location: [],
            timer: "",
            showBackTop: true
        };
    },
    watch: {},
    mounted() {
        this.initPanel();
        Ribbons.start()
    },
    computed: {},
    methods: {
        ...mapMutations(["setArchives", "setCategory", "setTags", "setNewComments", "setNewArticles", "setTotals"]),
        initPanel() {
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
</style>
