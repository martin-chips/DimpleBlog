<template>
  <div id="app">
    <router-view/>
  </div>
</template>
<script>
import {mapMutations} from 'vuex'

export default {
  components: {},
  data() {
    return {
      location: [],
      timer: '',
      showBackTop: true
    }
  },
  watch: {},
  mounted() {
    this.initPanel()
  },
  computed: {},
  methods: {
    ...mapMutations(['setArchives', 'setCategory', 'setTags', 'setNewComments', 'setNewArticles', 'setTotals']),
    async initPanel() {
      await this.$api.listPanelArchive({countType: "month", pageNum: 1, pageSize: 5}).then((res) => {
        if (res.code === 200) {
          this.setArchives(res.rows);
        }
      });
      await this.$api.listPanelCategory().then((res) => {
        if (res.code === 200) {
          this.setTotals({key: "category", value: res.total});
          this.setCategory(res.rows);
        }
      });
      await this.$api.listPanelTag().then((res) => {
        if (res.code === 200) {
          this.setTotals({key: "tag", value: res.total});
          this.setTags(res.rows);
        }
      });
      await this.$api.listPanelComment({
        pageNum: 1,
        pageSize: 5,
        orderByColumn: "createTime",
        isAsc: "desc",
      }).then((res) => {
        if (res.code === 200) this.setNewComments(res.rows);
      });
      await this.$api.listPanelArticle({
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
}
</script>
<style lang="scss">
@import '~@/style/reset.scss';
@import './assets/css/prism.css';
@import './assets/css/emoji-sprite.css';
</style>
