package com.dimple.modules.FrontDeskModule.service;

import com.dimple.modules.BackStageModule.BlogManager.bean.Blog;
import com.dimple.modules.BackStageModule.BlogManager.bean.Category;
import com.dimple.modules.BackStageModule.BlogManager.repository.BlogRepository;
import com.dimple.modules.BackStageModule.BlogManager.repository.CategoryRepository;
import com.dimple.modules.BackStageModule.BlogManager.service.BlogService;
import com.dimple.modules.BackStageModule.FrontSetting.service.RotationService;
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

    @Autowired
    BlogService blogService;

    @Override
    public List<String> getCategorySupportName() {
        List<Category> categories = categoryRepository.findALlCategorySupportedOrderByWeightDesc();
        List<String> categoryNames = new LinkedList<>();
        int i = categories.size() < 10 ? categories.size() : 10;
        for (int j = 0; j < i; j++) {
            categoryNames.add(categories.get(j).getTitle());
        }
        return categoryNames;
    }

    @Override
    public List<List<Blog>> getBlogsInfo() {
        List<Category> allCategoryOrderByWeight = categoryRepository.findALlCategorySupportedOrderByWeightDesc();
        List<List<Blog>> blogList = new ArrayList<>();
        for (Category category : allCategoryOrderByWeight) {
            //设置其博文显示
            Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "weight");
            Integer categoryId = category.getCategoryId();
            Specification<Blog> specification = (Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> list = new LinkedList<>();
                list.add(criteriaBuilder.equal(root.get("categoryId").as(Integer.class), categoryId));
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), 1));
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
        List<Blog> blogs = blogService.getAllBlogByPageable(pageable);
        //将List集合转为Map
        //使用LinkedhashMap保证有序
        List<Map<String, Object>> resultList = new LinkedList<>();
        for (Blog blog : blogs) {
            Map<String, Object> map = new HashMap<>();
            map.put("blogId", blog.getBlogId());
            map.put("title", blog.getTitle());
            map.put("summary", blog.getSummary());
            //设置分类的ID
            map.put("categtoryId", blog.getCategoryId());
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
