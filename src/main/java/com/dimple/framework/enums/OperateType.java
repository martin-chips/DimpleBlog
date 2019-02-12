package com.dimple.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: OperateType
 * @Description: 操作的类型
 * @Auther: Owenb
 * @Date: 12/05/18 14:12
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum OperateType {
    /**
     * 推动到百度站长平台
     */
    POSH_BAIDU(9, "推送到百度站长平台"),
    /**
     * 上传文件
     */
    FILE_UPLOAD(8, "文件上传"),
    /**
     * 切换状态
     */
    CHANGE_STATUS(7, "切换状态"),

    /**
     * 验证码生成
     */
    GENERATE_CAPTCHA(6, "验证码生成"),

    /**
     * 查询
     */
    SELECT(5, "查询"),

    /**
     * 其它
     */
    OTHER(0, "其他"),

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 更新
     */
    UPDATE(2, "更新"),

    /**
     * 删除
     */
    DELETE(3, "删除"),

    /**
     * 清空
     */
    CLEAN(4, "清空"),

    ;


    private Integer type;
    private String name;
}


