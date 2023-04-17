module.exports = {
  root: true,
  parser: 'vue-eslint-parser',
  parserOptions: {
    parser: '@babel/eslint-parser',
    sourceType: 'module'
  },
  env: {
    browser: true,
    node: true,
    es6: true
  },
  extends: ['plugin:vue/essential', 'eslint:recommended'],
  // required to lint *.vue files
  plugins: ['vue', 'html'],
  // check if imports actually resolve

  globals: {
    BMap: true,
    AMap: true,
    AMapUI: true,
    processEnv: true,
    _: true,
    Prism: true,
    QRCode: true,
    QC: true
  },
  // add your custom rules here
  //it is base on https://github.com/vuejs/eslint-config-vue
  rules: {
    'vue/multi-word-component-names': 'off'
  }
}
