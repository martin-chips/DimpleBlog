package com.dimple.system.service.service.impl;

import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.security.utils.DictUtils;
import com.dimple.system.api.model.SysDictDataBO;
import com.dimple.system.service.entity.SysDictData;
import com.dimple.system.service.mapper.SysDictDataMapper;
import com.dimple.system.service.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author Dimple
 */
@Service
public class SysDictDataServiceImpl implements SysDictDataService {
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictDataBO> selectDictDataList(SysDictDataBO dictData) {
        return BeanMapper.convertList(dictDataMapper.selectDictDataList(BeanMapper.convert(dictData, SysDictData.class)), SysDictDataBO.class);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictDataBO selectDictDataById(Long id) {
        return BeanMapper.convert(dictDataMapper.selectDictDataById(id), SysDictDataBO.class);
    }

    /**
     * 批量删除字典数据信息
     *
     * @param ids 需要删除的字典数据ID
     */
    @Override
    public void deleteDictDataByIds(Long[] ids) {
        for (Long id : ids) {
            SysDictDataBO data = selectDictDataById(id);
            dictDataMapper.deleteDictDataById(id);
            List<SysDictDataBO> dictDatas = BeanMapper.convertList(dictDataMapper.selectDictDataByType(data.getDictType()), SysDictDataBO.class);
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
    }

    /**
     * 新增保存字典数据信息
     *
     * @param data 字典数据信息
     * @return affected lines
     */
    @Override
    public int insertDictData(SysDictDataBO data) {
        SysDictData sysDictData = BeanMapper.convert(data, SysDictData.class);
        int row = dictDataMapper.insertDictData(sysDictData);
        if (row > 0) {
            List<SysDictDataBO> dictDatas = BeanMapper.convertList(dictDataMapper.selectDictDataByType(data.getDictType()), SysDictDataBO.class);
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param data 字典数据信息
     * @return affected lines
     */
    @Override
    public int updateDictData(SysDictDataBO data) {
        SysDictData sysDictData = BeanMapper.convert(data, SysDictData.class);
        int row = dictDataMapper.updateDictData(sysDictData);
        if (row > 0) {
            List<SysDictDataBO> dictDatas = BeanMapper.convertList(dictDataMapper.selectDictDataByType(data.getDictType()), SysDictDataBO.class);
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }
}
