<template>
  <div class="comments">
    <div class="comments__top" v-for="(msg, index) in messages" :key="index">
      <comments-item
        :message="msg"
        @changeCurrentReplyMessage="changeCurrentReplyMessage"
        @addLike="addLike"
      ></comments-item>
      <submit
        v-if="msg.id === currentReplyMessage.id"
        :currentReplyMessage="currentReplyMessage"
        @changeCurrentReplyMessage="changeCurrentReplyMessage"
        @submitContent="submitContent"
      ></submit>
      <div class="comments__sub" v-if="msg.subComments && msg.subComments.length">
        <template v-for="(reply, _index) in msg.subComments">
          <comments-item
            :key="'reply_' + _index"
            :message="reply"
            :subType="true"
            @addLike="addLike"
            @changeCurrentReplyMessage="changeCurrentReplyMessage"
          ></comments-item>
          <submit
            v-if="reply.id === currentReplyMessage.id"
            :key="_index"
            :currentReplyMessage="currentReplyMessage"
            @submitContent="submitContent"
            @changeCurrentReplyMessage="changeCurrentReplyMessage"
          ></submit>
        </template>
      </div>
    </div>
    <ElEmpty class="comments__empty" description="emm...没人理我" v-if="!messages.length"></ElEmpty>
  </div>
</template>
<script>
import commentsItem from "./comments-item.vue";
import submit from "./submit";

export default {
  name: "comments",
  props: {
    messages: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  data() {
    return {
      currentReplyMessage: {}
    };
  },
  watch: {
    messages(newValue, oldValue) {

    }
  },
  components: {
    commentsItem,
    submit
  },
  methods: {
    submitContent(content, cb) {
      this.$emit("submitReply", content, this.currentReplyMessage, cb);
    },
    changeCurrentReplyMessage(message) {
      this.currentReplyMessage = message;
    },
    addLike(message) {
      this.$emit("addLike", message);
    }
  }
};
</script>
<style lang="scss">
@import '~@/style/index.scss';

.comments {
  @include themify() {
    color: themed('color-comments');
  }

  &__top {
  }

  &__sub {
    padding-left: 50px;
    @include respond-to(xs) {
      padding-left: 20px;
    }
  }

  &__empty {
    padding: 16px;
    text-align: center;
    @include themify() {
      color: themed('color-ele-holder');
    }
  }
}
</style>
