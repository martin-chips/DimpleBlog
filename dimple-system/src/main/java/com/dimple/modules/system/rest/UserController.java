package com.dimple.modules.system.rest;

import com.dimple.annotation.OLog;
import com.dimple.config.RsaProperties;
import com.dimple.exception.BadRequestException;
import com.dimple.modules.system.domain.User;
import com.dimple.modules.system.domain.vo.UserPassVO;
import com.dimple.modules.system.service.RoleService;
import com.dimple.modules.system.service.UserService;
import com.dimple.modules.system.service.VerifyService;
import com.dimple.modules.system.service.dto.RoleSmallDTO;
import com.dimple.modules.system.service.dto.UserDTO;
import com.dimple.modules.system.service.dto.UserQueryCriteria;
import com.dimple.utils.RsaUtils;
import com.dimple.utils.SecurityUtils;
import com.dimple.utils.enums.CodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @className: UserController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final VerifyService verificationCodeService;

    @OLog("导出用户数据")
    @ApiOperation("导出用户数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check('user:list')")
    public void download(HttpServletResponse response, UserQueryCriteria criteria) throws IOException {
        userService.download(userService.queryAll(criteria), response);
    }

    @OLog("查询用户")
    @ApiOperation("查询用户")
    @GetMapping
    @PreAuthorize("@ps.check('user:list')")
    public ResponseEntity<Object> query(UserQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(userService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @OLog("新增用户")
    @ApiOperation("新增用户")
    @PostMapping
    @PreAuthorize("@ps.check('user:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody User resources) {
        checkLevel(resources);
        // 默认密码 123456
        resources.setPassword(passwordEncoder.encode("123456"));
        userService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @OLog("修改用户")
    @ApiOperation("修改用户")
    @PutMapping
    @PreAuthorize("@ps.check('user:edit')")
    public ResponseEntity<Object> update(@Validated(User.Update.class) @RequestBody User resources) {
        checkLevel(resources);
        userService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("修改用户：个人中心")
    @ApiOperation("修改用户：个人中心")
    @PutMapping(value = "center")
    public ResponseEntity<Object> center(@Validated(User.Update.class) @RequestBody User resources) {
        if (!resources.getId().equals(SecurityUtils.getCurrentUserId())) {
            throw new BadRequestException("不能修改他人资料");
        }
        userService.updateCenter(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("删除用户")
    @ApiOperation("删除用户")
    @DeleteMapping
    @PreAuthorize("@ps.check('user:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        for (Long id : ids) {
            Integer currentLevel = Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDTO::getLevel).collect(Collectors.toList()));
            Integer optLevel = Collections.min(roleService.findByUsersId(id).stream().map(RoleSmallDTO::getLevel).collect(Collectors.toList()));
            if (currentLevel > optLevel) {
                throw new BadRequestException("角色权限不足，不能删除：" + userService.findById(id).getUsername());
            }
        }
        userService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/updatePass")
    public ResponseEntity<Object> updatePass(@RequestBody UserPassVO passVo) throws Exception {
        String oldPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, passVo.getOldPass());
        String newPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, passVo.getNewPass());
        UserDTO user = userService.findByName(SecurityUtils.getCurrentUsername());
        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
            throw new BadRequestException("修改失败，旧密码错误");
        }
        if (passwordEncoder.matches(newPass, user.getPassword())) {
            throw new BadRequestException("新密码不能与旧密码相同");
        }
        userService.updatePass(user.getUsername(), passwordEncoder.encode(newPass));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("修改头像")
    @PostMapping(value = "/updateAvatar")
    public ResponseEntity<Object> updateAvatar(@RequestParam MultipartFile avatar) {
        return new ResponseEntity<>(userService.updateAvatar(avatar), HttpStatus.OK);
    }

    @OLog("修改邮箱")
    @ApiOperation("修改邮箱")
    @PostMapping(value = "/updateEmail/{code}")
    public ResponseEntity<Object> updateEmail(@PathVariable String code, @RequestBody User user) throws Exception {
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, user.getPassword());
        UserDTO userDto = userService.findByName(SecurityUtils.getCurrentUsername());
        if (!passwordEncoder.matches(password, userDto.getPassword())) {
            throw new BadRequestException("密码错误");
        }
        verificationCodeService.validated(CodeEnum.EMAIL_RESET_EMAIL_CODE.getKey() + user.getEmail(), code);
        userService.updateEmail(userDto.getUsername(), user.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 如果当前用户的角色级别低于创建用户的角色级别，则抛出权限不足的错误
     *
     * @param resources /
     */
    private void checkLevel(User resources) {
        Integer currentLevel = Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDTO::getLevel).collect(Collectors.toList()));
        Integer optLevel = roleService.findByRoles(resources.getRoles());
        if (currentLevel > optLevel) {
            throw new BadRequestException("角色权限不足");
        }
    }
}
