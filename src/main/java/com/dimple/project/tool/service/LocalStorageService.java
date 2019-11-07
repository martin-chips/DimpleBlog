package com.dimple.project.tool.service;

import com.dimple.project.tool.domain.LocalStorage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @className: LocalStorageService
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
public interface LocalStorageService {
    /**
     * 获取本地存储信息
     *
     * @param localStorage 查询条件
     * @return list
     */
    List<LocalStorage> selectLocalStorageList(LocalStorage localStorage);

    /**
     * 上传文件并保存到数据库
     *
     * @param name 文件名
     * @param file 文件信息
     * @return 受影响的行数
     */
    int upload(String name, MultipartFile file);

    /**
     * 更改数据库中文件信息
     *
     * @param localStorage 本地存储文件信息
     * @return 受影响的行数
     */
    int updateLocalStorage(LocalStorage localStorage);

    /**
     * 删除本地存储信息
     *
     * @param id id
     * @return 受影响的行数
     */
    int deleteLocalStorage(Long id);
}
