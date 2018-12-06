package com.dimple.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;

import java.io.*;
import java.net.*;

/**
 * @ClassName: AddressUtil
 * @Description: 根据IP地址获取详细的地理位置信息，使用淘宝API接口
 * @Auther: Owenb
 * @Date: 11/29/18 11:01
 * @Version: 1.0
 */
@Slf4j
public class AddressUtil {

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    /**
     * 获取查询结果
     *
     * @param url   像指定的URl发送POST请求
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return
     */
    private static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            String urlNameString = url + "?" + param;
            log.info("sendPost - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("recv - {}", result);
        } catch (ConnectException e) {
            log.error("调用sendPost ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用sendPost IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用sendPost Exception, url=" + url + ",param=" + param, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("调用in.close Exception, url=" + url + ",param=" + param, e);
            }
        }
        return result.toString();
    }

    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";
        if (IpUtil.internalIp(ip)) {
            return "内网IP";
        }
        String response = sendPost(IP_URL, "ip=" + ip);
        if (StringUtils.isBlank(response)) {
            log.error("获取地理位置异常{}", ip);
            return address;
        }
        JsonParser jsonParser = new JsonParser();
        //使用JsonParser解析Json树

        JsonObject jsonObject = null;
        try {
            jsonObject = (JsonObject) jsonParser.parse(response);
            log.info(jsonObject.toString());
            String code = jsonObject.get("code").getAsString();
            //只有成功的时候进行解析
            if (Integer.parseInt(code) == 0) {
                JsonObject data = jsonObject.get("data").getAsJsonObject();
                String country = data.get("country").getAsString();
                String region = data.get("region").getAsString();
                String city = data.get("city").getAsString();
                address = country + " " + region + " " + city;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("解析数据出错！{}", jsonObject);
        }
        return address;
    }
}
