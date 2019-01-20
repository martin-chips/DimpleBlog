package com.dimple.modules.common.controller;

import com.dimple.modules.common.bean.server.UserOnline;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.common.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : SessionController
 * @description :
 * @date : 01/09/19 21:03
 */
@Controller
public class SessionController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/online/list")
    @ResponseBody
    public Result getOnlineList() {
        List<UserOnline> list = sessionService.getOnlineList();
        return ResultUtil.success(list);
    }

    @GetMapping("/online/{sessionId}")
    @ResponseBody
    public Result forceLogout(@PathVariable String sessionId[]) {
        sessionService.forceLogout(sessionId);
        return ResultUtil.success();
    }


}
