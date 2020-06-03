create schema `dimpleblog-vue` collate utf8mb4_0900_ai_ci;

create table blacklist
(
	id bigint auto_increment comment 'id'
		primary key,
	ip varchar(128) null comment 'ip地址',
	description varchar(256) null comment '封禁描述',
	intercept_count bigint null comment '封禁后再访问次数',
	last_access_url varchar(256) null comment '上次访问的url',
	last_access_time datetime null comment '上次访问时间',
	create_by varchar(150) null,
	create_time datetime null,
	update_by varchar(150) null,
	update_time datetime null,
	delete_by varchar(150) null,
	delete_time datetime null
)
comment '系统黑名单' charset=utf8;

create table blog
(
	id bigint auto_increment comment ' id'
		primary key,
	category_id bigint null comment ' 分类id ',
	title varchar(150) charset utf8 null comment ' 文章标题 ',
	summary varchar(256) charset utf8 null comment ' 摘要 ',
	header_img_type int not null,
	header_img varchar(256) charset utf8 null comment ' 封面图片地址 ',
	html_content longtext null,
	content longtext charset utf8 null comment ' 正文内容 ',
	status tinyint null comment ' 文章状态,
    1表示已经发表,
    2表示草稿箱 ',
	comment tinyint null comment ' 是否允许评论,
    1表示允许,
    0表示不允许 ',
	support tinyint default 0 null comment ' 推荐 ',
	weight bigint default 1 null comment ' 权重 ',
	`like` bigint default 0 null comment ' 点赞数 ',
	click bigint default 0 null comment ' 点击数 ',
	create_by varchar(150) charset utf8 null,
	create_time datetime null,
	update_by varchar(150) charset utf8 null,
	update_time datetime null,
	delete_time datetime null,
	delete_by varchar(150) charset utf8 null
)
comment '博客表';

create index blog_delete_time_index
	on blog (delete_time);

create index blog_id_support_status_index
	on blog (id, support, status);

create table carousel
(
	id bigint auto_increment
		primary key,
	description varchar(512) null comment '显示文本',
	click bigint null comment '点击次数',
	header_img varchar(256) null comment '图片URL',
	display tinyint null comment '是否显示',
	target tinyint null comment '是否当前窗口打开',
	url varchar(128) null comment '链接地址',
	title varchar(128) null comment 'header',
	create_by varchar(128) null,
	create_time datetime null,
	update_by varchar(128) null,
	delete_by varchar(128) null,
	update_time datetime null,
	delete_time datetime null
)
comment '轮播图' charset=utf8;

create table category
(
	id bigint auto_increment
		primary key,
	title varchar(128) null comment '分类名称',
	description varchar(256) null comment '描述',
	support tinyint null comment '是否推荐',
	create_by varchar(128) null,
	create_time datetime null,
	update_by varchar(128) null,
	update_time datetime null,
	delete_by varchar(128) null,
	delete_time datetime null
)
comment '分类表' charset=utf8;

create table comment
(
	id bigint auto_increment
		primary key,
	nick_name varchar(128) null comment '昵称',
	email varchar(128) null comment 'Email地址',
	ip varchar(50) null comment 'IP地址
',
	location varchar(128) null comment '地理位置',
	os varchar(128) null comment '系统',
	browser varchar(128) null comment '浏览器',
	parent_id bigint null comment '父评论的id',
	qq_num varchar(128) null comment 'QQ号码',
	avatar varchar(256) null comment '头像地址',
	page_id bigint null comment '页面ID',
	url varchar(128) null comment '页面的URL
',
	display tinyint null comment '1表示显示,0表示不显示',
	good bigint default 0 null comment '点赞
',
	bad bigint default 0 null comment '踩',
	content varchar(2048) null comment '评论内容',
	create_by varchar(128) null,
	create_time datetime null,
	update_by varchar(128) null,
	update_time datetime null,
	delete_by varchar(128) null,
	delete_time datetime null,
	html_content longtext null,
	reply tinyint null,
	admin_reply tinyint null,
	reply_id bigint null
)
comment '评论表' charset=utf8;

create table config
(
	id int auto_increment comment '参数主键'
		primary key,
	config_key varchar(100) default '' null comment '参数键名',
	config_value varchar(500) default '' null comment '参数键值',
	remark varchar(500) null comment '备注',
	create_by varchar(64) default '' null comment '创建者',
	create_time datetime null comment '创建时间',
	update_by varchar(64) default '' null comment '更新者',
	update_time datetime null comment '更新时间',
	delete_by varchar(64) default '' null,
	delete_time datetime null
)
comment '参数配置表' charset=utf8;

create table dict_data
(
	dict_code bigint auto_increment comment '字典编码'
		primary key,
	dict_sort int default 0 null comment '字典排序',
	dict_label varchar(100) default '' null comment '字典标签',
	dict_value varchar(100) default '' null comment '字典键值',
	dict_type varchar(100) default '' null comment '字典类型',
	css_class varchar(100) null comment '样式属性（其他样式扩展）',
	list_class varchar(100) null comment '表格回显样式',
	is_default char default 'N' null comment '是否默认（Y是 N否）',
	status char default '0' null comment '状态（0正常 1停用）',
	create_by varchar(64) default '' null comment '创建者',
	create_time datetime null comment '创建时间',
	update_by varchar(64) default '' null comment '更新者',
	update_time datetime null comment '更新时间',
	delete_by varchar(64) default '' null comment '刪除者',
	delete_time datetime null comment '删除时间',
	remark varchar(500) null comment '备注'
)
comment '字典数据表' charset=utf8;

create table dict_type
(
	id bigint auto_increment comment '字典主键'
		primary key,
	dict_name varchar(100) default '' null comment '字典名称',
	dict_type varchar(100) default '' null comment '字典类型',
	status char default '0' null comment '状态（0正常 1停用）',
	create_by varchar(64) default '' null comment '创建者',
	create_time datetime null comment '创建时间',
	update_by varchar(64) default '' null comment '更新者',
	update_time datetime null comment '更新时间',
	delete_by varchar(64) default '' null comment '刪除者',
	delete_time datetime null comment '删除时间',
	remark varchar(500) null comment '备注',
	constraint dict_type
		unique (dict_type)
)
comment '字典类型表' charset=utf8;

create table link
(
	id bigint auto_increment comment 'id'
		primary key,
	title varchar(128) null comment '友链名称',
	url varchar(128) null comment '友链地址',
	description varchar(512) null comment '友链描述',
	header_img varchar(128) null comment '网站图片',
	status tinyint default 0 null comment '1表示审核通过,0表示未审核',
	display tinyint default 0 null comment '是否显示友链',
	email varchar(128) null comment '站长邮箱地址',
	weight bigint default 0 null comment '权重',
	create_by varchar(128) null,
	create_time datetime null,
	update_by varchar(128) null,
	update_time datetime null,
	delete_by varchar(128) null,
	delete_time datetime null,
	support tinyint default 0 null
)
comment '友链表' charset=utf8;

create table local_storage
(
	id bigint auto_increment
		primary key,
	real_name varchar(256) null comment '文件真实名称',
	name varchar(256) null comment '文件名',
	suffix varchar(128) null comment '后缀',
	path varchar(256) null comment '路径',
	type varchar(128) null comment '文件类型',
	size varchar(128) null comment '大小',
	create_by varchar(128) null,
	create_time datetime null,
	update_time datetime null,
	update_by varchar(128) null,
	delete_by varchar(128) null,
	delete_time datetime null
)
comment '本地存储' charset=utf8;

create table login_log
(
	id bigint auto_increment comment '访问ID'
		primary key,
	user_name varchar(50) default '' null comment '用户账号',
	ip varchar(50) default '' null comment '登录IP地址',
	location varchar(255) default '' null comment '登录地点',
	browser varchar(50) default '' null comment '浏览器类型',
	os varchar(50) default '' null comment '操作系统',
	status tinyint default 0 null comment '登录状态（1成功 0失败）',
	msg varchar(255) default '' null comment '提示消息',
	create_time datetime null comment '访问时间',
	create_by varchar(128) null,
	delete_by varchar(128) null,
	delete_time datetime null
)
comment '系统访问记录' charset=utf8;

create table menu
(
	id bigint auto_increment comment '菜单ID'
		primary key,
	menu_name varchar(50) not null comment '菜单名称',
	parent_id bigint default 0 null comment '父菜单ID',
	order_num int default 0 null comment '显示顺序',
	path varchar(200) default '' null comment '路由地址',
	component varchar(255) null comment '组件路径',
	is_frame int default 1 null comment '是否为外链（0是 1否）',
	menu_type char default '' null comment '菜单类型（0目录 1菜单 2按钮）',
	visible char default '0' null comment '菜单状态（0显示 1隐藏）',
	perms varchar(100) null comment '权限标识',
	icon varchar(100) default '#' null comment '菜单图标',
	create_by varchar(64) default '' null comment '创建者',
	create_time datetime null comment '创建时间',
	update_by varchar(64) default '' null comment '更新者',
	update_time datetime null comment '更新时间',
	delete_by varchar(64) default '' null comment '刪除者',
	delete_time datetime null comment '删除时间',
	remark varchar(500) default '' null comment '备注'
)
comment '菜单权限表' charset=utf8;

create table notice
(
	id int auto_increment comment '公告ID'
		primary key,
	title varchar(50) not null comment '公告标题',
	type char not null comment '公告类型（1通知 2公告）',
	content varchar(2000) null comment '公告内容',
	html_content varchar(2500) null,
	status char default '0' null comment '公告状态（0正常 1关闭）',
	create_by varchar(64) default '' null comment '创建者',
	create_time datetime null comment '创建时间',
	update_by varchar(64) default '' null comment '更新者',
	update_time datetime null comment '更新时间',
	delete_by varchar(64) default '' null comment '刪除者',
	delete_time datetime null comment '删除时间',
	remark varchar(255) null comment '备注'
)
comment '通知公告表' charset=utf8;

create table operate_log
(
	id bigint auto_increment comment '日志主键'
		primary key,
	title varchar(50) default '' null comment '模块标题',
	business_type int default 0 null comment '业务类型（0其它 1新增 2修改 3删除）',
	method varchar(100) default '' null comment '方法名称',
	request_method varchar(10) default '' null comment '请求方式',
	operator_type int default 0 null comment '操作类别（0其它 1后台用户 2手机端用户）',
	operate_name varchar(50) default '' null comment '操作人员',
	url varchar(255) default '' null comment '请求URL',
	ip varchar(50) default '' null comment '主机地址',
	location varchar(255) default '' null comment '操作地点',
	param varchar(2000) default '' null comment '请求参数',
	json_result varchar(2000) default '' null comment '返回参数',
	status int default 0 null comment '操作状态（0正常 1异常）',
	error_msg varchar(2000) default '' null comment '错误消息',
	cost bigint null comment '耗时,单位毫秒',
	create_time datetime null comment '操作时间',
	create_by varchar(128) null,
	delete_by varchar(128) null,
	delete_time datetime null
)
comment '操作日志记录' charset=utf8;

create table qi_niu_content
(
	id bigint auto_increment
		primary key,
	name varchar(128) null comment '七牛云文件名称',
	bucket varchar(128) null comment '空间',
	size varchar(64) null comment '文件大小',
	url varchar(128) null comment '文件访问地址',
	suffix varchar(64) null comment '文件后缀',
	type varchar(64) null comment '类型',
	create_by varchar(128) null,
	create_time datetime null,
	update_time datetime null,
	update_by varchar(128) null,
	delete_by varchar(128) null,
	delete_time datetime null
)
comment '七牛云数据本地缓存' charset=utf8;

create table quartz_job
(
	id bigint auto_increment comment 'ID'
		primary key,
	job_name varchar(255) null comment '任务名称',
	bean_name varchar(255) null comment 'Spring Bean名称',
	method_name varchar(255) null comment '方法名称',
	method_params varchar(255) null comment '参数',
	cron_expression varchar(255) null comment 'cron 表达式',
	status tinyint null comment '状态:true表示运行,false表示暂停',
	remark varchar(255) null comment '备注',
	create_time datetime null comment '创建日期',
	create_by varchar(128) null,
	update_by varchar(128) null,
	update_time datetime null,
	delete_by varchar(128) null,
	delete_time datetime null
)
comment '定时任务表' charset=utf8;

create table quartz_log
(
	id bigint auto_increment
		primary key,
	job_name varchar(255) null comment '任务名称',
	bean_name varchar(255) null comment 'Bean的名称',
	method_name varchar(255) null comment '方法名称',
	method_params varchar(255) null comment '参数',
	cron_expression varchar(255) null comment 'corn表达式',
	exception text null comment '异常信息',
	result text null comment '返回结果值',
	status tinyint null comment 'true表示成功,false表示失败',
	cost bigint null comment '耗时',
	create_time datetime null,
	create_by varchar(128) null,
	delete_time datetime null,
	delete_by varchar(128) null
)
comment 'Job日志' charset=utf8;

create table role
(
	id bigint auto_increment comment '角色ID'
		primary key,
	role_name varchar(30) not null comment '角色名称',
	role_key varchar(100) not null comment '角色权限字符串',
	role_sort int not null comment '显示顺序',
	status char not null comment '角色状态（0正常 1停用）',
	create_by varchar(64) default '' null comment '创建者',
	create_time datetime null comment '创建时间',
	update_by varchar(64) default '' null comment '更新者',
	update_time datetime null comment '更新时间',
	delete_by varchar(64) default '' null comment '刪除者',
	delete_time datetime null comment '删除时间',
	remark varchar(500) null comment '备注'
)
comment '角色信息表' charset=utf8;

create table role_menu
(
	role_id bigint not null comment '角色ID',
	menu_id bigint not null comment '菜单ID',
	primary key (role_id, menu_id)
)
comment '角色和菜单关联表' charset=utf8;

create table sys_user_role
(
	user_id bigint not null comment '用户ID',
	role_id bigint not null comment '角色ID',
	primary key (user_id, role_id)
)
comment '用户和角色关联表' charset=utf8;

create table tag
(
	id bigint auto_increment
		primary key,
	color varchar(32) null comment '标签轮廓颜色',
	title varchar(128) null comment '标签名',
	create_by varchar(128) null,
	create_time datetime null,
	update_by varchar(128) null,
	update_time datetime null,
	delete_by varchar(128) null,
	delete_time datetime null,
	constraint bg_tag_title_uindex
		unique (title)
)
comment '博客标签表' charset=utf8;

create table tag_mapping
(
	id bigint auto_increment
		primary key,
	tag_id bigint null,
	blog_id bigint null
);

create table user
(
	id bigint auto_increment comment '用户ID'
		primary key,
	user_name varchar(30) not null comment '用户账号',
	nick_name varchar(30) not null comment '用户昵称',
	user_type varchar(2) default '00' null comment '用户类型（00系统用户）',
	email varchar(50) default '' null comment '用户邮箱',
	phone varchar(11) default '' null comment '手机号码',
	sex char default '0' null comment '用户性别（0男 1女 2未知）',
	avatar varchar(100) default '' null comment '头像地址',
	password varchar(100) default '' null comment '密码',
	status char default '0' null comment '帐号状态（0正常 1停用）',
	login_ip varchar(50) default '' null comment '最后登陆IP',
	login_date datetime null comment '最后登陆时间',
	create_by varchar(64) default '' null comment '创建者',
	create_time datetime null comment '创建时间',
	update_by varchar(64) default '' null comment '更新者',
	update_time datetime null comment '更新时间',
	delete_by varchar(64) default '' null comment '刪除者',
	delete_time datetime null comment '删除时间',
	remark varchar(500) null comment '备注'
)
comment '用户信息表' charset=utf8;

create table visit_log
(
	id bigint auto_increment
		primary key,
	ip varchar(128) null comment 'IP地址',
	page_id mediumtext null comment '页面ID',
	location varchar(128) null comment '地理位置',
	browser varchar(128) null comment '浏览器',
	os varchar(128) null comment '操作系统',
	entry_url varchar(256) null,
	url varchar(128) null comment '访问URL地址',
	error_msg varchar(2000) null,
	status tinyint null comment '状态,1表示成功,0表示失败',
	title varchar(128) null comment '访问模块',
	spider varchar(128) null comment '爬虫',
	create_time datetime null,
	delete_time datetime null,
	create_by varchar(128) null,
	delete_by varchar(128) null
)
comment '访问日志表';

