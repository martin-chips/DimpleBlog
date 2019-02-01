package com.dimple.modules.BackStageModule.BlogManager.service;

import com.dimple.modules.BackStageModule.BlogManager.bean.Tag;
import com.dimple.modules.BackStageModule.BlogManager.repository.TagRepository;
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
 * @class : TagServiceImpl
 * @description :
 * @date : 12/28/18 20:17
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository tagRepository;

    @Override
    public Tag insertTag(Tag tag) {
        if (tag == null || StringUtils.isBlank(tag.getTitle())) {
            return tag;
        }
        return tagRepository.save(tag);
    }

    @Override
    public int deleteTagById(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return -1;
        }
        int count = 0;
        for (Integer id : ids) {
            tagRepository.deleteById(id);
            count++;
        }
        return count;
    }

    @Override
    public void updateTag(Tag tag) {
        if (tag == null) {
            return;
        }
        Tag one = tagRepository.findTagById(tag.getId());

    }

    @Override
    public Page<Tag> getTag(Pageable pageable, String title, Date startTime, Date endTime) {
        return tagRepository.findAll((Specification<Tag>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(title)) {
                list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + title + "%"));
            }
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("start_time").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("end_time").as(Date.class), endTime));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagRepository.findTagById(id);
    }

    @Override
    public Tag getTagByTitle(String title) {
        return tagRepository.getTagByTitle(title);
    }

    @Override
    public String[] getTagTitleByBlogId(Integer id) {
        return tagRepository.getTagByBlogId(id);
    }
}
