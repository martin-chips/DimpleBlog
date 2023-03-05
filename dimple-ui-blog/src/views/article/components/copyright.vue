<template>
  <div class="copyright">
    <ul>
      <li>
        <span class="copyright__label">文章作者：</span>
        <span>
          <a href="mailto:undefined">{{ author }}</a>
        </span>
      </li>
      <li>
        <span class="copyright__label">文章链接：</span>
        <span>
          <a :href="url">{{ url }}</a>
        </span>
      </li>
      <li class="copyright__licence">
        <span class="copyright__label">版权声明：</span>
        <span>
          本博客原创系列文章遵守
          <a target="_blank" href="https://creativecommons.org/licenses/by-nc-nd/3.0/deed.zh">创意共享3.0许可证</a>
          （自由分享-署名-非商用-禁止演绎）
        </span>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  name: 'copyright',
  props: {
    author: {
      type: String,
      default: 'Dimple'
    },
    url: {
      type: String,
      default: ''
    },
    copyright: String
  },
  data() {
    return {}
  },
  components: {},
  methods: {}
}
</script>
<style lang="scss">
@import '~@/style/index.scss';

.copyright {
  ul {
    padding: 12px;
    margin: 0;
    li {
      padding: 4px 0;
    }
  }
  a {
    text-decoration: underline !important;
  }
  a:hover {
    @include themeify() {
      color: themed('color-ele-primary');
    }
  }
  border: 1px solid #eee;
  @include themeify() {
    border-color: themed('color-navbar');
  }
  &__label {
    font-weight: bold;
    @include themeify() {
      color: themed('color-copyright-label');
    }
  }
  &__licence {
  }
}
</style>
