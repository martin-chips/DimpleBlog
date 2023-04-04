package com.dimple.log.service.service.impl;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.log.service.entity.SysLogininfor;
import com.dimple.log.service.mapper.SysLogininforMapper;
import com.dimple.log.service.service.SysLogininforService;
import com.dimple.system.api.model.SysLogininforBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author Dimple
 */
@Service
public class SysLogininforServiceImpl implements SysLogininforService {

    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public int insertLogininfor(SysLogininforBO logininfor) {
        SysLogininfor sysLogininfor = BeanMapper.convert(logininfor, SysLogininfor.class);
        return logininforMapper.insertLogininfor(sysLogininfor);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininforBO> selectLogininforList(SysLogininforBO logininfor) {
        SysLogininfor sysLogininfor = BeanMapper.convert(logininfor, SysLogininfor.class);
        return BeanMapper.convertList(logininforMapper.selectLogininforList(sysLogininfor), SysLogininforBO.class);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的登录日志ID
     * @return affected lines
     */
    @Override
    public int deleteLogininforByIds(Long[] ids) {
        return logininforMapper.deleteLogininforByIds(ids);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor() {
        logininforMapper.cleanLogininfor();
    }
}
