// 文字省略模式
export const LineBreakMode = {
    WrappingTruncatingTail: 1, // 显示头部文字内容，其他直接截断。
    WrappingTruncatingHead: 2, // 显示尾部文字内容，其他直接截断。
    EllipsisTruncatingTail: 3, // 结尾部分的内容以……方式省略，显示头的文字内容。
    EllipsisTruncatingMiddle: 4, // 中间的内容以……方式省略，显示头尾的文字内容。
    EllipsisTruncatingHead: 5 // 前面部分文字以……方式省略，显示尾部文字内容。
};
// 默认请求数据时的limit size
export const DefaultLimitSize = 10;

// 请求数据时的最大limit size
export const MaxLimitSize = 100;

// section title默认的menus
export const SectionTitleDefaultMenus = [
    {
        title: '顺序',
        selectedTitle: '倒序',
        selected: true,
        method: 'timeSorted'
    },
    {title: '最多评论', selected: false, method: 'mostComment'},
    {title: '推荐', selected: false, method: 'recommend'}
];
