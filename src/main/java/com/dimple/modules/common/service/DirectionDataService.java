package com.dimple.modules.common.service;

import java.util.Map;

/**
 * @ClassName: DirectionDataService
 * @Description: 数据字典获取信息的service
 * @Auther: Owenb
 * @Date: 12/29/18 10:03
 * @Version: 1.0
 */
public interface DirectionDataService {

    /**
     * 获取操作类型
     *
     * @return
     */
    Map<Integer, String> selectOperateTypeEnum();


    String selectOperateTypeNameById(Integer id);
}
