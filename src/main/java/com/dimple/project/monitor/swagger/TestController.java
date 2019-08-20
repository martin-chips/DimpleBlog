package com.dimple.project.monitor.swagger;

import com.dimple.common.utils.poi.ExcelUtil;
import com.dimple.framework.interceptor.annotation.NoRepeatSubmit;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.log.logininfor.domain.Logininfor;
import com.dimple.project.log.logininfor.service.ILogininforService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
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
@Controller
@RequestMapping("/test/")
@Slf4j
public class TestController extends BaseController {
    private final static List<Test> TEST_LIST = new ArrayList<>();

    {
        TEST_LIST.add(new Test("1", "admin", "admin123"));
        TEST_LIST.add(new Test("2", "ry", "admin123"));
    }

    @Autowired
    ILogininforService logininforService;


    @GetMapping("/export")
    @ResponseBody
    public void export() {
        List<Logininfor> logininfors = logininforService.selectLogininforList(new Logininfor());
        ExcelUtil<Logininfor> logininforExcelUtil = new ExcelUtil<>(Logininfor.class);
        logininforExcelUtil.exportExcel(logininfors, "ss");
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

    @GetMapping("/noRepeatSubmit/ajax")
    @NoRepeatSubmit(intervalSecond = 20)
    @ResponseBody
    public AjaxResult noRepeatSubmitAjax(String name) {
        return AjaxResult.success(name);
    }

    @GetMapping("/noRepeatSubmit/page")
    @NoRepeatSubmit(intervalSecond = 20)
    public String noRepeatSubmitPage(String name) {
        System.out.println("在Page里面收到name" + name);
        return "blog/blog/blog";
    }

    @GetMapping("/upload")
    public String upload() {
        return "test1";
    }

    @GetMapping("/sendDataToCSDN")
    @ResponseBody
    public String sendDataToCSDN() {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            String urlNameString = "https://mp.csdn.net/postedit/saveArticle?isPub=1" + "&" + "titl=test&typ=1&resourceUrl=&cont=%3Cp%3Etttt%3C%2Fp%3E%0A&categories=blog&chnl=31&level=0&tag2=1213%2C121&artid=0&readtype=public&authorized_status=false&original_link=&stat=publish";
            log.info("sendPost - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("cookie", "uuid_tt_dd=10_8042633310-1564712448092-416041; dc_session_id=10_1564712448092.596137; hasSub=true; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1564970899,1564971380,1564971429,1564971465; c-login-auto=15; c_adb=1; SESSION=d8c4960d-8842-402c-b049-37072dac8131; UserName=qq_32454537; UserInfo=6fc6efd3afd546afa980b67a7e84eb50; UserToken=6fc6efd3afd546afa980b67a7e84eb50; UserNick=DimpleMe; AU=0D4; UN=qq_32454537; BT=1564989801362; p_uid=U000000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_8042633310-1564712448092-416041!5744*1*qq_32454537; TINGYUN_DATA=%7B%22id%22%3A%22-sf2Cni530g%23HL5wvli0FZI%22%2C%22n%22%3A%22WebAction%2FCI%2Fconfigure%22%2C%22tid%22%3A%22ce584778cf7fc0%22%2C%22q%22%3A0%2C%22a%22%3A14%7D; dc_tos=pvr5vw; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1564989837");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("recv - {}", result);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                //log.error("transfer in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
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
