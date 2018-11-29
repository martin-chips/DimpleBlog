package com.dimple.service;

import com.dimple.bean.OperatorLog;

/**
 * @ClassName: OperatorLogService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/29/18 11:19
 * @Version: 1.0
 */
public interface OperatorLogService {
    /**
     * 插入一条日志记录
     *
     * @param operatorLog 日志记录对象
     * @return sql执行的条数
     */
    Integer insertOperatorLog(OperatorLog operatorLog);
}
