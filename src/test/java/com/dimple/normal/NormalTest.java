package com.dimple.normal;

import com.dimple.utils.BaiduPushUtil;
import org.junit.Test;

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
        System.out.println(url.lastIndexOf("/images/"));
        System.out.println(url.substring(url.lastIndexOf("/images/")));
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
}
