<template>
  <div class="custom-mavon-editor" :class="theme">
    <div class="operate">
      <el-row>
        <el-col :xs="8" :sm="8" :md="6" :lg="6" style="padding-left: 0; padding-right: 7.5px;">
          <el-input v-model="nickName" placeholder="请输入昵称" size="large">
            <span slot="prepend">昵称</span>
          </el-input>
        </el-col>
        <el-col :xs="16" :sm="16" :md="12" :lg="11" style="padding-left: 0; padding-right: 0;">
          <el-input v-model="email" placeholder="请输入邮箱" size="large">
            <span slot="prepend">邮箱地址</span>
          </el-input>
        </el-col>
      </el-row>
    </div>
    <div class="editor-area">
      <!--      <i-spin size="large" v-if="!post.is_commentable" fix style="z-index: 1001;">-->
      <!--        {{ post.is_commentable ? '' : $t('comments.commentClosed') }}-->
      <!--      </i-spin>-->
      <mavon-editor v-model="origin_content"
                    v-if="showEditor"
                    style="height: 100%; min-height: 50px; min-width: 200px; z-index: 1000;"
                    :codeStyle="codeStyle"
                    :editable="post.comment"
                    :toolbarsFlag="toolbarsFlag"
                    :subfield="subfield"
                    :placeholder="placeholderText"
                    :toolbars="toolbars"
                    @change="change"
                    @imgAdd="addImage"
                    @save="saveEditorContent"
                    ref="mavonEditor"></mavon-editor>
    </div>
    <div class="bottom-area">
      <div class="comment-tip">
        <a href="https://guides.github.com/features/mastering-markdown/" target="_blank">
          <i class=el-icon-orange></i>
          支持Markdown</a>
      </div>
      <div class="publish-area">
        <el-button size="default" :type="buttonType" :loading="publishing" @click="send"
                   :disabled="!post.comment">
          <span v-if="!publishing">发布</span>
          <span v-else>发布中</span>
        </el-button>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import {
    mapState
  } from 'vuex';
  import MavonEditor from 'mavon-editor';
  import 'mavon-editor/dist/css/index.css';


  export default {
    name: 'custom-mavon-editor',
    props: {
      post: {
        Type: Object,
        default: undefined
      },
      replyToComment: {
        Type: Object,
        default: undefined
      },
      theme: {
        Type: String,
        default: ''
      },
      subfield: {
        default: false
      },
      placeholder: {
        default: undefined
      }
    },
    data() {
      return {
        guest: undefined,
        reply_to: undefined,
        nickName: '',
        email: '',
        mobile: '',
        origin_content: '',
        select: 'email',
        showEditor: false,
        valueChanged: false,
        toolbarsFlag: false,
        toolbars: {
          bold: true, // 粗体
          italic: true, // 斜体
          header: true, // 标题
          underline: true, // 下划线
          strikethrough: true, // 中划线
          mark: true, // 标记
          superscript: true, // 上角标
          subscript: true, // 下角标
          quote: true, // 引用
          ol: true, // 有序列表
          ul: true, // 无序列表
          link: true, // 链接
          imagelink: true, // 图片链接
          code: true, // code
          table: true, // 表格
          fullscreen: true, // 全屏编辑
          readmodel: false, // 沉浸式阅读
          htmlcode: false, // 展示html源码
          help: false, // 帮助
          /* 1.3.5 */
          undo: true, // 上一步
          redo: true, // 下一步
          trash: true, // 清空
          save: true, // 保存（触发events中的save事件）
          /* 1.4.2 */
          navigation: false, // 导航目录
          /* 2.1.8 */
          alignleft: true, // 左对齐
          aligncenter: true, // 居中
          alignright: true, // 右对齐
          /* 2.2.1 */
          subfield: true, // 单双栏模式
          preview: true // 预览
        },
        publishing: false
      };
    },
    computed: {
      ...mapState({
        siteTheme: state => state.base.siteTheme
      }),
      buttonType: function () {
        return this.theme === 'dark-theme' ? 'warning' : 'primary';
      },
      codeStyle: {
        get: function () {
          return this.siteTheme === 'dark' ? 'dark' : 'default';
        },
        set: function (newTheme) {
          this.updateSiteTheme(newTheme);
        }
      },
      placeholderText: function () {
        if (this.placeholder !== undefined && this.placeholder.length > 0) {
          return this.placeholder;
        } else {
          return "请输入评论";
        }
      }
    },
    methods: {
      comment_level: function () {
        if (this.replyToComment === undefined) return 0;
        return 1;
      },
      reply_to_author: function () {
        if (this.replyToComment === undefined) return null;
        return this.replyToComment.author.id;
      },
      parent_comment: function () {
        if (this.replyToComment === undefined) return null;
        if (this.replyToComment.parent_comment === null) return this.replyToComment.id;
        return this.replyToComment.parent_comment;
      },
      reply_to_comment: function () {
        if (this.replyToComment === undefined) return null;
        return this.replyToComment.id;
      },
      change(value) {
        if (value.length > 0) {
          if (!this.valueChanged) {
            this.$emit('valueChanged', true);
            this.valueChanged = true;
            this.toolbarsFlag = true;
            this.toolbars['navigation'] = true;
            this.listenWindowWidth();
          }
        } else {
          if (this.valueChanged) {
            this.$emit('valueChanged', false);
            this.valueChanged = false;
            this.toolbarsFlag = false;
            this.toolbars['navigation'] = false;
            this.listenWindowWidth();
          }
        }
      },
      addImage(pos, $file) {
        uploadFile($file, 'comment', (hash, fileURL) => {
          this.$refs.mavonEditor.$img2Url(pos, fileURL);
        }, (error) => {
          console.log(error);
        });
      },
      saveEditorContent(origin, formatted) {
        console.log(origin, formatted);
        saveToLocal('comment_temp', `${this.$route.fullPath}-${this.reply_to}`, origin);
      },
      listenWindowWidth() {
        // 此方法用于监听窗口宽度变化,改变编辑器菜单
        if (!document) return;
        let clientWidth = document.documentElement.clientWidth;
        if (clientWidth < 900) {
          this.$set(this.toolbars, 'bold', false);
          this.$set(this.toolbars, 'italic', false);
          this.$set(this.toolbars, 'header', false);
          this.$set(this.toolbars, 'underline', false);
          this.$set(this.toolbars, 'strikethrough', false);
          this.$set(this.toolbars, 'mark', false);
          this.$set(this.toolbars, 'superscript', false);
          this.$set(this.toolbars, 'subscript', false);
          this.$set(this.toolbars, 'quote', false);
          this.$set(this.toolbars, 'ol', false);
          this.$set(this.toolbars, 'ul', false);
          this.$set(this.toolbars, 'link', false);
          this.$set(this.toolbars, 'imagelink', false);
          this.$set(this.toolbars, 'code', false);
          this.$set(this.toolbars, 'table', false);
          this.$set(this.toolbars, 'alignleft', false);
          this.$set(this.toolbars, 'aligncenter', false);
          this.$set(this.toolbars, 'alignright', false);
          this.$set(this.toolbars, 'readmodel', false);
          this.$set(this.toolbars, 'htmlcode', false);
          this.$set(this.toolbars, 'navigation', false);
          this.$set(this.toolbars, 'subfield', false);
        } else {
          this.$set(this.toolbars, 'bold', true);
          this.$set(this.toolbars, 'italic', true);
          this.$set(this.toolbars, 'header', true);
          this.$set(this.toolbars, 'underline', true);
          this.$set(this.toolbars, 'strikethrough', true);
          this.$set(this.toolbars, 'mark', true);
          this.$set(this.toolbars, 'superscript', true);
          this.$set(this.toolbars, 'subscript', true);
          this.$set(this.toolbars, 'quote', true);
          this.$set(this.toolbars, 'ol', true);
          this.$set(this.toolbars, 'ul', true);
          this.$set(this.toolbars, 'link', true);
          this.$set(this.toolbars, 'imagelink', true);
          this.$set(this.toolbars, 'code', true);
          this.$set(this.toolbars, 'table', true);
          this.$set(this.toolbars, 'alignleft', true);
          this.$set(this.toolbars, 'aligncenter', true);
          this.$set(this.toolbars, 'alignright', true);
          this.$set(this.toolbars, 'readmodel', true);
          this.$set(this.toolbars, 'htmlcode', true);
          this.$set(this.toolbars, 'navigation', true);
          this.$set(this.toolbars, 'subfield', true);
        }
      },
      send() {
        if (this.nickName.length === 0) {
          this.msgError('您需要填写昵称');
          return;
        }
        if (this.email.length === 0) {
          this.msgError('您需要填写您的邮箱');
          return;
        }
        if (this.origin_content.length === 0) {
          this.msgError('您需要填写评论内容');
          return;
        }
        console.log('replyToComment', this.replyToComment);
        if (!(loadFromLocal('comment_auth', 'email', '') === this.email &&
          loadFromLocal('comment_auth', 'verified', false) &&
          loadFromLocal('comment_auth', 'author_id', undefined) !== undefined &&
          loadFromLocal('comment_auth', 'nick_name', undefined) !== undefined)) {
          // 该邮箱在本地没有评论记录,需要验证邮箱
          this.publishing = true;
          let that = this;
          API.getEmailCode({
            nick_name: this.nickName,
            email: this.email
          }).then((response) => {
            this.checkEmail();
          }).catch((error) => {
            console.log(error.data.email[0]);
            that.$Notice.error({
              title: '验证码发送失败',
              desc: error.data.email[0]
            });
            // 关闭loading状态
            that.publishing = false;
          });
        } else {
          // 该邮箱在本地有评论记录,直接评论
          this.guest = loadFromLocal('comment_auth', 'author_id', undefined);
          this.publish();
        }
      },
      checkEmail() {
        let that = this;
        this.$Modal.confirm({
          render: (h) => {
            let children = [];
            children.push(h('h2', {
              domProps: {
                innerHTML: '提示'
              },
              'class': {
                'modal-title': true
              }
            }));
            children.push(h('p', {
              domProps: {
                innerHTML: '第一次评论需要验证您的邮箱有效性，已经向您的邮箱发送了验证码，请输入验证码验证后再进行评论'
              },
              'class': {
                'modal-message': true
              }
            }));
            children.push(h('i-input', {
              props: {
                value: this.value,
                autofocus: true,
                placeholder: '请输入验证码'
              },
              'class': {
                'modal-input': true
              },
              on: {
                input: (value) => {
                  this.email_code = value;
                }
              }
            }));
            return h('div', {}, children);
          },
          onCancel: () => {
            // 关闭loading状态
            this.publishing = false;
          },
          onOk: () => {
            API.verifyEmailCode({
              params: {
                email: that.email,
                nick_name: that.nickName,
                code: this.email_code
              }
            }).then((response) => {
              this.guest = response.data.guest;
              saveToLocal('comment_auth', 'email', this.email);
              saveToLocal('comment_auth', 'verified', true);
              saveToLocal('comment_auth', 'author_id', this.guest);
              saveToLocal('comment_auth', 'nick_name', this.nickName);
              this.publish();
            }).catch((error) => {
              that.$Notice.error({
                title: '验证码验证失败',
                desc: error.error,
                onClose: () => {
                  that.checkEmail();
                }
              });
            });
          }
        });
      },
      publish() {
        let that = this;
        API.addCommentInfo({
          detail: {
            origin_content: this.origin_content
          },
          author: this.guest,
          reply_to_author: this.reply_to_author(),
          comment_level: this.comment_level(),
          is_active: true,
          post: this.post.id,
          parent_comment: this.parent_comment(),
          reply_to_comment: this.reply_to_comment()
        }).then((response) => {
          // 清空评论框内容
          this.origin_content = '';
          // 关闭loading状态
          this.publishing = false;
          this.$Notice.success({
            title: '提示',
            desc: '发送评论成功'
          });
          let comment = response.data;
          comment.author = {
            id: this.guest,
            nick_name: this.nickName
          };
          comment.reply_to_author = this.replyToComment ? this.replyToComment.author : null;
          comment.sub_comment = [];
          this.$emit('publishedComment', comment);
        }).catch((error) => {
          console.log(error);
          // 关闭loading状态
          that.publishing = false;
          that.$Notice.error({
            title: '发送评论失败',
            desc: error.error
          });
        });
      }
    },
    mounted() {
      // 显示编辑器
      this.showEditor = true;
      // 监听屏幕大小
      let that = this;
      if (!window) return;
      window.onresize = function temp() {
        that.listenWindowWidth();
      };
    },
    components: {
      'mavon-editor': MavonEditor.mavonEditor
    }
  };
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../common/stylus/theme.styl";
  .custom-mavon-editor
    position relative
    height 100%
    width 100%
    display flex
    flex-direction column

    .operate
      margin-bottom 15px

      .ivu-input-group-prepend
        background $default-border-color

        span
          color $default-desc-color

      .ivu-input
        background $default-background-color
        color $default-desc-color

      .i-dropdown-link
        display block
        height 36px
        line-height 36px
        text-align right
        font-size 15px
        color $default-desc-hover-color

        &:hover
          cursor pointer

    .editor-area
      position relative
      flex 1
      padding 2px
      height 100%
      min-height 50px
      min-width 200px
      // 编辑器区域

      .markdown-body
        background $default-background-color

        .v-note-op
          background $default-background-hover-color

        textarea
          background $default-background-color
          color $default-desc-color

        .v-show-content
          background $default-background-color
          color $default-desc-color

    .bottom-area
      display flex
      padding-top 15px
      justify-content space-between

      .publish-area
        display flex

    &.dark-theme
      .operate
        margin-bottom 15px

        .i-dropdown-link
          display block
          height 36px
          line-height 36px
          text-align right
          font-size 15px
          color $color-gradually-gray-61

          &:hover
            color $iview-secondary-warning-color
            border-bottom 2px solid $iview-secondary-warning-color
            cursor pointer

      .bottom-area
        .comment-tip
          a
            color $default-link-color

            &:hover
              color $default-link-hover-color
</style>
