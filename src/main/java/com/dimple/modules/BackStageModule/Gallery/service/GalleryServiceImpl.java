package com.dimple.modules.BackStageModule.Gallery.service;

import com.dimple.utils.FileOperateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class: GalleryServiceImpl
 * @description : 图库处理Service
 * @date: 12/18/18 14:19
 */
@Service
@Transactional
public class GalleryServiceImpl implements GalleryService {

    private FileOperateUtil fileOperateUtil;

    @Autowired
    public GalleryServiceImpl(FileOperateUtil fileOperateUtil) {
        Assert.notNull(fileOperateUtil, "fileOperateUtil is not allow null");
        this.fileOperateUtil = fileOperateUtil;
    }

    @Override
    public Map<String, Date> selectImagesNameAndModifyTime() {
        Map<String, Date> result = fileOperateUtil.selectImagesNameAndModifyTime();
        return result;
    }


}
