package com.dimple.modules.BackStageModule.SystemMonitor.job;

import com.dimple.modules.BackStageModule.LinkManager.bean.Link;
import com.dimple.modules.BackStageModule.LinkManager.service.LinksService;
import com.dimple.modules.BackStageModule.SystemMonitor.util.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @className: HelloWorldJob
 * @description: 业务Job
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
@Component("job")
@Slf4j
public class HelloWorldJob {

    @Value("${dimple.localUrl}")
    private String localUrl;

    @Autowired
    LinksService linksService;

    public void task() {
        log.info("Hello World!");
    }

    /**
     * 检查友链
     */
    public void checkLinkAvailable() {
        log.info("check links start");
        List<Link> linksAll = linksService.getAllLink();
        if (linksAll != null) {
            for (Link link : linksAll) {
                //获取其友链的地址
                String linkURl = link.getUrl();
                try {
                    boolean result = JobUtil.checkRemoteStationHasString(linkURl, localUrl);
                    //设置link的available字段
                    if (!result) {
                        linksService.changeAvailable(link.getLinkId(), false);
                    } else {
                        linksService.changeAvailable(link.getLinkId(), true);
                    }
                } catch (IOException e) {
                    log.error("处理友链： {}，检查有效性失败！", link.toString());
                    log.error("异常信息如下:{},{}", e.getStackTrace(), e.getMessage());
                }
            }
        }
        log.info("check links end");
    }

    /**
     * 自动处理申请的友链
     */
    public void autoHandleLink() {
        List<Link> allLinkUnhandled = linksService.getAllLinkUnhandled();
        if (allLinkUnhandled == null || allLinkUnhandled.size() == 0) {
            return;
        }
        for (Link link : allLinkUnhandled) {
            try {
                if (JobUtil.checkRemoteUrlAvailable(link.getUrl())) {
                    link.setHandle(true);
                } else {
                    link.setHandle(false);
                }
            } catch (IOException e) {
                throw new RuntimeException("当前友链自动处理失败:" + link.toString());
            }
        }
    }

    /**
     * 同步prod数据库到test数据库
     */
    public void syncDB() {
        log.info("sync prod data to test data");
    }

}
