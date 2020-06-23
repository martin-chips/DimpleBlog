<template>
    <div class="link-content layout-content">
        <Row>
            <Col :xs="24" :sm="24" :md="24" :lg="17" :xl="17">
                <div class="layout-left">
                    <Divider orientation="left">
                        <div class="header" style=";">
                            <Icon type="ios-at"/>
                            <h4> &nbsp;&nbsp;DimpleBlog欢迎您的留言</h4>
                        </div>
                    </Divider>
                    <SocialSection
                            :page-id="-1000"
                            :allowComment="true"
                            :commentList="list"/>
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
    import Recommend from "../../views/Recommend";
    import Hot from "../../views/Hot";
    import SocialSection from "../../views/comment/SocialSection";
    import {listComment} from "@/api"

    export default {
        name: "LinkContent",
        data() {
            return {
                list: [],
                pageId: -1000
            }
        },
        created() {
            listComment(this.pageId).then(resposne => {
                this.list = resposne.data;
            });
        },
        components: {
            Recommend, Hot, SocialSection
        },
    }
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
</style>
