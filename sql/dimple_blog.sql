use `dimple-cloud`;

drop table if exists blog_article;
create table blog_article
(
    id             bigint auto_increment
        primary key,
    title          varchar(255) not null comment 'article title',
    header_image   varchar(255) not null default '' comment 'article header image',
    content        text         not null default '' comment 'article content',
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
    head_image  varchar(255)            null comment 'user head image',
    content     varchar(1024)           not null comment 'comment content, max length is 1024',
    email       varchar(128)            null comment 'user email, if email is not null will reply when the comment has been replied',
    reply_id    bigint       default -1 comment '',
    link        varchar(255) default '' comment '',
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
    user_agent      varchar(255)  default '' comment '',
    request_method  varchar(255)  default '' comment '',
    request_params  varchar(2000) default '' comment '',
    response_params varchar(2000) default '' comment '',
    exception       varchar(2000) default '' comment '',
    status_code     int(11)       default 1 comment 'http request status',
    controller_name varchar(255)  default '' comment 'the handler name',
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

