package com.dimple.service.impl;

import com.dimple.bean.OperateLog;
import com.dimple.dao.OperateLogMapper;
import com.dimple.service.OperateLogService;
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
public class OperateServiceImpl implements OperateLogService {

    @Autowired
    OperateLogMapper operateLogMapper;

    @Override
    public Integer insertOperatorLog(OperateLog operateLog) {
        int i = operateLogMapper.insertSelective(operateLog);
        return i;
    }
}
