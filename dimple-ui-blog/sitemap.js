const fs = require('fs');
const {SitemapStream, streamToPromise} = require('sitemap');
const axios = require("axios");
const url = require("url");

const sitemapFilePath = './sitemap.xml';
const isProd = process.env.NODE_ENV === 'production'
const host = isProd ? "http://dimple-gateway:8080" : "http://localhost:8080"

const baseUrls = [
  {url: '/', changefreq: 'daily', priority: 1},
  {url: '/app/messageBoard', changefreq: 'daily', priority: 0.8},
  {url: '/app/archives', changefreq: 'daily', priority: 0.8},
  {url: '/app/tags', changefreq: 'daily', priority: 0.8},
  {url: '/app/category', changefreq: 'daily', priority: 0.8},
  {url: '/app/links', changefreq: 'daily', priority: 0.8},
  {url: '/app/about', changefreq: 'daily', priority: 0.8},
];

function requestAxios(uri, params) {
  axios.get(host + uri, {
    params
  }).then(response => {
    const rows = response.data.rows;
    return rows;
  }).catch(error => {
    console.log(error);
  });
  return [];
}

async function requestAxios(uri, params) {
  try {
    const response = await axios.get(host + uri, {params});
    const rows = response.data.rows;
    return rows;
  } catch (error) {
    console.log(error);
    return [];
  }
}

async function getUrl() {
  const urls = [...baseUrls];
  try {
    const [articlesRows, categoryRows, tagRows] = await Promise.all([
      requestAxios("/blog-front/article/list", {pageNum: 1, pageSize: 1000}),
      requestAxios("/blog-front/category/list", {pageNum: 1, pageSize: 1000}),
      requestAxios("/blog-front/tag/list", {pageNum: 1, pageSize: 1000})
    ]);

    if (articlesRows && articlesRows.length > 0) {
      for (let articlesRow of articlesRows) {
        urls.push({
          url: "/app/article/" + articlesRow.id,
          changefreq: 'daily',
          priority: 0.8
        })
      }
    }

    for (let categoryRow of categoryRows) {
      urls.push({
        url: "/app/article/category/" + categoryRow.id,
        changefreq: 'daily',
        priority: 0.8
      })
    }

    for (let tagRow of tagRows) {
      urls.push({
        url: "/app/article/tag/" + tagRow.id,
        changefreq: 'daily',
        priority: 0.8
      })
    }
  } catch (error) {
    console.log(error);
  }

  return urls;
}

async function generateSitemap() {
  const sitemapStream = new SitemapStream({hostname: isProd ? "https://www.bianxf.com" : "http://localhost:8820"});
  var urls = await getUrl();
  urls.forEach((url) => {
    sitemapStream.write(url);
  });
  sitemapStream.end();
  streamToPromise(sitemapStream)
    .then((sitemap) => {
      fs.writeFileSync(sitemapFilePath, sitemap);
      console.log('Sitemap generated successfully.');
    })
    .catch((error) => {
      console.error('Error generating sitemap:', error);
    });
}

module.exports = {
  generateSitemap,
  sitemapFilePath,
};
