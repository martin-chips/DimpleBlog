package com.dimple.modules.endModule.blogManager.service;

import com.dimple.framework.enums.BlogStatus;
import com.dimple.modules.endModule.blogManager.bean.Blog;
import com.dimple.modules.endModule.blogManager.bean.BlogInfo;
import com.dimple.modules.endModule.blogManager.repository.BlogInfoRepository;
import com.dimple.modules.endModule.blogManager.repository.BlogRepository;
import com.dimple.modules.endModule.blogManager.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;

import static com.dimple.utils.JpaUpdateUtil.copyProperties;

/**
 * @ClassName: BlogServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 12/12/18 14:56
 * @Version: 1.0
 */
@Service
@Slf4j
@Transactional
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;


    @Autowired
    CategoryRepository categoryRepository;


    @Autowired
    BlogInfoRepository blogInfoRepository;


    @Override
    public Page<Blog> getAllBlogs(String title, Date startTime, Date endTime, Integer status, Pageable pageable) {
        Specification<Blog> specification = (Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
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
            if (status != null) {
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), status));
            }
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        return blogRepository.findAll(specification, pageable);
    }

    @Override
    public Blog insertBlog(Blog blog) {
        if (blog == null || StringUtils.isBlank(blog.getTitle()) || StringUtils.isBlank(blog.getContent())) {
            return null;
        }
        blog.setCreateTime(new Date());
        //设置摘要
        if (StringUtils.isBlank(blog.getSummary())) {
            blog.setSummary(blog.getContent().substring(0, blog.getContent().length() < 150 ? blog.getContent().length() : 150));
        }
        blog.setClick(0);
        blog.setSupport(false);
        blog.setWeight(0);
        //blog.setUpdateTime(new Date());
        if (blog.getStatus() == null) {
            //设置为已发表状态
            blog.setStatus(BlogStatus.PUBLISHED.PUBLISHED.getCode());
        }
        Blog save = blogRepository.save(blog);
        //将博客内容同步到blogInfo表中
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setContent(save.getContent());
        blogInfo.setBlogId(save.getBlogId());
        blogInfoRepository.save(blogInfo);
        return save;
    }


    @Override
    public void deleteBlog(Integer blogId) {
        if (blogId == null) {
            return;
        }
        blogRepository.deleteById(blogId);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        if (blog.getBlogId() == null || StringUtils.isBlank(blog.getTitle())) {
            return null;
        }
        Blog blogDB = selectBlogById(blog.getBlogId());
        BlogInfo blogInfo = blogInfoRepository.findByBlogId(blog.getBlogId());
        blogInfo.setContent(blog.getContent());
        copyProperties(blogDB, blog);
        blog.setUpdateTime(new Date());
        //设置头像URL与域名与关
        return blogRepository.save(blog);
    }

    @Override
    public int changeBlogStatus(Integer[] ids, Integer status) {
        if (ids == null || ids.length == 0 || status == null) {
            return -1;
        }
        int count = 0;
        for (Integer id : ids) {
            Blog blog = blogRepository.findByBlogId(id);
            blog.setStatus(status);
            blogRepository.save(blog);
            count++;
        }
        return count;
    }

    @Override
    public Blog selectBlogById(Integer id) {
        if (id == null) {
            return null;
        }
        return blogRepository.findByBlogId(id);
    }

    @Override
    public Map<String, Integer> selectCountOfBlogStatus() {
        Map<String, Integer> allBolgStatusCount = blogRepository.findAllBlogStatusCount();
        return allBolgStatusCount;
    }

    @Override
    public Blog selectBlogByIdBlobs(Integer id) {
        if (id == null) {
            return null;
        }
        Blog blog = blogRepository.findByBlogId(id);
        BlogInfo blogInfo = blogInfoRepository.findByBlogId(blog.getBlogId());
        blog.setContent(blogInfo.getContent());
        return blog;
    }

    @Override
    public int supportBlog(Integer[] ids, Boolean status) {
        if (ids == null || ids.length == 0 || status == null) {
            return -1;
        }
        int count = 0;
        for (Integer id : ids) {
            Blog blog = blogRepository.findByBlogId(id);
            if (blog != null) {
                blog.setSupport(!status);
                blogRepository.save(blog);
            }
        }
        log.info("改变推荐Id为{}的状态从{}到{}", ids, status, !status);
        return count;
    }

    @Override
    public int selectBlogCountByStatus(BlogStatus blogStatus) {
        Integer count = 0;
        if (blogStatus == BlogStatus.ALL) {
            count = Math.toIntExact(blogRepository.count());
        } else {
            count = blogRepository.countByStatus(blogStatus.getCode());
        }
        return count;
    }

    @Override
    public List<Blog> getAllBlogByCategoryId(int id) {
        return blogRepository.findAllByCategoryId(id);
    }

    @Override
    public Blog getBlogById(Integer id) {
        return id == null ? null : blogRepository.getByBlogId(id);
    }

    @Override
    public Blog getBlogAndInfoById(Integer id) {
        Blog byBlogId = blogRepository.getByBlogId(id);
        if (byBlogId == null) {
            return null;
        }
        byBlogId.setContent(blogInfoRepository.findByBlogId(id).getContent());
        return byBlogId;
    }

    @Override
    public List<Blog> getClickRanking() {
        Pageable pageable = PageRequest.of(0, 8, Sort.Direction.DESC, "click");
        return getAllBlogs(null, null, null, null, pageable).getContent();
    }

    @Override
    public Map<String, Blog> getNextAndPreviousBlogById(Integer id) {
        //获取上下一篇
        Blog nextBlog = blogRepository.getNextBlog(id);
        Blog previousBlog = blogRepository.getPreviousBlog(id);
        Map<String, Blog> map = new HashMap<>();
        map.put("nextBlog", nextBlog);
        map.put("previousBlog", previousBlog);
        return map;
    }

    @Override
    public List<Blog> getRelatedBlogByCategoryId(Integer categoryId) {
        //根据点击数来获取
        Pageable pageable = PageRequest.of(0, 8, Sort.Direction.DESC, "click");
        Specification<Blog> specification = (Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            list.add(criteriaBuilder.equal(root.get("categoryId").as(Integer.class), categoryId));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        return blogRepository.findAll(specification, pageable).getContent();
    }

    @Override
    public List<Blog> getSupportBlog() {
        Pageable pageable = PageRequest.of(0, 6, Sort.Direction.DESC, "click");
        Specification<Blog> specification = (Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            list.add(criteriaBuilder.equal(root.get("support").as(Boolean.class), true));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        return blogRepository.findAll(specification, pageable).getContent();
    }

    @Override
    public List<Blog> getAllBlogByPageable(Pageable pageable) {
        return blogRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Blog> getRandomBlogLimit(int i) {
        return blogRepository.getRandomBlog(i);
    }
}
