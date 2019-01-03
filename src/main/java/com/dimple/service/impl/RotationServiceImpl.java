package com.dimple.service.impl;

import com.dimple.bean.Rotation;
import com.dimple.repository.RotationRepository;
import com.dimple.service.RotationService;
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
    public void deleteRotationById(Integer id) {
        rotationRepository.deleteById(id);
    }

    @Override
    public void insertRotation(Rotation rotation) {
        rotationRepository.save(rotation);
    }

    @Override
    public void updateRotation(Rotation rotation) {
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
}
