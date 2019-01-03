package com.dimple.service.impl;

import com.dimple.bean.LoginLog;
import com.dimple.repository.LoginLogRepository;
import com.dimple.service.LoginLogService;
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
 * @ClassName: LoginLogServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/29/18 18:16
 * @Version: 1.0
 */
@Service
@Transactional
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    LoginLogRepository loginLogRepository;


    @Override
    public LoginLog insertLoginLog(LoginLog loginLog) {
        LoginLog save = loginLogRepository.save(loginLog);
        return save;
    }

    @Override
    public Page<LoginLog> getAllLoginLog(String location, String loginName, Boolean status, Date startTime, Date endTime, String osType, String browserType, Pageable pageable) {
        return loginLogRepository.findAll((Specification<LoginLog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(location)) {
                list.add(criteriaBuilder.like(root.get("loginLocation").as(String.class), "%" + location + "%"));
            }
            if (StringUtils.isNotBlank(browserType)) {
                list.add(criteriaBuilder.like(root.get("browser").as(String.class), "%" + browserType + "%"));
            }
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), endTime));
            }
            if (StringUtils.isNotBlank(loginName)) {
                list.add(criteriaBuilder.like(root.get("loginName").as(String.class), loginName));
            }
            if (StringUtils.isNotBlank(osType)) {
                list.add(criteriaBuilder.like(root.get("os").as(String.class), osType));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);

    }

    @Override
    public void cleanLoginLog() {
        loginLogRepository.deleteAll();
    }

    @Override
    public Integer deleteLoginLog(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return -1;
        }
        Integer count = 0;
        for (Integer id : ids) {
            loginLogRepository.deleteById(id);
            count++;
        }
        return count;
    }

    @Override
    public Map<String, Integer> getDetails() {
        return loginLogRepository.getStatusCount();
    }
}
