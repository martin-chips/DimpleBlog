<template>
    <div>
        <layout title="友链" :cover="cover">
            <template>
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <h2>友链列表</h2>
                    </div>
                    <el-row :gutter="20" v-if="links.length">
                        <div v-for="(link, index) in links" :key="index">
                            <el-col el-col :span="8">
                                <el-card :title="link.description" shadow="hover" :body-style="{ padding: '0px' }">
                                    <a :href="link.url">
                                        <img v-lazy="link.headerImage" class="flink-item-icon">
                                    </a>
                                    <div style="padding: 14px;">
                                        <span class="flink-item-name">{{ link.title }}</span>
                                        <div>
                                            <div class="flink-item-desc">{{ link.description }}</div>
                                        </div>
                                    </div>
                                </el-card>
                            </el-col>
                        </div>
                    </el-row>
                    <ElEmpty v-else></ElEmpty>
                </el-card>
            </template>
            <el-card>
                <div slot="header" class="clearfix">
                    <h2>申请须知</h2>
                </div>
                <p class="desc">
                    ① .贵站需要先添加本站友链，友链信息见下方，本站会定时扫描所有友链，请确保网站访问正常，本站自动审核程序运行中，最迟会在24小时内跟上链接，谢谢！<br>
                    ② .申请提交后若无其它原因将在24小时内审核,如超过时间还未通过,请通过首页邮件地址联系我。(各种额外因素)
                </p>
                <p class="desc">
                    首页地址：https://www.bianxf.com <br>
                    友链地址：https://www.bianxf.com/app/link<br>
                    网站名称：Dimple的个人博客<br>
                    网站描述：边小丰的个人博客，记录学习，记录生活。<br>
                    网站图标地址：https://www.bianxf.com/favicon.ico
                </p>
            </el-card>
            <el-card shadow="hover" style="margin-top: 10px">
                <div slot="header" class="clearfix">
                    <h2>申请友链</h2>
                </div>
                <el-form :model="linkForm" ref="linkForm" :rules="rules" id="submitLink">
                    <el-form-item label="网站名称" prop="title">
                        <el-input v-model="linkForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="网站地址" prop="url">
                        <el-input v-model="linkForm.url"></el-input>
                    </el-form-item>
                    <el-form-item label="网站描述" prop="description">
                        <el-input v-model="linkForm.description"></el-input>
                    </el-form-item>
                    <el-form-item label="网站图标" prop="headerImage">
                        <el-input v-model="linkForm.headerImage"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱地址" prop="email">
                        <el-input v-model="linkForm.email"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="commitForm" type="primary">立即申请</el-button>
                    </el-form-item>
                </el-form>
            </el-card>
        </layout>
    </div>
</template>
<script>
import api from "@/api/";
import cover from "@/assets/img/cover/archive.jpeg";

async function getArchiveRes(pageNum = 1, pageSize = 10) {
    const params = {
        pageSize,
        pageNum
    };
    const archiveRes = await api.getArchives(params);
    return archiveRes;
}

export default {
    name: "archives",
    metaInfo() {
        return {
            title: `友链  - Dimple's Blog`,
            meta: [
                {
                    name: "description",
                    content: "友链"
                },
                {
                    name: "keywords",
                    content: "友链"
                }
            ]
        };
    },
    data() {
        return {
            rules: {
                title: [
                    {required: true, message: '请输入网站名称', trigger: 'blur'},
                    {min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur'}
                ],
                url: [
                    {required: true, message: '请输入网站地址', trigger: 'blur'},
                ],
                headerImage: [
                    {required: true, message: '请输入网站图标地址', trigger: 'blur'},
                ],
                description: [
                    {required: true, message: '请输入网站描述', trigger: 'blur'},
                    {min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur'}
                ],
            },
            linkForm: {},
            cover: cover,
            pageNum: 1,
            pageSize: 10,
            total: 0,
            links: []
        };
    },
    watch: {},
    created() {
        this.getLinks()
    },
    methods: {
        async commitForm() {
            this.$refs["linkForm"].validate(async (valid) => {
                if (valid) {
                    var addLinkRes = await this.$api.addLink(this.linkForm);
                    if (addLinkRes.code === 200) {
                        this.$message({
                            type: 'success',
                            message: '申请成功!'
                        })
                        this.$refs["linkForm"].resetFields()
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        async getLinks() {
            const archiveRes = await this.$api.listLink(this.pageNum, this.pageSize);
            if (archiveRes.code === 200) {
                this.links = archiveRes.rows;
                this.total = archiveRes.total;
            }
        }
    }
};
</script>
<style lang="scss">
@import '~@/style/index.scss';

.flink-item-icon {
  border-radius: 4px;
  width: 100%;
  height: 100%;
  -o-object-fit: cover;
  object-fit: cover;
  -webkit-transform: translateZ(0);
  -moz-transform: translateZ(0);
  -o-transform: translateZ(0);
  -ms-transform: translateZ(0);
  transform: translateZ(0)
}

.flink-item-name {
  cursor: pointer;
  @include themeify() {
    color: themed('color-ele-primary');
  }
}

.flink-item-desc {
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

</style>
