<template>
    <div class="book-catalog" v-if="book != undefined">
        <ul class="book-toc-list">
            <li class="book-toc-list-item" v-for="bookNote in book.bookNoteList" :key="bookNote.id">
                <a class="book-toc-link" :href="`/bookNote/${bookNote.id}`">
                    <span><Icon :type="'android-checkbox-outline'"></Icon></span>
                    {{ bookNote.title }}
                </a>
            </li>
        </ul>
    </div>
</template>

<script type="text/ecmascript-6">

    export default {
        name: 'book-catalog',
        props: {
            book: {
                Type: Object,
                default: undefined
            }
        },
        methods: {
            gotoBookNoteDetail(note) {
                if (!note.is_noted) {
                    this.$Notice.info({
                        title: '无文章信息'
                    });
                    return;
                }
                checkPostAuth.call(this, note, '提示', '该文章已加密，您需要输入访问密码', () => {
                    this.$router.push({name: note.post_type, params: {id: note.id}});
                }, (encryptedBrowseAuth) => {
                    this.$router.push({
                        name: note.post_type,
                        params: {id: note.id},
                        query: {browse_auth: encryptedBrowseAuth}
                    });
                }, (error) => {
                    console.log(error);
                    this.$Notice.error({
                        title: '密码错误'
                    });
                });
            }
        }
    };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../../common/stylus/theme.styl";
    .book-catalog
        li
            list-style: none

        .book-toc-list
            margin 0
            padding-left 25px
            overflow-y hidden

        > .book-toc-list
            padding-left 8px

        .book-toc-list-item
            a.book-toc-link
                line-height 30px
                padding 5px 0
                color $default-desc-color

                i
                    font-size 16px

                &.is-active-link
                    font-weight: 700
                    color $default-link-hover-color

                &:hover
                    color $default-link-hover-color
</style>
