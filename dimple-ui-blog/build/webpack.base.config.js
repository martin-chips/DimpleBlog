/* eslint-disable indent */
const path = require('path')
const webpack = require('webpack')
const vueConfig = require('./vue-loader.config')
const ExtractTextPlugin = require('extract-text-webpack-plugin')
const FriendlyErrorsPlugin = require('friendly-errors-webpack-plugin')
// const CopyWebpackPlugin = require('copy-webpack-plugin')
const isProd = process.env.NODE_ENV === 'production'

module.exports = {
  devtool: isProd ? false : '#cheap-module-source-map',
  output: {
    path: path.resolve(__dirname, '../dist/front/'),
    publicPath: '/',
    filename: 'js/[name].[chunkhash].js',
    chunkFilename: 'js/[name].[chunkhash].js'
  },
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      // 'echarts': 'echarts/dist/echarts.common.min.js',
      // 'vue': 'vue/dist/vue.runtime.min.js',
      // 'vue': 'vue/dist/vue.js',
      '@': path.resolve('src'),
      R: path.resolve('src/components')
    }
  },
  module: {
    noParse: /es6-promise\.js$/, // avoid webpack shimming process
    rules: [
      {
        test: /\.vue$/,
        loader: 'vue-loader',
        options: vueConfig
      },
      {
        test: /\.js$/,
        loader: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.(woff|eot|ttf)\??.*$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
          name: 'css/fonts/[name].[hash:8].[ext]'
        }
      },
      {
        test: /\.(gif|jpg|png|svg|webp)\??.*$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
          name: 'img/[name].[hash:8].[ext]'
        }
      },
      {
        test: /\.(less|css|scss)$/,
        use: isProd
          ? ExtractTextPlugin.extract({
              use: ['css-loader?minimize', 'less-loader', 'sass-loader'],
              fallback: 'vue-style-loader'
            })
          : ['vue-style-loader', 'css-loader', 'less-loader', 'sass-loader']
      }
    ]
  },
  performance: {
    maxEntrypointSize: 300000,
    hints: isProd ? 'warning' : false
  },
  plugins: isProd
    ? [
        new webpack.optimize.UglifyJsPlugin({
          compress: {
            warnings: false,
            drop_debugger: true,
            drop_console: true
          },
          sourceMap: false // true
        }),
        new ExtractTextPlugin({
          filename: 'css/common.[chunkhash].css',
          allChunks: true //所有组件的css都打包到一个css文件中
        }),
        new ExtractTextPlugin({
          filename: 'css/common.[chunkhash].less'
        })
      ]
    : [new FriendlyErrorsPlugin()]
}
