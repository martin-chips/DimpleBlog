package com.dimple.blog.front.service.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.dimple.blog.front.service.entity.BlogComment;
import com.dimple.blog.front.service.entity.KeyValue;
import com.dimple.blog.front.service.enums.BlogCommentType;
import com.dimple.blog.front.service.mapper.BlogCommentMapper;
import com.dimple.blog.front.service.service.BlogCommentService;
import com.dimple.blog.front.service.service.BlogConfigService;
import com.dimple.blog.front.service.service.bo.BlogCommentBO;
import com.dimple.common.core.utils.DateUtils;
import com.dimple.common.core.utils.ServletUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.ip.IpUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
    private BlogConfigService blogConfigService;

    @Override
    public List<BlogCommentBO> selectBlogCommentListWithSub(BlogCommentBO blogCommentBO) {
        List<BlogComment> blogCommentEntityList = blogCommentMapper.selectBlogCommentList(BeanMapper.convert(blogCommentBO, BlogComment.class));
        if (CollectionUtils.isEmpty(blogCommentEntityList)) {
            return Lists.newArrayList();
        }
        handCommentWithFriendlyLocation(blogCommentEntityList);
        List<Long> ids = blogCommentEntityList.stream().map(e -> e.getId()).collect(Collectors.toList());
        List<BlogComment> blogCommentByParentIds = blogCommentMapper.selectBlogCommentByParentIds(ids);
        handCommentWithFriendlyLocation(blogCommentByParentIds);
        List<BlogCommentBO> resultList = BeanMapper.convertList(blogCommentEntityList, BlogCommentBO.class);
        Map<Long, String> idUsernameMap = resultList.stream().collect(Collectors.toMap(BlogCommentBO::getId, BlogCommentBO::getUsername));
        idUsernameMap.putAll(blogCommentByParentIds.stream().collect(Collectors.toMap(BlogComment::getId, BlogComment::getUsername)));
        for (BlogCommentBO resultItem : resultList) {
            List<BlogComment> subCommentList = blogCommentByParentIds.stream()
                    .filter(e -> Objects.equals(e.getParentId(), resultItem.getId()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(subCommentList)) {
                continue;
            }
            List<BlogCommentBO> subCommentBOList = BeanMapper.convertList(subCommentList, BlogCommentBO.class);
            for (BlogCommentBO commentBO : subCommentBOList) {
                commentBO.setReplyUsername(idUsernameMap.getOrDefault(commentBO.getReplyId(), "-"));
            }
            resultItem.setSubComments(subCommentBOList);
        }
        return resultList;
    }

    private void handCommentWithFriendlyLocation(List<BlogComment> comments) {
        for (BlogComment comment : comments) {
            comment.setLocation(IpUtils.getFriendlyIpLocationByLocation(comment.getLocation()));
        }
    }

    @Override
    public int insertBlogComment(BlogCommentBO blogCommentBO) {
        BlogComment blogComment = BeanMapper.convert(blogCommentBO, BlogComment.class);
        blogComment.setCreateTime(DateUtils.getNowDate());
        if (Objects.equals(blogComment.getType(), BlogCommentType.GITHUB.getType())
                && Objects.equals(blogComment.getVisitorId(), blogConfigService.getGithubLoginConfig().getAdminId())) {

            blogComment.setAdmin(Boolean.TRUE);
        }
        String ip = IpUtils.getServletIp();
        String ipLocation = IpUtils.getIpLocation(ip);
        blogComment.setIp(ip);
        blogComment.setLocation(ipLocation);
        blogComment.setOs(ServletUtils.getUserAgentOs());
        blogComment.setBrowser(ServletUtils.getUserAgentBrowser());
        return blogCommentMapper.insertBlogComment(blogComment);
    }

    @Override
    public int addBlogCommentLikeCount(Long id) {
        return blogCommentMapper.addCommentLikeCount(id);
    }

    @Override
    public Map<Long, Long> selectCommentCountByArticleIds(Set<Long> articleIds) {
        List<KeyValue<Long, Long>> keyValues = blogCommentMapper.selectBlogCommentCountByArticleId(articleIds);
        Map<Long, Long> articleIdAndCountMap = keyValues.stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
        return articleIdAndCountMap;
    }

}
