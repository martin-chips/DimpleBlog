package com.dimple.system.service.mapper;

import com.dimple.system.service.entity.SysDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典表 数据层
 *
 * @author Dimple
 */
 public interface SysDictDataMapper {
    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
     List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
     List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
     String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    /**
     * 根据字典数据ID查询信息
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
     SysDictData selectDictDataById(Long id);

    /**
     * 查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据
     */
     int countDictDataByType(String dictType);

    /**
     * 通过字典ID删除字典数据信息
     *
     * @param id 字典数据ID
     * @return affected lines
     */
     int deleteDictDataById(Long id);

    /**
     * 批量删除字典数据信息
     *
     * @param ids 需要删除的字典数据ID
     * @return affected lines
     */
     int deleteDictDataByIds(Long[] ids);

    /**
     * 新增字典数据信息
     *
     * @param dictData 字典数据信息
     * @return affected lines
     */
     int insertDictData(SysDictData dictData);

    /**
     * 修改字典数据信息
     *
     * @param dictData 字典数据信息
     * @return affected lines
     */
     int updateDictData(SysDictData dictData);

    /**
     * 同步修改字典类型
     *
     * @param oldDictType 旧字典类型
     * @param newDictType 新旧字典类型
     * @return affected lines
     */
     int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}
