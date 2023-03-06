<template>
  <div class="submit">
    <div class="submit__avatar">
      <div class="submit__avatar-default">
        <img v-show="!!visitorInfo.headImage" :src="visitorInfo.headImage" :title="visitorInfo.username" />
        <i v-show="!visitorInfo.headImage" class="el-icon-user" :title="visitorInfo.username"></i>
      </div>
      <div class="submit__avatar-rel"></div>
    </div>
    <div class="submit__content">
      <div class="submit__input">
        <span v-if="currentReplyMessage.username" class="reply-name">回复 {{ currentReplyMessage.username }} :</span>
        <el-input
          ref="comment"
          type="textarea"
          :rows="3"
          placeholder="说点什么"
          @focus="focus"
          v-model="comment"
        ></el-input>
      </div>
      <div class="submit__handle">
        <div class="submit__emoji-userTag">
          <div class="submit__emoji">
            <emoji @getEmoji="getEmoji"></emoji>
          </div>
          <div class="submit__userTag" v-show="visitorInfo.username">
            <span>欢迎，{{ visitorInfo.username }}</span>
            <i class="el-icon-circle-close" title="退出" @click="logout"></i>
          </div>
        </div>

        <div class="submit__btn">
          <el-button v-if="currentReplyMessage.id" size="medium" @click="cancelReply">取消</el-button>
          <el-button size="medium" :disabled="!visitorInfo.id" @click="submitMessage">提交</el-button>
        </div>
      </div>
    </div>
    <el-dialog title="登录" :visible.sync="customVisible" width="30%" custom-class="visitor-submit-box">
      <div class="submit__login">
        <el-form label-width="60px" :model="customInfo" :rules="submitRules" ref="customForm">
          <el-form-item label="昵称" prop="username">
            <el-input v-model="customInfo.username" placeholder="请输入昵称"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="customInfo.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
          <el-form-item label="网址" prop="link">
            <el-input v-model="customInfo.link" placeholder="请输入你的主页 例如：https://awesome.me"></el-input>
          </el-form-item>
        </el-form>
        <div class="submit__register">
          <el-button size="small" type="primary" @click="register">注册</el-button>
        </div>
        <div class="submit__third-part">
          <div class="line">第三方登录</div>
          <div class="submit__third-app">
            <a href="javascript: void(0)" @click="openQQ" class="login-qq">
              <img src="~@img/qq.png" alt="QQ登录" />
            </a>
            <a href="javascript: void(0)" class="login-github" @click="openGithub">
              <img src="~@img/github.png" alt="github登录" />
            </a>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {mapMutations, mapState} from "vuex";
import {storage} from "@/utils/storage";
import emoji from "@/components/emoji";
import cover from "@/assets/img/avatar/avatar.jpeg";

export default {
  name: "submit",
  props: {
    currentReplyMessage: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  components: {
    emoji
  },
  data() {
    const nameValidator = (rule, value, callback) => {
      const reg = /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,16}$/gi;
      if (value === "") {
        callback(new Error("请输入昵称"));
      } else if (!reg.test(value)) {
        callback(new Error("昵称支持中英文、数字、下划线的组合，限16位"));
      } else {
        callback();
      }
    };
    return {
      cover: cover,
      comment: "",
      customVisible: false,
      perfectVisible: false,
      customInfo: {
        username: "",
        email: "",
        link: ""
      },
      tempInfo: {},
      submitRules: {
        username: [{ required: true, validator: nameValidator, trigger: "blur" }],
        email: [{ type: "email", required: true, message: "请填写邮箱", trigger: "blur" }],
        link: [{ type: "url", required: false, message: "请输入合法地址" }]
      }
    };
  },
  mounted() {
    if (storage.getVisitor()) {
      this.setVisitorInfo(storage.getVisitor());
    }
  },
  created() {
  },
  computed: {
    ...mapState(["visitorInfo"])
  },
  methods: {
    ...mapMutations(["setVisitor"]),
    openGithub() {

    },
    openQQ() {

    },
    register() {
      this.$refs.customForm.validate(async (valid) => {
        if (valid) {
          this.setVisitorInfo({
            ...this.customInfo,
              headImage: this.cover,
            id: 123
          });
          this.customVisible = false;
          this.customInfo = {
            username: "",
            email: "",
            link: ""
          };
        }
      });
    },
    handleQQCb() {
      this.$message({
        message: "拼命开发中😭"
      });
    },
    handleGithubCb(e) {
      this.$message({
        message: "拼命开发中😭"
      });
    },
    setVisitorInfo(info) {
      this.setVisitor(info);
      storage.setVisitor(info);
    },
    submitMessage() {
      if (this.comment.trim() === "") {
        this.$message({
          type: "warning",
          message: "Oops 至少得说两句~"
        });
        return;
      }
      this.$emit("submitContent", this.comment.trim(), () => {
        this.comment = "";
      });
    },
    cancelReply() {
      this.$emit("changeCurrentReplyMessage", {});
    },
    logout() {
      this.setVisitor({});
      storage.removeVisitor();
    },
    focus() {
      if (!storage.getVisitor()) this.customVisible = true;
    },
    getEmoji(emoji) {
      this.$refs.comment.focus();
      this.comment += emoji;
    }
  }
};
</script>
<style lang="scss">
@import '~@/style/index.scss';

.submit {
  display: flex;
  margin-top: 24px;

  &__avatar {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    @include themeify() {
      color: themed('color-avatar-icon');
      background: themed('color-avatar-bg');
    }

    &-default {
      @include flex-box-center;
      width: 100%;
      height: 100%;

      .el-icon-user {
        font-size: 42px;
      }

      img {
        width: 100%;
        height: 100%;
        border-radius: 50% 50%;
      }
    }
  }

  &__content {
    display: flex;
    flex-direction: column;
    flex: 1 1 auto;
    margin-left: 18px;
  }

  &__handle {
    display: flex;
    justify-content: space-between;
    padding: 12px 0;
  }

  &__emoji-userTag {
    display: flex;
    align-items: flex-start;
  }

  &__userTag {
    margin-left: 28px;
    color: #909399;
    display: flex;
    align-items: center;
    cursor: pointer;
    transition: all ease 0.38s;

    .el-icon-circle-close {
      margin-left: 8px;
      font-size: 16px;
      opacity: 0;
      transition: all ease 0.38s;
    }
  }

  &__userTag:hover {
    color: #409eff;

    .el-icon-circle-close {
      opacity: 1;
    }
  }

  &__input {
    width: 100%;

    .reply-name {
      display: inline-block;
      margin-bottom: 4px;
    }
  }

  &__login {
    padding: 0 20px 0 0;
  }

  &__register {
    text-align: right;
  }

  &__third-part {
    .line {
      color: #b9b9b9;
      margin: 15px 0;
      font-size: 10px;
      text-align: center;
    }
  }

  &__third-app {
    display: flex;
    justify-content: center;

    a {
      display: inline-block;
      width: 50px;
      height: 50px;
      margin: 20px;
    }

    img {
      border: none;
      width: 100%;
      height: 100%;
    }
  }

  &__perfect {
    padding: 0 20px 0 0;
  }

  &__perfect-footer {
    text-align: right;
  }
}

.visitor-submit-box {
  min-width: 340px;
}
</style>
