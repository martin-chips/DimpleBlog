<template>
    <div class="friend-links" v-if="friendLinks.length>0">
        <panel title="友链">
            <ul class="link-list" slot="content">
                <li v-for="friendLink in friendLinks">
                    <a :href="friendLink.url" target="_blank" @click="updateCount(link.id)">
                        <img :src="friendLink.headerImg"
                             :class="{'no-pic': friendLink.headerImg === null || friendLink.headerImg.length === 0}"
                             alt="">
                        <div class="right">
                            <p class="title" :title="friendLink.title">{{ friendLink.title }}</p>
                            <p class="link" :title="friendLink.description">{{ friendLink.description }}</p>
                        </div>
                    </a>
                </li>
            </ul>
        </panel>
    </div>
</template>

<script type="text/ecmascript-6">
    import {
        mapState,
        mapActions
    } from 'vuex';
    import Panel from "./Panel";
    import {updateLinkCount} from "@/api"

    export default {
        name: 'friend-links',
        mounted() {
            if (this.$store.state.common.friendLinks.length === 0) {
                this['common/GET_FRIEND_LINKS']();
            }
        },
        computed: {
            ...mapState({
                friendLinks: state => state.common.friendLinks
            })
        },
        methods: {
            ...mapActions(['common/GET_FRIEND_LINKS']),
            updateCount(id) {
                updateLinkCount(id);
            },
        },
        components: {
            'panel': Panel
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../common/stylus/theme.styl";
    .friend-links
        position relative

        ul.link-list
            padding 15px 0
            border-left 1px solid $default-border-color

            li
                padding 2px 25px

                a
                    display flex
                    transform: translate(0, 0)
                    transition: all 0.2s ease-in

                    &:hover
                        transform: translate(30px, 0)
                        transition: all 0.2s ease-in-out

                    img
                        flex 0 0 50px
                        width 50px
                        height 50px
                        margin 5px 0
                        margin-right 12px
                        border 1px solid $default-border-color

                        &.no-pic
                            flex 0 0 0
                            width 0
                            margin-right 0
                            border none

                    .right
                        display flex
                        flex-direction column
                        height 60px
                        line-height 60px

                        p
                            flex 1
                            text-align left

                            &.title
                                height 35px
                                line-height 30px
                                font-size 17px
                                color $default-title-hover-color
                                white-space: nowrap;
                                overflow: hidden;
                                text-overflow: ellipsis;
                                width: 16rem;

                            &.link
                                height 15px
                                line-height 15px
                                font-weight 100
                                color $default-link-color
                                white-space: nowrap;
                                overflow: hidden;
                                text-overflow: ellipsis;
                                width: 16rem;
</style>
