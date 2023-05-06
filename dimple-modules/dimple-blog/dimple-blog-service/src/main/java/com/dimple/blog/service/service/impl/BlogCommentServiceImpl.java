package com.dimple.blog.service.service.impl;

import com.dimple.blog.api.bo.BlogArticleBO;
import com.dimple.blog.api.bo.BlogCommentBO;
import com.dimple.blog.api.bo.KeyValue;
import com.dimple.blog.service.entity.BlogComment;
import com.dimple.blog.service.mapper.BlogCommentMapper;
import com.dimple.blog.service.service.BlogArticleService;
import com.dimple.blog.service.service.BlogCommentService;
import com.dimple.common.core.enums.BlogPageId;
import com.dimple.common.core.utils.DateUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service业务层处理
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Service
public class BlogCommentServiceImpl implements BlogCommentService {
    @Autowired
    private BlogCommentMapper blogCommentMapper;
    @Autowired
    private BlogArticleService blogArticleService;

    @Override
    public BlogCommentBO selectBlogCommentById(Long id) {
        BlogCommentBO blogCommentBO = BeanMapper.convert(blogCommentMapper.selectBlogCommentById(id), BlogCommentBO.class);
        fillCommentInfo(Arrays.asList(blogCommentBO));
        return blogCommentBO;
    }

    @Override
    public List<BlogCommentBO> selectBlogCommentByIds(List<Long> ids) {
        return BeanMapper.convertList(blogCommentMapper.selectBlogCommentByParentIds(ids), BlogCommentBO.class);
    }

    @Override
    public List<KeyValue<Long, Long>> selectBlogCommentCountByArticleId(List<Long> ids) {
        return blogCommentMapper.selectBlogCommentCountByArticleId(new HashSet<>(ids));
    }

    @Override
    public List<BlogCommentBO> selectBlogCommentList(BlogCommentBO blogCommentBO) {
        BlogComment blogComment = BeanMapper.convert(blogCommentBO, BlogComment.class);
        List<BlogComment> blogComments = blogCommentMapper.selectBlogCommentList(blogComment);
        List<BlogCommentBO> blogCommentBOS = BeanMapper.convertList(blogComments, BlogCommentBO.class);
        fillCommentInfo(blogCommentBOS);
        return blogCommentBOS;
    }

    private void fillCommentInfo(List<BlogCommentBO> blogCommentBOList) {
        List<Long> articleIds = blogCommentBOList.stream().map(BlogCommentBO::getArticleId).collect(Collectors.toList());
        Map<Long, String> articleIdAndTitleMap = blogArticleService.selectBlogArticleByIds(articleIds).stream().collect(Collectors.toMap(BlogArticleBO::getId, BlogArticleBO::getTitle));
        articleIdAndTitleMap.putAll(BlogPageId.getPageIdAndDescMap());
        for (BlogCommentBO blogCommentBO : blogCommentBOList) {
            blogCommentBO.setArticleTitle(articleIdAndTitleMap.getOrDefault(blogCommentBO.getArticleId(), "已删除的文章"));
        }
    }

    @Override
    public int updateBlogComment(BlogCommentBO blogCommentBO) {
        BlogComment blogComment = BeanMapper.convert(blogCommentBO, BlogComment.class);
        blogComment.setUpdateTime(DateUtils.getNowDate());
        return blogCommentMapper.updateBlogComment(blogComment);
    }

    @Override
    public int deleteBlogCommentByIds(Long[] ids) {
        return blogCommentMapper.deleteBlogCommentByIds(ids);
    }

    @Override
    public int deleteBlogCommentById(Long id) {
        return blogCommentMapper.deleteBlogCommentById(id);
    }

    @Override
    public int insertBlogComment(BlogCommentBO blogComment) {
        return blogCommentMapper.insertBlogComment(BeanMapper.convert(blogComment, BlogComment.class));
    }

    @Override
    public int addCommentLikeCount(Long id) {
        return blogCommentMapper.addCommentLikeCount(id);
    }

}
