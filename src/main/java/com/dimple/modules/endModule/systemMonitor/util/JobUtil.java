package com.dimple.modules.endModule.systemMonitor.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @className: JobUtil
 * @description: 任务的工具类
 * @auther: Owenb
 * @date: 01/30/19
 * @version: 1.0
 */
@Slf4j
public class JobUtil {
    /**
     * 从远程网页获取网页数据
     *
     * @param urlStr
     * @return
     */
    public static String getContentFromRemoteUrl(String urlStr) throws IOException {
        if (StringUtils.isBlank(urlStr)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();

        URL url = new URL(urlStr);
        URLConnection URLconnection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
        int responseCode = httpConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            log.info("获取数据成功：{}", urlStr);
            InputStream in = httpConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            String str;
            while ((str = bufr.readLine()) != null) {
                stringBuffer.append(str);
            }
            bufr.close();
        } else {
            log.error("获取数据失败：{}", urlStr);
        }
        return stringBuffer.toString();
    }

    /**
     * 从远程的url检查是否有某一个字符串
     *
     * @param urlStr
     * @param checkStr
     * @return
     */
    public static boolean checkRemoteStationHasString(String urlStr, String checkStr) throws IOException {
        return getContentFromRemoteUrl(urlStr).contains(checkStr);
    }

    public static boolean checkRemoteUrlAvailable(String url) throws IOException {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        URL u = new URL(url);
        URLConnection urlConnection = u.openConnection();
        String string = urlConnection.getHeaderField(0);
        return string.contains("OK");

    }
}
