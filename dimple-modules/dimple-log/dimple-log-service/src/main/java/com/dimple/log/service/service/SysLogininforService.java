package com.dimple.log.service.service;



import com.dimple.system.api.model.SysLogininforBO;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层
 *
 * @author Dimple
 */
public interface SysLogininforService {
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    int insertLogininfor(SysLogininforBO logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<SysLogininforBO> selectLogininforList(SysLogininforBO logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的登录日志ID
     * @return affected lines
     */
    int deleteLogininforByIds(Long[] ids);

    /**
     * 清空系统登录日志
     */
    void cleanLogininfor();
}
