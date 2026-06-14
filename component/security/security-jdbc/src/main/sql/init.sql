-- 插入用户数据
insert into sys_user (id, user_name, nick_name, `password`, `status`, email, phone_number, sex, avatar, user_type, create_by, create_time, update_by, update_time, del_flag) values (1, 'zhangsan', '张三', '$2a$10$3U6iz34v.C4jMqNOuKDT/OkJ3uQfNf7DDCynfyUzJkDXef1Gsv5AO', '0', 'hly@itcast.cn', '1312103105', '0', 'http://www.itcast.cn', '1', 1, '2022-03-08 09:12:06', 1, '2022-03-08 09:12:06', 0);
insert into sys_user (id, user_name, nick_name, `password`, `status`, email, phone_number, sex, avatar, user_type, create_by, create_time, update_by, update_time, del_flag) values (2, 'admin', '系统用户', '$2a$10$3U6iz34v.C4jMqNOuKDT/OkJ3uQfNf7DDCynfyUzJkDXef1Gsv5AO', '0', 'hly@itcast.cn', '1312103105', '0', 'http://www.itcast.cn', '1', 1, '2022-03-08 09:12:06', 1, '2022-03-08 09:12:06', 0);

-- 插入菜单数据
insert into sys_menu (id, menu_name, path, component, visible, status, perms, icon, create_by, create_time, update_by, update_time, del_flag, remark) values (1, '添加用户', '/user/addUser', 'addUser', '0', '0', 'system:user:add', 'icon-add', 1, '2022-07-04 11:20:57', 1, '2022-07-04 11:20:57', 0, '添加用户按钮');
insert into sys_menu (id, menu_name, path, component, visible, status, perms, icon, create_by, create_time, update_by, update_time, del_flag, remark) values (2, '查看用户列表', '/user/userList', 'userList', '0', '0', 'system:user:list', 'icon-list', 1, '2022-07-04 11:22:06', 1, '2022-07-04 11:22:06', 0, '查看用户列表用户按钮');


-- 插入角色表数据
insert into sys_role (id, name, role_key, status, del_flag, create_by, create_time, update_by, update_time, remark) values (1, '系统管理员', 'admin', '0', 0, 1, '2022-07-04 19:25:06', 1, '2022-07-04 19:25:19', '系统管理员');
insert into sys_role (id, name, role_key, status, del_flag, create_by, create_time, update_by, update_time, remark) values (2, '普通用户', 'user', '0', 0, 1, '2022-07-04 19:25:48', 1, '2022-07-04 19:25:52', '普通用户角色');

# 插入基础数据
insert into sys_user_role (user_id, role_id) values (1, 2);
insert into sys_user_role (user_id, role_id) values (2, 1);

# 插入基础测试数据
insert into sys_role_menu (role_id, menu_id) values (1, 1);
insert into sys_role_menu (role_id, menu_id) values (1, 2);
insert into sys_role_menu (role_id, menu_id) values (2, 2);