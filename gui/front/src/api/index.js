import request from '@/axios'

// 获取网站信息
export function getSiteInfo(query) {
    return request({
        url: '/f/siteInfo',
        method: 'get',
        params: query
    })
}

//获取所有分类
export function listCategories() {
    return request({
        url: '/f/categories',
        method: 'get',
    })
}

//获取所有的菜单
export function listMenus() {
    return request({
        url: '/f/menus',
        method: 'get',
    })
}

//获取网站信息
export function getBloggerInfo() {
    return request({
        url: '/f/bloggerInfo',
        method: 'get',
    })
}

//获取推荐内容
export function listRecommend(query) {
    return request({
        url: '/f/support',
        method: 'get',
        params: query
    })
}

//获取最热内容
export function listHot(query) {
    return request({
        url: '/f/hot',
        method: 'get',
        params: query
    })
}

//获取友链
export function listFriendLinks() {
    return request({
        url: '/f/link',
        method: 'get',
    })
}

export function listSupportFriendLink() {
    return request({
        url: '/f/link/support',
        method: 'get',
    })
}

//新增友链
export function insertFriendLink(data) {
    return request({
        url: '/f/link',
        method: 'post',
        data: data
    })
}

//获取标签
export function listTag() {
    return request({
        url: '/f/tag',
        method: 'get',
    })
}

// 查询博客列表
export function listBlogBaseInfo(query) {
    return request({
        url: '/f/blog',
        method: 'get',
        params: query
    })
}

export function listBlogInfo(query) {
    return request({
        url: '/f/frontBlog',
        method: 'get',
        params: query
    })
}

// 查询博客列表
export function BlogDetail(id) {
    return request({
        url: '/f/blog/' + id,
        method: 'get',
    })
}

//点赞博客
export function LikeBlog(id) {
    return request({
        url: '/f/blog/like/' + id,
        method: 'put',
    })
}

//获取评论
export function listComment(pageId) {
    return request({
        url: '/f/comment/' + pageId,
        method: 'get',
    })
}

//点赞评论
export function goodComment(id) {
    return request({
        url: '/f/comment/good/' + id,
        method: 'put',
    })
}

//踩评论
export function badComment(id) {
    return request({
        url: '/f/comment/bad/' + id,
        method: 'put',
    })
}

//根据QQ获取信息
export function getInfoByQQ(qqNum) {
    return request({
        url: '/f/comment/qqNum/' + qqNum,
        method: 'get',
    })
}

//新增评论
export function insertComment(comment) {
    return request({
        url: '/f/comment',
        method: 'post',
        data: comment
    })
}

//获取轮播图
export function listCarousel() {
    return request({
        url: '/f/carousel',
        method: 'get',
    })
}

//获取提示
export function listNotice() {
    return request({
        url: '/f/notice',
        method: 'get',
    })
}

//图书
export function listBooks() {
    return request({
        url: '/f/notice',
        method: 'get',
    })
}

//点赞Book
export function LikeBook(id) {
    return request({
        url: '/f/book/like/' + id,
        method: 'put',
    })
}

// 查询图书列表
export function listBookBaseInfo(query) {
    return request({
        url: '/f/book',
        method: 'get',
        params: query
    })
}

// 查询笔记列表
export function listNoteBaseInfo(query) {
    return request({
        url: '/f/note',
        method: 'get',
        params: query
    })
}

//获取Book信息
export function BookDetail(params) {
    return request({
        url: '/f/book/' + params.id,
        method: 'get',
        params: params
    })
}

//增加Link点击量
export function updateLinkCount(id) {
    return request({
        url: '/f/link/' + id,
        method: 'put',
    })
}

//获取时光轴数据
export function listBlogArchive(query) {
    return request({
        url: '/f/archive',
        method: 'get',
        params: query
    })
}
