<template>
  <div class="tag-wall">
    <panel title="标签墙">
      <div slot="content" class="content">
        <a v-for="tag in tags">
          <el-tag :style="{borderColor:tag.color,color:tag.color} " effect="plain" size="small" :hit="true"
                  :key="tag.id" style="margin: 2px 4px 2px 0" class="border-tag">
            {{tag.title+' [ '+tag.count+' ]'}}
          </el-tag>
        </a>
      </div>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
  import {
    mapState,
    mapActions
  } from 'vuex';

  import Panel from "./Panel";

  export default {
    name: 'tag-wall',
    mounted() {
      if (this.$store.state.common.tags.length === 0) {
        this['common/GET_TAGS']({
          pageSize: 5,
          pageNum: 0
        });
      }
    },
    computed: {
      ...mapState({
        tags: state => state.common.tags
      })
    },
    methods: {
      ...mapActions(['common/GET_TAGS'])
    },
    components: {
      'panel': Panel
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  .tag-wall
    .content
      padding 5px 20px
      border-left 1px solid $default-border-color
</style>
