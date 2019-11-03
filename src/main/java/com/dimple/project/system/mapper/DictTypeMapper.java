package com.dimple.project.system.mapper;

import com.dimple.project.system.domain.DictType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className: DictTypeMapper
 * @description: 字典表 数据层
 * @author: Dimple
 * @date: 10/22/19
 */
@Mapper
public interface DictTypeMapper {
    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    List<DictType> selectDictTypeList(DictType dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    List<DictType> selectDictTypeAll();

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    DictType selectDictTypeById(Long dictId);

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    DictType selectDictTypeByType(String dictType);

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    int deleteDictTypeById(Long dictId, String loginUsername);

    /**
     * 新增字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int insertDictType(DictType dictType);

    /**
     * 修改字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int updateDictType(DictType dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    DictType checkDictTypeUnique(String dictType);
}
