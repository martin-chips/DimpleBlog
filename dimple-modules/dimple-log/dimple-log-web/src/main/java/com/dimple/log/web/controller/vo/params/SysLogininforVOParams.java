package com.dimple.log.web.controller.vo.params;

import com.dimple.common.core.annotation.Excel;
import com.dimple.common.core.annotation.Excel.ColumnType;
import com.dimple.common.core.web.vo.params.BaseVOParams;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 系统访问记录表 sys_logininfor
 *
 * @author Dimple
 */
@Data
public class SysLogininforVOParams extends BaseVOParams {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Excel(name = "序号", cellType = ColumnType.NUMERIC)
    private Long id;

    /**
     * 用户账号
     */
    @Excel(name = "用户账号")
    private String userName;

    /**
     * 状态 0成功 1失败
     */
    @Excel(name = "状态", readConverterExp = "0=成功,1=失败")
    private String status;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String ip;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String msg;

}