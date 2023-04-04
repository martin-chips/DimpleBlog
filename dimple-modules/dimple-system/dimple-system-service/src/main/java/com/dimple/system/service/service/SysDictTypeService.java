package com.dimple.system.service.service;


import com.dimple.system.api.model.SysDictDataBO;
import com.dimple.system.service.service.bo.SysDictTypeBO;

import java.util.List;

/**
 * 字典 业务层
 *
 * @author Dimple
 */
public interface SysDictTypeService {
    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictTypeBO> selectDictTypeList(SysDictTypeBO dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    List<SysDictTypeBO> selectDictTypeAll();

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictDataBO> selectDictDataByType(String dictType);

    /**
     * 根据字典类型ID查询信息
     *
     * @param id 字典类型ID
     * @return 字典类型
     */
    SysDictTypeBO selectDictTypeById(Long id);

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    SysDictTypeBO selectDictTypeByType(String dictType);

    /**
     * 批量删除字典信息
     *
     * @param ids 需要删除的字典ID
     */
    void deleteDictTypeByIds(Long[] ids);

    /**
     * 加载字典缓存数据
     */
    void loadingDictCache();

    /**
     * 清空字典缓存数据
     */
    void clearDictCache();

    /**
     * 重置字典缓存数据
     */
    void resetDictCache();

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return affected lines
     */
    int insertDictType(SysDictTypeBO dictType);

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return affected lines
     */
    int updateDictType(SysDictTypeBO dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return affected lines
     */
    String checkDictTypeUnique(SysDictTypeBO dictType);
}
