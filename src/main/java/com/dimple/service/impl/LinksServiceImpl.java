package com.dimple.service.impl;

import com.dimple.bean.Link;
import com.dimple.framework.enums.LinksSearchCode;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultEnum;
import com.dimple.framework.message.ResultUtil;
import com.dimple.repository.LinkRepository;
import com.dimple.service.LinksService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: LinksServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 17:31
 * @Version: 1.0
 */
@Service
@Transactional
public class LinksServiceImpl implements LinksService {

    @Autowired
    LinkRepository linkRepository;

    @Override
    public Page<Link> getAllLinksHandled(String title, Date startTime, Date endTime, Boolean display, Pageable pageable) {
        return linkRepository.findAll((Specification<Link>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(title)) {
                list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + title + "%"));
            }
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime").as(Date.class), endTime));
            }
            if (display != null) {
                list.add(criteriaBuilder.equal(root.get("display").as(Boolean.class), display));
            }
            list.add(criteriaBuilder.equal(root.get("status"), 1));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public Result switchLinkStatus(Integer linkId, Boolean display) {
        if (linkId == null || display == null) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        Link link = linkRepository.findByLinkId(linkId);
        if (link == null) {
            return ResultUtil.error(ResultEnum.LINKS_NOT_FOUND.getCode(), ResultEnum.LINKS_NOT_FOUND.getMsg());
        }
        link.setDisplay(!display);
        linkRepository.save(link);
        return ResultUtil.success();
    }

    @Override
    public Result deleteLinks(Integer[] ids) {
        //参数合法性校验
        if (ids == null || ids.length == 0) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        //todo 事务的设置，需要设置单个事务失败不影响整个事物
        //记录操作的数
        Integer count = 0;
        for (Integer id : ids) {
            Link link = linkRepository.findByLinkId(id);
            if (link == null) {
                return ResultUtil.error(ResultEnum.LINKS_NOT_FOUND.getCode(), ResultEnum.LINKS_NOT_FOUND.getMsg());
            }
            linkRepository.deleteById(id);
            count++;
        }
        return ResultUtil.success(count);
    }

    @Override
    public Result getLinkInfo(Integer linkId) {
        if (linkId == null) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        Link link = linkRepository.findByLinkId(linkId);
        return ResultUtil.success(link);
    }

    @Override
    public Result addLink(Link link) {
        if (link == null || StringUtils.isBlank(link.getTitle()) || StringUtils.isBlank(link.getUrl())) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        link.setCreateTime(new Date());
        //设置为已经处理
        link.setStatus(true);
        link.setAvailable(true);
        link.setClick(0);
        linkRepository.save(link);
        return ResultUtil.success();
    }

    @Override
    public Result applyLink(Link link) {
        if (link == null || StringUtils.isBlank(link.getTitle()) || StringUtils.isBlank(link.getUrl())) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        link.setCreateTime(new Date());
        //设置是否显示，默认是不显示
        link.setDisplay(false);
        //设置是否已经处理，默认未处理
        link.setStatus(false);
        linkRepository.save(link);
        return ResultUtil.success();
    }

    @Override
    public Result updateLinkInfo(Link link) {
        if (link == null || link.getLinkId() == null) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        Link save = linkRepository.save(link);
        return ResultUtil.success(save);
    }

    @Override
    public Page<Link> getAllLinksUnHandled(Date startTime, Date endTime, String title, Pageable pageable) {
        return linkRepository.findAll((Specification<Link>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            if (StringUtils.isNotBlank(title)) {
                list.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + title + "%"));
            }
            if (startTime != null) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime").as(Date.class), startTime));
            }
            if (endTime != null) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime").as(Date.class), endTime));
            }
            list.add(criteriaBuilder.equal(root.get("status").as(Boolean.class), false));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public Result passLinksApply(Integer linkId) {
        if (linkId == null) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        Link links = linkRepository.findByLinkId(linkId);
        if (links == null) {
            return ResultUtil.error(ResultEnum.LINKS_NOT_FOUND.getCode(), ResultEnum.LINKS_NOT_FOUND.getMsg());
        }
        //设置通过友链申请
        links.setStatus(true);
        Link save = linkRepository.save(links);
        return ResultUtil.success(save);
    }

    @Override
    public Integer getUnHandledLinksCount() {
        return linkRepository.countLinkUnhandled();
    }

    @Override
    public Page<Link> getLinksCondition(String title, Date startTime, Date endTime, Boolean display, LinksSearchCode searchCode, Pageable pageable) {
        if (searchCode == null) {
            return getAllLinksHandled(title, startTime, endTime, display, pageable);
        }
        Page<Link> links = null;
        switch (searchCode) {
            case SEARCH_CODE_ALL:
                links = getLinksAll(pageable);
                break;
            case SEARCH_CODE_DIE:
                links = getLinksDie(pageable);
                break;
            case SEARCH_CODE_UNHANDLED:
                links = getAllLinksUnHandled(null, null, null, pageable);
                break;
            case SEARCH_CODE_HIDE:
                links = getLinksHide(pageable);
                break;
            case SEARCH_CODE_DISPLAY:
                links = getLinksDisplay(pageable);
                break;
            default:
                break;
        }
        return links;
    }

    @Override
    public Page<Link> getLinksAll(Pageable pageable) {
        return linkRepository.findAll(pageable);
    }

    @Override
    public Page<Link> getLinksDie(Pageable pageable) {
        return linkRepository.findAll((Specification<Link>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            list.add(criteriaBuilder.equal(root.get("available").as(Boolean.class), false));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public Page<Link> getLinksHide(Pageable pageable) {
        return linkRepository.findAll((Specification<Link>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            list.add(criteriaBuilder.equal(root.get("display").as(Boolean.class), false));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public Page<Link> getLinksDisplay(Pageable pageable) {
        return linkRepository.findAll((Specification<Link>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new LinkedList<>();
            list.add(criteriaBuilder.equal(root.get("display").as(Boolean.class), true));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
    }

    @Override
    public Map<String, Integer> countStatusDetails() {
        return linkRepository.countStatusDetails();
    }
}
