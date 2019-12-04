<template>
    <div class="link-content layout-content">
        <Row>
            <Col :xs="24" :sm="24" :md="24" :lg="17" :xl="17">
                <div class="layout-left">
                    <Card>
                        <Row>
                            <Col v-for="link in list" :xs="10" :sm="10" :md="7" :lg="7" :xl="7" offset="1">
                                <Card style="height: 10rem">
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
                    </Card>
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