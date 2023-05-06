package com.dimple.blog.front.service.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.dimple.blog.api.RemoteBlogCommentService;
import com.dimple.blog.api.bo.BlogCommentBO;
import com.dimple.blog.api.bo.config.GithubLoginConfig;
import com.dimple.blog.api.model.BlogCommentDTO;
import com.dimple.blog.front.service.service.BlogRestCommentService;
import com.dimple.blog.front.service.service.BlogRestConfigService;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.enums.BlogCommentType;
import com.dimple.common.core.utils.DateUtils;
import com.dimple.common.core.utils.PageUtils;
import com.dimple.common.core.utils.ServletUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.ip.IpUtils;
import com.github.pagehelper.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service业务层处理
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Service
public class BlogRestCommentServiceImpl implements BlogRestCommentService {
    @Autowired
    private RemoteBlogCommentService remoteBlogCommentService;
    @Autowired
    private BlogRestConfigService blogRestConfigService;

    @Override
    public List<BlogCommentBO> selectBlogCommentListWithSub(BlogCommentBO blogCommentBO) {
        ResponseEntity<List<BlogCommentBO>> blogCommentListResponse = remoteBlogCommentService.selectBlogCommentList(BeanMapper
                .convert(blogCommentBO, BlogCommentDTO.class), SecurityConstants.INNER);
        Page<BlogCommentBO> blogCommentBOS = PageUtils.getInnerPage(blogCommentListResponse);
        if (CollectionUtils.isEmpty(blogCommentBOS)) {
            return blogCommentBOS;
        }
        handCommentWithFriendlyLocation(blogCommentBOS);
        List<Long> ids = blogCommentBOS.stream().map(e -> e.getId()).collect(Collectors.toList());
        List<BlogCommentBO> blogCommentByParentIds = Lists.newArrayList();
        ResponseEntity<List<BlogCommentBO>> byParentBlogCommentResponse = remoteBlogCommentService.selectBlogCommentByParentIds(ids, SecurityConstants.INNER);
        if (byParentBlogCommentResponse.isOk()) {
            blogCommentByParentIds = byParentBlogCommentResponse.getData();
        }
        handCommentWithFriendlyLocation(blogCommentByParentIds);
        List<BlogCommentBO> resultList = BeanMapper.convertList(blogCommentBOS, BlogCommentBO.class);
        Map<Long, String> idUsernameMap = resultList.stream().collect(Collectors.toMap(BlogCommentBO::getId, BlogCommentBO::getUsername));

        idUsernameMap.putAll(blogCommentByParentIds.stream().collect(Collectors.toMap(BlogCommentBO::getId, BlogCommentBO::getUsername)));
        for (BlogCommentBO resultItem : resultList) {
            List<BlogCommentBO> subCommentBOList = blogCommentByParentIds.stream()
                    .filter(e -> Objects.equals(e.getParentId(), resultItem.getId()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(subCommentBOList)) {
                continue;
            }
            for (BlogCommentBO commentBO : subCommentBOList) {
                commentBO.setReplyUsername(idUsernameMap.getOrDefault(commentBO.getReplyId(), "-"));
            }
            resultItem.setSubComments(subCommentBOList);
        }
        return resultList;
    }

    private void handCommentWithFriendlyLocation(List<BlogCommentBO> comments) {
        for (BlogCommentBO comment : comments) {
            comment.setLocation(IpUtils.getFriendlyIpLocationByLocation(comment.getLocation()));
        }
    }

    @Override
    public int insertBlogComment(BlogCommentBO blogCommentBO) {
        blogCommentBO.setCreateTime(DateUtils.getNowDate());
        blogCommentBO.setAdmin(false);
        if (Objects.equals(blogCommentBO.getType(), BlogCommentType.GITHUB.getType())) {
            Optional<String> adminIdOptional = Optional.ofNullable(blogRestConfigService.getGithubLoginConfig()).map(GithubLoginConfig::getAdminId);
            if (adminIdOptional.isPresent() && Objects.equals(adminIdOptional.get(), blogCommentBO.getVisitorId())) {
                blogCommentBO.setAdmin(true);
            }
        }
        String ip = IpUtils.getServletIp();
        String ipLocation = IpUtils.getIpLocation(ip);
        blogCommentBO.setIp(ip);
        blogCommentBO.setLocation(ipLocation);
        blogCommentBO.setOs(ServletUtils.getUserAgentOs());
        blogCommentBO.setBrowser(ServletUtils.getUserAgentBrowser());

        ResponseEntity<Integer> addCommentResponse = remoteBlogCommentService.insertBlogComment(blogCommentBO, SecurityConstants.INNER);
        if (addCommentResponse.isOk()) {
            return addCommentResponse.getData();
        }
        return -1;
    }

    @Override
    public int addBlogCommentLikeCount(Long id) {
        ResponseEntity<Integer> addCommentLikeCountResponse = remoteBlogCommentService.addCommentLikeCount(id, SecurityConstants.INNER);
        if (addCommentLikeCountResponse.isOk()) {
            return addCommentLikeCountResponse.getData();
        }
        return -1;
    }

}
