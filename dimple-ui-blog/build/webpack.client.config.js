const webpack = require('webpack')
const merge = require('webpack-merge')
const base = require('./webpack.base.config')
const VueSSRClientPlugin = require('vue-server-renderer/client-plugin')
const env = process.env.NODE_ENV === 'developemnt' ? 'dev' : 'prod'

const config = merge(base, {
  entry: {
    app: './src/entry-client.js',
    vendors: ['axios', '@/assets/js/prism.js'],
    vues: ['vue', 'vuex', 'vue-router']
  },
  plugins: [
    // extract vendor chunks for better caching
    // new webpack.optimize.CommonsChunkPlugin({
    //     name: ['vendors', 'echarts'],
    //     minChunks: function(module) {
    //         // a module is extracted into the vendor chunk if...
    //         return (
    //             // it's inside node_modules
    //             /node_modules/.test(module.context) &&
    //             // and not a CSS file (due to extract-text-webpack-plugin limitation)
    //             !/\.css$/.test(module.request)
    //         )
    //     }
    // }),
    new webpack.optimize.CommonsChunkPlugin({
      names: ['vendors', 'vues'],
      minChunks: 3
    }),
    // 重要信息：这将 webpack 运行时分离到一个引导 chunk 中，
    // 以便可以在之后正确注入异步 chunk。
    // 这也为你的 应用程序/vendor 代码提供了更好的缓存。
    new webpack.optimize.CommonsChunkPlugin({
      name: 'manifest',
      // minChunks: Infinity
      chunks: ['vendors', 'vues']
    }),
    // 此插件在输出目录中
    // 生成 `vue-ssr-client-manifest.json`。
    new VueSSRClientPlugin()
  ]
})

if (process.env.NODE_ENV === 'production') {
  const injectEnv = require(`../config/env.prod.js`)
  injectEnv.VUE_ENV = '"client"'
  config.plugins.push(
    // strip dev-only code in Vue source
    new webpack.DefinePlugin({
      'process.env': injectEnv
    })
  )
}

module.exports = config
