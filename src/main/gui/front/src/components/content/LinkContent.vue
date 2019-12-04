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
                            <Card style="height: 10rem;margin: 0.8rem 0.8rem 0.8rem 0">
                                <div slot="title">
                                    <Avatar :src="link.headerImg"/>
                                    <span class="link-title" :title="link.title" style="">{{link.title}}</span>
                                </div>
                                <div class="link-desc" :title="link.description" style="">
                                    {{link.description}}
                                </div>
                            </Card>
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
    import {listFriendLinks} from "@/api"
    import Recommend from "../../views/Recommend";
    import Hot from "../../views/Hot";

    export default {
        name: "LinkContent",
        components: {
            Recommend, Hot
        },
        data() {
            return {
                list: []
            }
        },
        created() {
            listFriendLinks().then(response => {
                this.list = response.data;
            });
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
        .ivu-card-bordered,.ivu-card-head
             border 1px solid $dark-border-color
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
        padding-bottom 3px
        overflow hidden
        display inline
        text-overflow ellipsis
        display -webkit-box
        -webkit-box-orient vertical
        -webkit-line-clamp 3

</style>