package com.dimple.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: IpUtil
 * @Description:
 * @Auther: Owenb
 * @Date: 12/03/18 20:15
 * @Version: 1.0
 */
@Slf4j
public class IpUtil {

    public static boolean internalIp(String ip) {
        byte[] addr = textToNumericFormatV4(ip);
        return internalIp(addr) || "127.0.0.1".equals(ip);
    }

    private static boolean internalIp(byte[] addr) {
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
     * 将IPv4地址转换成字节
     *
     * @param text
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
                    if ((l < 0L) || (l > 4294967295L))
                        return null;
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > 255L))
                        return null;
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > 16777215L))
                        return null;
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    for (i = 0; i < 2; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt(elements[2]);
                    if ((l < 0L) || (l > 65535L))
                        return null;
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    for (i = 0; i < 4; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L))
                            return null;
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

    private static InetAddress inetAddress;

    /**
     * 获取内网地址
     * <p>示例：dimple.tomcat.com/10.17.80.59</p>
     *
     * @return
     */
    public static InetAddress getLocalInetAddress() {
        if (inetAddress == null) {
            load();
        }
        return inetAddress;
    }

    /***
     * 获取IP地址
     * <p>示例：10.17.80.59</p>
     * @return
     */
    public static String getLocalHostAddress() {
        if (inetAddress == null) {
            load();
        }
        return inetAddress.getHostAddress();
    }

    /**
     * 获取主机名称
     * <P>示例：dimple</P>
     *
     * @return
     */
    public static String getLocalHostName() {
        if (inetAddress == null) {
            load();
        }
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return inetAddress.getHostName();
        }
    }

    private static InetAddress findValidateIp(List<IpUtil.Address> addresses) {
        InetAddress local = null;
        int size = addresses.size();
        int maxWeight = -1;

        for (int i = 0; i < size; i++) {
            IpUtil.Address address = addresses.get(i);
            if (address.isInet4Address()) {
                int weight = 0;

                if (address.isSiteLocalAddress()) {
                    weight += 8;
                }

                if (address.isLinkLocalAddress()) {
                    weight += 4;
                }

                if (address.isLoopbackAddress()) {
                    weight += 2;
                }

                if (address.hasHostName()) {
                    weight += 1;
                }

                if (weight > maxWeight) {
                    maxWeight = weight;
                    local = address.getAddress();
                }
            }
        }
        return local;
    }

    /**
     * 获取系统相关属性
     *
     * @param name 属性名称
     * @return
     */
    private static String getProperty(String name) {
        String value = null;
        value = System.getProperty(name);
        if (value == null) {
            value = System.getenv(name);
        }
        return value;
    }

    private static void load() {
        String ip = getProperty("host.ip");

        if (ip != null) {
            try {
                inetAddress = InetAddress.getByName(ip);
                return;
            } catch (Exception e) {
                System.err.println(e);
                // ignore
            }
        }

        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            List<IpUtil.Address> addresses = new ArrayList<IpUtil.Address>();
            InetAddress local = null;

            try {
                for (NetworkInterface ni : nis) {
                    if (ni.isUp() && !ni.isLoopback()) {
                        List<InetAddress> list = Collections.list(ni.getInetAddresses());

                        for (InetAddress address : list) {
                            addresses.add(new Address(address, ni));
                        }
                    }
                }
                local = findValidateIp(addresses);
            } catch (Exception e) {
                // ignore
            }
            inetAddress = local;
        } catch (SocketException e) {
            // ignore it
        }
    }

    static class Address {
        private InetAddress m_address;

        private boolean m_loopback;

        public Address(InetAddress address, NetworkInterface ni) {
            m_address = address;

            try {
                if (ni != null && ni.isLoopback()) {
                    m_loopback = true;
                }
            } catch (SocketException e) {
                // ignore it
            }
        }

        public InetAddress getAddress() {
            return m_address;
        }

        public boolean hasHostName() {
            return !m_address.getHostName().equals(m_address.getHostAddress());
        }

        public boolean isLinkLocalAddress() {
            return !m_loopback && m_address.isLinkLocalAddress();
        }

        public boolean isLoopbackAddress() {
            return m_loopback || m_address.isLoopbackAddress();
        }

        public boolean isSiteLocalAddress() {
            return !m_loopback && m_address.isSiteLocalAddress();
        }

        public boolean isInet4Address() {
            return m_address instanceof Inet4Address;
        }
    }
}
