package com.dimple.modules.CommonModule.service;

import com.dimple.framework.enums.OperateType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : DirectionDataServiceImpl
 * @description :
 * @date : 12/29/18 10:05
 */
@Service
public class DirectionDataServiceImpl implements DirectionDataService {


    @Override
    public Map<Integer, String> selectOperateTypeEnum() {
        Map<Integer, String> map = new HashMap<>();
        OperateType[] operateTypes = OperateType.values();
        for (OperateType operateType : operateTypes) {
            map.put(operateType.getType(), operateType.getName());
        }
        return map;
    }

    @Override
    public String selectOperateTypeNameById(Integer id) {
        OperateType[] values = OperateType.values();
        for (OperateType operateType : values) {
            if (operateType.getType() == id) {
                return operateType.getName();
            }
        }
        return "未知";
    }
}
