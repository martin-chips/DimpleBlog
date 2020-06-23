package com.dimple.rest;

import com.dimple.annotation.OLog;
import com.dimple.domain.EmailConfig;
import com.dimple.domain.vo.EmailVO;
import com.dimple.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: EmailController
 * @description: 发送邮件
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/email")
@Api(tags = "工具：邮件管理")
public class EmailController {

    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<Object> queryConfig() {
        return new ResponseEntity<>(emailService.find(), HttpStatus.OK);
    }

    @OLog("配置邮件")
    @PutMapping
    @ApiOperation("配置邮件")
    public ResponseEntity<Object> updateConfig(@Validated @RequestBody EmailConfig emailConfig) throws Exception {
        emailService.config(emailConfig, emailService.find());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @OLog("发送邮件")
    @PostMapping
    @ApiOperation("发送邮件")
    public ResponseEntity<Object> sendEmail(@Validated @RequestBody EmailVO emailVo) {
        emailService.send(emailVo, emailService.find());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
