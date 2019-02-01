package com.dimple.utils;

import com.dimple.framework.constant.PermissionConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @className: PermissionUntil
 * @description: 权限工具类
 * @auther: Owenb
 * @date: 01/31/19
 * @version: 1.0
 */
public class PermissionUntil {
    /**
     * 根据权限报错的信息判断是需要什么权限
     *
     * @param permissionErrorMsg 错误信息
     * @return
     */
    public static String getMsg(String permissionErrorMsg) {
        if (StringUtils.isBlank(permissionErrorMsg)) {
            return "";
        }

        String permisssion = StringUtils.substringBetween(permissionErrorMsg, "[", "]");
        String msg = MessageUtil.getMessage("auth.error.permission.default");
        if (StringUtils.endsWithIgnoreCase(permissionErrorMsg, PermissionConstants.INSERT_PERMISSION)) {

            msg = MessageUtil.getMessage("auth.error.permission.add", permisssion);

        } else if (StringUtils.endsWithIgnoreCase(permissionErrorMsg, PermissionConstants.DELETE_PERMISSION)) {

            msg = MessageUtil.getMessage("auth.error.permission.delete", permisssion);

        } else if (StringUtils.endsWithIgnoreCase(permissionErrorMsg, PermissionConstants.QUERY_PERMISSION)) {

            msg = MessageUtil.getMessage("auth.error.permission.query", permisssion);

        } else if (StringUtils.endsWithIgnoreCase(permissionErrorMsg, PermissionConstants.VIEW_PERMISSION)) {

            msg = MessageUtil.getMessage("auth.error.permission.view", permisssion);

        } else if (StringUtils.endsWithIgnoreCase(permissionErrorMsg, PermissionConstants.UPDATE_PERMISSION)) {

            msg = MessageUtil.getMessage("auth.error.permission.update", permisssion);

        }

        return msg;
    }
}
