package com.dimple.project.tool.tool.service.impl;

import com.dimple.project.tool.tool.domain.Tool;
import com.dimple.project.tool.tool.mapper.BgToolMapper;
import com.dimple.project.tool.tool.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className:
 * @description: 常用工具(Tool)$desc Service实现层
 * @auther: Dimple
 * @date: 09/04/2019
 * @version: 1.1
 */
@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private BgToolMapper bgToolMapper;

    @Override
    public Tool selectToolById(Integer toolId) {
        return this.bgToolMapper.selectToolById(toolId);
    }

    @Override
    public List<Tool> selectToolList(Tool tool) {
        return this.bgToolMapper.selectToolList(tool);
    }

    @Override
    public int insertTool(Tool tool) {
        return bgToolMapper.insertTool(tool);
    }

    @Override
    public int updateTool(Tool tool) {
        return bgToolMapper.updateTool(tool);
    }

    @Override
    public int deleteToolByIds(Integer[] toolId) {
        return bgToolMapper.deleteToolByIds(toolId);
    }
}