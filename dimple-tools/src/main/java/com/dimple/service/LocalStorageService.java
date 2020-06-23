package com.dimple.service;

import com.dimple.domain.LocalStorage;
import com.dimple.service.dto.LocalStorageDTO;
import com.dimple.service.dto.LocalStorageQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @className: LocalStorageService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface LocalStorageService {

    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(LocalStorageQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     *
     * @param criteria 条件
     * @return /
     */
    List<LocalStorageDTO> queryAll(LocalStorageQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    LocalStorageDTO findById(Long id);

    /**
     * 上传
     *
     * @param name 文件名称
     * @param file 文件
     */
    void create(String name, MultipartFile file);

    /**
     * 编辑
     *
     * @param resources 文件信息
     */
    void update(LocalStorage resources);

    /**
     * 多选删除
     *
     * @param ids /
     */
    void deleteAll(Long[] ids);

    /**
     * 导出数据
     *
     * @param localStorageDTOS 待导出的数据
     * @param response         /
     * @throws IOException /
     */
    void download(List<LocalStorageDTO> localStorageDTOS, HttpServletResponse response) throws IOException;
}
