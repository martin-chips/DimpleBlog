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
          本站所有文章除特别声明外，均采用
          <a target="_blank" href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>许可协议。
          （非商用）
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
  &:hover {
    -webkit-box-shadow: 0 0 8px 0 #E8EDFA99, 0 2px 4px 0 #E8EDFA7F;
    box-shadow: 0 0 8px 0 #2056E199, 0 2px 4px 0 #E8EDFA7F;
  }

  &:before {
    position: absolute;
    top: 2px;
    font-family: "FontAwesome";
    right: 12px;
    color: #49b1f5;
    content: '\f1f9';
    font-size: 1.3em;
  }

  position: relative;
  -webkit-transition: box-shadow .3s ease-in-out;
  -moz-transition: box-shadow .3s ease-in-out;
  -o-transition: box-shadow .3s ease-in-out;
  -ms-transition: box-shadow .3s ease-in-out;
  transition: box-shadow .3s ease-in-out;
  margin: 40px 0 10px;
  padding: 10px 16px;

  @include themify() {
    border: 1px solid themed('light-grey');
  }

  ul {
    padding: 12px;
    margin: 0;
    li {
      padding: 4px 0;
    }
  }

  a {
    color: #99a9bf;
    text-decoration: underline;
    &:hover {
      @include themify() {
        color: themed('btn-bg');
        text-decoration: none;
      }
    }
  }

  &__label {
    font-weight: bold;
    @include themify() {
      color: themed('default-bg-color');
    }
  }

  &__licence {
  }
}
</style>
