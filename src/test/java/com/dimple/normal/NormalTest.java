package com.dimple.normal;

import com.dimple.utils.BaiduPushUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : NormalTest
 * @description :
 * @date : 01/02/19 15:28
 */

public class NormalTest {


    @Test
    public void testString() {
        String url = "http://localhost:8080/1/d0096ec6c83575373e3a21d129ff8fef.jpg";
        System.out.println(url.lastIndexOf("/imgs/"));
        System.out.println(url.substring(url.lastIndexOf("/imgs/")));
    }

    @Test
    public void pushBaidu() {
        String[] param = {
                "http://www.919zzz.com/detail/487aba6fe48d4a86b2f74d671293a65c.html",
                "http://www.919zzz.com/detail/de426adc1ee14d638bfab7faad923cec.html",
                "http://www.919zzz.com/detail/aed9a47637804da4bd34208291d699cc.html",
                "http://www.919zzz.com/detail/d996825aa0404aa5b70369d1695f4b51.html"//需要推送的网址
        };
        String post = BaiduPushUtil.Post("http://data.zz.baidu.com/urls?site=www.bianxiaofeng.com&token=LqRHUkKxK89qudvA", param);
        System.out.println(post);
    }


    @Test
    public void getBaiduHTML() {
        try {
            URL url = new URL("https://blog.csdn.net/qq_32454537/article/details/80686087");
            URLConnection URLconnection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.err.println("成功");
                InputStream in = httpConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader bufr = new BufferedReader(isr);
                String str;
                while ((str = bufr.readLine()) != null) {
                    System.out.println(str);
                    if (str.contains("边小丰")) {
                        System.out.println("找到了");
                        break;

                    }
                }
                bufr.close();
            } else {
                System.err.println("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
