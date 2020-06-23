package com.dimple.config;

import com.dimple.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * @className: AuditorConfig
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Component("auditorAware")
@Slf4j
public class AuditorConfig implements AuditorAware<String> {

    /**
     * 返回操作员标志信息
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            // 这里应根据实际业务情况获取具体信息
            return Optional.of(SecurityUtils.getCurrentUsername());
        } catch (Exception ignored) {
            log.error("ignore exception [{}],{}", ignored.getMessage(), ignored);
        }
        // 用户定时任务，或者无Token调用的情况
        return Optional.of("System");
    }
}
