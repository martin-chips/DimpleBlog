package com.dimple.project.monitor.swagger;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: TestController
 * @description: swagger 测试方法
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/test/*")
public class TestController extends BaseController {
    private final static List<Test> TEST_LIST = new ArrayList<>();

    {
        TEST_LIST.add(new Test("1", "admin", "admin123"));
        TEST_LIST.add(new Test("2", "ry", "admin123"));
    }

    @ApiOperation("获取列表")
    @GetMapping("list")
    public List<Test> testList() {
        return TEST_LIST;
    }

    @ApiOperation("新增用户")
    @PostMapping("save")
    public AjaxResult save(Test test) {
        return TEST_LIST.add(test) ? success() : error();
    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(name = "Test", value = "单个用户信息", dataType = "Test")
    @PutMapping("update")
    public AjaxResult update(Test test) {
        return TEST_LIST.remove(test) && TEST_LIST.add(test) ? success() : error();
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "Tests", value = "单个用户信息", dataType = "Test")
    @DeleteMapping("delete")
    public AjaxResult delete(Test test) {
        return TEST_LIST.remove(test) ? success() : error();
    }
}

class Test {
    private String userId;
    private String username;
    private String password;

    public Test() {

    }

    public Test(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Test test = (Test) o;

        return userId != null ? userId.equals(test.userId) : test.userId == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
