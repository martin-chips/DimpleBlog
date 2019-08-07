package com.dimple.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dimple.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Optional;

/**
 * @className: QQUtil
 * @description:
 * @auther: Dimple
 * @date: 2019/7/17
 * @version: 1.0
 */
@Slf4j
public final class QQUtil {
    //QQ 头像和昵称查询地址
    private static final String QQ_QUERY_URL = "https://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg";

    public static class QQInfo implements Serializable {
        private long QQNum;
        private String nickName;
        private String avatar;
        private String email;

        private QQInfo(Builder builder) {
            this.QQNum = builder.QQNum;
            this.nickName = builder.nickName;
            this.avatar = builder.avatar;
            this.email = builder.email;
        }

        public static class Builder implements Serializable {
            private long QQNum;
            private String nickName = "匿名";
            private String avatar;
            private String email;

            public Builder QQNum(long QQNum) {
                this.QQNum = QQNum;
                return this;
            }

            public Builder nickName(String nickName) {
                this.nickName = nickName;
                return this;
            }

            public Builder avatar(String avatar) {
                this.avatar = avatar;
                return this;
            }

            public Builder email(String email) {
                this.email = email;
                return this;
            }

            public QQInfo build() {
                return new QQInfo(this);
            }
        }
    }

    /**
     * 根据QQ号码获取QQ信息
     *
     * @param QQNum qq号码
     * @return Optional
     */
    public static Optional<QQInfo> getQQByQQNum(long QQNum) {
        String json = HttpUtils.sendGet(QQ_QUERY_URL, "uins=" + QQNum);
        QQInfo qqInfo = null;
        if (!StringUtils.isEmpty(json)) {
            json = json.replaceAll("portraitCallBack|\\\\s*|\\t|\\r|\\n", "");
            json = json.substring(1, json.length() - 1);
            JSONObject object = JSONObject.parseObject(json);
            JSONArray array = object.getJSONArray(String.valueOf(QQNum));
            qqInfo = new QQInfo.Builder()
                    .nickName(array.getString(6))
                    .avatar("https://q1.qlogo.cn/g?b=qq&nk=" + QQNum + "&s=40")
                    .email(QQNum + "@qq.com")
                    .QQNum(QQNum)
                    .build();
        }
        return Optional.ofNullable(qqInfo);
    }
}
