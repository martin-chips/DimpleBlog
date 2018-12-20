package com.dimple.controller;

import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : TagsController
 * @description :
 * @date : 12/19/18 19:19
 */
@Controller
public class TagsController {

    @GetMapping("/page/tags.html")
    public String tagsPage() {
        return "blog/tags/tags";
    }


    @GetMapping("/api/tags")
    @ResponseBody
    public Result testApi() {
        List<Map<String, Object>> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("createTime", new Date());
            map.put("name", UUID.randomUUID().toString().substring(0, 3));
            map.put("status", i % 3);
            list.add(map);
        }
        return ResultUtil.success(list);
    }

}
