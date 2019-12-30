package com.dimple.framework.web.page;

import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.StringUtils;

/**
 * @className: TableSupport
 * @description: 表格数据处理
 * @author: Dimple
 * @date: 10/22/19
 */
public class TableSupport {
    private TableSupport() {
    }

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        pageDomain.setOrderByColumn(StringUtils.isEmpty(ServletUtils.getParameter(ORDER_BY_COLUMN)) ? "createTime" : ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(StringUtils.isEmpty(ServletUtils.getParameter(IS_ASC)) ? "desc" : ServletUtils.getParameter(IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
