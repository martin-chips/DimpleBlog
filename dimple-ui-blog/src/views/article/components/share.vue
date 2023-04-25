<template>
  <div class="share">
    <div class="share__tags">
      <i class="el-icon-price-tag"/>
      <el-tag size="small" class="tag" v-for="(tag, index) in tags" :key="index" effect="plain" type="">
        {{ tag }}
      </el-tag>
    </div>
    <div class="share__apps">
      <a
        href="javascript: void(0)"
        @click="share('QQ', 'http://connect.qq.com/widget/shareqq/index.html')"
        class="design-bg-qq"
      ></a>
      <a
        href="javascript: void(0)"
        @click="share('qzone', 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey')"
        class="design-bg-qzone"
      ></a>
      <a
        href="javascript: void(0)"
        @click="share('sina', 'http://v.t.sina.com.cn/share/share.php')"
        class="design-bg-sina"
      ></a>
      <el-popover placement="top" width="200" trigger="hover" @show="gQrcode" @hide="hideQrcode">
        <div class="wx-qrcode">
          <div class="wx-qrcode__title">微信扫一扫：分享</div>
          <div id="qrcode"></div>
        </div>
        <a href="javascript: void(0)" slot="reference" class="design-bg-weixin"></a>
      </el-popover>
      <a
        href="javascript: void(0)"
        @click="share('douban', 'http://shuo.douban.com/!service/share')"
        class="design-bg-douban"
      ></a>
    </div>
  </div>
</template>
<script>
export default {
  name: "share",
  props: {
    // 文章标签
    tags: {
      type: Array,
      default() {
        return [];
      }
    },
    // 文章标题
    url: {
      type: String,
      default: ""
    },
    // 文章链接
    title: {
      type: String,
      default: ""
    },
    // 分享内容摘要
    summary: {
      type: String,
      default: ""
    }
  },
  data() {
    return {};
  },
  components: {},
  methods: {
    share(type, appUrl) {
      const title = this.title;
      const el = document.createElement("a");
      // 分享的指向地址
      let url = this.url || window.location.href;
      let href = "";

      switch (type) {
        case "QQ":
          href = `${appUrl}?title=${title}&url=${url}&desc=${this.summary}&site=Dimple's Blog`;
          break;
        case "qzone":
          href = `${appUrl}?title=${title}&url=${url}&desc=${this.summary}&site=Dimple's Blog&summary=${this.summary}`;
          break;
        case "sina":
          href = `${appUrl}?title=${title}&url=${url}`;
          break;
        case "weixin":
          href = `${appUrl}&url=${url}`;
          break;
        case "douban":
          href = `${appUrl}?name=${title}&href=${url}`;
      }
      el.target = "_blank";
      el.href = href;
      el.click();
    },
    gQrcode() {
      const url = this.url || window.location.href;
      new QRCode(document.getElementById("qrcode"), {
        text: url,
        width: 160,
        height: 160,
        colorDark: "#000000",
        colorLight: "#ffffff",
        correctLevel: QRCode.CorrectLevel.H
      });
    },
    hideQrcode() {
      document.getElementById("qrcode").innerHTML = "";
    }
  }
};
</script>
<style lang="scss">
@import '~@/style/index.scss';

.share {
  @include flex-box-center;
  @include respond-to(xs) {
    flex-wrap: wrap;
  }

  &__tags {
    width: 60%;
    @include respond-to(xs) {
      width: 100%;
    }
    margin-right: 12px;

    > .tag {
      background: transparent;
      margin-right: 12px;
      margin-bottom: 8px;
    }

    > .tag:hover {
      background: #49b1f5;
      @include themify() {
        color: themed('color-white');
      }
    }
  }

  &__apps {
    display: flex;
    justify-content: flex-end;
    width: 40%;
    @include respond-to(xs) {
      width: 100%;
      justify-content: flex-start;
    }

    a {
      display: inline-block;
      width: 32px;
      height: 32px;
      padding: 1px;
      margin: 0 5px;
      transition: all ease 0.5s;
    }

    a:hover {
      opacity: 0.8;
      transform: rotate(360deg);
    }

    .design-bg-qq {
      margin: 0 5px 0 0;
      background: url('~@/assets/img/share.png') 0 0 no-repeat !important;
    }

    .design-bg-qzone {
      background: url('~@/assets/img/share.png') -57px 0 no-repeat !important;
    }

    .design-bg-sina {
      background: url('~@/assets/img/share.png') -118px -71px no-repeat !important;
    }

    .design-bg-douban {
      background: url('~@/assets/img/share.png') -118px -138px no-repeat !important;
    }

    .design-bg-weixin {
      background: url('~@/assets/img/share.png') 0 -71px no-repeat !important;
    }
  }
}

.wx-qrcode {
  &__title {
    @include flex-box-center;
    margin-bottom: 12px;
  }

  #qrcode {
    @include flex-box-center;
  }
}
</style>
