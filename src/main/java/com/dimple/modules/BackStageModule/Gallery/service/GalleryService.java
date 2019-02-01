package com.dimple.modules.BackStageModule.Gallery.service;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: GalleryService
 * @Description:
 * @Auther: Owenb
 * @Date: 12/18/18 14:19
 * @Version: 1.0
 */
public interface GalleryService {
    /**
     *
     * @return
     */
    Map<String, Date> selectImagesNameAndModifyTime();

}
