package com.dimple.modules.frontModule.front.service;

import com.dimple.modules.endModule.blogManager.bean.Blog;
import com.dimple.modules.endModule.blogManager.bean.Category;
import com.dimple.modules.endModule.blogManager.repository.BlogRepository;
import com.dimple.modules.endModule.blogManager.repository.CategoryRepository;
import com.dimple.modules.endModule.frontSetting.service.RotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @className: IndexServiceImpl
 * @description: 前台首页展示的Service
 * @auther: Owenb
 * @date: 01/23/19 17:19
 * @version: 1.0
 */
@Service
@Transactional
public class IndexServiceImpl implements IndexService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    RotationService rotationService;

    @Override
    public List<String> getCategorySupportName() {
        List<Category> categories = categoryRepository.findAllBySupportEquals(true);
        List<String> categoryNames = new LinkedList<>();
        int i = categories.size() < 5 ? categories.size() : 5;
        for (int j = 0; j < i; j++) {
            categoryNames.add(categories.get(j).getTitle());
        }
        return categoryNames;
    }

    @Override
    public List<List<Blog>> getBlogsInfo() {
        List<Category> allCategoryOrderByWeight = categoryRepository.findAllBySupportEquals(true);
        List<List<Blog>> blogList = new ArrayList<>();
        for (Category category : allCategoryOrderByWeight) {
            Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "weight");
            Integer categoryId = category.getCategoryId();
            Specification<Blog> specification = (Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> list = new LinkedList<>();
                list.add(criteriaBuilder.equal(root.get("categoryId").as(Integer.class), categoryId));
                Predicate[] predicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(predicates));
            };
            List<Blog> blogs = blogRepository.findAll(specification, pageable).getContent();
            blogList.add(blogs);
        }
        return blogList;
    }

    @Override
    public Object getRotationByPlace(int i) {
        return rotationService.getRotationByPlace(i);
    }

    @Override
    public List<Map<String, Object>> getNewestBlog(Pageable pageable) {
        List<Blog> blogs = blogRepository.findAll(pageable).getContent();
        //将List集合转为Map
        //使用LinkedhashMap保证有序
        List<Map<String, Object>> resultList = new LinkedList<>();
        for (Blog blog : blogs) {
            Map<String, Object> map = new HashMap<>();
            map.put("blogId", blog.getBlogId());
            map.put("title", blog.getTitle());
            map.put("summary", blog.getSummary());
            map.put("categoryName", getCategoryNameById(blog.getCategoryId()));
            map.put("headUrl", blog.getHeaderUrl());
            if (blog.getUpdateTime() == null) {
                map.put("time", new SimpleDateFormat("yyyy-MM-dd").format(blog.getCreateTime()));
            } else {
                map.put("time", new SimpleDateFormat("yyyy-MM-dd").format(blog.getUpdateTime()));
            }
            map.put("author", "边小丰");
            map.put("authorHeader", "/imgs/lalal");
            resultList.add(map);
        }
        return resultList;
    }


    /**
     * 根据category的id获取name
     *
     * @param categoryId
     * @return
     */
    private String getCategoryNameById(Integer categoryId) {
        Category category = categoryRepository.getByCategoryId(categoryId);
        return category == null ? "" : category.getTitle();
    }
}
