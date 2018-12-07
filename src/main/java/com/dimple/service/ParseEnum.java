package com.dimple.service;

import com.dimple.enums.OperateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: ParseEnum
 * @Description: 解析Enum的类
 * @Auther: Owenb
 * @Date: 12/06/18 20:43
 * @Version: 1.0
 */
@Service("parse")
public class ParseEnum {

    @Autowired
    OperateLogService operateLogService;

    /**
     * 主要服务前台Thymeleaf页面，根据type设置值
     *
     * @param type
     * @return
     */
    public String getOperateTypeName(Integer type) {
        Map<Integer, String> operateType = operateLogService.getOperateType();
        String typeName = operateType.get(type);
        return typeName;
    }
}
