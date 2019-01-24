package com.dimple.modules.frontModule.front.controller;

import com.dimple.framework.log.annotation.VLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: LeaveCommentController
 * @description:
 * @auther: Owenb
 * @date: 01/23/19 17:21
 * @version: 1.0
 */
@Controller
public class LeaveCommentController {


    @GetMapping("/leaveComment.html")
    @VLog(title = "留言")
    public String leaveCommentPage() {
        return "front/leaveComment";
    }

}
