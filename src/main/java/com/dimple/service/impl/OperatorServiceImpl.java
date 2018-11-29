package com.dimple.service.impl;

import com.dimple.bean.OperatorLog;
import com.dimple.dao.OperatorLogMapper;
import com.dimple.service.OperatorLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: OperatorServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/29/18 11:19
 * @Version: 1.0
 */
@Slf4j
@Service
@Transactional
public class OperatorServiceImpl implements OperatorLogService {

    @Autowired
    OperatorLogMapper operatorLogMapper;

    @Override
    public Integer insertOperatorLog(OperatorLog operatorLog) {
        int i = operatorLogMapper.insertSelective(operatorLog);
        return i;
    }
}
