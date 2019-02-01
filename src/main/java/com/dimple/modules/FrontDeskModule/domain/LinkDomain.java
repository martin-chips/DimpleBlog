package com.dimple.modules.FrontDeskModule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className: LinkDomain
 * @description:
 * @auther: Owenb
 * @date: 01/21/19 16:51
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class LinkDomain {
    private Integer linkId;
    private String title;
    private String url;
    private String description;
    private String headerUrl;
}
