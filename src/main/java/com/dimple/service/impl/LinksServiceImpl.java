package com.dimple.service.impl;

import com.dimple.bean.Links;
import com.dimple.bean.LinksExample;
import com.dimple.dao.LinksMapper;
import com.dimple.service.LinksService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultEnum;
import com.dimple.utils.message.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: LinksServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 17:31
 * @Version: 1.0
 */
@Service
public class LinksServiceImpl implements LinksService {
    @Autowired
    LinksMapper linksMapper;

    @Override
    public List<Links> getAllLinksHandled(String search) {
        LinksExample linksExample = new LinksExample();
        LinksExample.Criteria criteria = linksExample.createCriteria();
        //获取已经处理的友链
        criteria.andStatusEqualTo(true);
        if (StringUtils.isNotBlank(search)) {
            linksExample.or().andTitleLike(search);
            linksExample.or().andDescriptionLike(search);
            linksExample.or().andUrlLike(search);
//            LinksExample.Criteria criteriaTemp = linksExample.createCriteria();
//            criteriaTemp.andTitleLike(search);
//            criteriaTemp.andDescriptionLike(search);
//            criteriaTemp.andUrlLike(search);
//            linksExample.or(criteriaTemp);
        }
        List<Links> links = linksMapper.selectByExample(linksExample);
        return links;
    }

    @Override
    public Result switchLinkStatus(Integer linkId, Boolean status) {
        if (linkId == null || status == null) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        Links link = linksMapper.selectByPrimaryKey(linkId);
        if (link == null) {
            return ResultUtil.error(ResultEnum.LINKS_NOT_FOUND.getCode(), ResultEnum.LINKS_NOT_FOUND.getMsg());
        }
        link.setDisplay(!status);
        linksMapper.updateByPrimaryKey(link);
        return ResultUtil.success();
    }

    @Override
    public Result deleteLink(Integer linkId) {
        if (linkId == null) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        Links link = linksMapper.selectByPrimaryKey(linkId);
        if (link == null) {
            return ResultUtil.error(ResultEnum.LINKS_NOT_FOUND.getCode(), ResultEnum.LINKS_NOT_FOUND.getMsg());
        }
        int i = linksMapper.deleteByPrimaryKey(linkId);
        return ResultUtil.success(i);
    }

    @Override
    public Result getLinkInfo(Integer linkId) {
        if (linkId == null) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        Links link = linksMapper.selectByPrimaryKey(linkId);
        return ResultUtil.success(link);
    }

    @Override
    public Result addLink(Links links) {
        if (links == null || StringUtils.isBlank(links.getTitle()) || StringUtils.isBlank(links.getUrl())) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        links.setCreateDate(new Date());
        //设置是否显示，默认是显示的
        links.setDisplay(true);
        //设置是否已经处理，默认已经处理
        links.setStatus(true);
        linksMapper.insert(links);
        return ResultUtil.success();
    }

    @Override
    public Result applyLink(Links links) {
        if (links == null || StringUtils.isBlank(links.getTitle()) || StringUtils.isBlank(links.getUrl())) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        links.setCreateDate(new Date());
        //设置是否显示，默认是不显示
        links.setDisplay(false);
        //设置是否已经处理，默认未处理
        links.setStatus(false);
        linksMapper.insert(links);
        return ResultUtil.success();
    }

    @Override
    public Result updateLinkInfo(Links links) {
        if (links == null || links.getLinkId() == null) {
            return ResultUtil.error(ResultEnum.LINKS_PARAM_ERROR.getCode(), ResultEnum.LINKS_PARAM_ERROR.getMsg());
        }
        linksMapper.updateByPrimaryKey(links);
        return ResultUtil.success();
    }
}
