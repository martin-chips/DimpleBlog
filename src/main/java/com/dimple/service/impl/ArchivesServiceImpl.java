package com.dimple.service.impl;

import com.dimple.bean.Archives;
import com.dimple.bean.ArchivesExample;
import com.dimple.dao.ArchivesMapper;
import com.dimple.service.ArchivesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ArchivesServiceImpl
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/11 19:31
 * @Version: 1.0
 */
@Service
public class ArchivesServiceImpl implements ArchivesService {
    @Autowired
    ArchivesMapper archivesMapper;

    @Override
    public List<Archives> getAllBlogArchives(Date startTime, Date endTime, String description, String title) {
        ArchivesExample archivesExample = new ArchivesExample();
        ArchivesExample.Criteria criteria = archivesExample.createCriteria();
        if (StringUtils.isNotBlank(description)) {
            criteria.andDescriptionLike(description);
        }
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(title);
        }
        if (startTime != null && endTime != null) {
            criteria.andCreateTimeBetween(startTime, endTime);
        } else if (startTime != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(startTime);
        } else if (endTime != null) {
            criteria.andCreateTimeLessThanOrEqualTo(endTime);
        }
        List<Archives> archives = archivesMapper.selectByExample(archivesExample);
        return archives;
    }

    @Override
    public int insertBlogArchives(Archives archives) {
        if (archives == null || StringUtils.isBlank(archives.getTitle())) {
            return -1;
        }
        archives.setCreateTime(new Date());
        archives.setUpdateTime(new Date());
        int i = archivesMapper.insert(archives);
        return i;
    }

    @Override
    public int updateBlogArchives(Archives archives) {
        if (archives.getArchivesId() == null || StringUtils.isBlank(archives.getTitle())) {
            return -1;
        }
        int i = archivesMapper.updateByPrimaryKeySelective(archives);
        return i;
    }

    @Override
    public int deleteBlogArchives(Integer archivesIds[]) {
        if (archivesIds == null || archivesIds.length == 0) {
            return -1;
        }
        int count = 0;
        for (Integer archivesId : archivesIds) {
            count += archivesMapper.deleteByPrimaryKey(archivesId);
        }
        return count;
    }

    @Override
    public Archives selectBlogArchivesByArchivesId(Integer archivesId) {
        if (archivesId == null) {
            return null;
        }
        Archives archives = archivesMapper.selectByPrimaryKey(archivesId);
        return archives;
    }
}
