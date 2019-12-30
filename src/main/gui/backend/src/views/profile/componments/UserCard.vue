<template>
  <el-card style="margin-bottom:20px;" v-if="user!=null">
    <div slot="header" class="clearfix">
      <span>个人信息</span>
    </div>
    <div class="user-profile">
      <div class="box-center">
        <pan-thumb :image="avatar" :height="'100px'" :width="'100px'" :hoverable="false">
          <el-link type="primary" class="change-avatar" @click="dialogVisible = true">更换头像
          </el-link>
        </pan-thumb>
      </div>
      <div class="box-center">
        <div class="user-name text-center">{{ user.nickName }}</div>
        <div class="user-role text-center text-muted">
          <span>{{ user.roleGroup ? user.roleGroup : "无角色" }}</span>
        </div>
      </div>
    </div>
    <div class="user-bio">
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header">
          <el-icon class="el-icon-tickets"/>
          <span>个人信息</span>
        </div>
        <div class="user-bio-section-body">
          <ul class="list-group list-group-striped">
            <li class="list-group-item">
              <svg-icon icon-class="user"/>
              用户名称
              <div class="pull-right">{{ user.userName }}</div>
            </li>
            <li class="list-group-item">
              <svg-icon icon-class="phone"/>
              手机号码
              <div class="pull-right">{{ user.phone }}</div>
            </li>
            <li class="list-group-item">
              <svg-icon icon-class="email"/>
              用户邮箱
              <div class="pull-right">{{ user.email }}</div>
            </li>
            <li class="list-group-item">
              <svg-icon icon-class="peoples"/>
              所属角色
              <div class="pull-right">{{ user.roleGroup }}</div>
            </li>
            <li class="list-group-item">
              <svg-icon icon-class="date"/>
              创建日期
              <div class="pull-right">{{ user.createTime }}</div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <avatar
      :dialog-visible="dialogVisible"
      :id="this.user.userId"
      @close="dialogVisible = false"
      @success="changeSuccess"
    />
  </el-card>
</template>

<script>
  import PanThumb from "@/components/PanThumb"
  import Avatar from "./Avatar";


  export default {
    components: {PanThumb, Avatar},
    props: {
      user: {
        type: Object,
        default: () => {
          return {}
        }
      }
    },
    computed: {
      avatar() {
        return require(`@/assets/avatar/${this.$store.state.user.avatar}`);
      }
    },
    data() {
      return {
        dialogVisible: false,
        page: {
          width: window.screen.width * 0.5,
          height: window.screen.height * 0.5
        }
      }
    },
    methods: {
      changeSuccess(avatar) {
        this.dialogVisible = false;
        this.msgSuccess("修改成功");
        this.user.avatar = avatar;
        this.$store.commit('SET_AVATAR', avatar)
      },
    }
  }
</script>

<style lang="scss" scoped>
  .box-center {
    margin: 0 auto;
    display: table;
  }

  .text-muted {
    color: #777;
  }

  .user-profile {

  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .box-social {
    padding-top: 30px;

  .el-table {
    border-top: 1px solid #dfe6ec;
  }

  }
  .user-follow {
    padding-top: 20px;
  }

  }
  .user-bio {
    margin-top: 20px;
    color: #606266;

  span {
    padding-left: 4px;
  }

  .logo-wrapper {
    display: inline-block;
    margin: 10px 0;

  img {
    width: 1.4rem;
    display: inline-block;
    margin: 0 .6rem;
    cursor: pointer;

  &
  .unbind {
    -webkit-filter: grayscale(100%);
    -moz-filter: grayscale(100%);
    -o-filter: grayscale(100%);
    filter: grayscale(100%);
  }

  &
  .radius {
    border-radius: 50%;
  }

  }
  }
  .user-bio-section {
    font-size: 14px;
    padding: 15px 0;

  .user-bio-section-header {
    border-bottom: 1px solid #dfe6ec;
    padding-bottom: 10px;
    margin-bottom: 10px;
    font-weight: bold;
  }

  }
  }
</style>
