package com.dimple.common.core.utils;

import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.utils.sql.SqlUtil;
import com.dimple.common.core.web.page.PageDomain;
import com.dimple.common.core.web.page.TableSupport;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 分页工具类
 *
 * @author Dimple
 */
@Slf4j
public class PageUtils extends PageHelper {
    /**
     * 设置请求分页数据
     */
    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage() {
        PageHelper.clearPage();
    }

    public static Page getInnerPage(ResponseEntity responseEntity) {
        Page<Object> page = new Page<>();
        if (Objects.isNull(responseEntity) || !responseEntity.isOk()) {
            log.error("get empty result");
            return page;
        }
        Object responseEntityRows = responseEntity.getRows();

        if (responseEntityRows == null || CollectionUtils.isEmpty((List) responseEntityRows)) {
            return page;
        }
        page.addAll((Collection<?>) responseEntityRows);
        page.setTotal(responseEntity.getTotal());
        return page;
    }
}
