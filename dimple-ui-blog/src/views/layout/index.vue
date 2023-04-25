<template>
  <div class="layout">
    <header class="layout__header">
      <navbar></navbar>
      <slot name="header">
        <div class="layout__header-content">
          <layout-header :cover="cover" v-bind="$attrs">
            <slot name="custom-header"></slot>
          </layout-header>
        </div>
      </slot>
    </header>
    <main class="layout__body">
      <div class="layout__body-content">
        <div class="layout__body-page">
          <div class="body-page__wrapper">
            <slot name="custom-body">
              <el-card class="layout__body-default">
                <slot></slot>
              </el-card>
            </slot>
          </div>
        </div>
        <div v-if="panelShow" class="layout__body-panel">
          <panel></panel>
        </div>
      </div>
    </main>
    <footer class="layout__footer">
      <slot name="footer">
        <div class="layout__footer-content" :style="{ backgroundImage: 'url(' + cover + ')' }">
          <div class="layout__footer-item">
            <span>©2018 - 2023&nbsp;&nbsp;&nbsp;</span>
            <a href="https://github.com/martin-chips" target="_blank">Martin Chips</a>
          </div>
          <div class="layout__footer-item">Powered by Dimple's Blog</div>
          <div class="layout__footer-item item-icp">
            <img src="@/assets/img/icp.png" alt=""/>
            <!--<a href="http://www.beian.gov.cn/portal/index.do" target="_blank">蜀公安网备 </a>-->
            <a href="https://beian.miit.gov.cn/#/Integrated/index" target="_blank">蜀ICP备2023006757号</a>
          </div>
        </div>
      </slot>
    </footer>
  </div>
</template>
<script>
import navbar from '@/views/layout/components/navbar/'
import layoutHeader from '@/views/layout/components/header/'
import panel from '@/views/panel/'

export default {

  props: {
    panelShow: {
      type: Boolean,
      default: true
    },
    cover: {
      type: String,
      default: '/img/article/cover.jpg'
    }
  },
  components: {
    panel,
    layoutHeader,
    navbar
  },
}
</script>
<style lang="scss">
@import '~@/style/index.scss';
// clear the background color
.el-pagination, .el-menu, .el-drawer, .btn-next, .el-pagination, .el-input__inner, .el-dialog, .btn-prev, .el-pager li, .el-textarea__inner {
  background-color: transparent !important;
}

canvas:not(#ribbon-canvas) {
  -webkit-animation: to_show 4s;
  -moz-animation: to_show 4s;
  -o-animation: to_show 4s;
  -ms-animation: to_show 4s;
  animation: to_show 4s;
}

body {
  @include themify() {
    background: themed('global-bg');
  }
}

.el-drawer__body, .el-drawer__header, .el-dialog__header, .el-dialog__body {
  margin-bottom: 0px;
  @include themify() {
    background: themed('global-bg');
  }
}

.el-button {
  border: none;
  @include themify() {
    color: themed('btn-color');
    background-color: themed('btn-bg');
  }
}

.layout {
  position: relative;
  display: flex;
  flex-direction: column;
  min-height: 100vh;

  &__header {
    min-height: 150px;
    animation: header-effect 1s;
    -webkit-transition: all .5s;
    -moz-transition: all .5s;
    -o-transition: all .5s;
    -ms-transition: all .5s;
    transition: all .5s;
  }

  &__body {
    -webkit-animation: bottom-top 1s;
    -moz-animation: bottom-top 1s;
    -o-animation: bottom-top 1s;
    -ms-animation: bottom-top 1s;
    animation: bottom-top 1s;
    padding-bottom: 140px;

    &-content {
      display: flex;
      justify-content: space-between;
      // align-items: flex-start;
      margin: 0 auto;
      padding: 40px 15px;
    }

    &-page {
      flex: 0 1 auto;
      width: 75%;

      .el-card {
        box-shadow: 0 3px 8px 6px rgba(7, 17, 27, 0.06);
        border-radius: 8px;
        border: none;
        @include themify() {
          background: themed('card-bg');
          color: themed('font-color');
        }
      }
    }

    &-default {
      .el-card__body {
        padding: 50px 40px;
      }

      @include respond-to(xs) {
        .el-card__body {
          padding: 20px;
        }
      }
    }

    &-panel {
      flex: 1 1 auto;
      width: 25%;
      // border: 4px solid #ccc;
      margin-left: 16px;

      .el-card {
        border: none;
        @include themify() {
          background: themed('card-bg');
          color: themed('font-color');
        }
      }
    }

    @include respond-to(xs) {
      &-content {
        max-width: 768px;
        flex-direction: column;
        padding: 20px 5px;
      }
      &-page {
        width: 100%;
      }
      &-panel {
        width: 100%;
        margin-left: 0;
      }
    }
    @include respond-to(md) {
      &-content {
        max-width: 992px;
      }
    }
    @include respond-to(lg) {
      &-content {
        max-width: 1200px;
      }
    }
  }

  &__footer {
    -webkit-animation: bottom-top 1s;
    -moz-animation: bottom-top 1s;
    -o-animation: bottom-top 1s;
    -ms-animation: bottom-top 1s;
    animation: bottom-top 1s;
    width: 100%;
    height: 140px;
    position: absolute;
    bottom: 0;
    color: rgba(255, 255, 255, 0.7);

    &-content {
      position: relative;
      width: 100%;
      height: 100%;
      background-size: cover;
      background-position: center center;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;

      .item-icp {
        a {
          margin-left: 6px;
        }
      }
    }

    &-content:before {
      position: absolute;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      content: '';
      background: rgba(0, 0, 0, 0.4);
      z-index: 0;
    }

    &-item {
      display: flex;
      align-items: center;
      padding: 4px;
      z-index: 10;

      a {
        color: rgba(255, 255, 255, 0.7);
      }

      a:hover {
        @include themify() {
          color: themed('color-ele-primary');
        }
      }
    }
  }
}
</style>
