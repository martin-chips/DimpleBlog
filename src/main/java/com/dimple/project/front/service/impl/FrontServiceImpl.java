package com.dimple.project.front.service.impl;

import com.dimple.common.enums.CacheConstants;
import com.dimple.common.enums.TagType;
import com.dimple.common.utils.ObjectUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.ip.AddressUtils;
import com.dimple.common.utils.ip.IpUtils;
import com.dimple.framework.config.redis.CacheExpire;
import com.dimple.framework.config.redis.TimeType;
import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.domain.Comment;
import com.dimple.project.common.domain.Category;
import com.dimple.project.common.domain.Tag;
import com.dimple.project.front.domain.BlogQuery;
import com.dimple.project.front.mapper.FrontMapper;
import com.dimple.project.front.service.FrontService;
import com.dimple.project.system.domain.Carousel;
import com.dimple.project.system.domain.Config;
import com.dimple.project.system.domain.Link;
import com.dimple.project.system.domain.Notice;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @className: FrontServiceImpl
 * @description:
 * @author: Dimple
 * @date: 2020/1/1
 */
@Service
public class FrontServiceImpl implements FrontService {

    @Autowired
    FrontMapper frontMapper;

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'LinkList'")
    @CacheExpire(expire = 3, type = TimeType.HOURS)
    public List<Link> selectLinkList() {
        return frontMapper.selectLinkList();
    }

    @Override
    public int insertLink(Link link) {
        return frontMapper.insertLink(link);
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'CategoryList'")
    @CacheExpire(expire = 3, type = TimeType.HOURS)
    public List<Category> selectCategoryList() {
        return frontMapper.selectCategoryList();
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'SupportList'")
    @CacheExpire(expire = 3, type = TimeType.HOURS)
    public List<Blog> selectSupportBlogList() {
        return frontMapper.selectSupportBlogList();
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'HotList'")
    @CacheExpire(expire = 3, type = TimeType.HOURS)
    public List<Blog> selectHotBlogList() {
        return frontMapper.selectHotBlogList();
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'TagList'")
    @CacheExpire(expire = 3, type = TimeType.HOURS)
    public List<Tag> selectTagList() {
        return frontMapper.selectTagList();
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'CarouselList'")
    @CacheExpire(expire = 3, type = TimeType.HOURS)
    public List<Carousel> selectCarouselList() {
        return frontMapper.selectCarouselList();
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'NoticeList'")
    @CacheExpire(expire = 3, type = TimeType.HOURS)
    public List<Notice> selectNoticeList() {
        return frontMapper.selectNoticeList();
    }

    @Override
    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogItem_'-#comment.pageId")
    public int insertComment(Comment comment) {
        comment.setAdminReply(SecurityUtils.isAdmin());
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        comment.setOs(userAgent.getOperatingSystem().getName());
        comment.setBrowser(userAgent.getBrowser().getName());
        comment.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        comment.setLocation(AddressUtils.getCityInfoByIp(comment.getIp()));
        return frontMapper.insertComment(comment);
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'CommentItem_'+#id")
    @CacheExpire(expire = 30, type = TimeType.MINUTES)
    public List<Comment> selectCommentListByPageId(Long id) {
        //查询获取所有的comment
        List<Comment> commentList = frontMapper.selectCommentListByPageId(id);
        List<Comment> result = commentList.stream().filter(e -> e.getParentId() == null).collect(Collectors.toList());
        //CommentId和NickName的映射Map
        Map<Long, String> commentIdAndNickNameMap = commentList.stream().collect(Collectors.toMap(Comment::getId, Comment::getNickName));
        for (Comment comment : result) {
            Long commentId = comment.getId();
            comment.setSubCommentList(commentList.stream().filter(e -> commentId.equals(e.getParentId())).collect(Collectors.toList()));
            //设置replyNickName
            if (ObjectUtils.isNotEmpty(comment.getSubCommentList())) {
                for (Comment temp : comment.getSubCommentList()) {
                    if (temp.getReplyId() != null) {
                        temp.setReplyNickName(commentIdAndNickNameMap.get(temp.getReplyId()));
                    }
                }
            }
        }
        return result;
    }

    @Override
    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'CommentItem_'+#id")
    public int incrementCommentGood(Long id) {
        return frontMapper.incrementCommentGood(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.CACHE_NAME_FRONT, key = "'CommentItem_'+#id")
    public int incrementCommentBad(Long id) {
        return frontMapper.incrementCommentBad(id);
    }

    @Override
    public int incrementBlogLike(Long id) {
        return frontMapper.incrementBlogLike(id);
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogItem_' +#id")
    @CacheExpire(expire = 30, type = TimeType.MINUTES)
    public Blog selectBlogDetailById(Long id) {
        Blog blog = frontMapper.selectBlogDetailById(id);
        //get all comment
        blog.setCommentList(selectCommentListByPageId(id));
        return blog;
    }

    @Override
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT, key = "'BlogList_' +#blogQuery")
    @CacheExpire(expire = 3, type = TimeType.HOURS)
    public List<Blog> selectBlogList(BlogQuery blogQuery) {
        List<Blog> blogList = frontMapper.selectBlogList(blogQuery);
        for (Blog blog : blogList) {
            blog.setTagList(frontMapper.selectTagListByTypeAndId(TagType.BLOG.getType(), blog.getId()));
        }
        return blogList;
    }

    @Override
    public int incrementLinkClick(Integer id) {
        return frontMapper.incrementLinkClick(id);
    }

    @Override
    public int incrementBlogClick(Long id) {
        return frontMapper.incrementBlogClick(id);
    }

    @Override
    public String selectAbout() {
        Config config = frontMapper.selectAbout();
        return config.getConfigValue();
    }

    @Override
    public List<Blog> selectBlogArchive(BlogQuery blogQuery) {
        List<Blog> blogList = frontMapper.selectBlogList(blogQuery);
        for (Blog blog : blogList) {
            blog.setTagList(frontMapper.selectTagListByTypeAndId(TagType.BLOG.getType(), blog.getId()));
        }
        return blogList;
    }

}
