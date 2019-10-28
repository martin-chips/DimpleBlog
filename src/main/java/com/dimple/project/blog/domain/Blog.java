package com.dimple.project.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @className: Blog
 * @description: 博客对象 bg_blog
 * @author: Dimple
 * @date: 2019-10-28
 */
@Data
@NoArgsConstructor
public class Blog extends BaseEntity{

        /** id */
            private Long id;

            /** 分类id */
                                                                    private Long categoryId;

            /** 文章标题 */
                                                                    private String title;

            /** 摘要 */
                                                                    private String summary;

            /** 封面图片地址 */
                                                                    private String headerImg;

            /** 正文内容 */
                                                                    private String content;

            /** 文章状态,1表示已经发表,2表示草稿箱 */
                                                                    private Integer status;

            /** 是否允许评论,1表示允许,0表示不允许 */
                                                                    private Integer comment;

                            /** $column.columnComment */
                                                                    private String deleteBy;

            /** $column.columnComment */
                                                                    private Date deleteTime;

    }
