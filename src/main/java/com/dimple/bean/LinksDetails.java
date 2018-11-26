package com.dimple.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: LinksDetails
 * @Description: 首页用户详情
 * @Auther: Owenb
 * @Date: 11/26/18 18:14
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class LinksDetails {
    //友链的总数
    private Integer total;
    //死链的个数
    private Integer disabled;
    //未处理的链接的总数
    private Integer unHandled;
    //已经隐藏的友链的个数
    private Integer hide;
    //已经显示的友链的个数
    private Integer display;
}
