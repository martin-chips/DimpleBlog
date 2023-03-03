package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.front.service.entity.BlogComment;
import com.dimple.blog.front.service.mapper.BlogCommentMapper;
import com.dimple.blog.front.service.service.BlogArticleService;
import com.dimple.blog.front.service.service.BlogCommentService;
import com.dimple.blog.front.service.service.bo.BlogArticleBO;
import com.dimple.blog.front.service.service.bo.BlogCommentBO;
import com.dimple.common.core.utils.DateUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        for (BlogCommentBO blogCommentBO : blogCommentBOList) {
            blogCommentBO.setArticleTitle(articleIdAndTitleMap.getOrDefault(blogCommentBO.getArticleId(), "已删除的文章"));
        }
    }

    @Override
    public int insertBlogComment(BlogCommentBO blogCommentBO) {
        BlogComment blogComment = BeanMapper.convert(blogCommentBO, BlogComment.class);
        blogComment.setCreateTime(DateUtils.getNowDate());
        return blogCommentMapper.insertBlogComment(blogComment);
    }

}
