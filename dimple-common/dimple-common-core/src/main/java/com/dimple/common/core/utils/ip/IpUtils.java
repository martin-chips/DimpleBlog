package com.dimple.common.core.utils.ip;

import com.dimple.common.core.utils.ServletUtils;
import com.dimple.common.core.utils.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.lionsoul.ip2region.xdb.Searcher;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * 获取IP方法
 *
 * @author Dimple
 */
@Slf4j
public class IpUtils {


    @SneakyThrows
    public static String getIpLocation(String ip) {
        if (internalIp(ip)) {
            return "内网IP";
        }
        IpLocationInfo ipLocationInfo = getIpLocationInfo(ip);
        return ipLocationInfo.getLocationInfo();
    }

    public static String getFriendlyIpLocation(String ip) {
        IpLocationInfo ipLocationInfo = getIpLocationInfo(ip);
        if (Objects.equals(ipLocationInfo.getCountry(), "中国")) {
            return ipLocationInfo.getProvince();
        }
        return StringUtils.format("{}-{}", ipLocationInfo.getCountry(), ipLocationInfo.getProvince());
    }

    public static String getFriendlyIpLocationByLocation(String location) {
        String[] split = location.split("-");
        if (split.length != 4) {
            return "未知地址";
        }
        String country = split[0];
        if (country.equals("中国")) {
            return StringUtils.format("{}", split[1].replace("省", "").replace("市", ""));
        }
        return StringUtils.format("{}-{}", country, split[1]);
    }

    public static IpLocationInfo getIpLocationInfo(String ip) {
        IpLocationInfo ipLocationInfo = new IpLocationInfo();
        try {
            InputStream stream = IpUtils.class.getResourceAsStream("/ip2region/ip2region.xdb");
            Searcher searcher = Searcher.newWithBuffer(IOUtils.toByteArray(stream));
            String region = searcher.search(ip);
            String[] ipRegionArr = region.split("\\|");
            if (ipRegionArr.length == 5) {
                ipLocationInfo.setCountry(ipRegionArr[0]);
                ipLocationInfo.setRegion(ipRegionArr[1]);
                ipLocationInfo.setProvince(ipRegionArr[2]);
                ipLocationInfo.setCity(ipRegionArr[3]);
                ipLocationInfo.setIsp(ipRegionArr[4]);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return ipLocationInfo;
    }

    public static void main(String[] args) {
        System.out.println(getIpLocation("220.196.160.51"));
    }

    public static String getServletIp() {
        return getIpAddr(ServletUtils.getRequest());
    }


    /**
     * 将IPv4地址转换成字节
     *
     * @param text IPv4地址
     * @return byte 字节
     */
    public static byte[] textToNumericFormatV4(String text) {
        if (text.length() == 0) {
            return null;
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try {
            long l;
            int i;
            switch (elements.length) {
                case 1:
                    l = Long.parseLong(elements[0]);
                    if ((l < 0L) || (l > 4294967295L)) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > 255L)) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > 16777215L)) {
                        return null;
                    }
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    for (i = 0; i < 2; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt(elements[2]);
                    if ((l < 0L) || (l > 65535L)) {
                        return null;
                    }
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    for (i = 0; i < 4; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    break;
                default:
                    return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return bytes;
    }

    /**
     * 检查是否为内部IP地址
     *
     * @param ip IP地址
     * @return affected lines
     */
    public static boolean internalIp(String ip) {
        byte[] addr = textToNumericFormatV4(ip);
        return internalIp(addr) || "127.0.0.1".equals(ip);
    }

    /**
     * 检查是否为内部IP地址
     *
     * @param addr byte地址
     * @return affected lines
     */
    private static boolean internalIp(byte[] addr) {
        if (StringUtils.isNull(addr) || addr.length < 2) {
            return true;
        }
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;
        }
    }


    /**
     * 获取客户端IP
     *
     * @param request 请求对象
     * @return IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        log.info("x-node-real-ip {}", request.getHeader("x-node-real-ip"));
        log.info("x-node-user-agent {}", request.getHeader("x-node-user-agent"));
        log.info("Proxy-Client-IP {}", request.getHeader("Proxy-Client-IP"));
        log.info("X-Forwarded-For {}", request.getHeader("X-Forwarded-For"));
        log.info("WL-Proxy-Client-IP {}", request.getHeader("WL-Proxy-Client-IP"));
        log.info("X-Real-IP {}", request.getHeader("X-Real-IP"));
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : getMultistageReverseProxyIp(ip);
    }


    /**
     * 获取IP地址
     *
     * @return 本地IP地址
     */
    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return "127.0.0.1";
    }

    /**
     * 获取主机名
     *
     * @return 本地主机名
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        return "未知";
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     */
    public static String getMultistageReverseProxyIp(String ip) {
        // 多级反向代理检测
        if (ip != null && ip.indexOf(",") > 0) {
            final String[] ips = ip.trim().split(",");
            for (String subIp : ips) {
                if (false == isUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关
     *
     * @param checkString 被检测的字符串
     * @return 是否未知
     */
    public static boolean isUnknown(String checkString) {
        return StringUtils.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
    }
}