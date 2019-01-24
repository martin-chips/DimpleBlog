package com.dimple.modules.endModule.visitorManager.service;

import com.dimple.modules.endModule.visitorManager.bean.Blacklist;
import com.dimple.modules.endModule.visitorManager.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: BlackListServiceImpl
 * @Description:
 * @Auther: Dimple
 * @Date: 2019/1/20 13:44
 * @Version: 1.0
 */
@Service
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    BlackListRepository blackListRepository;

    @Autowired
    VisitorService visitorService;

    @Override
    public int insertBlackList(String ips[]) {
        if (ips == null || ips.length == 0) {
            return 0;
        }
        int count = 0;
        for (String ip : ips) {
            Blacklist blacklist = new Blacklist();
            blacklist.setCreateTime(new Date());
            blacklist.setCount(visitorService.getVisitorCountByIp(ip));
            blacklist.setIp(ip);
            //设置访客标识
            visitorService.setVisitorSign(ip, true);
            blackListRepository.save(blacklist);
            count++;
        }
        return count;
    }

    @Override
    public void deleteBlackList(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        for (Integer id : ids) {
            Blacklist byId = blackListRepository.getById(id);
            String ip = byId.getIp();
            visitorService.setVisitorSign(ip, false);
            blackListRepository.deleteById(id);
        }
    }

    @Override
    public Page<Blacklist> getAllBlacklist(Pageable pageable, Date startTime, Date endTime) {
        return blackListRepository.findAll((Specification<Blacklist>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), endTime));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }
}
