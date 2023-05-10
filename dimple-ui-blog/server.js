const express = require('express')
const path = require('path')
const lruCache = require('lru-cache')
const fs = require('fs')
const cookieParser = require('cookie-parser')
const bodyParser = require('body-parser')
const logger = require('morgan')
const ejs = require('ejs')
const detect = require('detect-port')
const compression = require('compression')
const {createBundleRenderer} = require('vue-server-renderer')
const isProd = process.env.NODE_ENV === 'production'
const tpl = isProd ? './front/index.template.html' : './src/index.template.html'
const template = fs.readFileSync(tpl, 'utf-8')
const server = express()
// nginx 反向代理后获取真实ip
logger.token('remote-addr', (req) => {
  return req.headers['x-real-ip'] || req.headers['x-forwarded-for'] || req.connection.remoteAddress
})
// const resolve = (file) => path.resolve(__dirname, file)
// 日志记录中间件
server.use(logger(isProd ? 'combined' : 'tiny'))
server.use(cookieParser())

//引入ejs模板引擎
server.set('views', [path.join(__dirname, isProd ? 'front' : 'static')])
server.engine('.html', ejs.__express)
server.set('view engine', 'ejs')

const LRU = new lruCache({
  max: 1000, ttl: 1000 * 60 * 15
})

function createRenderer(bundle, options) {
  return createBundleRenderer(bundle, Object.assign(options, {
    template: template, cache: LRU, // basedir: isProd ? resolve('./front') : resolve('./dist'),
    runInNewContext: false
  }))
}

let renderer
// eslint-disable-next-line no-unused-vars
let readyPromise
if (isProd) {
  const bundle = require('./front/vue-ssr-server-bundle.json')
  const clientManifest = require('./front/vue-ssr-client-manifest.json')
  renderer = createRenderer(bundle, {
    clientManifest
  })
  readyPromise = new Promise((resolve) => {
    checkPort().then((port) => {
      process.env.__SAFE_PORT__ = port
      resolve()
    })
  })
} else {
  readyPromise = require('./build/setup-dev-server')(server, (bundle, options) => {
    renderer = createRenderer(bundle, options)
  })
}
var proxy = require('http-proxy-middleware').createProxyMiddleware;

// proxy middleware options

server.use(`/dev-api`, proxy({
  target: `http://localhost:8080`,
  changeOrigin: true,
  pathRewrite: {
    "^/dev-api": "",
  }
}));

server.use(bodyParser.json())
server.use(bodyParser.urlencoded({extended: true}))
// change markdown to html
let MarkDowner = require('markdown-it');
const {generateSitemap} = require("./sitemap");
server.post('/api/markdown/convert', (req, res) => {
  var md = new MarkDowner({
    html: true,
    linkify: true,
    typographer: true,
    breaks: true,
  });
  var content = req.body.content;
  if (content) {
    content = content.replace(/&lt;/g, '<').replace(/&gt;/g, '>');
    res.send(md.render(content));
  } else {
    res.send("N/A")
  }
});

readyPromise.then(() => {
  server.use(compression()) //开启gzip压缩
  // 伺服静态资源
  if (isProd) {
    server.use(express.static(path.join(__dirname, 'front')))
  } else {
    server.use(express.static(path.join(__dirname, 'static')))
  }


  // 前端请求
  server.get(['/', '/app/*'], (req, res) => {
    try {
      const context = {
        title: `Dimple's Blog`, url: req.url
      }
      context._req = req;
      renderer.renderToString(context, (err, html) => {
        if (err) {
          if (err === 'NotFound') {
            res.render('404.html', {
              title: 'No Found'
            });
          } else {
            console.error(err)
            res.status(500).end('Internal Server Error')
            return
          }
        }
        if (context.meta) {
          const {title, meta} = context.meta.inject()
          html = html.replace(/<title.*?<\/title>/g, title.text())
          html = html.replace(/<meta\s+.*?name="description".*?>/g, meta.text())
        }

        res.end(html)
      })
    } catch (e) {
      console.error(e)
      res.status(500).end('Internal Server Error')
    }
  })

  const {generateSitemap, sitemapFilePath} = require('./sitemap');
  // 每隔 1 天重新生成 sitemap
  setInterval(() => {
    console.log("Start auto generate sitemap !")
    generateSitemap();
  }, 24 * 60 * 60 * 1000);
  server.get('/sitemap.xml/:reload?', (req, res) => {
    if (req.params.reload) {
      generateSitemap();
    }
    // 检查 sitemap 文件是否存在
    fs.access(sitemapFilePath, fs.constants.F_OK, (err) => {
      if (err) {
        // 如果文件不存在，生成 sitemap 并返回
        generateSitemap();
        res.status(200).sendFile(sitemapFilePath, {root: "."});
      } else {
        // 如果文件存在，直接返回
        res.status(200).sendFile(sitemapFilePath, {root: "."});
      }
    });
  });

  server.get('*', function (req, res) {
    res.render('404.html', {
      title: 'No Found'
    })
  })

  const port = process.env.__SAFE_PORT__
  const uri = 'http://localhost:' + port
  console.log()
  console.log('启动服务路径' + uri)
  console.log()
  server.listen(port, '0.0.0.0')
})

// 检查端口占用情况
function checkPort(port = 8820) {
  return new Promise((resolve) => {
    detect(port, (err, _port) => {
      if (err) {
        console.log(err)
      }
      if (port == _port) {
        resolve(port)
      } else {
        resolve(_port)
        console.log(`port: ${port} was occupied, try port: ${_port}`)
      }
    })
  })
}
