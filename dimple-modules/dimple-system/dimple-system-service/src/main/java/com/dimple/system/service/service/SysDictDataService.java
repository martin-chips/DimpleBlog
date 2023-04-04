package com.dimple.system.service.service;


import com.dimple.system.api.model.SysDictDataBO;

import java.util.List;

/**
 * 字典 业务层
 *
 * @author Dimple
 */
public interface SysDictDataService {
    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    List<SysDictDataBO> selectDictDataList(SysDictDataBO dictData);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    String selectDictLabel(String dictType, String dictValue);

    /**
     * 根据字典数据ID查询信息
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
    SysDictDataBO selectDictDataById(Long id);

    /**
     * 批量删除字典数据信息
     *
     * @param ids 需要删除的字典数据ID
     */
    void deleteDictDataByIds(Long[] ids);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return affected lines
     */
    int insertDictData(SysDictDataBO dictData);

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return affected lines
     */
    int updateDictData(SysDictDataBO dictData);
}
