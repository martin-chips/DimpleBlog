<template>
    <div class="message-board">
        <layout title="留言板" :cover="cover">
            <div class="message-board__content">
                <note>
                    <p class="message-board__welcome">you must be a surprise someone is looking forward to</p>
                </note>
                <splitLine></splitLine>
                <div class="message-board__title">
                    <i class="el-icon-chat-dot-round"></i>
                    <span>留言</span>
                </div>
                <div class="message-board__submit">
                    <submit @submitContent="submitContent"></submit>
                </div>
                <div class="message-board__list">
                    <div class="message-board__total">
                        <span>{{ total }}条留言</span>
                    </div>
                    <div class="message-board__list">
                        <comments :messages="messages" @submitReply="submitReply" @addLike="addLike"></comments>
                        <el-pagination
                                class="message-board__page"
                                :current-page.sync="pageNum"
                                :total="total"
                                v-if="total>messages.length"
                                layout="prev, pager, next"
                                :page-size="pageSize"
                                @current-change="currentChange"
                        ></el-pagination>
                    </div>
                </div>
            </div>
        </layout>
    </div>
</template>
<script>
import {mapState} from "vuex";
import note from "@/components/note/";
import splitLine from "@/components/splitLine/";
import submit from "@/views/components/submit";
import comments from "@/views/components/comments";
import api from "@/api/messageBoard";
import cover from "@/assets/img/cover/msgboard.jpeg";
import {storage} from "@/utils/storage";

export default {
    name: 'messageBoard',
    metaInfo() {
        return {
            title: `留言板  - ` + (this.$store.state.globalConfig.siteConfig.siteName || "Dimple's Blog Inside"),
            meta: [
                {
                    name: 'description',
                    content: '支持第三方登录的留言板功能'
                },
                {
                    name: 'keywords',
                    content: '三方登录,留言板'
                }
            ]
        }
    },
    components: {
        note,
        submit,
        splitLine,
        comments
    },
    data() {
        return {
            cover: cover,
            pageNum: 1,
            total: 0,
            pageSize: 10,
            articleId: -1000,
            messages: []
        }
    },
    created() {
        this.getMessageBoard();
    },
    computed: {
        ...mapState(['visitorInfo'])
    },
    methods: {
        submitContent(content, cb) {
            this.submit(content, null, cb)
        },
        submitReply(content, currentReplyMessage, cb) {
            this.submit(content, currentReplyMessage, cb)
        },
        async submit(content, currentReplyMessage, cb) {
            let parentId
            let replyId
            if (currentReplyMessage) {
                if (currentReplyMessage.parentId && currentReplyMessage.parentId > 0) {
                    parentId = currentReplyMessage.parentId;
                    replyId = currentReplyMessage.id;
                } else {
                    parentId = currentReplyMessage.id;
                    replyId = currentReplyMessage.id;
                }
            }
            var visitor = storage.getVisitor();
            const res = await api.saveMessageBoard(Object.assign({
                content: content,
                replyId: replyId,
                parentId: parentId,
                articleId: this.articleId,
            }, visitor))
            if (res.code === 200) {
                if (cb) {
                    cb()
                }
                this.$message({
                    type: 'success',
                    message: '留言成功'
                })
                await this.getMessageBoard()
            }
        },
        async getMessageBoard() {
            const msgRes = await api.getMessageBoard({
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                articleId: this.articleId,
                orderByColumn: "createTime",
                isAsc: "desc",
            })
            if (msgRes.code === 200) {
                this.messages = msgRes.rows
                this.total = msgRes.total
            }
        },
        async addLike(message) {
            message.liked = 1;
            const likeRes = await api.likeMessageBoard(message.id)
            if (likeRes.code === 200) {
                message.likeCount += 1;
                this.$message({
                    type: 'success',
                    message: "点赞成功!"
                })
            }
        },
        currentChange() {
            this.getMessageBoard()
        }
    }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';

.message-board {
  &__welcome {
    font-family: 'sf-arch';
    font-size: 28px;
    line-height: 1.8;
    @include respond-to(xs) {
      font-size: 18px;
    }
  }

  &__title {
    padding: 16px 0;
    font-size: 20px;
    font-weight: 700;

    > [class^='el-icon-'] {
      font-weight: 700;
    }

    span {
      margin-left: 12px;
    }
  }

  &__list {
    margin-top: 28px;
  }

  &__total {
    color: #4c4948;
    font-size: 25px;
    font-weight: bold;
  }

  &__page {
    @include flex-box-center;
    padding: 16px 0;
  }
}
</style>
