-- ----------------------------
-- 校园二手交易平台 - 商品模块初始化脚本
-- 适用：MySQL 8.x
-- 说明：已有库可直接执行，create table if not exists / insert ignore 不会破坏已有数据。
-- ----------------------------

-- 1. 校区表
create table if not exists biz_campus (
  id          bigint       not null auto_increment comment '校区ID',
  name        varchar(50)  not null                comment '校区名称',
  address     varchar(255) default null            comment '校区详细地址',
  sort        int          default 0               comment '排序',
  status      tinyint      default 1               comment '状态（0停用 1启用）',
  create_by   varchar(64)  default ''              comment '创建者',
  create_time datetime     default null            comment '创建时间',
  update_by   varchar(64)  default ''              comment '更新者',
  update_time datetime     default null            comment '更新时间',
  remark      varchar(500) default null            comment '备注',
  del_flag    tinyint      default 0               comment '删除标志（0未删除 1已删除）',
  primary key (id),
  unique key uk_name (name)
) engine=innodb default charset=utf8mb4 comment='校区表';

-- 2. 商品分类表
create table if not exists biz_product_category (
  id          bigint       not null auto_increment comment '分类ID',
  name        varchar(30)  not null                comment '分类名称',
  parent_id   bigint       default 0               comment '父分类ID（0为一级分类）',
  icon        varchar(255) default null            comment '分类图标',
  sort        int          default 0               comment '排序',
  status      tinyint      default 1               comment '状态（0停用 1启用）',
  create_by   varchar(64)  default ''              comment '创建者',
  create_time datetime     default null            comment '创建时间',
  update_by   varchar(64)  default ''              comment '更新者',
  update_time datetime     default null            comment '更新时间',
  remark      varchar(500) default null            comment '备注',
  del_flag    tinyint      default 0               comment '删除标志（0未删除 1已删除）',
  primary key (id),
  key idx_parent_id (parent_id)
) engine=innodb default charset=utf8mb4 comment='商品分类表';

-- 3. 商品表
create table if not exists biz_product (
  id             bigint        not null auto_increment comment '商品ID',
  seller_id      bigint        not null                comment '卖家用户ID',
  category_id    bigint        not null                comment '分类ID',
  campus_id      bigint        not null                comment '校区ID',
  title          varchar(100)  not null                comment '商品标题',
  description    text                                  comment '商品详情描述',
  price          decimal(10,2) not null                comment '售价',
  original_price decimal(10,2) default null            comment '原价',
  images         varchar(1000) default null            comment '商品图片URL，逗号分隔',
  quality        tinyint       default 3               comment '成色（1全新 2九成新 3八成新 4七成新及以下）',
  trade_type     tinyint       default 1               comment '交易方式（1自提 2邮寄 3面交）',
  status         tinyint       default 1               comment '状态（0下架 1上架 2已售出 3违规下架）',
  view_count     int           default 0               comment '浏览量',
  create_by      varchar(64)   default ''              comment '创建者',
  create_time    datetime      default null            comment '创建时间',
  update_by      varchar(64)   default ''              comment '更新者',
  update_time    datetime      default null            comment '更新时间',
  remark         varchar(500)  default null            comment '备注',
  del_flag       tinyint       default 0               comment '删除标志（0未删除 1已删除）',
  primary key (id),
  key idx_campus_status_time (campus_id, status, create_time),
  key idx_category_status (category_id, status),
  key idx_seller_status (seller_id, status),
  key idx_status (status)
) engine=innodb default charset=utf8mb4 comment='商品表';

-- 已有库补组合索引（只执行一次；重复执行会报 Duplicate key name，可忽略）
-- alter table biz_product add index idx_campus_status_time (campus_id, status, create_time);
-- alter table biz_product add index idx_category_status (category_id, status);
-- alter table biz_product add index idx_seller_status (seller_id, status);

-- 4. 字典类型
insert ignore into sys_dict_type (dict_name, dict_type, status, create_by, create_time, remark) values
('商品状态', 'status',     '0', 'admin', sysdate(), '商品状态'),
('商品成色', 'quality',    '0', 'admin', sysdate(), '商品成色'),
('交易方式', 'trade_type', '0', 'admin', sysdate(), '交易方式'),
('删除标志', 'del_flag',   '0', 'admin', sysdate(), '删除标志');

-- 5. 字典数据
insert ignore into sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, remark) values
(1, '下架',     '0', 'status',     '', 'info',    'Y', '0', 'admin', sysdate(), '商品状态'),
(2, '上架',     '1', 'status',     '', 'primary', 'N', '0', 'admin', sysdate(), '商品状态'),
(3, '已售出',   '2', 'status',     '', 'success', 'N', '0', 'admin', sysdate(), '商品状态'),
(4, '违规下架', '3', 'status',     '', 'danger',  'N', '0', 'admin', sysdate(), '商品状态'),
(1, '全新',         '1', 'quality',    '', 'success', 'Y', '0', 'admin', sysdate(), '商品成色'),
(2, '九成新',       '2', 'quality',    '', 'primary', 'N', '0', 'admin', sysdate(), '商品成色'),
(3, '八成新',       '3', 'quality',    '', 'warning', 'N', '0', 'admin', sysdate(), '商品成色'),
(4, '七成新及以下', '4', 'quality',    '', 'info',    'N', '0', 'admin', sysdate(), '商品成色'),
(1, '自提', '1', 'trade_type', '', 'primary', 'Y', '0', 'admin', sysdate(), '交易方式'),
(2, '邮寄', '2', 'trade_type', '', 'success', 'N', '0', 'admin', sysdate(), '交易方式'),
(3, '面交', '3', 'trade_type', '', 'warning', 'N', '0', 'admin', sysdate(), '交易方式'),
(1, '未删除', '0', 'del_flag', '', 'primary', 'Y', '0', 'admin', sysdate(), '删除标志'),
(2, '已删除', '1', 'del_flag', '', 'danger',  'N', '0', 'admin', sysdate(), '删除标志');

-- 6. 菜单与按钮权限
insert ignore into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) values
(2006, '商品管理', 1, 10, 'product', 'business/product/index', '', '', 1, 0, 'C', '0', '0', 'business:product:list',   'shopping', 'admin', sysdate(), '', null, '商品管理菜单'),
(2007, '商品查询', 2006, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'business:product:query',  '#', 'admin', sysdate(), '', null, ''),
(2008, '商品新增', 2006, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'business:product:add',    '#', 'admin', sysdate(), '', null, ''),
(2009, '商品修改', 2006, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'business:product:edit',   '#', 'admin', sysdate(), '', null, ''),
(2010, '商品删除', 2006, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'business:product:remove', '#', 'admin', sysdate(), '', null, ''),
(2011, '商品导出', 2006, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'business:product:export', '#', 'admin', sysdate(), '', null, '');

-- 7. 菜单授权
-- role_id=1 为 admin 角色；超级管理员 userId=1 不受此限制。
-- 如果商品管理是给其他角色用的，把 1 改成对应 role_id。
insert ignore into sys_role_menu (role_id, menu_id) values
(1, 2006), (1, 2007), (1, 2008), (1, 2009), (1, 2010), (1, 2011);
