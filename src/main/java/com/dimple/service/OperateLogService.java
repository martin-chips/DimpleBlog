package com.dimple.service;

import com.dimple.bean.OperateLog;
import com.dimple.enums.OperateType;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: OperateLogService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/29/18 11:19
 * @Version: 1.0
 */
public interface OperateLogService {
    /**
     * 插入一条日志记录
     *
     * @param operateLog 日志记录对象
     * @return sql执行的条数
     */
    Integer insertOperatorLog(OperateLog operateLog);

    /**
     * 查询操作日志记录
     *
     * @param title
     * @param operatorName
     * @param operateType
     * @param startTime
     * @param endTime
     * @return
     */
    List<OperateLog> getAllOperateLog(String title, String operatorName, OperateType operateType, Date startTime, Date endTime);

    /**
     * 删除操作日志记录
     *
     * @param ids
     * @return
     */
    Integer deleteOperateLog(Integer[] ids);

    /**
     * 清空操作日志
     *
     * @return
     */
    Integer cleanOperateLog();

    /**
     * 获取某个OperateLog的详细
     *
     * @param id
     * @return
     */
    OperateLog getDetailsOperateLog(Integer id);

    /**
     * 获取所有的操作类型
     *
     * @return
     */
    Map<Integer, String> getOperateType();
}
