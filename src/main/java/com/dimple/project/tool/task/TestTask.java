package com.dimple.project.tool.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {

    public void run() {
        log.info("执行成功");
    }

    public String run1(String str) {
        log.info("执行成功，参数为： {}", str);
        return "这是返回值";
    }
}