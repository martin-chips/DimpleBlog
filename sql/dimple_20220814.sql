DROP DATABASE IF EXISTS `dimple-cloud`;

CREATE DATABASE  `dimple-cloud` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `dimple-cloud`;

SET NAMES utf8mb4;

-- ----------------------------
-- 用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user
(
    user_id     bigint(20) not null auto_increment comment '用户ID',
    user_name   varchar(30) not null comment '用户账号',
    nick_name   varchar(30) not null comment '用户昵称',
    user_type   varchar(2)   default '00' comment '用户类型（00系统用户）',
    email       varchar(50)  default '' comment '用户邮箱',
    phonenumber varchar(11)  default '' comment '手机号码',
    sex         char(1)      default '0' comment '用户性别（0男 1女 2未知）',
    avatar      varchar(100) default '' comment '头像地址',
    password    varchar(100) default '' comment '密码',
    status      char(1)      default '0' comment '帐号状态（0正常 1停用）',
    del_flag    char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    login_ip    varchar(128) default '' comment '最后登录IP',
    login_date  datetime comment '最后登录时间',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (user_id)
) engine=innodb auto_increment=100 comment = '用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user
values (1, 'admin', 'Dimple', '00', 'dimple@163.com', '15888888888', '1', '',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin',
        sysdate(), '', null, '管理员');
insert into sys_user
values (2, 'test', 'Dimple', '00', 'test@qq.com', '15666666666', '1', '',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin',
        sysdate(), '', null, '测试员');

-- ----------------------------
-- 角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role
(
    role_id             bigint(20) not null auto_increment comment '角色ID',
    role_name           varchar(30)  not null comment '角色名称',
    role_key            varchar(100) not null comment '角色权限字符串',
    role_sort           int(4) not null comment '显示顺序',
    data_scope          char(1)      default '1' comment '数据范围（1：全部数据权限 2：自定数据权限）',
    menu_check_strictly tinyint(1) default 1 comment '菜单树选择项是否关联显示',
    status              char(1)      not null comment '角色状态（0正常 1停用）',
    del_flag            char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    create_by           varchar(64)  default '' comment '创建者',
    create_time         datetime comment '创建时间',
    update_by           varchar(64)  default '' comment '更新者',
    update_time         datetime comment '更新时间',
    remark              varchar(500) default null comment '备注',
    primary key (role_id)
) engine=innodb auto_increment=100 comment = '角色信息表';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role
values ('1', '超级管理员', 'admin', 1, 1, 1, 1, '0', 'admin', sysdate(), '', null, '超级管理员');
insert into sys_role
values ('2', '普通角色', 'common', 2, 2, 1, 1, '0', 'admin', sysdate(), '', null, '普通角色');


-- ----------------------------
-- 菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu
(
    menu_id     bigint(20) not null auto_increment comment '菜单ID',
    menu_name   varchar(50) not null comment '菜单名称',
    parent_id   bigint(20) default 0 comment '父菜单ID',
    order_num   int(4) default 0 comment '显示顺序',
    path        varchar(200) default '' comment '路由地址',
    component   varchar(255) default null comment '组件路径',
    query       varchar(255) default null comment '路由参数',
    is_frame    int(1) default 1 comment '是否为外链（0是 1否）',
    is_cache    int(1) default 0 comment '是否缓存（0缓存 1不缓存）',
    menu_type   char(1)      default '' comment '菜单类型（M目录 C菜单 F按钮）',
    visible     char(1)      default 0 comment '菜单状态（0显示 1隐藏）',
    status      char(1)      default 0 comment '菜单状态（0正常 1停用）',
    perms       varchar(100) default null comment '权限标识',
    icon        varchar(100) default '#' comment '菜单图标',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default '' comment '备注',
    primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu
values ('1', '系统管理', '0', '1', 'system', null, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', sysdate(), '', null,
        '系统管理目录');
insert into sys_menu
values ('2', '系统监控', '0', '2', 'monitor', null, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', sysdate(), '',
        null, '系统监控目录');
insert into sys_menu
values ('3', '系统工具', '0', '3', 'tool', null, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', sysdate(), '', null,
        '系统工具目录');
insert into sys_menu
values ('4', 'Dimple官网', '0', '10', 'http://bianxf.com', null, '', 0, 0, 'M', '0', '0', '', 'guide', 'admin',
        sysdate(),
        '', null, 'Dimple官网地址');
insert into sys_menu
values ('5', '日志管理', '0', '9', 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', sysdate(), '', null,
        '日志管理菜单');
-- 二级菜单
insert into sys_menu
values ('100', '用户管理', '1', '1', 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user',
        'admin', sysdate(), '', null, '用户管理菜单');
insert into sys_menu
values ('101', '角色管理', '1', '2', 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list',
        'peoples', 'admin', sysdate(), '', null, '角色管理菜单');
insert into sys_menu
values ('102', '菜单管理', '1', '3', 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list',
        'tree-table', 'admin', sysdate(), '', null, '菜单管理菜单');
insert into sys_menu
values ('105', '字典管理', '1', '6', 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict',
        'admin', sysdate(), '', null, '字典管理菜单');
insert into sys_menu
values ('106', '参数设置', '1', '7', 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list',
        'edit', 'admin', sysdate(), '', null, '参数设置菜单');
insert into sys_menu
values ('107', '通知公告', '1', '8', 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list',
        'message', 'admin', sysdate(), '', null, '通知公告菜单');
insert into sys_menu
values ('109', '在线用户', '2', '1', 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list',
        'online', 'admin', sysdate(), '', null, '在线用户菜单');
insert into sys_menu
values ('108', 'Redis监控', '2', '1', 'online', 'monitor/redis/index', '', 1, 0, 'C', '0', '0', 'monitor:redis:list',
        'online', 'admin', sysdate(), '', null, 'Redis监控菜单');
insert into sys_menu
values ('110', '定时任务', '3', '2', 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job',
        'admin', sysdate(), '', null, '定时任务菜单');
insert into sys_menu
values ('111', 'Sentinel控制台', '2', '3', 'http://localhost:8718', '', '', 0, 0, 'C', '0', '0',
        'monitor:sentinel:list', 'sentinel', 'admin', sysdate(), '', null, '流量控制菜单');
insert into sys_menu
values ('112', 'Nacos控制台', '2', '4', 'http://localhost:8848/nacos', '', '', 0, 0, 'C', '0', '0',
        'monitor:nacos:list', 'nacos', 'admin', sysdate(), '', null, '服务治理菜单');
insert into sys_menu
values ('113', 'Admin控制台', '2', '5', 'http://localhost:9100/login', '', '', 0, 0, 'C', '0', '0',
        'monitor:server:list', 'server', 'admin', sysdate(), '', null, '服务监控菜单');
insert into sys_menu
values ('116', '系统接口', '3', '3', 'http://localhost:8080/swagger-ui/index.html', '', '', 0, 0, 'C', '0', '0',
        'tool:swagger:list', 'swagger', 'admin', sysdate(), '', null, '系统接口菜单');
insert into sys_menu
values ('500', '操作日志', '5', '1', 'operlog', 'log/operlog/index', '', 1, 0, 'C', '0', '0',
        'log:operlog:list', 'form', 'admin', sysdate(), '', null, '操作日志菜单');
insert into sys_menu
values ('501', '登录日志', '5', '2', 'logininfor', 'log/logininfor/index', '', 1, 0, 'C', '0', '0',
        'log:logininfor:list', 'logininfor', 'admin', sysdate(), '', null, '登录日志菜单');
insert into sys_menu values('114',  '表单构建',       '3',   '1', 'build',      'tool/build/index',                  '', 1, 0, 'C', '0', '0', 'tool:build:list',         'build',         'admin', sysdate(), '', null, '表单构建菜单');
insert into sys_menu values('115',  '代码生成',       '3',   '2', 'gen',        'tool/gen/index',                    '', 1, 0, 'C', '0', '0', 'tool:gen:list',           'code',          'admin', sysdate(), '', null, '代码生成菜单');
insert into sys_menu values('116',  '系统接口',       '3',   '3', 'http://localhost:8080/swagger-ui/index.html', '', '', 0, 0, 'C', '0', '0', 'tool:swagger:list',       'swagger',       'admin', sysdate(), '', null, '系统接口菜单');
-- 用户管理按钮
insert into sys_menu
values ('1000', '用户查询', '100', '1', '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1001', '用户新增', '100', '2', '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1002', '用户修改', '100', '3', '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1003', '用户删除', '100', '4', '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1004', '用户导出', '100', '5', '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1005', '用户导入', '100', '6', '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1006', '重置密码', '100', '7', '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin',
        sysdate(), '', null, '');
-- 角色管理按钮
insert into sys_menu
values ('1007', '角色查询', '101', '1', '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1008', '角色新增', '101', '2', '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1009', '角色修改', '101', '3', '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1010', '角色删除', '101', '4', '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1011', '角色导出', '101', '5', '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', sysdate(),
        '', null, '');
-- 菜单管理按钮
insert into sys_menu
values ('1012', '菜单查询', '102', '1', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1013', '菜单新增', '102', '2', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1014', '菜单修改', '102', '3', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1015', '菜单删除', '102', '4', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', sysdate(),
        '', null, '');
-- 字典管理按钮
insert into sys_menu
values ('1025', '字典查询', '105', '1', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1026', '字典新增', '105', '2', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1027', '字典修改', '105', '3', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1028', '字典删除', '105', '4', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1029', '字典导出', '105', '5', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', sysdate(),
        '', null, '');
-- 参数设置按钮
insert into sys_menu
values ('1030', '参数查询', '106', '1', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1031', '参数新增', '106', '2', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1032', '参数修改', '106', '3', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1033', '参数删除', '106', '4', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1034', '参数导出', '106', '5', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin',
        sysdate(), '', null, '');
-- 通知公告按钮
insert into sys_menu
values ('1035', '公告查询', '107', '1', '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1036', '公告新增', '107', '2', '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1037', '公告修改', '107', '3', '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1038', '公告删除', '107', '4', '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin',
        sysdate(), '', null, '');
-- 操作日志按钮
insert into sys_menu
values ('1039', '操作查询', '500', '1', '#', '', '', 1, 0, 'F', '0', '0', 'log:operlog:query', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1040', '操作删除', '500', '2', '#', '', '', 1, 0, 'F', '0', '0', 'log:operlog:remove', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1041', '日志导出', '500', '3', '#', '', '', 1, 0, 'F', '0', '0', 'log:operlog:export', '#', 'admin',
        sysdate(), '', null, '');
-- 登录日志按钮
insert into sys_menu
values ('1042', '登录查询', '501', '1', '#', '', '', 1, 0, 'F', '0', '0', 'log:logininfor:query', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1043', '登录删除', '501', '2', '#', '', '', 1, 0, 'F', '0', '0', 'log:logininfor:remove', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1044', '日志导出', '501', '3', '#', '', '', 1, 0, 'F', '0', '0', 'log:logininfor:export', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1045', '账户解锁', '501', '4', '#', '', '', 1, 0, 'F', '0', '0', 'system:user:unlock', '#', 'admin',
        sysdate(), '', null, '');
-- 在线用户按钮
insert into sys_menu
values ('1046', '在线查询', '109', '1', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1047', '批量强退', '109', '2', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1048', '单条强退', '109', '3', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin',
        sysdate(), '', null, '');
-- 定时任务按钮
insert into sys_menu
values ('1049', '任务查询', '110', '1', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1050', '任务新增', '110', '2', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1051', '任务修改', '110', '3', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1052', '任务删除', '110', '4', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1053', '状态修改', '110', '5', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1054', '任务导出', '110', '6', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', sysdate(),
        '', null, '');
-- 代码生成按钮
insert into sys_menu values('1055', '生成查询', '115', '1', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query',             '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1056', '生成修改', '115', '2', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit',              '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1057', '生成删除', '115', '3', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1058', '导入代码', '115', '2', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import',            '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1059', '预览代码', '115', '4', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview',           '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('1060', '生成代码', '115', '5', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code',              '#', 'admin', sysdate(), '', null, '');
-- blog
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values  (6, '博客管理', 0, 6, 'blog', '', '', 1, 0, 'M', '0', '0', '', 'post', 'admin', sysdate(), '', null, 'Blog管理菜单');

insert into sys_menu (menu_id,menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(601,'文章', '6', '1', 'article', 'blog/article/index', 1, 0, 'C', '0', '0', 'blog:article:list', 'edit', 'admin', sysdate(), '', null, '文章菜单');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章查询', 601, '1',  '#', '', 1, 0, 'F', '0', '0', 'blog:article:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章新增', 601, '2',  '#', '', 1, 0, 'F', '0', '0', 'blog:article:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章修改', 601, '3',  '#', '', 1, 0, 'F', '0', '0', 'blog:article:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章删除', 601, '4',  '#', '', 1, 0, 'F', '0', '0', 'blog:article:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('文章导出', 601, '5',  '#', '', 1, 0, 'F', '0', '0', 'blog:article:export',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id,menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(602,'分类', 6, '1', 'category', 'blog/category/index', 1, 0, 'C', '0', '0', 'blog:category:list', 'skill', 'admin', sysdate(), '', null, '分类菜单');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('分类查询', 602, '1',  '#', '', 1, 0, 'F', '0', '0', 'blog:category:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('分类新增', 602, '2',  '#', '', 1, 0, 'F', '0', '0', 'blog:category:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('分类修改', 602, '3',  '#', '', 1, 0, 'F', '0', '0', 'blog:category:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('分类删除', 602, '4',  '#', '', 1, 0, 'F', '0', '0', 'blog:category:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('分类导出', 602, '5',  '#', '', 1, 0, 'F', '0', '0', 'blog:category:export',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(603,'评论',6, '1', 'comment', 'blog/comment/index', 1, 0, 'C', '0', '0', 'blog:comment:list', 'message', 'admin', sysdate(), '', null, '评论菜单');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论查询', 603, '1',  '#', '', 1, 0, 'F', '0', '0', 'blog:comment:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论新增', 603, '2',  '#', '', 1, 0, 'F', '0', '0', 'blog:comment:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论修改', 603, '3',  '#', '', 1, 0, 'F', '0', '0', 'blog:comment:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论删除', 603, '4',  '#', '', 1, 0, 'F', '0', '0', 'blog:comment:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('评论导出', 603, '5',  '#', '', 1, 0, 'F', '0', '0', 'blog:comment:export',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id,menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(604,'标签', 6, '1', 'tag', 'blog/tag/index', 1, 0, 'C', '0', '0', 'blog:tag:list', 'row', 'admin', sysdate(), '', null, '标签菜单');
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签查询', 604, '1',  '#', '', 1, 0, 'F', '0', '0', 'blog:tag:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签新增', 604, '2',  '#', '', 1, 0, 'F', '0', '0', 'blog:tag:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签修改', 604, '3',  '#', '', 1, 0, 'F', '0', '0', 'blog:tag:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签删除', 604, '4',  '#', '', 1, 0, 'F', '0', '0', 'blog:tag:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('标签导出', 604, '5',  '#', '', 1, 0, 'F', '0', '0', 'blog:tag:export',       '#', 'admin', sysdate(), '', null, '');

-- ----------------------------
-- 用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role
(
    user_id bigint(20) not null comment '用户ID',
    role_id bigint(20) not null comment '角色ID',
    primary key (user_id, role_id)
) engine=innodb comment = '用户和角色关联表';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role
values ('1', '1');
insert into sys_user_role
values ('2', '2');


-- ----------------------------
-- 角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu
(
    role_id bigint(20) not null comment '角色ID',
    menu_id bigint(20) not null comment '菜单ID',
    primary key (role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu
values ('2', '1');
insert into sys_role_menu
values ('2', '2');
insert into sys_role_menu
values ('2', '3');
insert into sys_role_menu
values ('2', '4');
insert into sys_role_menu
values ('2', '100');
insert into sys_role_menu
values ('2', '101');
insert into sys_role_menu
values ('2', '102');
insert into sys_role_menu
values ('2', '103');
insert into sys_role_menu
values ('2', '104');
insert into sys_role_menu
values ('2', '105');
insert into sys_role_menu
values ('2', '106');
insert into sys_role_menu
values ('2', '107');
insert into sys_role_menu
values ('2', '108');
insert into sys_role_menu
values ('2', '109');
insert into sys_role_menu
values ('2', '110');
insert into sys_role_menu
values ('2', '111');
insert into sys_role_menu
values ('2', '112');
insert into sys_role_menu
values ('2', '113');
insert into sys_role_menu
values ('2', '114');
insert into sys_role_menu
values ('2', '115');
insert into sys_role_menu
values ('2', '116');
insert into sys_role_menu
values ('2', '500');
insert into sys_role_menu
values ('2', '501');
insert into sys_role_menu
values ('2', '1000');
insert into sys_role_menu
values ('2', '1001');
insert into sys_role_menu
values ('2', '1002');
insert into sys_role_menu
values ('2', '1003');
insert into sys_role_menu
values ('2', '1004');
insert into sys_role_menu
values ('2', '1005');
insert into sys_role_menu
values ('2', '1006');
insert into sys_role_menu
values ('2', '1007');
insert into sys_role_menu
values ('2', '1008');
insert into sys_role_menu
values ('2', '1009');
insert into sys_role_menu
values ('2', '1010');
insert into sys_role_menu
values ('2', '1011');
insert into sys_role_menu
values ('2', '1012');
insert into sys_role_menu
values ('2', '1013');
insert into sys_role_menu
values ('2', '1014');
insert into sys_role_menu
values ('2', '1015');
insert into sys_role_menu
values ('2', '1016');
insert into sys_role_menu
values ('2', '1017');
insert into sys_role_menu
values ('2', '1018');
insert into sys_role_menu
values ('2', '1019');
insert into sys_role_menu
values ('2', '1020');
insert into sys_role_menu
values ('2', '1021');
insert into sys_role_menu
values ('2', '1022');
insert into sys_role_menu
values ('2', '1023');
insert into sys_role_menu
values ('2', '1024');
insert into sys_role_menu
values ('2', '1025');
insert into sys_role_menu
values ('2', '1026');
insert into sys_role_menu
values ('2', '1027');
insert into sys_role_menu
values ('2', '1028');
insert into sys_role_menu
values ('2', '1029');
insert into sys_role_menu
values ('2', '1030');
insert into sys_role_menu
values ('2', '1031');
insert into sys_role_menu
values ('2', '1032');
insert into sys_role_menu
values ('2', '1033');
insert into sys_role_menu
values ('2', '1034');
insert into sys_role_menu
values ('2', '1035');
insert into sys_role_menu
values ('2', '1036');
insert into sys_role_menu
values ('2', '1037');
insert into sys_role_menu
values ('2', '1038');
insert into sys_role_menu
values ('2', '1039');
insert into sys_role_menu
values ('2', '1040');
insert into sys_role_menu
values ('2', '1041');
insert into sys_role_menu
values ('2', '1042');
insert into sys_role_menu
values ('2', '1043');
insert into sys_role_menu
values ('2', '1044');
insert into sys_role_menu
values ('2', '1045');
insert into sys_role_menu
values ('2', '1046');
insert into sys_role_menu
values ('2', '1047');
insert into sys_role_menu
values ('2', '1048');
insert into sys_role_menu
values ('2', '1049');
insert into sys_role_menu
values ('2', '1050');
insert into sys_role_menu
values ('2', '1051');
insert into sys_role_menu
values ('2', '1052');
insert into sys_role_menu
values ('2', '1053');
insert into sys_role_menu
values ('2', '1054');
insert into sys_role_menu
values ('2', '1055');
insert into sys_role_menu
values ('2', '1056');
insert into sys_role_menu
values ('2', '1057');
insert into sys_role_menu
values ('2', '1058');
insert into sys_role_menu
values ('2', '1059');
insert into sys_role_menu
values ('2', '1060');
-- ----------------------------
-- 操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log
(
    oper_id        bigint(20) not null auto_increment comment '日志主键',
    title          varchar(50)   default '' comment '模块标题',
    business_type  int(2) default 0 comment '业务类型（0其它 1新增 2修改 3删除）',
    method         varchar(100)  default '' comment '方法名称',
    request_method varchar(10)   default '' comment '请求方式',
    operator_type  int(1) default 0 comment '操作类别（0其它 1后台用户 2手机端用户）',
    oper_name      varchar(50)   default '' comment '操作人员',
    oper_url       varchar(255)  default '' comment '请求URL',
    oper_ip        varchar(128)  default '' comment '主机地址',
    oper_location  varchar(255)  default '' comment '操作地点',
    oper_param     varchar(2000) default '' comment '请求参数',
    json_result    varchar(2000) default '' comment '返回参数',
    status         int(1) default 0 comment '操作状态（0正常 1异常）',
    error_msg      varchar(2000) default '' comment '错误消息',
    oper_time      datetime comment '操作时间',
    primary key (oper_id)
) engine=innodb auto_increment=100 comment = '操作日志记录';


-- ----------------------------
-- 字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
    dict_id     bigint(20) not null auto_increment comment '字典主键',
    dict_name   varchar(100) default '' comment '字典名称',
    dict_type   varchar(100) default '' comment '字典类型',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (dict_id),
    unique (dict_type)
) engine=innodb auto_increment=100 comment = '字典类型表';

insert into sys_dict_type
values (1, '用户性别', 'sys_user_sex', '0', 'admin', sysdate(), '', null, '用户性别列表');
insert into sys_dict_type
values (2, '菜单状态', 'sys_show_hide', '0', 'admin', sysdate(), '', null, '菜单状态列表');
insert into sys_dict_type
values (3, '系统开关', 'sys_normal_disable', '0', 'admin', sysdate(), '', null, '系统开关列表');
insert into sys_dict_type
values (4, '任务状态', 'sys_job_status', '0', 'admin', sysdate(), '', null, '任务状态列表');
insert into sys_dict_type
values (5, '任务分组', 'sys_job_group', '0', 'admin', sysdate(), '', null, '任务分组列表');
insert into sys_dict_type
values (6, '系统是否', 'sys_yes_no', '0', 'admin', sysdate(), '', null, '系统是否列表');
insert into sys_dict_type
values (7, '通知类型', 'sys_notice_type', '0', 'admin', sysdate(), '', null, '通知类型列表');
insert into sys_dict_type
values (8, '通知状态', 'sys_notice_status', '0', 'admin', sysdate(), '', null, '通知状态列表');
insert into sys_dict_type
values (9, '操作类型', 'sys_oper_type', '0', 'admin', sysdate(), '', null, '操作类型列表');
insert into sys_dict_type
values (10, '系统状态', 'sys_common_status', '0', 'admin', sysdate(), '', null, '登录状态列表');


-- ----------------------------
-- 字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
    dict_code   bigint(20) not null auto_increment comment '字典编码',
    dict_sort   int(4) default 0 comment '字典排序',
    dict_label  varchar(100) default '' comment '字典标签',
    dict_value  varchar(100) default '' comment '字典键值',
    dict_type   varchar(100) default '' comment '字典类型',
    css_class   varchar(100) default null comment '样式属性（其他样式扩展）',
    list_class  varchar(100) default null comment '表格回显样式',
    is_default  char(1)      default 'N' comment '是否默认（Y是 N否）',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (dict_code)
) engine=innodb auto_increment=100 comment = '字典数据表';

insert into sys_dict_data
values (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', sysdate(), '', null, '性别男');
insert into sys_dict_data
values (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', sysdate(), '', null, '性别女');
insert into sys_dict_data
values (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', sysdate(), '', null, '性别未知');
insert into sys_dict_data
values (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '显示菜单');
insert into sys_dict_data
values (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '隐藏菜单');
insert into sys_dict_data
values (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data
values (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data
values (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data
values (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data
values (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', sysdate(), '', null, '默认分组');
insert into sys_dict_data
values (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', sysdate(), '', null, '系统分组');
insert into sys_dict_data
values (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '系统默认是');
insert into sys_dict_data
values (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '系统默认否');
insert into sys_dict_data
values (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', sysdate(), '', null, '通知');
insert into sys_dict_data
values (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '公告');
insert into sys_dict_data
values (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data
values (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '关闭状态');
insert into sys_dict_data
values (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '其他操作');
insert into sys_dict_data
values (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '新增操作');
insert into sys_dict_data
values (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '修改操作');
insert into sys_dict_data
values (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '删除操作');
insert into sys_dict_data
values (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', sysdate(), '', null, '授权操作');
insert into sys_dict_data
values (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '导出操作');
insert into sys_dict_data
values (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '导入操作');
insert into sys_dict_data
values (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '强退操作');
insert into sys_dict_data
values (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '生成操作');
insert into sys_dict_data
values (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '清空操作');
insert into sys_dict_data
values (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data
values (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '停用状态');


-- ----------------------------
-- 参数配置表
-- ----------------------------
drop table if exists sys_config;
create table sys_config
(
    config_id    int(5) not null auto_increment comment '参数主键',
    config_name  varchar(100) default '' comment '参数名称',
    config_key   varchar(100) default '' comment '参数键名',
    config_value varchar(500) default '' comment '参数键值',
    config_type  char(1)      default 'N' comment '系统内置（Y是 N否）',
    create_by    varchar(64)  default '' comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(64)  default '' comment '更新者',
    update_time  datetime comment '更新时间',
    remark       varchar(500) default null comment '备注',
    primary key (config_id)
) engine=innodb auto_increment=100 comment = '参数配置表';

insert into sys_config
values (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', sysdate(), '', null,
        '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
insert into sys_config
values (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', sysdate(), '', null,
        '初始化密码 123456');
insert into sys_config
values (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', sysdate(), '', null,
        '深色主题theme-dark，浅色主题theme-light');
insert into sys_config
values (4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', sysdate(), '', null,
        '是否开启注册用户功能`（true开启，false关闭）');


-- ----------------------------
-- 系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor
(
    info_id     bigint(20) not null auto_increment comment '访问ID',
    user_name   varchar(50)  default '' comment '用户账号',
    ipaddr      varchar(128) default '' comment '登录IP地址',
    status      char(1)      default '0' comment '登录状态（0成功 1失败）',
    msg         varchar(255) default '' comment '提示信息',
    access_time datetime comment '访问时间',
    primary key (info_id)
) engine=innodb auto_increment=100 comment = '系统访问记录';


-- ----------------------------
-- 定时任务调度表
-- ----------------------------
drop table if exists sys_job;
create table sys_job
(
    job_id          bigint(20) not null auto_increment comment '任务ID',
    job_name        varchar(64)  default '' comment '任务名称',
    job_group       varchar(64)  default 'DEFAULT' comment '任务组名',
    invoke_target   varchar(500) not null comment '调用目标字符串',
    cron_expression varchar(255) default '' comment 'cron执行表达式',
    misfire_policy  varchar(20)  default '3' comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    concurrent      char(1)      default '1' comment '是否并发执行（0允许 1禁止）',
    status          char(1)      default '0' comment '状态（0正常 1暂停）',
    create_by       varchar(64)  default '' comment '创建者',
    create_time     datetime comment '创建时间',
    update_by       varchar(64)  default '' comment '更新者',
    update_time     datetime comment '更新时间',
    remark          varchar(500) default '' comment '备注信息',
    primary key (job_id, job_name, job_group)
) engine=innodb auto_increment=100 comment = '定时任务调度表';

insert into sys_job
values (1, '系统默认（无参）', 'DEFAULT', 'dimpleTask.dimpleNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin',
        sysdate(), '',
        null, '');
insert into sys_job
values (2, '系统默认（有参）', 'DEFAULT', 'dimpleTask.dimpleParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin',
        sysdate(),
        '', null, '');
insert into sys_job
values (3, '系统默认（多参）', 'DEFAULT', 'dimpleTask.dimpleMultipleParams(\'ry\', true, 2000L, 316.50D, 100)',
        '0/20 * * * * ?',
        '3', '1', '1', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 定时任务调度日志表
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log
(
    job_log_id     bigint(20) not null auto_increment comment '任务日志ID',
    job_name       varchar(64)  not null comment '任务名称',
    job_group      varchar(64)  not null comment '任务组名',
    invoke_target  varchar(500) not null comment '调用目标字符串',
    job_message    varchar(500) comment '日志信息',
    status         char(1)       default '0' comment '执行状态（0正常 1失败）',
    exception_info varchar(2000) default '' comment '异常信息',
    create_time    datetime comment '创建时间',
    primary key (job_log_id)
) engine=innodb comment = '定时任务调度日志表';


-- ----------------------------
-- 通知公告表
-- ----------------------------
drop table if exists sys_notice;
create table sys_notice
(
    notice_id      int(4) not null auto_increment comment '公告ID',
    notice_title   varchar(50) not null comment '公告标题',
    notice_type    char(1)     not null comment '公告类型（1通知 2公告）',
    notice_content longblob     default null comment '公告内容',
    status         char(1)      default '0' comment '公告状态（0正常 1关闭）',
    create_by      varchar(64)  default '' comment '创建者',
    create_time    datetime comment '创建时间',
    update_by      varchar(64)  default '' comment '更新者',
    update_time    datetime comment '更新时间',
    remark         varchar(255) default null comment '备注',
    primary key (notice_id)
) engine=innodb auto_increment=10 comment = '通知公告表';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into sys_notice
values ('1', '温馨提醒：2018-07-01 Dimple新版本发布啦', '2', '新版本内容', '0', 'admin', sysdate(), '', null, '管理员');
insert into sys_notice
values ('2', '维护通知：2018-07-01 Dimple系统凌晨维护', '1', '维护内容', '0', 'admin', sysdate(), '', null, '管理员');


-- ---------------------------
-- 代码生成业务表
-- ----------------------------
drop table if exists gen_table;
create table gen_table (
                           table_id          bigint(20)      not null auto_increment    comment '编号',
                           table_name        varchar(200)    default ''                 comment '表名称',
                           table_comment     varchar(500)    default ''                 comment '表描述',
                           sub_table_name    varchar(64)     default null               comment '关联子表的表名',
                           sub_table_fk_name varchar(64)     default null               comment '子表关联的外键名',
                           class_name        varchar(100)    default ''                 comment '实体类名称',
                           tpl_category      varchar(200)    default 'crud'             comment '使用的模板（crud单表操作 tree树表操作）',
                           package_name      varchar(100)                               comment '生成包路径',
                           module_name       varchar(30)                                comment '生成模块名',
                           business_name     varchar(30)                                comment '生成业务名',
                           function_name     varchar(50)                                comment '生成功能名',
                           function_author   varchar(50)                                comment '生成功能作者',
                           gen_type          char(1)         default '0'                comment '生成代码方式（0zip压缩包 1自定义路径）',
                           gen_path          varchar(200)    default '/'                comment '生成路径（不填默认项目路径）',
                           options           varchar(1000)                              comment '其它生成选项',
                           create_by         varchar(64)     default ''                 comment '创建者',
                           create_time 	    datetime                                   comment '创建时间',
                           update_by         varchar(64)     default ''                 comment '更新者',
                           update_time       datetime                                   comment '更新时间',
                           remark            varchar(500)    default null               comment '备注',
                           primary key (table_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表';


-- ----------------------------
-- 代码生成业务表字段
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column (
                                  column_id         bigint(20)      not null auto_increment    comment '编号',
                                  table_id          varchar(64)                                comment '归属表编号',
                                  column_name       varchar(200)                               comment '列名称',
                                  column_comment    varchar(500)                               comment '列描述',
                                  column_type       varchar(100)                               comment '列类型',
                                  java_type         varchar(500)                               comment 'JAVA类型',
                                  java_field        varchar(200)                               comment 'JAVA字段名',
                                  is_pk             char(1)                                    comment '是否主键（1是）',
                                  is_increment      char(1)                                    comment '是否自增（1是）',
                                  is_required       char(1)                                    comment '是否必填（1是）',
                                  is_insert         char(1)                                    comment '是否为插入字段（1是）',
                                  is_edit           char(1)                                    comment '是否编辑字段（1是）',
                                  is_list           char(1)                                    comment '是否列表字段（1是）',
                                  is_query          char(1)                                    comment '是否查询字段（1是）',
                                  query_type        varchar(200)    default 'EQ'               comment '查询方式（等于、不等于、大于、小于、范围）',
                                  html_type         varchar(200)                               comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
                                  dict_type         varchar(200)    default ''                 comment '字典类型',
                                  sort              int                                        comment '排序',
                                  create_by         varchar(64)     default ''                 comment '创建者',
                                  create_time 	    datetime                                   comment '创建时间',
                                  update_by         varchar(64)     default ''                 comment '更新者',
                                  update_time       datetime                                   comment '更新时间',
                                  primary key (column_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表字段';
