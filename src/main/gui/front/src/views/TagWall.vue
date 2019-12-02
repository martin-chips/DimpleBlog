<template>
    <div class="tag-wall">
        <panel title="标签墙">
            <div slot="content" class="content">
                <Tag v-for="tag in tags" type="dot" style="margin: 0 5px 5px 0;"
                     :color="tag.color"
                     :key="tag.id" class="dot-tag">
                    {{tag.title+' [ '+tag.count+' ]'}}
                </Tag>
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
