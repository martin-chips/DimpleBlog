package com.dimple.modules.system.service;

import com.dimple.modules.system.domain.Dict;
import com.dimple.modules.system.service.dto.DictDTO;
import com.dimple.modules.system.service.dto.DictQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @className: DictService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface DictService {

    /**
     * 分页查询
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Map<String, Object> queryAll(DictQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     *
     * @param dict /
     * @return /
     */
    List<DictDTO> queryAll(DictQueryCriteria dict);

    /**
     * 创建
     *
     * @param resources /
     * @return /
     */
    void create(Dict resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Dict resources);

    /**
     * 删除
     *
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 导出数据
     *
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<DictDTO> queryAll, HttpServletResponse response) throws IOException;
}
