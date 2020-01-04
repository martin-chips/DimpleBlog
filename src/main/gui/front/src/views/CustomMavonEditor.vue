<template>
    <div class="custom-mavon-editor">
        <div class="operate">
            <Form>
                <Row>
                    <Col :xs="10" :sm="10" :md="10" :lg="10" style="padding-left: 0; padding-right: 7.5px;">
                        <Input placeholder="推荐输入QQ号码自动填充" v-model="form.qqNum" clearable @on-blur="getInfoByQQ"
                               size="default">
                            <span slot="prepend">Q  Q</span>
                        </Input>
                    </Col>
                    <Col :xs="4" :sm="4" :md="4" :lg="4" style="padding-left: 0; padding-right: 0;">
                        <Avatar icon="ios-person" :src="form.avatar==undefined?'':form.avatar"/>
                    </Col>
                    <Col :xs="10" :sm="10" :md="10" :lg="10">
                        <Input v-model="form.nickName" placeholder="(必填)请输入昵称" size="default">
                            <span slot="prepend">昵称</span>
                        </Input>
                    </Col>
                </Row>
                <br>
                <Row>
                    <Col :xs="10" :sm="10" :md="10" :lg="10">
                        <Input v-model="form.email" placeholder="(必填)请输入邮箱" size="default">
                            <span slot="prepend">邮箱</span>
                        </Input>
                    </Col>
                    <Col :xs="6" :sm="6" :md="6" :lg="6">
                        <Checkbox v-model="form.reply" style=" margin: 5% 8%;">邮件通知</Checkbox>
                    </Col>
                </Row>
            </Form>
        </div>

        <div class="editor-area">
            <Spin size="large" v-if="!allowComment" fix style="z-index: 1001;">
                该文章关闭了评论功能
            </Spin>
            <mavon-editor v-model="form.content"
                          v-if="showEditor"
                          :autofocus="false"
                          style="height: 100%; min-height: 50px; min-width: 200px; z-index: 1000;"
                          :codeStyle="codeStyle"
                          :editable="allowComment"
                          :toolbarsFlag="toolbarsFlag"
                          :subfield="false"
                          placeholder="请输入评论"
                          :toolbars="toolbars"
                          @change="change"
                          ref="mavonEditor"></mavon-editor>
        </div>
        <div class="bottom-area">
            <div class="comment-tip">
                <a href="https://guides.github.com/features/mastering-markdown/" target="_blank">
                    <Icon type="logo-markdown"/>
                    支持Markdown
                </a>
            </div>
            <div class="publish-area">
                <Button size="default" :type="buttonType" :loading="publishing" @click="send"
                        :disabled="!allowComment">
                    <span v-if="!publishing">发布</span>
                    <span v-else>发布中</span>
                </Button>
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

    import {getInfoByQQ, insertComment} from '@/api'
    import marked from "marked";

    export default {
        name: 'CustomMavonEditor',
        props: {
            //页面Id
            pageId: {
                type: Number,
                default: undefined
            },
            //是否允许评论
            allowComment: {
                type: Boolean,
                default: undefined
            },
            //回复评论的级别
            replyToComment: {
                Type: Object,
                default: undefined
            },
            //回复的commentId
            replyId: {
                Type: Number,
                default: undefined
            },
            //回复的父级Id
            parentId: {
                Type: Number,
                default: undefined
            },
            url: {
                default: window.location.href
            }
        },
        data() {
            return {
                form: {
                    nickName: '',
                    qqNum: '',
                    email: '',
                    avatar: '',
                    reply: true
                },
                rules: {
                    nickName: [
                        {required: true, message: "昵称不能为空", trigger: "blur"},
                        {min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'change'}
                    ],
                    email: [
                        {required: true, message: "邮箱不能为空", trigger: "blur"},
                        {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
                    ]
                },
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
                return this.siteTheme === 'dark' ? 'warning' : 'primary';
            },
            codeStyle: {
                get: function () {
                    return this.siteTheme === 'dark' ? 'dark' : 'default';
                },
                set: function (newTheme) {
                    this.updateSiteTheme(newTheme);
                }
            },
        },
        methods: {
            getInfoByQQ() {
                if (this.form.qqNum != '') {
                    getInfoByQQ(this.form.qqNum).then(response => {
                        console.log(response);
                        if (response.code == 200) {
                            if (response.data.nickName == '') {
                                this.$Message.error("获取信息失败,请手动填写");
                                this.form.avatar = '';
                            } else {
                                this.$Message.info("获取信息成功");
                                this.form.nickName = response.data.nickName;
                                this.form.email = response.data.email;
                                this.form.avatar = response.data.avatar;
                                console.log(this.form);
                            }
                        }
                    }).catch(error => {
                        console.log(error);
                    })
                }
            },
            comment_level: function () {
                if (this.replyToComment === undefined) return 1;
                return 2;
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
                this.publishing = true;
                if (this.form.nickName.length === 0) {
                    this.$Notice.warning({
                        title: '您需要填写昵称',
                    });
                    return;
                }
                if (this.form.email.length === 0) {
                    this.$Notice.warning({
                        title: '您需要填写邮箱',
                    });
                    return;
                }
                if (this.form.content.length === 0) {
                    this.$Notice.warning({
                        title: '您需要填写内容',
                    });
                    return;
                }
                let that = this;
                this.form.htmlContent = marked(this.form.content);
                this.form.replyId = this.replyId;
                this.form.parentId = this.parentId;
                this.form.pageId = this.pageId;
                this.form.url = this.url;
                let obj = JSON.parse(JSON.stringify(this.form));
                insertComment(obj).then((response) => {
                    if (response.code == 200) {
                        // 清空评论框内容
                        this.form = {};
                        this.$Message.success("评论成功");
                        this.$emit('reloadCommentList');
                    } else {
                        this.msgError("评论失败,请重新再试!");
                    }
                    // 关闭loading状态
                    this.publishing = false;
                }).catch((error) => {
                    console.log(error);
                    // 关闭loading状态
                    that.publishing = false;
                    that.msgError("评论失败");
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
            console.log(typeof this.comment)
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
        a
            color #7e8c8d
            text-decoration none
            -webkit-backface-visibility hidden

        .operate
            margin-bottom 15px
            .ivu-input-group-prepend,
                background $default-border-color

                span
                    color $default-desc-color

            .ivu-input
                background $default-background-color
                color $default-desc-color

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
                    background $default-background-hover-color!important

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

        &.dark
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
