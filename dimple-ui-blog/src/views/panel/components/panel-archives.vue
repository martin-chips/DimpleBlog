<template>
  <div class="panel-archives">
    <el-card>
      <div class="panel__item-title">
        <i class="el-icon-takeaway-box"></i>
        <span>归档</span>
      </div>
      <div class="panel__item-body">
        <ul v-if="archives.length">
          <li v-for="(archive, index) in archives" :key="index" @click="filterArchives(archive)">
            <span>{{ archive.dateStr }}</span>
            <span>{{ archive.count }}</span>
          </li>
        </ul>
        <ElEmpty v-else></ElEmpty>
      </div>
    </el-card>
  </div>
</template>
<script>
import {mapState} from 'vuex'

export default {
  name: 'panelArchives',
  props: {},
  data() {
    return {}
  },
  computed: {
    ...mapState(['archives'])
  },
  methods: {
    filterArchives(archive) {
      this.$router.push({
        name: 'archives',
        query: {
          filter: archive.dateStr
        }
      })
    }
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
@import '~@/views/panel/style/mixins';

.panel-archives {
  @include panel-frame;

  .panel__item-body {
    li {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 8px;
      cursor: pointer;
      transition: all ease 0.38s;
    }

    li:hover {
      padding: 8px 12px;
      @include themify() {
        background: themed('color-list-hover');
      }
    }
  }
}
</style>
