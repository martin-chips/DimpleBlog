package com.dimple.monitor.web.controller;

import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.redis.constants.CacheConstants;
import com.dimple.common.redis.service.RedisService;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.monitor.service.service.SysUserOnlineService;
import com.dimple.monitor.service.service.impl.bo.SysUserOnlineBO;
import com.dimple.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 在线用户监控
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/online")
public class SysUserOnlineController extends BaseController {
    @Autowired
    private SysUserOnlineService userOnlineService;

    @Autowired
    private RedisService redisService;

    @RequiresPermissions("monitor:online:list")
    @GetMapping("/list")
    public TableDataInfo list(String ip, String userName) {
        Collection<String> keys = redisService.keys(CacheConstants.LOGIN_TOKEN_KEY_DEFINE.formatKey("*"));
        List<SysUserOnlineBO> userOnlineList = new ArrayList<>();
        for (String key : keys) {
            LoginUser user = redisService.getCacheObject(key);
            if (StringUtils.isNotEmpty(ip) && StringUtils.isNotEmpty(userName)) {
                if (StringUtils.equals(ip, user.getIp()) && StringUtils.equals(userName, user.getUsername())) {
                    userOnlineList.add(userOnlineService.selectOnlineByInfo(ip, userName, user));
                }
            } else if (StringUtils.isNotEmpty(ip)) {
                if (StringUtils.equals(ip, user.getIp())) {
                    userOnlineList.add(userOnlineService.selectOnlineByip(ip, user));
                }
            } else if (StringUtils.isNotEmpty(userName)) {
                if (StringUtils.equals(userName, user.getUsername())) {
                    userOnlineList.add(userOnlineService.selectOnlineByUserName(userName, user));
                }
            } else {
                userOnlineList.add(userOnlineService.loginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        return getDataTable(userOnlineList);
    }

    /**
     * 强退用户
     */
    @RequiresPermissions("monitor:online:forceLogout")
    @OperationLog(title = "在线用户", businessType = BusinessType.FORCE)
    @DeleteMapping("/{tokenId}")
    public AjaxResult forceLogout(@PathVariable String tokenId) {
        redisService.deleteObject(CacheConstants.LOGIN_TOKEN_KEY_DEFINE.formatKey(tokenId));
        return success();
    }
}
