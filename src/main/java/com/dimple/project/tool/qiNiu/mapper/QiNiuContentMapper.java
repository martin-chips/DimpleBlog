package com.dimple.project.tool.qiNiu.mapper;

import com.dimple.project.tool.qiNiu.domain.QiNiuContent;

import java.util.List;

/**
 * @className: QiNiuContentMapper
 * @description:
 * @author: Dimple
 * @date: 11/06/19
 */
public interface QiNiuContentMapper {
    /**
     * 获取七牛云文件List
     *
     * @param qiNiuContent 查询条件
     * @return list
     */
    List<QiNiuContent> selectContentList(QiNiuContent qiNiuContent);

    /**
     * 根据Id查询文件
     *
     * @param id id
     * @return 文件信息
     */
    QiNiuContent selectContentById(Long id);

    /**
     * 根据Id删除文件
     *
     * @param id id
     * @return 受影响的行数
     */
    int deleteContentById(Long id);
}
