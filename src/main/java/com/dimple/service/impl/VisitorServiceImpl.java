package com.dimple.service.impl;

import com.dimple.bean.Visitor;
import com.dimple.repository.VisitorRepository;
import com.dimple.service.VisitorService;
import com.dimple.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VisitorServiceImpl
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/25 13:08
 * @Version: 1.0
 */
@Service
@Transactional
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorRepository visitorRepository;

    @Override
    public Boolean checkoutIp(String ip) {
        //如果这个IP地址是获取不到的，就先让它通过
        if (StringUtils.isBlank(ip)) {
            return true;
        }
        return true;
    }

    @Override
    public Page<Visitor> getAllVisitorLog(Pageable pageable, String location, Date startTime, Date endTime) {
        return visitorRepository.findAll((Specification<Visitor>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("visitTime").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("visitTime").as(Date.class), endTime));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public void deleteVisitorLogById(Integer[] ids) {

    }

    @Override
    public Visitor getVisitorLogById(Integer id) {
        if (id == null) {
            return null;
        }
        return visitorRepository.getById(id);
    }

    @Override
    public void insertVisitorLog(Visitor visitor) {
        visitorRepository.save(visitor);
    }


    @Override
    public long getHistoryVisitorCount() {
        return visitorRepository.count();
    }

    @Override
    public long getLocalMonthCount() {
        return visitorRepository.countByMonthVisitTime(DateUtil.dateTimeNow(DateUtil.YYYY_MM_DD));
    }

    @Override
    public void cleanVisitorLog() {
        visitorRepository.deleteAll();
    }

    @Override
    public Page<List<Map<String, String>>> getAllVisitorCount(Pageable pageable, String location) {
        Page<List<Map<String, String>>> allVisitorCount = visitorRepository.getAllVisitorCount(pageable);
        return allVisitorCount;
    }
}
