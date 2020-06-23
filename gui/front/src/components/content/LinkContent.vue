<template>
    <div class="link-content layout-content">
        <Row>
            <Col :xs="24" :sm="24" :md="24" :lg="17" :xl="17">
                <div class="layout-left">
                    <Divider orientation="left">
                        <div class="header" style=";">
                            <Icon type="ios-at"/>
                            <h4> &nbsp;&nbsp;本站友情链接</h4>
                        </div>
                    </Divider>
                    <Row>
                        <Col v-for="link in list" :xs="8" :sm="8" :md="7" :lg="7" :xl="7">
                            <a :href="link.url"  style="color:#333;" @click="updateCount(link.id)">
                                <Card style="height: 10rem;margin: 0.8rem 0.8rem 0.8rem 0">
                                    <div slot="title">
                                        <Avatar :src="link.headerImg"/>
                                        <span class="link-title" :title="link.title" style="">{{link.title}}</span>
                                    </div>
                                    <div class="link-desc" :title="link.description" style="">
                                        {{link.description}}
                                    </div>
                                </Card>
                            </a>
                        </Col>
                    </Row>
                    <Divider orientation="left">
                        <div class="header" style=";">
                            <Icon type="md-notifications"/>
                            <h4> &nbsp;&nbsp;申请说明</h4>
                        </div>
                    </Divider>
                    <div class="desc">
                        ① .贵站需要先添加本站友链，友链信息见下方，本站会定时扫描所有友链，请确保网站访问正常，本站自动审核程序运行中，最迟会在24小时内跟上链接，谢谢！<br>
                        ② .申请提交后若无其它原因将在24小时内审核,如超过时间还未通过,请通过首页邮件地址联系我。(各种额外因素)
                    </div>
                    <Divider orientation="left">
                        <div class="header" style=";">
                            <Icon type="md-pin"/>
                            <h4> &nbsp;&nbsp;站点信息</h4>
                        </div>
                    </Divider>
                    <div class="desc">
                        首页地址：https://www.bianxiaofeng.com <br>
                        友链地址：https://www.bianxiaofeng.com/link.html<br>
                        网站名称：Dimple的个人博客<br>
                        网站描述：边小丰的个人博客，记录学习，记录生活。<br>
                        网站图标地址：https://www.bianxiaofeng.com/favicon.ico
                    </div>
                    <Divider orientation="left">
                        <div class="header" style=";">
                            <Icon type="ios-create"/>
                            <h4> &nbsp;&nbsp;开始申请: 欢迎交换友链</h4>
                        </div>
                    </Divider>
                    <div style="width: 60%">
                        <Form ref="form" :model="form" :rules="rules" :label-width="120">
                            <FormItem prop="url" label="网站地址">
                                <Input v-model="form.url" placeholder="网站地址">
                                    <Tooltip content="输入网址自动查询" placement="top" slot="append">
                                        <Button @click="searchSiteInfo()" icon="ios-search"></Button>
                                    </Tooltip>
                                </Input>
                            </FormItem>
                            <FormItem prop="title" label="网站名">
                                <Input v-model="form.title"
                                       placeholder="请输入网站名">
                                </Input>
                            </FormItem>
                            <FormItem prop="headerImg" label="网站图标地址">
                                <Input v-model="form.headerImg"
                                       placeholder="网站图标地址">
                                </Input>
                            </FormItem>
                            <FormItem prop="description" label="网站介绍">
                                <Input type="textarea" v-model="form.description"
                                       placeholder="网站介绍">
                                </Input>
                            </FormItem>
                            <FormItem prop="email" label="站长邮箱">
                                <Input v-model="form.email"
                                       placeholder="站长邮箱(用于给您发送审核信息,您的邮箱地址将被严格保密)">
                                </Input>
                            </FormItem>
                            <FormItem>
                                <Button :type="buttonType" :loading="loading" @click="handleSubmit()">提交申请</Button>
                            </FormItem>
                        </Form>
                    </div>
                </div>
            </Col>
            <Col :xs="24" :sm="24" :md="24" :lg="7">
                <div class="layout-right">
                    <Recommend style="margin-top:15px;"/>
                    <Hot style="margin-top:15px;"/>
                </div>
            </Col>
        </Row>
    </div>
</template>

<script>
    import {listFriendLinks, insertFriendLink,updateLinkCount} from "@/api"
    import Recommend from "../../views/Recommend";
    import Hot from "../../views/Hot";
    import {mapState} from "vuex";

    export default {
        name: "LinkContent",
        components: {
            Recommend, Hot
        },
        data() {
            return {
                list: [],
                form: {},
                loading: false,
                rules: {
                    url: [
                        {required: true, message: "邮箱地址不能为空", trigger: "blur"},
                        {type: 'url', message: '请输入正确的网址', trigger: 'change'},
                        {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
                    ],
                    title: [
                        {required: true, message: "网站名不能为空", trigger: "blur"},
                        {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
                    ],
                    headerImg: [
                        {required: true, message: "网站图标不能为空", trigger: "blur"},
                        {type: 'url', message: '请输入正确的网站图标地址', trigger: 'change'},
                        {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
                    ],
                    description: [
                        {required: true, message: "描述不能为空", trigger: "blur"},
                        {min: 1, max: 100, message: '长度在 1 到 500 个字符', trigger: 'change'}
                    ],
                    email: [
                        {required: true, message: "邮箱不能为空", trigger: "blur"},
                        {type: 'email', message: '请输入正确的邮箱地址', trigger: 'change'},
                        {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
                    ]
                },
            }
        },
        computed: {
            ...mapState({
                siteTheme: state => state.base.siteTheme
            }),
            buttonType: function () {
                return this.siteTheme === 'dark' ? 'warning' : 'primary';
            },
        },
        created() {
            listFriendLinks().then(response => {
                this.list = response.data;
            });
        },
        methods: {
            updateCount(id){
                updateLinkCount(id);
            },
            searchSiteInfo() {
                this.$Notice.warning({
                    title: '网站信息查询失败,请手动输入',
                });
            },
            handleSubmit() {
                this.$refs['form'].validate(valid => {
                    if (valid) {
                        this.loading = true;
                        let obj = JSON.parse(JSON.stringify(this.form));
                        insertFriendLink(obj).then(response => {
                            if (response.code == 200) {
                                this.$Notice.success({
                                    title: '申请成功,请等待系统自动审核!',
                                });
                                this.form = {};
                            } else {
                                this.$Notice.error({
                                    title: '申请失败',
                                })
                            }
                            this.loading = false;
                        });
                    } else {
                        this.$Notice.error({
                            title: '校验失败',
                        });
                        this.loading = false;
                    }
                });
            }
        }
    }
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
    @import "../../common/stylus/theme.styl";
    .header
        display -webkit-box

    .ivu-divider
        h4
            font-size 18px

    body.dark
        .link-content
            .link-title, .link-desc
                color $dark-info-color

            .ivu-card-bordered, .ivu-card-head
                border 1px solid $dark-border-color

            .ivu-form-item-label
            .ivu-input
            .ivu-input-group-append
                background $dark-background-color
                color $dark-desc-color

            .ivu-card
                background-color $dark-cell-background-color

            .desc
                background $dark-background-color
                color $dark-info-color

            .ivu-divider
                color $dark-info-color

                &:before, &:after
                    border-top 1px solid $dark-border-color!important

                h4
                    color $dark-title-color

    .ivu-card-body
        padding 7px !important


    .link-title
        overflow hidden
        width 80%
        font-weight bold
        font-size 1.2rem
        display inline-block
        line-height inherit
        text-overflow ellipsis
        white-space nowrap

    .link-desc
        /*padding-bottom 3px*/
        overflow hidden
        display inline
        text-overflow ellipsis
        display -webkit-box
        -webkit-box-orient vertical
        -webkit-line-clamp 3

</style>
