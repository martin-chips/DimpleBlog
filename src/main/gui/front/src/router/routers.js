import SimpleHeader from "../components/header/SimpleHeader/SimpleHeader";
import HomeContent from "../components/content/HomeContent";
import Footer from "../components/footer/Footer";
import Index from "../components/Index"

import ArticleHomeContent from "../components/content/ArticleHomeContent";
import TimeLineContent from "../components/content/TimeLineContent";
import ArticleContent from "../components/content/ArticleContent";
import LinkContent from "../components/content/LinkContent";
import Error404 from "../views/error/Error404";
import LeaveCommentContent from "../components/content/LeaveCommentContent";
import BookContent from "../components/content/BookContent";
import AboutContent from "../components/content/AboutContent";
export default [
    {
        path: '/',
        component: Index,
        children: [
            {
                path: '/',
                components: {
                    header: SimpleHeader,
                    content: HomeContent,
                    footer: Footer
                },
                meta: {
                    title: '首页',
                    content:{
                        keywords:'DimpleBlog,边小丰,Java',
                        description:'DimpleBlog,Java',
                    },
                }
            },
            {
                path: '/articles',
                name: 'articles',
                components: {
                    header: SimpleHeader,
                    content: ArticleHomeContent,
                    footer: Footer
                },
                meta: {
                    title: '文章列表',
                    content:{
                        keywords:'DimpleBlog,边小丰,Java',
                        description:'DimpleBlog,Java',
                    },
                }
            },
            {
                path: '/articles/category/:id',
                name: 'articles/category',
                components: {
                    header: SimpleHeader,
                    content: ArticleHomeContent,
                    footer: Footer
                },
                meta: {
                    title: '文章列表',
                    content:{
                        keywords:'DimpleBlog,边小丰,Java',
                        description:'DimpleBlog,Java',
                    },
                }
            },
            {
                path: 'link',
                name: 'link',
                components: {
                    header: SimpleHeader,
                    content: LinkContent,
                    footer: Footer
                },
                meta: {
                    title: '友链',
                    content:{
                        keywords:'DimpleBlog,边小丰,Java',
                        description:'DimpleBlog,Java',
                    },
                }
            },
            {
                path: '/leaveComment',
                name: 'leaveComment',
                components: {
                    header: SimpleHeader,
                    content: LeaveCommentContent,
                    footer: Footer
                },
                meta: {
                    title: '留言',
                    content:{
                        keywords:'DimpleBlog,边小丰,Java,留言',
                        description:'DimpleBlog,Java',
                    },
                }
            },
            {
                path: '/about',
                name: 'leaveComment',
                components: {
                    header: SimpleHeader,
                    content: AboutContent,
                    footer: Footer
                },
                meta: {
                    title: '关于',
                    content:{
                        keywords:'DimpleBlog,边小丰,Java,关于',
                        description:'DimpleBlog,Java',
                    },
                }
            },
            {
                path: '/archive',
                components: {
                    header: SimpleHeader,
                    content: TimeLineContent,
                    footer: Footer
                },
                meta: {
                    title: '时光轴',
                    content:{
                        keywords:'DimpleBlog,边小丰,Java',
                        description:'DimpleBlog,Java',
                    },
                }
            },
            {
                path: 'article/:id',
                name: 'article',
                components: {
                    header: SimpleHeader,
                    content: ArticleContent,
                    footer: Footer
                },
                meta: {
                    title: '文章详情',
                    need_log: false,
                    content:{
                        keywords:'DimpleBlog,边小丰,Java',
                        description:'DimpleBlog,Java',
                    },
                }
            },
            {
                path: 'book/:id',
                name: 'book',
                components: {
                    header: SimpleHeader,
                    content: BookContent,
                    footer: Footer
                },
                meta: {
                    title: '书籍概览',
                    need_log: false
                }
            },
        ]
    },
    {
        path: '*',
        component: Error404,
    }
];
