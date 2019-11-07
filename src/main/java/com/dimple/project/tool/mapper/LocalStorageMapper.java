package com.dimple.project.tool.mapper;

import com.dimple.project.tool.domain.LocalStorage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: LocalStorageMapper
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
public interface LocalStorageMapper {
    /**
     * 添加本地存储信息
     *
     * @param localStorage 本地存储
     * @return 受影响的行数
     */
    int insertLocalStorage(LocalStorage localStorage);

    /**
     * 更新本地存储的信息
     *
     * @param localStorage 本地存储信息
     * @return 受影响的行数
     */
    int updateLocalStorage(LocalStorage localStorage);

    /**
     * 删除本地存储信息
     *
     * @param id       需要删除的id
     * @param username 操作者姓名
     * @return
     */
    int deleteLocalStorageById(@Param("id") Long id, @Param("username") String username);

    /**
     * 列举本地存储信息
     *
     * @param localStorage 查询条件
     * @return list
     */
    List<LocalStorage> selectLocalStorageList(LocalStorage localStorage);

    /**
     * 根据ID获取信息
     *
     * @return local storage
     */
    LocalStorage selectLocalStorageById(Long id);


}
