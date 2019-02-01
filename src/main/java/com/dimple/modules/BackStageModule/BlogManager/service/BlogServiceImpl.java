package com.dimple.modules.BackStageModule.BlogManager.service;

import com.dimple.framework.enums.BlogStatus;
import com.dimple.modules.BackStageModule.BlogManager.bean.Blog;
import com.dimple.modules.BackStageModule.BlogManager.bean.BlogInfo;
import com.dimple.modules.BackStageModule.BlogManager.bean.Tag;
import com.dimple.modules.BackStageModule.BlogManager.repository.BlogInfoRepository;
import com.dimple.modules.BackStageModule.BlogManager.repository.BlogRepository;
import com.dimple.modules.BackStageModule.BlogManager.repository.CategoryRepository;
import com.dimple.modules.FrontDeskModule.domain.Archive;
import com.dimple.modules.FrontDeskModule.domain.BlogDomain;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    TagService tagService;

    @Autowired
    BlogTagService blogTagService;

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
        Page<Blog> all = blogRepository.findAll(specification, pageable);
        return all;
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
        if (blog.getStatus() == null) {
            //设置为已发表状态
            blog.setStatus(BlogStatus.PUBLISHED.PUBLISHED.getCode());
        }
        Blog blogDB = blogRepository.save(blog);
        //将博客内容同步到blogInfo表中
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setContent(blogDB.getContent());
        blogInfo.setBlogId(blogDB.getBlogId());
        //设置tags
        String[] tags = blog.getTags();
        for (String tag : tags) {
            Tag tagDB = tagService.getTagByTitle(tag);
            //判断数据库是否是已经有该Tag了
            if (tagDB != null) {
                //设置blog和tag的关系
                blogTagService.insertBlogTag(blogDB.getBlogId(), tagDB.getId());
            } else {
                //说明是新的tag,需要创建tag
                Tag tempTag = new Tag();
                tempTag.setTitle(tag);
                tempTag.setCreateTime(new Date());
                Tag tagTempDB = tagService.insertTag(tempTag);
                //设置blog和tag的关系
                blogTagService.insertBlogTag(blogDB.getBlogId(), tagTempDB.getId());
            }

        }
        //保存blog正文内容
        blogInfoRepository.save(blogInfo);
        return blogDB;
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
        //设置更新时间
        blog.setUpdateTime(new Date());
        //删除博文与Tag的所有的关联，重新建立关联
        blogTagService.deleteByBlogId(blog.getBlogId());
        //重新建立连接
        String[] tags = blog.getTags();
        for (String tag : tags) {
            //从数据库获取tag
            Tag tagDB = tagService.getTagByTitle(tag);
            if (tagDB != null) {
                //说明是已经存在tag,设置关联关系
                blogTagService.insertBlogTag(blog.getBlogId(), tagDB.getId());
            } else {
                //新建tag
                Tag tagTemp = new Tag();
                tagTemp.setCreateTime(new Date());
                tagTemp.setTitle(tag);
                Tag tagTempDB = tagService.insertTag(tagTemp);
                blogTagService.insertBlogTag(blog.getBlogId(), tagTempDB.getId());
            }
        }
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
        //设置博客正文信息
        BlogInfo blogInfo = blogInfoRepository.findByBlogId(blog.getBlogId());
        blog.setContent(blogInfo.getContent());
        //设置博客的标签tag信息
        blog.setTags(tagService.getTagTitleByBlogId(id));
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

    @Override
    public List<Archive> getArchive() {
        List<Map<String, Object>> dateAndCount = blogRepository.getDateAndCount();
        if (dateAndCount == null) {
            return null;
        }
        List<Archive> archives = new ArrayList<>();
        for (Map<String, Object> dateIntegerMap : dateAndCount) {
            Archive archive = new Archive();
            String date = (String) dateIntegerMap.get("date");
            archive.setDate(date);
            List<Map<String, Object>> allByUpdateTime = blogRepository.getAllByUpdateTime(date);
            archive.setBlogs(allByUpdateTime);
            //如果不使用这种方式转换会出现java.math.BigInteger cannot be cast to java.lang.Integer
            Integer count = Integer.valueOf(dateIntegerMap.get("count").toString());
            archive.setCount(count);
            archives.add(archive);
        }
        return archives;
    }

    @Override
    public Page<BlogDomain> getALlBlogByCategoryId(Pageable pageable, int categoryId) {
        return blogRepository.getAllBlogByCategoryId(pageable, categoryId);
    }
}
