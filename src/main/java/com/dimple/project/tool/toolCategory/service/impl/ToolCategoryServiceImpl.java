package com.dimple.project.tool.toolCategory.service.impl;

import com.dimple.project.tool.toolCategory.domain.ToolCategory;
import com.dimple.project.tool.toolCategory.mapper.ToolCategoryMapper;
import com.dimple.project.tool.toolCategory.service.ToolCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: ToolCategoryServiceImpl
 * @description: 工具的分类(ToolCategory)$desc Service实现层
 * @auther: Dimple
 * @date: 09/04/2019
 * @version: 1.1
 */
@Service
public class ToolCategoryServiceImpl implements ToolCategoryService {
    @Autowired
    private ToolCategoryMapper toolCategoryMapper;

    @Override
    public ToolCategory selectToolCategoryById(Integer toolCategoryId) {
        return this.toolCategoryMapper.selectToolCategoryById(toolCategoryId);
    }

    @Override
    public List<ToolCategory> selectToolCategoryList(ToolCategory toolCategory) {
        return this.toolCategoryMapper.selectToolCategoryList(toolCategory);
    }

    @Override
    public int insertToolCategory(ToolCategory toolCategory) {
        return toolCategoryMapper.insertToolCategory(toolCategory);
    }

    @Override
    public int updateToolCategory(ToolCategory toolCategory) {
        return toolCategoryMapper.updateToolCategory(toolCategory);
    }

    @Override
    public int deleteToolCategoryByIds(Integer[] toolCategoryId) {
        return toolCategoryMapper.deleteToolCategoryByIds(toolCategoryId);
    }
}