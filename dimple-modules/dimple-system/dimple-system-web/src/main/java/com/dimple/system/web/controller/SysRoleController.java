package com.dimple.system.web.controller;

import com.dimple.common.core.constant.UserConstants;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.system.api.model.SysRoleBO;
import com.dimple.system.api.model.SysUserBO;
import com.dimple.system.service.service.SysRoleService;
import com.dimple.system.service.service.SysUserService;
import com.dimple.system.service.service.bo.SysUserRoleBO;
import com.dimple.system.web.controller.vo.SysRoleVO;
import com.dimple.system.web.controller.vo.SysUserVO;
import com.dimple.system.web.controller.vo.params.SysRoleVOParams;
import com.dimple.system.web.controller.vo.params.SysUserRoleVOParams;
import com.dimple.system.web.controller.vo.params.SysUserVOParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色信息
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserService userService;

    @RequiresPermissions("system:role:list")
    @GetMapping("/list")
    public TableDataInfo list(SysRoleVOParams role) {
        startPage();
        SysRoleBO sysRoleBO = BeanMapper.convert(role, SysRoleBO.class);
        List<SysRoleBO> list = roleService.selectRoleList(sysRoleBO);
        return getDataTable(BeanMapper.convertList(list, SysRoleVO.class));
    }

    @OperationLog(title = "角色管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:role:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRoleVOParams role) {
        SysRoleBO sysRoleBO = BeanMapper.convert(role, SysRoleBO.class);
        List<SysRoleBO> list = roleService.selectRoleList(sysRoleBO);
        ExcelUtil<SysRoleVO> util = new ExcelUtil<>(SysRoleVO.class);
        util.exportExcel(response, BeanMapper.convertList(list, SysRoleVO.class), "角色数据");
    }

    /**
     * 根据角色编号获取详细信息
     */
    @RequiresPermissions("system:role:query")
    @GetMapping("/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId) {
        roleService.checkRoleDataScope(roleId);
        return success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @RequiresPermissions("system:role:add")
    @OperationLog(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysRoleVOParams role) {
        SysRoleBO sysRoleBO = BeanMapper.convert(role, SysRoleBO.class);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(sysRoleBO))) {
            return error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(sysRoleBO))) {
            return error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        return toAjax(roleService.insertRole(sysRoleBO));

    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("system:role:edit")
    @OperationLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysRoleVOParams role) {
        SysRoleBO sysRoleBO = BeanMapper.convert(role, SysRoleBO.class);
        roleService.checkRoleAllowed(sysRoleBO);
        roleService.checkRoleDataScope(role.getRoleId());
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(sysRoleBO))) {
            return error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(sysRoleBO))) {
            return error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        return toAjax(roleService.updateRole(sysRoleBO));
    }

    /**
     * 修改保存数据权限
     */
    @RequiresPermissions("system:role:edit")
    @OperationLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody SysRoleVOParams role) {
        SysRoleBO sysRoleBO = BeanMapper.convert(role, SysRoleBO.class);
        roleService.checkRoleAllowed(sysRoleBO);
        roleService.checkRoleDataScope(role.getRoleId());
        return toAjax(roleService.authDataScope(sysRoleBO));
    }

    /**
     * 状态修改
     */
    @RequiresPermissions("system:role:edit")
    @OperationLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysRoleVOParams role) {
        SysRoleBO sysRoleBO = BeanMapper.convert(role, SysRoleBO.class);
        roleService.checkRoleAllowed(sysRoleBO);
        roleService.checkRoleDataScope(role.getRoleId());
        return toAjax(roleService.updateRoleStatus(sysRoleBO));
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("system:role:remove")
    @OperationLog(title = "角色管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds) {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取角色选择框列表
     */
    @RequiresPermissions("system:role:query")
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        return success(roleService.selectRoleAll());
    }

    /**
     * 查询已分配用户角色列表
     */
    @RequiresPermissions("system:role:list")
    @GetMapping("/authUser/allocatedList")
    public TableDataInfo allocatedList(SysUserVOParams user) {
        startPage();
        SysUserBO sysUserBO = BeanMapper.convert(user, SysUserBO.class);
        List<SysUserBO> list = userService.selectAllocatedList(sysUserBO);
        return getDataTable(BeanMapper.convertList(list, SysUserVO.class));
    }

    /**
     * 查询未分配用户角色列表
     */
    @RequiresPermissions("system:role:list")
    @GetMapping("/authUser/unallocatedList")
    public TableDataInfo unallocatedList(SysUserVOParams user) {
        startPage();
        SysUserBO sysUserBO = BeanMapper.convert(user, SysUserBO.class);
        List<SysUserBO> list = userService.selectUnallocatedList(sysUserBO);
        return getDataTable(BeanMapper.convertList(list, SysUserVO.class));
    }

    /**
     * 取消授权用户
     */
    @RequiresPermissions("system:role:edit")
    @OperationLog(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancel")
    public AjaxResult cancelAuthUser(@RequestBody SysUserRoleVOParams userRole) {
        SysUserRoleBO sysUserRoleBO = BeanMapper.convert(userRole, SysUserRoleBO.class);
        return toAjax(roleService.deleteAuthUser(sysUserRoleBO));
    }

    /**
     * 批量取消授权用户
     */
    @RequiresPermissions("system:role:edit")
    @OperationLog(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancelAll")
    public AjaxResult cancelAuthUserAll(Long roleId, Long[] userIds) {
        return toAjax(roleService.deleteAuthUsers(roleId, userIds));
    }

    /**
     * 批量选择用户授权
     */
    @RequiresPermissions("system:role:edit")
    @OperationLog(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/selectAll")
    public AjaxResult selectAuthUserAll(Long roleId, Long[] userIds) {
        roleService.checkRoleDataScope(roleId);
        return toAjax(roleService.insertAuthUsers(roleId, userIds));
    }
}
