package com.dimple.common.utils.ip;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.file.FileUtils;
import com.dimple.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @className: AddressUtils
 * @description: 获取地址类
 * @author: Dimple
 * @date: 10/22/19
 */
@Slf4j
public class AddressUtils {
    private AddressUtils() {
    }

    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    /**
     * call taobao api will get error like <code>{"msg":"the request over max qps for user ,the accessKey=public","code":4}</code>
     * this method has deprecated.
     *
     * @param ip ip location
     * @return location info
     */
    @Deprecated
    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
        if (StringUtils.isEmpty(rspStr)) {
            log.error("获取地理位置异常 {}", ip);
            return address;
        }
        JSONObject obj = JSON.parseObject(rspStr);
        JSONObject data = obj.getObject("data", JSONObject.class);
        String region = data.getString("region");
        String city = data.getString("city");
        address = region + " " + city;
        return address;
    }

    /**
     * 根据Ip使用ip2region进行查询地址
     *
     * @param ip ip地址
     * @return 地址信息
     */
    public static String getCityInfoByIp(String ip) {
        try {
            String path = "ip2region/ip2region.db";
            String name = "ip2region.db";
            DbConfig config = new DbConfig();
            File file = FileUtils.inputStreamToFile(new ClassPathResource(path).getInputStream(), name);
            DbSearcher searcher = new DbSearcher(config, file.getPath());
            Method method;
            method = searcher.getClass().getMethod("btreeSearch", String.class);
            DataBlock dataBlock;
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            String address = dataBlock.getRegion().replace("0|", "");
            if (address.charAt(address.length() - 1) == '|') {
                address = address.substring(0, address.length() - 1);
            }
            return address.equals("内网IP|内网IP") ? "内网IP" : address;
        } catch (Exception e) {
            log.error("Get Error In Get City Address ,{}", e.getMessage(), e);
        }
        return "";
    }
}
