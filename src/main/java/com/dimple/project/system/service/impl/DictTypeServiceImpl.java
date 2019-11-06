package com.dimple.project.system.service.impl;

import com.dimple.common.constant.UserConstants;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.system.domain.DictType;
import com.dimple.project.system.mapper.DictDataMapper;
import com.dimple.project.system.mapper.DictTypeMapper;
import com.dimple.project.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @className: DictTypeServiceImpl
 * @description: 字典 业务层处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public List<DictType> selectDictTypeList(DictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    @Override
    public List<DictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    @Override
    public DictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    @Override
    public DictType selectDictTypeByType(String dictType) {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    @Override
    public int deleteDictTypeById(Long dictId) {
        String loginUsername = SecurityUtils.getUsername();
        return dictTypeMapper.deleteDictTypeById(dictId, loginUsername);
    }

    @Override
    public int insertDictType(DictType dictType) {
        return dictTypeMapper.insertDictType(dictType);
    }

    @Override
    @Transactional
    public int updateDictType(DictType dictType) {
        DictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        return dictTypeMapper.updateDictType(dictType);
    }

    @Override
    public String checkDictTypeUnique(DictType dict) {
        Long dictId = StringUtils.isNull(dict.getId()) ? -1L : dict.getId();
        DictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getId().longValue() != dictId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
