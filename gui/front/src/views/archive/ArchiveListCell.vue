<template>
    <div class="archive-list-cell">
        <div class="info" @click.prevent="gotoPostDetail(post)">
            <Tag class="time-tag border-tag">{{ post.createTime | formatDate }}</Tag>
            <div class="base-info">
                <a class="title">
                    {{post.title}}
                </a>
                <a class="see-desc" @click.stop="seeDesc" :class="{show: showDesc}">{{ showDesc ? '隐藏摘要' : '显示摘要' }}
                    <Icon type="md-arrow-dropup" />
                </a>
                <p class="desc" :class="{show: showDesc}">{{post.summary}}</p>
            </div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">

    export default {
        name: 'ArchiveListCell',
        props: {
            post: {
                Type: Object
            }
        },
        data() {
            return {
                showDesc: false
            };
        },
        methods: {
            seeDesc() {
                this.showDesc = !this.showDesc;
            },
            gotoPostDetail(post) {
                this.$router.push({
                    name: "article",
                    params: {id: post.id},
                });
            },
            routerInfos(post) {

            }
        },
        computed: {
            typeTag() {
                return '';
            }
        },
        filters: {
            formatDate: function (date) {
                let formattedDate = new Date(date.replace(/\-/g, "/"));
                let year = formattedDate.getFullYear();
                let month = formattedDate.getMonth() + 1;
                month = month >= 10 ? month : '0' + month; // 不满10前面加0
                let day = formattedDate.getDate();
                day = day >= 10 ? day : '0' + day; // 不满10前面加0
                return year + '-' + month + '-' + day;
            }
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../../common/stylus/theme.styl"
    .archive-list-cell
        position relative
        padding 5px 0

        &::after
            content ''
            position absolute
            min-height 25px
            top 0
            bottom 0
            left 17px
            width 4px
            background $default-link-hover-color

        .info
            display flex
            font-size 17px
            line-height 25px
            margin-left 45px
            cursor pointer
            @media only screen and (max-width: 720px)
                font-size 13px

            .time-tag
                flex-shrink 0

            .base-info
                a.title
                    margin-left 10px
                    font-weight 300
                    color $default-title-color
                    cursor pointer

                    &:hover
                        color $default-title-hover-color

                a.see-desc
                    color $default-desc-color
                    font-size 13px
                    margin-left 5px
                    font-weight 100
                    cursor pointer

                    > i
                        transition: All 0.4s ease-in-out
                        transform rotateX(180deg)

                    &:hover, &.show
                        color $default-desc-hover-color

                    &.show
                        > i
                            transform rotateX(0deg)

                .desc
                    font-size 14px
                    font-weight 300
                    margin-left 5px
                    color $default-desc-color
                    overflow hidden
                    max-height 0
                    transition All 0.4s ease-in-out

                    &.show
                        max-height 250px
</style>
