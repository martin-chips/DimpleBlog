package com.dimple.service.impl;

import com.dimple.bean.Category;
import com.dimple.bean.CategoryExample;
import com.dimple.dao.CategoryMapper;
import com.dimple.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ArchivesServiceImpl
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/11 19:31
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllBlogCategory(Date startTime, Date endTime, String description, String title) {
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        if (StringUtils.isNotBlank(description)) {
            criteria.andDescriptionLike(description);
        }
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(title);
        }
        if (startTime != null && endTime != null) {
            criteria.andCreateTimeBetween(startTime, endTime);
        } else if (startTime != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
        } else if (endTime != null) {
            criteria.andCreateTimeLessThanOrEqualTo(endTime);
        }
        List<Category> archives = categoryMapper.selectByExample(categoryExample);
        return archives;
    }

    @Override
    public List<Category> getAllBlogCategory() {
        return categoryMapper.selectByExample(null);
    }

    @Override
    public int insertBlogCategory(Category category) {
        if (category == null || StringUtils.isBlank(category.getTitle())) {
            return -1;
        }
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        int i = categoryMapper.insert(category);
        return i;
    }

    @Override
    public int updateBlogCategory(Category category) {
        if (category.getCategoryId() == null || StringUtils.isBlank(category.getTitle())) {
            return -1;
        }
        int i = categoryMapper.updateByPrimaryKeySelective(category);
        return i;
    }

    @Override
    public int deleteBlogCategory(Integer categoryId[]) {
        if (categoryId == null || categoryId.length == 0) {
            return -1;
        }
        int count = 0;
        for (Integer archivesId : categoryId) {
            count += categoryMapper.deleteByPrimaryKey(archivesId);
        }
        return count;
    }

    @Override
    public Category selectBlogCategoryByCategoryId(Integer archivesId) {
        if (archivesId == null) {
            return null;
        }
        Category category = categoryMapper.selectByPrimaryKey(archivesId);
        return category;
    }
}
