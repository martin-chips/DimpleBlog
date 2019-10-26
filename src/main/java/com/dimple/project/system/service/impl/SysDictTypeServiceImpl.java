package com.dimple.project.system.service.impl;

import com.dimple.common.constant.UserConstants;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.common.DimpleBlogContext;
import com.dimple.project.system.domain.SysDictType;
import com.dimple.project.system.mapper.SysDictDataMapper;
import com.dimple.project.system.mapper.SysDictTypeMapper;
import com.dimple.project.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @className: SysDictTypeServiceImpl
 * @description: 字典 业务层处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    @Override
    public List<SysDictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    @Override
    public int deleteDictTypeById(Long dictId) {
        String loginUsername = DimpleBlogContext.getLoginUsername();
        return dictTypeMapper.deleteDictTypeById(dictId, loginUsername);
    }

    @Override
    public int insertDictType(SysDictType dictType) {
        return dictTypeMapper.insertDictType(dictType);
    }

    @Override
    @Transactional
    public int updateDictType(SysDictType dictType) {
        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        return dictTypeMapper.updateDictType(dictType);
    }

    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
