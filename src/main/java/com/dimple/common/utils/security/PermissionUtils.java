package com.dimple.common.utils.security;

import org.apache.commons.lang3.StringUtils;
import com.dimple.common.constant.PermissionConstants;
import com.dimple.common.utils.MessageUtils;

/**
 * @className: PermissionUtils
 * @description: permission 工具类
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class PermissionUtils {
    /**
     * 权限错误消息提醒
     *
     * @param permissionsStr 错误信息
     * @return
     */
    public static String getMsg(String permissionsStr) {
        String permission = StringUtils.substringBetween(permissionsStr, "[", "]");
        String msg = MessageUtils.message("no.view.permission", permission);
        if (StringUtils.endsWithIgnoreCase(permission, PermissionConstants.ADD_PERMISSION)) {
            msg = MessageUtils.message("no.create.permission", permission);
        } else if (StringUtils.endsWithIgnoreCase(permission, PermissionConstants.EDIT_PERMISSION)) {
            msg = MessageUtils.message("no.update.permission", permission);
        } else if (StringUtils.endsWithIgnoreCase(permission, PermissionConstants.REMOVE_PERMISSION)) {
            msg = MessageUtils.message("no.delete.permission", permission);
        } else if (StringUtils.endsWithIgnoreCase(permission, PermissionConstants.EXPORT_PERMISSION)) {
            msg = MessageUtils.message("no.export.permission", permission);
        } else if (StringUtils.endsWithAny(permission, new String[]{PermissionConstants.VIEW_PERMISSION, PermissionConstants.LIST_PERMISSION})) {
            msg = MessageUtils.message("no.view.permission", permission);
        }
        return msg;
    }
}
