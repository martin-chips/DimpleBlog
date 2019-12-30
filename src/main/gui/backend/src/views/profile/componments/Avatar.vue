<template>
  <el-dialog :close-on-click-modal="false"
    class="update-avatar"
    title="更改头像"
    :width="width"
    top="50px"
    :close-on-press-escape="false"
    :visible.sync="isVisible"
  >
    <el-tabs v-model="activeName" class="avatar-tabs">
      <el-tab-pane label="后田花子" name="first">
        <template v-for="(avatar, index) in hthz">
          <div :key="index" class="avatar-wrapper">
            <img :src="resolveAvatar(avatar)" @click="change(avatar)">
          </div>
        </template>
      </el-tab-pane>
      <el-tab-pane label="阿里系" name="second">
        <template v-for="(avatar, index) in al">
          <div :key="index" class="avatar-wrapper">
            <img :src="resolveAvatar(avatar)" @click="change(avatar)">
          </div>
        </template>
      </el-tab-pane>
      <el-tab-pane label="脸萌" name="third">
        <template v-for="(avatar, index) in lm">
          <div :key="index" class="avatar-wrapper">
            <img :src="resolveAvatar(avatar)" @click="change(avatar)">
          </div>
        </template>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>
<script>
  import {updateUserProfile} from "@/api/system/user";

  const hthz = [
    'default.gif', 'hthz_1.jpeg',
    'hthz_2.jpg', 'hthz_3.jpg',
    'hthz_4.jpeg', 'hthz_5.jpg',
    'hthz_6.jpg', 'hthz_7.jpeg',
    'hthz_8.jpeg', 'hthz_9.jpeg',
    'hthz_10.jpeg', 'hthz_11.jpg',
    'hthz_12.jpg', 'hthz_13.jpeg',
    'hthz_14.jpeg', 'hthz_15.jpg',
    'hthz_16.jpg', 'hthz_17.jpeg'
  ];
  const al = [
    'al_2.png', 'al_1.png', 'al_3.png',
    'al_6.png', 'al_5.png', 'al_4.png'
  ];
  const lm = [
    'lm_18.jpg', 'lm_12.jpg', 'lm_19.jpg',
    'lm_13.jpg', 'lm_1.jpg', 'lm_2.jpg',
    'lm__3.jpg', 'lm_4.jpg', 'lm_5.jpg',
    'lm_6.jpg', 'lm_7.jpg', 'lm_8.jpg',
    'lm_10.jpg', 'lm_11.jpg', 'lm_14.jpg',
    'lm_15.jpg', 'lm_16.jpg', 'lm_17.jpg'
  ];
  export default {
    name: 'Avatar',
    props: {
      dialogVisible: {
        type: Boolean,
        default: false
      },
      id: {
        default: undefined
      }
    },
    data() {
      return {
        activeName: 'first',
        screenWidth: 0,
        updating: false,
        width: this.initWidth(),
        hthz,
        al,
        lm
      }
    },
    computed: {
      isVisible: {
        get() {
          return this.dialogVisible
        },
        set() {
          this.close()
        }
      }
    },
    mounted() {
      window.onresize = () => {
        return (() => {
          this.width = this.initWidth()
        })()
      }
    },
    methods: {
      resolveAvatar(avatar) {
        return require(`@/assets/avatar/${avatar}`)
      },
      change(avatar) {
        if (this.updating) {
          this.msgWarning("正在修改中");
          return
        }
        this.updating = true;
        updateUserProfile({
          avatar: avatar,
          userId: this.id
        }).then(() => {
          this.msgSuccess("修改成功");
          this.$emit('success', avatar);
          this.updating = false
        }).catch((r) => {
          this.msgError("修改失败");
          this.updating = false
        })
      },
      initWidth() {
        this.screenWidth = document.body.clientWidth;
        if (this.screenWidth < 991) {
          return '90%'
        } else if (this.screenWidth < 1400) {
          return '55%'
        } else {
          return '820px'
        }
      },
      close() {
        this.$emit('close')
      }
    }
  }
</script>
<style lang="scss" scoped>
  .update-avatar {
    padding: 0 1rem 1rem 1rem !important;

  .avatar-tabs {

  .avatar-wrapper {
    display: inline-block;

  img {
    width: 5rem;
    border-radius: 50%;
    display: inline-block;
    margin: .5rem;
    cursor: pointer;
  }

  }
  }
  }
</style>
