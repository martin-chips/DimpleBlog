<template>
  <div class="message-board">
    <layout title="留言板" cover="/img/cover/msgboard.jpg">
      <div class="message-board__content">
        <p class="message-board__welcome">
          Troubleshooting any problem without the error log
          is like driving with your eyes closed.<br>
          在没有错误日志的情况下诊断任何问题无异于闭眼开车</p>
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
            <div class="message-board__page" v-if="messages.length">
              <el-pagination
                :current-page.sync="currentPage"
                :total="total"
                layout="prev, pager, next"
                :page-size="pageSize"
                @current-change="currentChange"
              ></el-pagination>
            </div>
          </div>
        </div>
      </div>
    </layout>
  </div>
</template>
<script>
import {mapState} from 'vuex'
import note from '@/components/note/'
import splitLine from '@/components/splitLine/'
import submit from '@/views/components/submit'
import comments from '@/views/components/comments'
import api from '@/api/'
import {storage} from "@/utils/storage";
import {Enum} from "../../api/visitor";

export default {
  name: 'messageBoard',
  metaInfo() {
    return {
      title: `留言板  - Dimple's Blog`,
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
      currentPage: 1,
      pageNum: 1,
      total: 0,
      pageSize: 10,
      articleId: -1000,
      messages: []
    }
  },
  async asyncData({store, route, isServer, _req}) {
    const msgRes = await api.getAsyncMessageBoard({
      pageNum: 1,
      pageSize: 10
    }, _req);
    await api.saveVisitLog(Enum.LIST_MESSAGE_BOARD, "", "/app/messageBoard", msgRes.code, _req);
    if (msgRes.code === 200) return {messages: msgRes.rows, total: msgRes.total}
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
    text-align: center;
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
    font-size: 25px;
    font-weight: bold;
  }

  &__page {
    @include flex-box-center;
    padding: 16px 0;
  }
}
</style>
