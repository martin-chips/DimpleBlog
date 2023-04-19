<template>
  <div class="comments-item">
    <div class="comments-item__avatar" :class="{ 'comments-item__avatar--small': subType }">
      <a :href="message.link" target="_blank">
        <img :src="message.avatars" alt=""/>
      </a>
    </div>
    <div class="comments-item__content">
      <div class="comments-item__visitor">
        <a :href="message.link" target="_blank">
                    <span class="detail-visitor-name" :class="{ 'detail-visitor-name--bold': message.admin }">
                      {{ message.username }}
                    </span>
        </a>
        <span class="detail-visitor-aite" v-if="message.replyId>0" style="fontweight: bold">&nbsp;@&nbsp;</span>
        <span class="detail-visitor-aited" v-if="message.replyId>0">{{ message.replyUsername }} :</span>
      </div>

      <div class="comments-item__say">{{ message.content }}</div>

      <div class="comments-item__detail">
        <i class="el-icon-date"></i>
        <span class="detail-visitor-date">{{ message.createTime | formatDate }}</span>
        <i class="el-icon-location-outline"></i>
        <span>{{ message.location }}</span>
        <i
          class="el-icon-thumb"
          @click="addLike(message)"
          :class="{ 'el-icon-thumb--active': message.liked == 1 }"
        ></i>
        <span :class="{ 'el-icon-thumb--active': message.replyId == 1 }">{{ message.likeCount }}</span>
        <i class="el-icon-chat-dot-round" @click="changeCurrentReplyMessage(message)"></i>
        <span>{{ message | replyCount }}</span>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'commentsItem',
  props: {
    subType: {
      type: Boolean,
      default: false
    },
    message: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  components: {},
  filters: {
    replyCount(message) {
      if (message.subComments && message.subComments.length) return message.subComments.length
      return ''
    }
  },
  methods: {
    addLike(message) {
      if (message.liked == 1) {
        this.$message({
          type: 'warning',
          message: "已经点赞过了哦!"
        })
        return
      }
      this.$emit('addLike', message);
    },
    changeCurrentReplyMessage(message) {
      this.$emit('changeCurrentReplyMessage', message)
    }
  }
}
</script>
<style lang="scss">
@import '~@/style/index.scss';

.comments-item {
  display: flex;
  padding: 14px 0;
  border-bottom: 1px solid rgb(245, 240, 240);

  &__content {
    flex: 1 1 auto;
    padding-left: 20px;
  }

  &__avatar {
    width: 50px;
    height: 50px;
    flex: 0 0 auto;

    img {
      border-radius: 4px;
      width: 100%;
      height: 100%;
    }
  }

  &__avatar--small {
    width: 36px;
    height: 36px;
  }

  &__visitor {
    height: 50px;

    .detail-visitor-name {
    }

    .detail-visitor-name--bold {
      color: #eaa156;
      font-weight: 700;
    }

    .detail-visitor-aite {
      font-weight: bold;
    }

    .detail-visitor-aited {
      font-size: smaller;
    }
  }

  &__detail {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    font-size: 12px;
    // color: #909399;
    @include themify() {
      color: themed('color-home-article-detail');
    }
    margin-top: 10px;

    i {
      font-size: 16px;
      margin-left: 12px;
      // font-weight: 600;
    }

    i:hover {
      color: #409eff;
    }

    span {
      margin-left: 6px;
    }

    .el-icon-thumb,
    .el-icon-chat-dot-round {
      cursor: pointer;
    }

    .el-icon-thumb--active {
      color: #409eff;
    }
  }

  &__say {
    word-break: break-word;
  }
}
</style>
