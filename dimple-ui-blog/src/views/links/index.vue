<template>
  <div>
    <layout title="友链" cover="/img/cover/links.jpg">
      <el-card class='flink' shadow="hover">
        <div slot="header" class="clearfix">
          <h2><i class="el-icon-s-unfold" style="color: #f66"></i>&nbsp;友链列表</h2>
        </div>
        <div class="flink-list" v-if="links.length">
          <div v-for="(link, index) in links" :key="index" class="flink-list-item">
            <a @click="addLinkVisitCount(link.id)" :href="link.url" :title="link.title" target="_blank">
              <div class="flink-item-icon">
                <img v-lazy="link.headerImage"
                     :alt="link.title">
              </div>
              <div class="flink-item-name">{{ link.title }}</div>
              <div class="flink-item-desc"
                   :title="link.description">
                {{ link.description }}
              </div>
            </a>
          </div>
        </div>
        <ElEmpty v-else></ElEmpty>
      </el-card>
      <el-card>
        <div slot="header" class="clearfix">
          <h2><i class="el-icon-s-opportunity" style="color: #0cc"></i>&nbsp;申请须知</h2>
        </div>
        <p class="desc">
          ① .贵站需要先添加本站友链，友链信息见下方，本站会定时扫描所有友链，请确保网站访问正常，本站自动审核程序运行中，最迟会在24小时内跟上链接，谢谢！<br>
          ② .申请提交后若无其它原因将在24小时内审核,如超过时间还未通过,请通过首页邮件地址联系我。(各种额外因素)
        </p>
        <p class="desc">
          首页地址：https://www.bianxf.com <br>
          友链地址：https://www.bianxf.com/app/link<br>
          网站名称：Dimple的个人博客<br>
          网站描述：个人博客，记录学习，记录生活。<br>
          网站图标地址：https://www.bianxf.com/favicon.ico
        </p>
      </el-card>
      <el-card shadow="hover" style="margin-top: 10px">
        <div slot="header" class="clearfix">
          <h2><i class="el-icon-edit-outline" style="    color: blue;"></i>&nbsp;申请友链</h2>
        </div>
        <el-form :model="linkForm" ref="linkForm" :rules="rules" id="submitLink">
          <el-form-item label="网站名称" prop="title">
            <el-input placeholder="请输入网站名称，如Dimple's Blog" v-model="linkForm.title"></el-input>
          </el-form-item>
          <el-form-item label="网站地址" prop="url">
            <el-input placeholder="请输入网站地址，如https://www.bianxf.com" v-model="linkForm.url"></el-input>
          </el-form-item>
          <el-form-item label="网站描述" prop="description">
            <el-input placeholder="请输入网站描述，如'个人博客，记录学习，记录生活'"
                      v-model="linkForm.description"></el-input>
          </el-form-item>
          <el-form-item label="网站图标" prop="headerImage">
            <el-input placeholder="请输入网站图标地址" v-model="linkForm.headerImage"></el-input>
          </el-form-item>
          <el-form-item label="邮箱地址" prop="email">
            <el-input placeholder="请输入邮箱地址，将用于接收审核通知" v-model="linkForm.email"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-finished" @click="commitForm" type="primary">立即申请</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </layout>
  </div>
</template>
<script>
import api from '@/api/'

export default {
  name: "archives",
  metaInfo() {
    return {
      title: `友链  - ` + "Dimple's Blog",
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
          {min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur'}
        ],
        headerImage: [
          {required: true, message: '请输入网站图标地址', trigger: 'blur'},
        ],
        email: [{type: 'email', required: true, message: '请填写邮箱', trigger: 'blur'},
          {min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur'}],
        description: [
          {required: true, message: '请输入网站描述', trigger: 'blur'},
          {min: 3, max: 100, message: '长度在 3 到 100 个字符', trigger: 'blur'}
        ],
      },
      linkForm: {},
      pageNum: 1,
      pageSize: 10,
      total: 0,
      links: []
    };
  },
  watch: {},
  async asyncData() {
    const archiveRes = await api.listLink(1, 10);
    if (archiveRes.code == 200) {
      return {
        links: archiveRes.rows,
        total: archiveRes.total
      }
    }
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
    async addLinkVisitCount(id) {
      const archiveRes = await this.$api.addLinkVisitCount(id);
    }
  }
};
</script>
<style lang="scss" scoped>
@import '~@/style/index.scss';

.flink .flink-list > .flink-list-item:hover:before {
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -o-transform: scale(1);
  -ms-transform: scale(1);
  transform: scale(1);
}

.flink {
  margin-bottom: 20px
}

.flink .flink-list {
  overflow: auto;
  padding: 10px 10px 0;
  text-align: center
}

.flink .flink-list > .flink-list-item {
  position: relative;
  float: left;
  overflow: hidden;
  margin: 15px 7px;
  width: calc(100% / 3 - 15px);
  height: 90px;
  border-radius: 8px;
  line-height: 17px;
  -webkit-transform: translateZ(0)
}

@media screen and (max-width: 1024px) {
  .flink .flink-list > .flink-list-item {
    width: calc(50% - 15px) !important
  }
}

@media screen and (max-width: 600px) {
  .flink .flink-list > .flink-list-item {
    width: calc(100% - 15px) !important
  }
}

.flink .flink-list > .flink-list-item:hover .flink-item-icon {
  margin-left: -10px;
  width: 0
}

.flink .flink-list > .flink-list-item:before {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: -1;
  @include themify() {
    background: themed('text-bg-hover');
  }
  content: '';
  -webkit-transition: -webkit-transform .3s ease-out;
  -moz-transition: -moz-transform .3s ease-out;
  -o-transition: -o-transform .3s ease-out;
  -ms-transition: -ms-transform .3s ease-out;
  transition: transform .3s ease-out;
  -webkit-transform: scale(0);
  -moz-transform: scale(0);
  -o-transform: scale(0);
  -ms-transform: scale(0);
  transform: scale(0)
}

.flink .flink-list > .flink-list-item:active:before, .flink .flink-list > .flink-list-item:focus:before, .flink .flink-list > .flink-list-item:hover:before {
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -o-transform: scale(1);
  -ms-transform: scale(1);
  transform: scale(1)
}

.flink .flink-list > .flink-list-item a {
  @include themify() {
    color: themed('font-color');
  }
  text-decoration: none
}

.flink .flink-list > .flink-list-item a .flink-item-icon {
  float: left;
  overflow: hidden;
  margin: 15px 10px;
  width: 60px;
  height: 60px;
  border-radius: 35px;
  -webkit-transition: width .3s ease-out;
  -moz-transition: width .3s ease-out;
  -o-transition: width .3s ease-out;
  -ms-transition: width .3s ease-out;
  transition: width .3s ease-out
}

.flink .flink-list > .flink-list-item a .flink-item-icon img {
  width: 100%;
  height: 100%;
  -webkit-transition: filter 375ms ease-in .2s, -webkit-transform .3s;
  -moz-transition: filter 375ms ease-in .2s, -moz-transform .3s;
  -o-transition: filter 375ms ease-in .2s, -o-transform .3s;
  -ms-transition: filter 375ms ease-in .2s, -ms-transform .3s;
  transition: filter 375ms ease-in .2s, transform .3s;
  object-fit: cover
}

.flink .flink-list > .flink-list-item a .img-alt {
  display: none
}

.flink .flink-item-name {
  padding: 16px 10px 0 0;
  height: 40px;
  font-weight: 700;
  font-size: 1.43em
}

.flink .flink-item-desc {
  padding: 16px 10px 16px 0;
  height: 50px;
  font-size: .93em
}

.flink .flink-name {
  margin-bottom: 5px;
  font-weight: 700;
  font-size: 1.5em
}
</style>
