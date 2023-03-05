<template>
    <div class="panel-tags">
        <el-card>
            <div class="panel__item-title">
                <i class="el-icon-collection-tag"></i>
                <span>标签</span>
            </div>
            <div class="panel__item-body">
                <tags-iterator :tags="tags" size="small"></tags-iterator>
            </div>
        </el-card>
    </div>
</template>
<script>
import { mapState } from "vuex";
import tagsIterator from "@/views/components/tags-iterator";

export default {
    name: 'panelTags',
    props: {},
    components: {tagsIterator},
    data() {
        return {}
    },
    computed: {
        ...mapState(['tags'])
    },
    methods: {}
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
@import '~@/views/panel/style/mixins';

.panel-tags {
  @include panel-frame;
}
</style>
