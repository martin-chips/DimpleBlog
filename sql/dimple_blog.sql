use
`dimple-cloud`;
SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

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
    create_by      varchar(64) null,
    create_time    datetime              default (now()) null,
    update_by      varchar(50) null,
    update_time    datetime              default (now()) null
) comment 'Blog article';

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
', 0, 1, 'Hello, I''m Martin. I do open source!', 1, 0, 'Dimple', sysdate(), null, sysdate());
insert into dimple-cloud.blog_article (id, title, header_image, content, category_id, original, summary, article_status,
                                       like_count, create_by, create_time, update_by, update_time)
values (1, 'Markdown Style test', 'https://file.crazywong.com/gh/jerryc127/CDN/img/Markdown-Style-test-cover.png', '---
title: Markdown Style test
tags:
  - highlight
  - Bar
categories:
  - Markdown
abbrlink: ''89757140''
date: 2018-07-24 23:31:06
mathjax: true
cover: https://file.crazywong.com/gh/jerryc127/CDN/img/Markdown-Style-test-cover.png
keywords: ''hexo,butterfly,主題,doc,教程,文檔,Markdown''
comments: false
---

This post is originated from [here](https://gist.github.com/apackeer/4159268) and is used for testing markdown style. This post contains nearly every markdown usage. Make sure all the markdown elements below show up correctly.

<!-- more -->

-------

## Headers

```markdown
# H1
## H2
### H3
#### H4
##### H5
###### H6

Alternatively, for H1 and H2, an underline-ish style:

Alt-H1
======

Alt-H2
------
```

# H1
## H2
### H3
#### H4
##### H5
###### H6


Alternatively, for H1 and H2, an underline-ish style:

Alt-H1
======

Alt-H2
------


## Emphasis

```markdown
Emphasis, aka italics, with *asterisks* or _underscores_.

Strong emphasis, aka bold, with **asterisks** or __underscores__.

Combined emphasis with **asterisks and _underscores_**.

Strikethrough uses two tildes. ~~Scratch this.~~
```

Emphasis, aka italics, with *asterisks* or _underscores_.

Strong emphasis, aka bold, with **asterisks** or __underscores__.

Combined emphasis with **asterisks and _underscores_**.

Strikethrough uses two tildes. ~~Scratch this.~~


## Lists

```markdown
1. First ordered list item
2. Another item
  * Unordered sub-list.
1. Actual numbers don''t matter, just that it''s a number
  1. Ordered sub-list
4. And another item.

   You can have properly indented paragraphs within list items. Notice the blank line above, and the leading spaces (at least one, but we''ll use three here to also align the raw Markdown).

   To have a line break without a paragraph, you will need to use two trailing spaces.
   Note that this line is separate, but within the same paragraph.
   (This is contrary to the typical GFM line break behaviour, where trailing spaces are not required.)

* Unordered list can use asterisks
- Or minuses
+ Or pluses
- Paragraph In unordered list

  For example like this.

Common Paragraph with some text.
And more text.
```

1. First ordered list item
2. Another item
  * Unordered sub-list.
1. Actual numbers don''t matter, just that it''s a number
  1. Ordered sub-list
4. And another item.

   You can have properly indented paragraphs within list items. Notice the blank line above, and the leading spaces (at least one, but we''ll use three here to also align the raw Markdown).

   To have a line break without a paragraph, you will need to use two trailing spaces.
   Note that this line is separate, but within the same paragraph.
   (This is contrary to the typical GFM line break behaviour, where trailing spaces are not required.)

* Unordered list can use asterisks
- Or minuses
+ Or pluses
- Paragraph In unordered list

  For example like this.

Common Paragraph with some text.
And more text.

## Inline HTML

```markdown
<p>To reboot your computer, press <kbd>ctrl</kbd>+<kbd>alt</kbd>+<kbd>del</kbd>.</p>
```

<p>To reboot your computer, press <kbd>ctrl</kbd>+<kbd>alt</kbd>+<kbd>del</kbd>.</p>


```markdown
<dl>
    <dt>Definition list</dt>
    <dd>Is something people use sometimes.</dd>

    <dt>Markdown in HTML</dt>
    <dd>Does *not* work **very** well. Use HTML <em>tags</em>.</dd>
</dl>
```


<dl>
    <dt>Definition list</dt>
    <dd>Is something people use sometimes.</dd>

    <dt>Markdown in HTML</dt>
    <dd>Does *not* work **very** well. Use HTML <em>tags</em>.</dd>
</dl>


## Links

```markdown
[I''m an inline-style link](https://www.google.com)

[I''m an inline-style link with title](https://www.google.com "Google''s Homepage")

[I''m a reference-style link][Arbitrary case-insensitive reference text]

[I''m a relative reference to a repository file](../blob/master/LICENSE)

[You can use numbers for reference-style link definitions][1]

Or leave it empty and use the [link text itself]

Some text to show that the reference links can follow later.

[arbitrary case-insensitive reference text]: https://hexo.io
[1]: https://hexo.io/docs/
[link text itself]: https://hexo.io/api/
```

[I''m an inline-style link](https://www.google.com)

[I''m an inline-style link with title](https://www.google.com "Google''s Homepage")

[I''m a reference-style link][Arbitrary case-insensitive reference text]

[I''m a relative reference to a repository file](../blob/master/LICENSE)

[You can use numbers for reference-style link definitions][1]

Or leave it empty and use the [link text itself]

Some text to show that the reference links can follow later.

[arbitrary case-insensitive reference text]: https://hexo.io
[1]: https://hexo.io/docs/
[link text itself]: https://hexo.io/api/

## Images

```markdown
hover to see the title text:

Inline-style:

![alt text](https://hexo.io/icon/favicon-196x196.png "Logo Title Text 1")

Reference-style:
![alt text][logo]

[logo]: https://hexo.io/icon/favicon-196x196.png "Logo Title Text 2"
```

hover to see the title text:

Inline-style:

![alt text](https://hexo.io/icon/favicon-196x196.png "Logo Title Text 1")

Reference-style:
![alt text][logo]

[logo]: https://hexo.io/icon/favicon-196x196.png "Logo Title Text 2"

## Code and Syntax Highlighting

Inline `code` has `back-ticks around` it.


```javascript
var s = "JavaScript syntax highlighting";
alert(s);
```

```python
s = "Python syntax highlighting"
print s
```

```
No language indicated, so no syntax highlighting.
But let''s throw in a <b>tag</b>.
```

## Tables

```markdown
|                |ASCII                          |HTML                         |
|----------------|-------------------------------|-----------------------------|
|Single backticks|`''Isn''t this fun?''`            |''Isn''t this fun?''            |
|Quotes          |`"Isn''t this fun?"`            |"Isn''t this fun?"            |
|Dashes          |`-- is en-dash, --- is em-dash`|-- is en-dash, --- is em-dash|
```

|                |ASCII                          |HTML                         |
|----------------|-------------------------------|-----------------------------|
|Single backticks|`''Isn''t this fun?''`            |''Isn''t this fun?''            |
|Quotes          |`"Isn''t this fun?"`            |"Isn''t this fun?"            |
|Dashes          |`-- is en-dash, --- is em-dash`|-- is en-dash, --- is em-dash|




Colons can be used to align columns.

```markdown
| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned |  |
| col 2 is      | centered      |    |
| zebra stripes | are neat      |
```

| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned |  |
| col 2 is      | centered      |    |
| zebra stripes | are neat      |     |

The outer pipes (|) are optional, and you don''t need to make the raw Markdown line up prettily. You can also use inline Markdown.

```markdown
Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3
```

Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3

> You can find more information about **LaTeX** mathematical expressions [here](https://math.meta.stackexchange.com/questions/5020/mathjax-basic-tutorial-and-quick-reference).


## Blockquotes

> Blockquotes are very handy in email to emulate reply text.
> This line is part of the same quote.

Quote break.

> This is a very long line that will still be quoted properly when it wraps. Oh boy let''s keep writing to make sure this is long enough to actually wrap for everyone. Oh, you can *put* **Markdown** into a blockquote.

## Horizontal Rule

Three or more...

```markdown
---

Hyphens

***

Asterisks

___

Underscores
```

---

Hyphens

***

Asterisks

___

Underscores


## Line Breaks

```markdown
Here''s a line for us to start with.

This line is separated from the one above by two newlines, so it will be a *separate paragraph*.

This line is also a separate paragraph, but...
This line is only separated by a single newline, so it''s a separate line in the *same paragraph*.
```


Here''s a line for us to start with.

This line is separated from the one above by two newlines, so it will be a *separate paragraph*.

This line is also a separate paragraph, but...
This line is only separated by a single newline, so it''s a separate line in the *same paragraph*.

-----

```markdown
This is a regular paragraph.

<table>
    <tr>
        <td>Foo</td>
    </tr>
</table>

This is another regular paragraph.
```

This is a regular paragraph.

<table>
    <tr>
        <td>Foo</td>
    </tr>
</table>

This is another regular paragraph.

## Youtube videos

```markdown
<a href="https://www.youtube.com/watch?feature=player_embedded&v=ARted4RniaU
" target="_blank"><img src="https://img.youtube.com/vi/ARted4RniaU/0.jpg"
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

Pure markdown version:

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/ARted4RniaU/0.jpg)](https://www.youtube.com/watch?v=ARted4RniaU)
```

<a href="https://www.youtube.com/watch?feature=player_embedded&v=ARted4RniaU
" target="_blank"><img src="https://img.youtube.com/vi/ARted4RniaU/0.jpg"
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>

Pure markdown version:

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/ARted4RniaU/0.jpg)](https://www.youtube.com/watch?v=ARted4RniaU)

', 1, 1, 'This post is originated from here and is used for testing markdown style. This post contains nearly every markdown usage. Make sure all the markdown elements below show up correctly.', 1, 0, 'Dimple', sysdate(), null, sysdate());
drop table if exists blog_category;
create table blog_category
(
    id          bigint auto_increment primary key,
    title       varchar(255) not null comment 'category title, max length is 255',
    create_by   varchar(50) null,
    create_time datetime default (now()) null,
    update_by   varchar(55) null,
    update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null
) comment 'blog category';
insert into dimple-cloud.blog_category (id, title, create_by, create_time, update_by, update_time)
values (1, 'Markdown', 'Dimple', sysdate(), null, sysdate());
drop table if exists blog_comment;
create table blog_comment
(
    id          bigint auto_increment primary key,
    article_id  bigint                  not null comment 'comment article id',
    username    varchar(255)            not null comment 'user name',
    parent_id   bigint       default -1 not null comment 'parent comment is, default is -1',
    avatars     varchar(255) null comment 'user head image',
    content     varchar(1024)           not null comment 'comment content, max length is 1024',
    email       varchar(128) null comment 'user email, if email is not null will reply when the comment has been replied',
    reply_id    bigint       default -1 comment '',
    link        varchar(255) default '' comment '',
    visitor_id  varchar(255) default '' comment '',
    type        tinyint      default 0 comment '',
    admin       tinyint      default 0 comment '',
    ip          varchar(64)  default '' comment '',
    location    varchar(128) default '' comment '',
    browser     varchar(128) default '' comment '',
    os          varchar(128) default '' comment '',
    like_count  bigint       default 0 comment 'like count',
    create_by   varchar(50) null,
    create_time datetime     default (now()) null,
    update_by   varchar(55) null,
    update_time datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null
);

drop table if exists blog_tag;
create table blog_tag
(
    id          bigint auto_increment primary key,
    title       varchar(255) not null comment 'the article tag',
    create_by   varchar(50) null,
    create_time datetime default (now()) null,
    update_by   varchar(55) null,
    update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null
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
    status_code     int(11) default 1 comment 'http request status',
    method_name     varchar(255)  default '' comment ' the handler method name',
    os              varchar(255)  default ' ' comment 'the os name',
    spider          varchar(255)  default '' comment 'the spider name',
    browser         varchar(255)  default '' comment 'the browser name',
    ip              varchar(255)  default '' comment 'the ip address',
    location        varchar(255)  default '' comment 'location',
    create_by       varchar(50) null,
    create_time     datetime      default (now()) null,
    update_by       varchar(55) null,
    update_time     datetime      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null
);
drop table if exists blog_link;
create table blog_link
(
    id            bigint auto_increment primary key,
    title         varchar(255)          default null comment 'blog visit module, eg, HOME,CATEGORY...',
    header_image  varchar(255)          default '',
    description   varchar(255)          default '' comment 'user request uri',
    url           varchar(255)          default '' comment ' user referer in the http request',
    email         varchar(255) not null default '' comment 'link owner email',
    email_content text comment 'link owner email',
    link_status   tinyint               default 1 comment '1 not approve, 2 rejected, 3 approve',
    visit_count   bigint                default 0 comment 'the visit count from our website',
    create_by     varchar(50) null,
    create_time   datetime              default (now()) null,
    update_by     varchar(55) null,
    update_time   datetime              default (now()) null
);

insert into blog_link (title, header_image, description, url, email, email_content, link_status, visit_count, create_by,
                       create_time, update_by, update_time)
values ('Github', 'https://avatars.githubusercontent.com/u/33685170?v=4', 'Dimple''s Blog Github Location',
        'https://github.com/martin-chips/DimpleBlog', 'bianxiaofeng@sohu.com', null, 3, 0, 'Dimple', sysdate(), null,
        null),
       ('Dimple''s Blog', 'https://www.bianxf.com/img/favicon.ico', 'Blog', 'https://www.bianxf.com',
        'bianxiaofeng@sohu.com', null, 3, 0, 'Dimple', sysdate(), null, sysdate());

drop table if exists blog_config;
create table blog_config
(
    id           bigint auto_increment primary key,
    config_value text not null comment 'config value',
    create_by    varchar(50) null,
    create_time  datetime default (now()) null,
    update_by    varchar(55) null,
    update_time  datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP null
);
insert into blog_config (id, config_value, create_by, create_time, update_by, update_time)
values (1, '{
    "commentConfig": {
        "enableGithubLogin": true,
        "enableQQLogin": true,
        "githubLoginConfig": {
            "accessTokenUrl": "https://github.com/login/oauth/access_token",
            "adminId": "33685170",
            "clientId": "fca4ba1e780fd9c444b2",
            "clientSecrets": "",
            "loginUrl": "https://github.com/login/oauth/authorize?scope=[''user:admin'']&client_id=fca4ba1e780fd9c444b2&scope=[''user'']&redirect_uri=http://124.221.138.118/login/auth/github",
            "userInfoUrl": "https://api.github.com/user"
        }
    },
    "emailConfig": {
        "host": "smtp.126.com",
        "password": "",
        "username": "dimple_blog@126.com"
    },
    "siteConfig": {
        "avatars": "https://avatars.githubusercontent.com/u/33685170?s=48&v=4",
        "copyright": "©2018 - 2023&nbsp;&nbsp;&nbsp;",
        "githubUrl": "https://github.com/martin-chips",
        "githubUserName": "Martin Chips",
        "icp": "蜀ICP备2023006757号",
        "infoPanel": "&lt;p&gt;Hi，欢迎来到Dimple''s Blog，一个记录学习和生活的个人博客，你可以在&lt;a href=\\"https://bianxf.com\\" rel=\\"noopener noreferrer\\" target=\\"_blank\\"&gt;这里&lt;/a&gt;了解到关于本站的技术细节。&lt;/p&gt;",
        "mask": false,
        "siteName": "Dimple Blog"
    }
}', 'admin', sysdate(), null, sysdate());
