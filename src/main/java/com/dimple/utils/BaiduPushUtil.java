package com.dimple.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @className: BaiduPushUtil
 * @description: 百度推送的工具
 * @auther: Dimple
 * @date: 01/14/19 20:31
 * @version: 1.0
 */
@Slf4j
public class BaiduPushUtil {
    /**
     * 向百度提交的方法
     * 百度返回数据含义如下：（成功）
     * 1、success yes int  成功推送的url条数
     * 2、remain yes int 当天剩余可以推送的url条数
     * 3、not_same_site no array 由于不是本站url而未处理的列表
     * 4、not_valid no array 不合法的url列表
     * （失败）
     * 1、error yes int 错误码
     * 2、message yes string 错误描述
     *
     * @param PostUrl    百度提交的地址
     * @param Parameters 需要提交的网址
     * @return
     */
    public static String Post(String PostUrl, String[] Parameters) {
        if (null == PostUrl || null == Parameters || Parameters.length == 0) {
            return null;
        }
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            //建立URL之间的连接
            URLConnection conn = new URL(PostUrl).openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("Host", "data.zz.baidu.com");
            conn.setRequestProperty("User-Agent", "curl/7.12.1");
            conn.setRequestProperty("Content-Length", "83");
            conn.setRequestProperty("Content-Type", "text/plain");

            //发送POST请求必须设置如下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //获取conn对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数
            String param = "";
            for (String s : Parameters) {
                param += s + "\n";
            }
            out.print(param.trim());
            //进行输出流的缓冲
            out.flush();
            //通过BufferedReader输入流来读取Url的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (Exception e) {
            System.out.println("发送post请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
