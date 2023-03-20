use `dimple-cloud`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

drop table if exists blog_article;
create table blog_article
(
    id             bigint auto_increment
        primary key,
    title          varchar(255) not null comment 'article title',
    header_image   varchar(255) not null default '' comment 'article header image',
    content        text         not null comment 'article content',
    category_id    bigint                default -1 not null comment 'article category id',
    original       tinyint      not null default 1 comment 'is original, 1 yes, 0 no',
    summary        varchar(512) not null default '' comment 'article summary,max length 512',
    article_status tinyint      not null default 1 comment 'article status, 1 is published, 2 is draft',
    like_count     bigint       not null default 0 comment 'article like count',
    create_by      varchar(64)  null,
    create_time    datetime     null,
    update_by      varchar(50)  null,
    update_time    datetime     null
)
    comment 'Blog article';

insert into blog_article (id, title, header_image, content, category_id, original, summary, article_status, like_count,
                          create_by, create_time, update_by, update_time)
values (-2000, '关于我', 'http://rrfyveqgo.hn-bkt.clouddn.com/lake-g57a1ac87a_1920.jpgd85e5.jpg', '<p align="center"><a href="https://www.bianxf.com">Hello, I''m Martin. I do open source!</a></p>

<br />


I''m a passionate developer from China

**About me**

- ? Java Engineer

- ? The only sustainable competitive advantage is the ability to learn over competitors.

- ❤️ I love Coding, and building fun experiments

- ? Ask me about anything [here](https://bianxf.com/app/aboutme)

| <a href="https://bianxf.com"><img align="center" src="https://github-readme-stats.vercel.app/api?username=martin-chips&show_icons=true&include_all_commits=true&theme=buefy&hide_border=true" alt="Martin''s github stats" /></a> | <a href="https://bianxf.com"><img align="center" src="https://github-readme-stats.vercel.app/api/top-langs/?username=martin-chips&layout=compact&theme=buefy&hide_border=true" /></a> |
| ------------- | ------------- |

#### Top Repositories
| <a href="https://github.com/martin-chips/DimpleBlog"><img align="center" src="https://github-readme-stats.vercel.app/api/pin/?username=martin-chips&repo=DimpleBlog&theme=buefy"/></a> | <a href="https://github.com/martin-chips/EAMS"><img align="center" src="https://github-readme-stats.vercel.app/api/pin/?username=martin-chips&repo=EAMS&theme=buefy" /></a> |
| ------------- | ------------- |
|<a href="https://github.com/martin-chips/online_exam_system"><img align="center" src="https://github-readme-stats.vercel.app/api/pin/?username=martin-chips&repo=online_exam_system&theme=buefy" /></a>||

<br />
<br />
', 0, 1, '', 1, 0, 'Dimple', sysdate(), null, null);
drop table if exists blog_category;
create table blog_category
(
    id          bigint auto_increment primary key,
    title       varchar(255) not null comment 'category title, max length is 255',
    create_by   varchar(50)  null,
    create_time datetime     null,
    update_by   varchar(55)  null,
    update_time datetime     null
)
    comment 'blog category';

drop table if exists blog_comment;
create table blog_comment
(
    id          bigint auto_increment primary key,
    article_id  bigint                  not null comment 'comment article id',
    username    varchar(255)            not null comment 'user name',
    parent_id   bigint       default -1 not null comment 'parent comment is, default is -1',
    avatars     varchar(255)            null comment 'user head image',
    content     varchar(1024)           not null comment 'comment content, max length is 1024',
    email       varchar(128)            null comment 'user email, if email is not null will reply when the comment has been replied',
    reply_id    bigint       default -1 comment '',
    link        varchar(255) default '' comment '',
    visitor_id  bigint       default 0 comment '',
    type        tinyint      default 0 comment '',
    admin       tinyint      default 0 comment '',
    ip          varchar(64)  default '' comment '',
    location    varchar(128) default '' comment '',
    browser     varchar(128) default '' comment '',
    os          varchar(128) default '' comment '',
    like_count  bigint       default 0 comment 'like count',
    create_by   varchar(50)             null,
    create_time datetime                null,
    update_by   varchar(55)             null,
    update_time datetime                null
);

drop table if exists blog_tag;
create table blog_tag
(
    id          bigint auto_increment primary key,
    title       varchar(255) not null comment 'the article tag',
    create_by   varchar(50)  null,
    create_time datetime     null,
    update_by   varchar(55)  null,
    update_time datetime     null
);

drop table if exists blog_article_category;
create table blog_article_category
(
    id          bigint auto_increment primary key,
    article_id  bigint not null,
    category_id bigint not null
);

drop table if exists blog_article_tag;
create table blog_article_tag
(
    id         bigint auto_increment primary key,
    article_id bigint not null,
    tag_id     bigint not null
);

drop table if exists blog_visit_log;
create table blog_visit_log
(
    id              bigint auto_increment primary key,
    title           varchar(255)  default null comment 'blog visit module, eg, HOME,CATEGORY...',
    page_id         bigint        default 0 comment 'page id',
    request_uri     varchar(255)  default '' comment 'user request uri',
    referer         varchar(255)  default '' comment ' user referer in the http request',
    request_method  varchar(255)  default '' comment '',
    request_params  varchar(2000) default '' comment '',
    response_params varchar(2000) default '' comment '',
    exception       varchar(2000) default '' comment '',
    status_code     int(11)       default 1 comment 'http request status',
    method_name     varchar(255)  default '' comment ' the handler method name',
    os              varchar(255)  default ' ' comment 'the os name',
    spider          varchar(255)  default '' comment 'the spider name',
    browser         varchar(255)  default '' comment 'the browser name',
    ip              varchar(255)  default '' comment 'the ip address',
    location        varchar(255)  default '' comment 'location',
    create_by       varchar(50) null,
    create_time     datetime    null,
    update_by       varchar(55) null,
    update_time     datetime    null
);
drop table if exists blog_link;
create table blog_link
(
    id           bigint auto_increment primary key,
    title        varchar(255)          default null comment 'blog visit module, eg, HOME,CATEGORY...',
    header_image varchar(255)          default '',
    description  varchar(255)          default '' comment 'user request uri',
    url          varchar(255)          default '' comment ' user referer in the http request',
    email        varchar(255) not null default '' comment 'link owner email',
    link_status  tinyint               default 1 comment '1 not approve, 2 rejected, 3 approve',
    visit_count  bigint                default 0 comment 'the visit count from our website',
    create_by    varchar(50)  null,
    create_time  datetime     null,
    update_by    varchar(55)  null,
    update_time  datetime     null
);

drop table if exists blog_config;
create table blog_config
(
    id           bigint auto_increment primary key,
    config_key   varchar(255) not null comment 'config key ',
    config_value varchar(255) not null comment 'config value',
    remark       varchar(255) default '' comment 'config remark',
    create_by    varchar(50)  null,
    create_time  datetime     null,
    update_by    varchar(55)  null,
    update_time  datetime     null
);
