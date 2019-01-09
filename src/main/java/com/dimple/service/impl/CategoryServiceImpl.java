package com.dimple.service.impl;

import com.dimple.bean.Category;
import com.dimple.repository.CategoryRepository;
import com.dimple.service.CategoryService;
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
 * @ClassName: ArchivesServiceImpl
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/11 19:31
 * @Version: 1.0
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Page<Category> getAllBlogCategory(Date startTime, Date endTime, String description, String title, Pageable pageable) {
        return categoryRepository.findAll((Specification<Category>) (root, criteriaQuery, criteriaBuilder) -> {
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
            if (StringUtils.isNotBlank(description)) {
                list.add(criteriaBuilder.like(root.get("description").as(String.class), description));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public List<Category> getAllBlogCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category insertBlogCategory(Category category) {
        if (category == null || StringUtils.isBlank(category.getTitle())) {
            return null;
        }
        category.setCreateTime(new Date());
        //设置默认属性
        category.setSupport(false);
        category.setUpdateTime(new Date());
        Category categorySave = categoryRepository.save(category);
        return categorySave;
    }

    @Override
    public Category updateBlogCategory(Category category) {
        if (category.getCategoryId() == null || StringUtils.isBlank(category.getTitle())) {
            return null;
        }
        category.setUpdateTime(new Date());
        Category save = categoryRepository.save(category);
        return save;
    }

    @Override
    public int deleteBlogCategory(Integer[] categoryId) {
        if (categoryId == null || categoryId.length == 0) {
            return -1;
        }
        int count = 0;
        for (Integer id : categoryId) {
            categoryRepository.deleteById(id);
            count++;
        }
        return count;
    }

    @Override
    public Category selectBlogCategoryByCategoryId(Integer id) {
        if (id == null) {
            return null;
        }
        Category category = categoryRepository.getByCategoryId(id);
        return category;
    }

    @Override
    public int updateCategorySupport(Integer[] ids, Boolean support) {
        if (ids == null || ids.length == 0 || support == null) {
            return -1;
        }
        int count = 0;
        for (Integer id : ids) {
            Category category = categoryRepository.getByCategoryId(id);
            if (category != null) {
                category.setSupport(!support);
                categoryRepository.save(category);
                count++;
            }
        }
        return count;
    }

    @Override
    public Category getCategoryByTitle(String title) {
        if (StringUtils.isBlank(title)) {
            return null;
        }
        return categoryRepository.getByTitle(title);
    }
}
