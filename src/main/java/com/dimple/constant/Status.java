package com.dimple.constant;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @ClassName: Status
 * @Description: 状态
 * @Auther: Owenb
 * @Date: 11/29/18 10:52
 * @Version: 1.0
 */
public class Status {
    /**
     * 成功
     */
    public static final Boolean SUCCESS = true;
    /**
     * 失败
     */
    public static final Boolean Failure = false;

    /**
     * 其它
     */
    public static final String OTHER = "0";
    /**
     * 新增
     */
    public static final String INSERT = "1";
    /**
     * 修改
     */
    public static final String UPDATE = "2";
    /**
     * 保存
     */
    public static final String SAVE = "3";
    /**
     * 删除
     */
    public static final String DELETE = "4";
    /**
     * 授权
     */
    public static final String GRANT = "5";
    /**
     * 导出
     */
    public static final String EXPORT = "6";
    /**
     * 导入
     */
    public static final String IMPORT = "7";
    /**
     * 强退
     */
    public static final String FORCE = "8";
    /**
     * 禁止访问
     */
    public static final String FORBID = "9";
}
