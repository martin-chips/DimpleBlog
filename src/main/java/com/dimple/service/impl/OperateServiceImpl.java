package com.dimple.service.impl;

import com.dimple.bean.OperateLog;
import com.dimple.bean.OperateLogExample;
import com.dimple.dao.OperateLogMapper;
import com.dimple.enums.OperateType;
import com.dimple.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<OperateLog> getAllOperateLog(String title, String operatorName, OperateType operateType, Date startTime, Date endTime) {
        OperateLogExample operateLogExample = new OperateLogExample();
        OperateLogExample.Criteria criteria = operateLogExample.createCriteria();
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(title);
        }
        if (StringUtils.isNotBlank(operatorName)) {
            criteria.andOperatorNameLike(operatorName);
        }
        if (operateType != null) {
            criteria.andOperateTypeEqualTo(operateType.getType());
        }
        if (startTime != null && endTime != null) {
            criteria.andOperateTimeBetween(startTime, endTime);
        } else if (startTime == null && endTime != null) {
            criteria.andOperateTimeLessThanOrEqualTo(endTime);
        } else if (startTime != null && endTime == null) {
            criteria.andOperateTimeGreaterThanOrEqualTo(startTime);
        }
        List<OperateLog> operateLogs = operateLogMapper.selectByExample(operateLogExample);
        return operateLogs;
    }

    @Override
    public Integer deleteOperateLog(Integer[] ids) {
        int count = 0;
        if (ids != null && ids.length != 0) {
            for (Integer id : ids) {
                count += operateLogMapper.deleteByPrimaryKey(id);
            }
        }
        return count;
    }

    @Override
    public Integer cleanOperateLog() {
        int i = operateLogMapper.deleteByExample(null);
        return i;
    }

    @Override
    public OperateLog getDetailsOperateLog(Integer id) {
        if (id == null) {
            return null;
        }
        OperateLog operateLog = operateLogMapper.selectByPrimaryKey(id);
        return operateLog;
    }

    @Override
    public Map<Integer, String> getOperateType() {
        Map<Integer, String> map = new HashMap<>();
        OperateType[] operateTypes = OperateType.values();
        for (OperateType operateType : operateTypes) {
            map.put(operateType.getType(), operateType.getName());
        }
        return map;
    }
}
