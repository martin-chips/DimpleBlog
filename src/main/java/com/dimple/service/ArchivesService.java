package com.dimple.service;

import com.dimple.bean.Archives;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ArchivesService
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/11 19:30
 * @Version: 1.0
 */
public interface ArchivesService {
    public List<Archives> getAllBlogArchives(Date startTime, Date endTime, String description, String title);

    /**
     * 新增归档
     *
     * @param archives
     * @return
     */
    int insertBlogArchives(Archives archives);

    /**
     * 更新归档
     *
     * @param archives
     * @return
     */
    int updateBlogArchives(Archives archives);

    /**
     * 删除归档
     *
     * @param archivesId
     * @return
     */
    int deleteBlogArchives(Integer archivesId[]);

    /**
     * 根据ID获取归档信息
     *
     * @param archivesId
     * @return
     */
    Archives selectBlogArchivesByArchivesId(Integer archivesId);
}
