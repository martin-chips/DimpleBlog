package com.dimple.dao;

import com.dimple.bean.LinksDetails;
import org.springframework.stereotype.Repository;

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
}
