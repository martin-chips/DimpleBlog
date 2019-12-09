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
            },
            {
                path: '/articles',
                name: 'articles',
                components: {
                    header: SimpleHeader,
                    content: ArticleHomeContent,
                    footer: Footer
                },
            },
            {
                path: '/articles/category/:id',
                name: 'articles/category',
                components: {
                    header: SimpleHeader,
                    content: LeaveCommentContent,
                    footer: Footer
                },
                meta: {
                    title: '文章列表',
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
                    title: '文章列表',
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
            },
            // {
            //     path: '/about',
            //     component: () => import('@/views/front/about'),
            // },
            {
                path: '/archive',
                components: {
                    header: SimpleHeader,
                    content: TimeLineContent,
                    footer: Footer
                },
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
