package com.dimple.project.system.domain;

import lombok.Data;

/**
 * @className: SiteSetting
 * @description: setting for front
 * @author: Dimple
 * @date: 01/08/20
 */
@Data
public class SiteSetting {
    /**
     * the title of front site
     */
    private String title;
    /**
     * icp of this site
     */
    private String icp;
    /**
     * description of the site
     */
    private String description;
    /**
     * copyright of this site
     */
    private String copyright;
    /**
     * description of copyright
     */
    private String copyrightDesc;
    /**
     * description of copyright with english
     */
    private String copyrightDescEn;
}
