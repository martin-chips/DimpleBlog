package com.dimple.framework.service;

import com.dimple.service.DirectionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : DirectionService
 * @description : 数据字典获取
 * @date : 12/29/18 10:02
 */
@Service("direction")
public class DirectionService {

    @Autowired
    DirectionDataService directionDataService;

    /**
     * 获取操作类型的Map集合
     *
     * @return
     */
    public Map<Integer, String> getOperateType() {
        return directionDataService.selectOperateTypeEnum();
    }

    public String selectOperateNameById(Integer id) {
        return directionDataService.selectOperateTypeNameById(id);
    }


}
