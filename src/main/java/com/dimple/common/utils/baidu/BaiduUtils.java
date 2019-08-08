package com.dimple.common.utils.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dimple.common.utils.HttpUtilNew;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.framework.config.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 获取token类
 */
@Slf4j
public class BaiduUtils {
    private static final String URL = "https://aip.baidubce.com/rest/2.0/antispam/v2/spam";
    private static final String SCORE = "score";
    private static final String HIT = "hit";
    private static final String LABEL = "label";
    private static ServerConfig serverConfig = SpringUtils.getBean(ServerConfig.class);

    /**
     * 获取权限token
     *
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "asFku0ak7ObQVM8gq6KZLnmB";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "eYCvzbe7vbdBvMyY9tnYtFCKleGoTL1g";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            log.error("get Token fail ,{},{}", e.getMessage(), e);
        }
        return null;
    }

    public static String checkText(String content) {
        String access_token = serverConfig.getBaiduAIAccessToken();
        Map<String, String> params = new HashedMap();
        //添加调用参数
        params.put("access_token", access_token);
        params.put("content", content);
        //调用文本审核接口
        String result = HttpUtilNew.doFormPost(URL, params);
        log.info("check [{}] response {}", content, result);
        // JSON转换
        JSONObject jsonObj = JSON.parseObject(result);
        JSONObject resultJson = jsonObj.getJSONObject("result");
        //获取一定有问题的语句
        JSONArray reject = resultJson.getJSONArray("reject");
        String msg = "";

        for (Object o : reject) {
            Map<String, Object> map = (Map<String, Object>) o;
            String hit = map.get(HIT).toString();
            String label = BaiduLabel.getDesc((Integer) map.get(LABEL));
            String score = new BigDecimal(String.valueOf(map.get(SCORE))).setScale(2, RoundingMode.HALF_UP).toString();

            log.warn("Hit {},seem {},score {}", hit, label, score);
            if ("[]".equals(hit)) {
                msg = StringUtils.format("语句涉嫌{},置信度{}", label, score);
            } else {
                msg = StringUtils.format("词语{}涉嫌{},置信度{}", hit, label, score);
            }
        }
        return msg;
    }

    public static void main(String[] args) {
        System.out.println(checkText("暴恐违禁\t默认开启，高级设置可选择关闭\n" +
                "2\t文本色情\t默认开启，高级设置可选傻逼东西择关闭\n" +
                "3\t政治敏感\t默认开启，高级设置可选择给你一刀关闭\n" +
                "4\t恶意推广\t默认开启，高级设俄日你妈置可选择关闭\n" +
                "5\t低俗辱骂\t默认开启，高级设置可选择关闭\n" +
                "6\t低质灌水\t默认关闭，高级设置可选择开启\n"));
    }
}