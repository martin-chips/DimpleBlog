package com.dimple.project.tool.tool.service;

import com.dimple.project.tool.tool.domain.Tool;

import java.util.List;

/**
 * @className:
 * @description: 常用工具(Tool)$desc Service层
 * @auther: Dimple
 * @date: 09/04/2019
 * @version: 1.1
 */
public interface ToolService {

    /**
     * 通过ID查询单条数据
     *
     * @param toolId 主键
     * @return 实例对象
     */
    Tool selectToolById(Integer toolId);

    /**
     * 查询多条数据
     *
     * @param tool 带有查询条件的tool对象
     * @return 对象列表
     */
    List<Tool> selectToolList(Tool tool);

    /**
     * 新增数据
     *
     * @param tool 实例对象
     * @return 受影响的行数
     */
    int insertTool(Tool tool);

    /**
     * 修改数据
     *
     * @param tool 实例对象
     * @return 受影响的行数
     */
    int updateTool(Tool tool);

    /**
     * 通过主键删除数据
     *
     * @param toolId 主键
     * @return 受影响的行数
     */
    int deleteToolByIds(Integer[] toolId);

}