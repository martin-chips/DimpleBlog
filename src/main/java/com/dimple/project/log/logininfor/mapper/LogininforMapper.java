package com.dimple.project.log.logininfor.mapper;

import com.dimple.project.log.logininfor.domain.Logininfor;

import java.util.List;

/**
 * @className: LogininforMapper
 * @description: 系统访问日志情况信息 数据层
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public interface LogininforMapper {
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(Logininfor logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    List<Logininfor> selectLogininforList(Logininfor logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteLogininforByIds(String[] ids);

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
    int cleanLogininfor();

    /**
     * 获取20条登录记录
     *
     * @return 登录记录集合
     */
    List<Logininfor> selectLoginInforData();
}
