package com.dimple.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @className: EmailVO
 * @description: 发送邮件时，接收参数的类
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVO {

    /**
     * 收件人，支持多个收件人
     */
    @NotEmpty
    private List<String> tos;

    @NotBlank
    private String subject;

    @NotBlank
    private String content;
}
