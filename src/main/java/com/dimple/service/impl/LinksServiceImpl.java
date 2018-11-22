package com.dimple.service.impl;

import com.dimple.bean.Links;
import com.dimple.dao.LinksMapper;
import com.dimple.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Links> getAllLinks() {
        List<Links> links = linksMapper.selectByExample(null);
        return links;
    }
}
