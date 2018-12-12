package com.dimple.enums;

/**
 * @ClassName: BlogStatus
 * @Description:
 * @Auther: Owenb
 * @Date: 12/12/18 15:02
 * @Version: 1.0
 */
public enum BlogStatus {
    /**
     * 已发布
     */
    PUBLISHED(1),
    /**
     * 草稿箱
     */
    DRAFT(2),
    /**
     * 垃圾箱
     */
    DUSTBIN(3);

    private Integer code;

    BlogStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }}

    