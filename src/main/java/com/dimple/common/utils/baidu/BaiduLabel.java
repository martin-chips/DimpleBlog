package com.dimple.common.utils.baidu;


public enum BaiduLabel {
    RIOT_BAN(1, "暴恐违禁"),
    TEXT_PORN(2, "文本色情"),
    POLITICALLY_SENSITIVE(3, "政治敏感"),
    MALICIOUS_PROMOTION(4, "恶意推广"),
    LOW_INSULT(5, "低俗辱骂"),
    LOW_QUALITY_IRRIGATION(6, "低质灌水");

    private Integer code;
    private String desc;

    BaiduLabel(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据Code获取Desc
     *
     * @param code Code
     * @return Desc
     */
    public static String getDesc(Integer code) {
        for (BaiduLabel baiduLabel : BaiduLabel.values()) {
            if (code == baiduLabel.getCode()) {
                return baiduLabel.getDesc();
            }
        }
        return "";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

