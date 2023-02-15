package com.dimple.system.web.controller;

import com.dimple.common.core.constant.UserConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.file.FileTypeUtils;
import com.dimple.common.core.utils.file.MimeTypeUtils;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.service.TokenService;
import com.dimple.common.security.utils.SecurityUtils;
import com.dimple.system.api.RemoteFileService;
import com.dimple.system.api.model.LoginUser;
import com.dimple.system.api.model.SysFileBO;
import com.dimple.system.api.model.SysUserBO;
import com.dimple.system.service.service.SysUserService;
import com.dimple.system.web.controller.vo.params.SysUserVOParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * 个人信息 业务处理
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/user/profile")
public class SysProfileController extends BaseController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile() {
        String username = SecurityUtils.getUsername();
        SysUserBO user = userService.selectUserByUserName(username);
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(username));
        return ajax;
    }

    /**
     * 修改用户
     */
    @OperationLog(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUserVOParams user) {
        SysUserBO sysUserBO = BeanMapper.convert(user, SysUserBO.class);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUserBO sysUser = loginUser.getSysUser();
        sysUserBO.setUserName(sysUser.getUserName());
        if (StringUtils.isNotEmpty(sysUserBO.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(sysUserBO))) {
            return error("修改用户'" + sysUserBO.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(sysUserBO.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(sysUserBO))) {
            return error("修改用户'" + sysUserBO.getUserName() + "'失败，邮箱账号已存在");
        }
        sysUserBO.setUserId(sysUser.getUserId());
        sysUserBO.setPassword(null);
        sysUserBO.setAvatar(null);
        if (userService.updateUserProfile(sysUserBO) > 0) {
            // 更新缓存用户信息
            loginUser.getSysUser().setNickName(sysUserBO.getNickName());
            loginUser.getSysUser().setPhonenumber(sysUserBO.getPhonenumber());
            loginUser.getSysUser().setEmail(sysUserBO.getEmail());
            loginUser.getSysUser().setSex(sysUserBO.getSex());
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @OperationLog(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword) {
        String username = SecurityUtils.getUsername();
        SysUserBO user = userService.selectUserByUserName(username);
        String password = user.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(username, SecurityUtils.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            LoginUser loginUser = SecurityUtils.getLoginUser();
            loginUser.getSysUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @OperationLog(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) {
        if (!file.isEmpty()) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            String extension = FileTypeUtils.getExtension(file);
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
                return error("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
            }
            ResponseEntity<SysFileBO> fileResult = remoteFileService.upload(file);
            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData())) {
                return error("文件服务异常，请联系管理员");
            }
            String url = fileResult.getData().getUrl();
            if (userService.updateUserAvatar(loginUser.getUsername(), url)) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", url);
                // 更新缓存用户头像
                loginUser.getSysUser().setAvatar(url);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return error("上传图片异常，请联系管理员");
    }
}
