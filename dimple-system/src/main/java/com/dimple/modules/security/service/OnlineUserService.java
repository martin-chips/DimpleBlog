package com.dimple.modules.security.service;

import com.dimple.modules.security.config.bean.SecurityProperties;
import com.dimple.modules.security.service.dto.JwtUserDTO;
import com.dimple.modules.security.service.dto.OnlineUserDTO;
import com.dimple.utils.EncryptUtils;
import com.dimple.utils.FileUtil;
import com.dimple.utils.IpUtil;
import com.dimple.utils.PageUtil;
import com.dimple.utils.RedisUtils;
import com.dimple.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @className: OnlineUserService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service
@Slf4j
public class OnlineUserService {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;

    public OnlineUserService(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    /**
     * 保存在线用户信息
     */
    public void save(JwtUserDTO jwtUserDto, String token, HttpServletRequest request) {
        String ip = IpUtil.getIp(request);
        String browser = IpUtil.getBrowser(request);
        String address = IpUtil.getCityInfo(ip);
        OnlineUserDTO onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDTO(jwtUserDto.getUsername(), jwtUserDto.getUser().getNickName(), browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDto, properties.getTokenValidityInSeconds() / 1000);
    }

    /**
     * 查询全部数据
     */
    public Map<String, Object> getAll(String filter, Pageable pageable) {
        List<OnlineUserDTO> onlineUserDTOS = getAll(filter);
        return PageUtil.toPage(
                PageUtil.toPage(pageable.getPageNumber(), pageable.getPageSize(), onlineUserDTOS),
                onlineUserDTOS.size()
        );
    }

    /**
     * 查询全部数据，不分页
     */
    public List<OnlineUserDTO> getAll(String filter) {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUserDTO> onlineUserDTOS = new ArrayList<>();
        for (String key : keys) {
            OnlineUserDTO onlineUserDto = (OnlineUserDTO) redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUserDto.toString().contains(filter)) {
                    onlineUserDTOS.add(onlineUserDto);
                }
            } else {
                onlineUserDTOS.add(onlineUserDto);
            }
        }
        onlineUserDTOS.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDTOS;
    }

    /**
     * 踢出用户
     */
    public void kickOut(String key) {
        key = properties.getOnlineKey() + key;
        redisUtils.del(key);
    }

    /**
     * 退出登录
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    /**
     * 导出
     */
    public void download(List<OnlineUserDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OnlineUserDTO user : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", user.getUserName());
            map.put("登录IP", user.getIp());
            map.put("登录地点", user.getAddress());
            map.put("浏览器", user.getBrowser());
            map.put("登录日期", user.getLoginTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 查询用户
     */
    public OnlineUserDTO getOne(String key) {
        return (OnlineUserDTO) redisUtils.get(key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     *
     * @param userName 用户名
     */
    public void checkLoginOnUser(String userName, String igoreToken) {
        List<OnlineUserDTO> onlineUserDTOS = getAll(userName);
        if (onlineUserDTOS == null || onlineUserDTOS.isEmpty()) {
            return;
        }
        for (OnlineUserDTO onlineUserDto : onlineUserDTOS) {
            if (onlineUserDto.getUserName().equals(userName)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUserDto.getKey());
                    if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
                        this.kickOut(token);
                    } else if (StringUtils.isBlank(igoreToken)) {
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

}
