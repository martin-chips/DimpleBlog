package com.dimple.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dimple.common.utils.http.HttpUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

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

    @Data
    @AllArgsConstructor
    public static class QQInfo implements Serializable {
        private long QQNum;
        private String nickname;
        private String avatar;
        private String email;

    }

    /**
     * 根据QQ号码获取QQ信息
     *
     * @param QQNum qq号码
     * @return Optional
     */
    public static QQInfo getQQByQQNum(long QQNum) {
        String json = HttpUtils.sendGet(QQ_QUERY_URL, "uins=" + QQNum);
        //String json = "portraitCallBack({\"726567707\":[\"http://qlogo4.store.qq.com/qzone/726567707/726567707/100\",52779,-1,0,0,0,\"Dimple\",0]})";
        QQInfo qqInfo = null;
        if (!StringUtils.isEmpty(json)) {
            json = json.replaceAll("portraitCallBack|\\\\s*|\\t|\\r|\\n", "");
            json = json.substring(1, json.length() - 1);
            JSONObject object = JSONObject.parseObject(json);
            JSONArray array = object.getJSONArray(String.valueOf(QQNum));
            qqInfo = new QQInfo(QQNum, array.getString(6), "https://q1.qlogo.cn/g?b=qq&nk=" + QQNum + "&s=40", QQNum + "@qq.com");
        }
        return qqInfo;
    }


    public static void main(String[] args) {
        getQQByQQNum(726567707);
    }
}
