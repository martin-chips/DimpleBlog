package com.dimple.project.system.service.impl;

import com.dimple.project.common.DimpleBlogContext;
import com.dimple.project.system.domain.SysDictData;
import com.dimple.project.system.mapper.SysDictDataMapper;
import com.dimple.project.system.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SysDictDataServiceImpl
 * @description: 字典 业务层处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class SysDictDataServiceImpl implements SysDictDataService {
    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }


    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }


    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    @Override
    public SysDictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    @Override
    public int deleteDictDataById(Long dictCode) {
        String loginUsername = DimpleBlogContext.getLoginUsername();
        return dictDataMapper.deleteDictDataById(dictCode, loginUsername);
    }

    @Override
    public int insertDictData(SysDictData dictData) {
        return dictDataMapper.insertDictData(dictData);
    }

    @Override
    public int updateDictData(SysDictData dictData) {
        return dictDataMapper.updateDictData(dictData);
    }
}
