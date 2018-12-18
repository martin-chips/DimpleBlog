package com.dimple.dao;

import com.dimple.bean.LinksDetails;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @ClassName: CustomMapper
 * @Description:
 * @Auther: Owenb
 * @Date: 11/26/18 18:31
 * @Version: 1.0
 */
@Repository
public interface CustomMapper {

    /**
     * 查询友链的概览
     *
     * @return 友链概览
     */
    LinksDetails selectLinksDetails();

    /**
     * 获取未处理的Links的个数
     *
     * @return
     */
    Integer selectUnHandledLinksCount();

    /**
     * 获取博客状态类型的数量统计
     *
     * @return
     */
    Map<String, Integer> getAllBolgStatusCount();

//    Map<String, String>
}
