package com.dimple.modules.BackStageModule.FrontSetting.service;

import com.dimple.modules.BackStageModule.FrontSetting.bean.Rotation;
import com.dimple.modules.BackStageModule.FrontSetting.repository.RotationRepository;
import com.dimple.utils.JpaUpdateUtil;
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

/**
 * @author : Dimple
 * @version : 1.0
 * @class : RotationServiceImpl
 * @description :
 * @date : 01/03/19 20:42
 */
@Service
@Transactional
public class RotationServiceImpl implements RotationService {

    @Autowired
    RotationRepository rotationRepository;

    @Override
    public Page<Rotation> getRotations(String title, Boolean display, Date startTime, Date endTime, Pageable pageable) {
        return rotationRepository.findAll((Specification<Rotation>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(title)) {
                list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + title + "%"));
            }
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), endTime));
            }
            if (display != null) {
                list.add(criteriaBuilder.equal(root.get("display").as(Boolean.class), display));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public Rotation getRotationById(Integer id) {
        return rotationRepository.getById(id);
    }

    @Override
    public int deleteRotationById(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return 0;
        }
        int count = 0;
        for (Integer id : ids) {
            count++;
            rotationRepository.deleteById(id);
        }
        return count;
    }

    @Override
    public void insertRotation(Rotation rotation) {
        if (rotation != null && StringUtils.isNotBlank(rotation.getTitle()) && StringUtils.isNotBlank(rotation.getImg())) {
            rotation.setCreateTime(new Date());
            if (rotation.getDisplay() == null) {
                rotation.setDisplay(true);
            }

            rotationRepository.save(rotation);
        }
    }

    @Override
    public void updateRotation(Rotation rotation) {
        if (rotation == null || rotation.getId() == null) {
            return;
        }
        Rotation rotationDB = rotationRepository.getById(rotation.getId());
        JpaUpdateUtil.copyProperties(rotationDB, rotation);
        rotationRepository.save(rotation);
    }

    @Override
    public void changeDisplay(Integer id, Boolean status) {
        Rotation byId = rotationRepository.getById(id);
        if (byId != null) {
            byId.setDisplay(!status);
            rotationRepository.save(byId);
        }
    }

    @Override
    public int changePlace(Integer[] ids, Integer place) {
        if (ids == null || ids.length == 0 || place == null) {
            return 0;
        }
        int count = 0;
        for (Integer id : ids) {
            Rotation byId = rotationRepository.getById(id);
            byId.setPlace(place);
            rotationRepository.save(byId);
            count++;
        }
        return count;
    }

    @Override
    public List<Rotation> getRotationByPlace(Integer i) {
        if (i == null) {
            return null;
        }
        return rotationRepository.getRotationByPlace(i);
    }
}
