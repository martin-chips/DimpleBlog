<template>
  <div class="app-container">
    <el-form :model="configForm" :rules="rules" ref="configForm"
             label-width="100px">
      <el-tabs ref="tabs">
        <el-tab-pane>
          <span slot="label"><i class="el-icon-date"></i> 站点配置</span>
          <el-form-item prop="siteConfig.mask">
            <template slot="label">
              <span>灰色主题</span>
              <el-tooltip class="item" effect="dark" content="开启灰色主题后，博客网站界面将会变灰" placement="top">
                <i class="el-icon-question" style="color:#2c68ff"/>
              </el-tooltip>
            </template>
            <el-switch v-model="configForm.siteConfig.mask"></el-switch>
          </el-form-item>
          <el-form-item prop="siteConfig.infoPanel">
            <template slot="label">
              <span>网站介绍信息</span>
              <el-tooltip class="item" effect="dark" content="网站介绍信息将显示在界面上介绍信息卡片中" placement="top">
                <i class="el-icon-question" style="color:#2c68ff"/>
              </el-tooltip>
            </template>
            <editor v-model="configForm.siteConfig.infoPanel" :min-height="100"/>
          </el-form-item>
          <el-form-item label="网站介绍名称" prop="siteConfig.siteName">
            <template slot="label">
              <span>网站介绍名称</span>
              <el-tooltip class="item" effect="dark" content="网站介绍名称将显示在界面介绍信息卡片头部" placement="top">
                <i class="el-icon-question" style="color:#2c68ff"/>
              </el-tooltip>
            </template>
            <el-input v-model="configForm.siteConfig.siteName"/>
          </el-form-item>
          <el-form-item label="网站介绍头像" prop="siteConfig.avatars">
            <template slot="label">
              <span>网站介绍头像</span>
              <el-tooltip class="item" effect="dark" content="网站介绍头像将显示在界面介绍信息卡片头部" placement="top">
                <i class="el-icon-question" style="color:#2c68ff"/>
              </el-tooltip>
            </template>
            <el-input v-model="configForm.siteConfig.avatars"/>
          </el-form-item>
          <el-form-item label="版本声明" prop="siteConfig.copyright">
            <editor v-model="configForm.siteConfig.copyright"/>
          </el-form-item>
          <el-form-item label="备案号" prop="siteConfig.icp">
            <el-input v-model="configForm.siteConfig.icp"/>
          </el-form-item>
          <el-form-item label="GitHub地址" prop="siteConfig.githubUrl">
            <el-input v-model="configForm.siteConfig.githubUrl"/>
          </el-form-item>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label"><i class="el-icon-date"></i> 评论配置</span>
          <el-row style="margin-bottom: 10px">
            <el-card>
              <div slot="header" class="card-header">
                <span>启用GitHub登录</span>
                <el-switch style="float: right; padding: 3px 0" v-model="configForm.commentConfig.enableGithubLogin"></el-switch>
              </div>
              <el-alert
                type="info"
                description="用户在评论的时候可以选择使用GitHub一键登录。"
                style="margin-bottom: 10px"
                show-icon>
              </el-alert>
              <el-col :span="12">
                <el-form-item label="管理员ID" prop="commentConfig.githubLoginConfig.adminId">
                  <template slot="label">
                    <span>管理员ID</span>
                    <el-tooltip class="item" effect="dark" content="GitHub管理员ID将用于区分是否是管理员回复"
                                placement="top">
                      <i class="el-icon-question" style="color:#2c68ff"/>
                    </el-tooltip>
                  </template>
                  <el-input v-model="configForm.commentConfig.githubLoginConfig.adminId"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="ClientID" prop="commentConfig.githubLoginConfig.clientId">
                  <el-input v-model="configForm.commentConfig.githubLoginConfig.clientId"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="ClientSecrets" prop="commentConfig.githubLoginConfig.clientSecrets">
                  <el-input v-model="configForm.commentConfig.githubLoginConfig.clientSecrets"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="调用地址" prop="commentConfig.githubLoginConfig.loginUrl">
                  <template slot="label">
                    <span>调用地址</span>
                    <el-tooltip class="item" effect="dark" content="用户点击GitHub图标后将访问此页面"
                                placement="top">
                      <i class="el-icon-question" style="color:#2c68ff"/>
                    </el-tooltip>
                  </template>
                  <el-input v-model="configForm.commentConfig.githubLoginConfig.loginUrl"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="AccessTokenUrl" prop="commentConfig.githubLoginConfig.accessTokenUrl">
                  <el-input v-model="configForm.commentConfig.githubLoginConfig.accessTokenUrl"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="UserInfoUrl" prop="commentConfig.githubLoginConfig.userInfoUrl">
                  <el-input v-model="configForm.commentConfig.githubLoginConfig.userInfoUrl"></el-input>
                </el-form-item>
              </el-col>
            </el-card>
          </el-row>
          <el-row>
            <el-card>
              <div slot="header" class="card-header">
                <span>启用QQ登录</span>
                <el-switch style="float: right; padding: 3px 0"
                           v-model="configForm.commentConfig.enableQQLogin"></el-switch>
              </div>
            </el-card>
          </el-row>
          <el-card>
            <div slot="header" class="card-header">
              <span>启用本地登录</span>
              <el-switch disabled="" style="float: right; padding: 3px 0"
                         v-model="configForm.commentConfig.enableLocalLogin"></el-switch>
            </div>
            <el-alert
              type="warning"
              description="本地登录必须启用，不可更改！"
              show-icon>
            </el-alert>
          </el-card>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label"><i class="el-icon-date"></i> 邮件配置</span>
          <el-row style="margin-bottom: 10px">
            <el-card>
              <el-col :span="12">
                <el-form-item label="地址" prop="emailConfig.username">
                  <el-input v-model="configForm.emailConfig.username"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="密码" prop="emailConfig.password">
                  <template slot="label">
                    <span>密码</span>
                    <el-tooltip class="item" effect="dark" content="某些网站又称授权码"
                                placement="top">
                      <i class="el-icon-question" style="color:#2c68ff"/>
                    </el-tooltip>
                  </template>
                  <el-input v-model="configForm.emailConfig.password"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="邮件服务器地址" prop="emailConfig.host">
                  <el-input v-model="configForm.emailConfig.host"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="邮件服务器端口" prop="emailConfig.port">
                  <el-input v-model="configForm.emailConfig.port"></el-input>
                </el-form-item>
              </el-col>
            </el-card>
          </el-row>
        </el-tab-pane>
        <el-button-group class="btnGroup" ref="btnGroup">
          <el-button type="success" size="mini" :plain="true" @click="submitForm" icon="el-icon-truck" :round="true"
                     ref="submitBtn">保存
          </el-button>
          <el-button type="danger " size="mini" :plain="true" @click="deleteCache" icon="el-icon-truck" :round="true"
                     ref="refreshBtn">刷新缓存
          </el-button>
        </el-button-group>
      </el-tabs>
    </el-form>
  </div>
</template>

<script>
import {deleteCache, getConfig, updateConfig} from "@/api/blog/config";

export default {
  data() {
    return {
      configForm: {
        siteConfig: {
          mask: false,
          infoPanel: "",
          siteName: "Dimple Blog",
          avatars: "https://avatars.githubusercontent.com/u/33685170?s=48&v=4",
          icp: "蜀1-2234",
          githubUrl: "https://github.com/martin-chips",
          copyright: "©2018 - 2023&nbsp;&nbsp;&nbsp;",
          githubUserName: "Martin Chips",
        },
        emailConfig: {
          host: "",
          port: "",
          username: "",
          password: ""
        },
        commentConfig: {
          enableLocalLogin: true,
          enableGithubLogin: false,
          enableQQLogin: false,
          githubLoginConfig: {
            adminId: "",
            clientId: "",
            clientSecrets: "",
            accessTokenUrl: "",
            loginUrl: "",
            userInfoUrl: "",
          }
        }
      },
      rules: {}
    };
  },
  mounted() {
    const scrollBar = this.$refs.tabs.$el.querySelector('.el-tabs__nav-scroll')
    scrollBar.appendChild(this.$refs.btnGroup.$el);
  },
  created() {
    this.getConfig();
  },
  methods: {
    deleteCache() {
      deleteCache().then(res => {
        this.$modal.msgSuccess("删除成功");
      });
    },
    getConfig() {
      getConfig().then(res => {
        if (res.code === 200) {
          this.configForm = res.data;
          this.configForm.siteConfig.infoPanel = this.unEscapeSpecialCharacters(this.configForm.siteConfig.infoPanel)
          this.configForm.siteConfig.copyright = this.unEscapeSpecialCharacters(this.configForm.siteConfig.copyright)
        }
      })
    },
    submitForm() {
      this.$refs['configForm'].validate((valid) => {
        if (valid) {
          var form = JSON.parse(JSON.stringify(this.configForm));
          form.siteConfig.infoPanel = this.escapeSpecialCharacters(form.siteConfig.infoPanel)
          form.siteConfig.copyright = this.escapeSpecialCharacters(form.siteConfig.copyright)
          updateConfig(form).then(res => {
            if (res.code === 200) {
              this.$modal.msgSuccess("修改成功");
              this.getConfig();
            }
          })
        } else {
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>
.card-header {
  font-size: 14px;
  color: #606266;
  font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial, sans-serif;
}

.el-tabs__nav-scroll {
  display: flex;
  justify-content: space-between;
}

.btnGroup {
  z-index: 9999;
  position: absolute;
  top: 8px;
  right: 20px;
}

</style>
