package com.dimple.common.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * VisitLogTitle
 *
 * @author Dimple
 * @date 2023/3/16
 */
@Getter
@AllArgsConstructor
public enum VisitLogTitle {
    LIST_ARCHIVES("查看归档"),
    LIST_ARTICLE("首页"),
    GET_ARTICLE("查看文章"),
    LIKE_ARTICLE("点赞文章"),
    LIST_CATEGORY("查看分类"),
    LIST_COMMENT("查看文章评论"),
    ADD_COMMENT("新增文章评论"),
    LIKE_COMMENT("点赞博客评论"),
    LIST_LINK("查看友链"),
    ADD_LINK("申请友链"),
    VISIT_LINK("访问友链"),
    LIST_MESSAGE_BOARD("查看留言板"),
    ADD_MESSAGE_BOARD("新增留言板留言"),
    LIKE_MESSAGE_BOARD("点赞留言板留言"),
    LIST_TAG("查看标签"),
    LIST_ABOUT("查看关于我"),
    GITHUB_LOGIN("Github登录"),
    UNKNOWN("未知"),
    ;
    private String desc;
}
