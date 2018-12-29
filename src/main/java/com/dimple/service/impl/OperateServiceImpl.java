package com.dimple.service.impl;

import com.dimple.bean.OperateLog;
import com.dimple.framework.enums.OperateType;
import com.dimple.repository.OperateLogRepository;
import com.dimple.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
    OperateLogRepository operateLogRepository;

    @Override
    public OperateLog insertOperatorLog(OperateLog operateLog) {
        OperateLog save = operateLogRepository.save(operateLog);
        return save;
    }

    @Override
    public Page<OperateLog> getAllOperateLog(String title, String operatorName, OperateType operateType, Date startTime, Date endTime, Pageable pageable) {
        return operateLogRepository.findAll((Specification<OperateLog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(title)) {
                list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + title + "%"));
            }
            if (StringUtils.isNotBlank(operatorName)) {
                list.add(criteriaBuilder.like(root.get("operatorName").as(String.class), "%" + operatorName + "%"));
            }
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("startTime").as(Date.class), endTime));
            }
            if (operateType != null) {
                list.add(criteriaBuilder.equal(root.get("operateType").as(Integer.class), operateType.getType()));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public Integer deleteOperateLog(Integer[] ids) {
        int count = 0;
        if (ids != null && ids.length != 0) {
            for (Integer id : ids) {
                operateLogRepository.deleteById(id);
                count++;
            }
        }
        return count;
    }

    @Override
    public void cleanOperateLog() {
        operateLogRepository.deleteAll();
    }

    @Override
    public OperateLog getDetailsOperateLog(Integer id) {
        if (id == null) {
            return null;
        }
        OperateLog operateLog = operateLogRepository.findByOperateId(id);
        return operateLog;
    }


}
