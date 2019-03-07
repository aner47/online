/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.24 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

insert into `sys_menu` (`id`, `create_date`, `modify_date`, `version`, `icon`, `name`, `perms`, `pid`, `sort`, `url`) values('1','2017-04-11 11:15:20','2017-04-11 11:15:22','0',NULL,'系统管理',NULL,'0','0',NULL);
insert into `sys_menu` (`id`, `create_date`, `modify_date`, `version`, `icon`, `name`, `perms`, `pid`, `sort`, `url`) values('11','2017-04-11 11:27:08','2017-04-11 11:27:10','0','icon-caidanguanli','菜单管理','admin:systemmenu:list,admin:systemmenu:viewPage,admin:systemmenu:query,admin:systemmenu:addPage,admin:systemmenu:querymenu,ad','15','0','/admin/systemmenu/list.jhtml');
insert into `sys_menu` (`id`, `create_date`, `modify_date`, `version`, `icon`, `name`, `perms`, `pid`, `sort`, `url`) values('12','2017-04-11 11:36:52','2017-04-11 11:50:09','1','icon-shujuchaxun','字典管理','','1','0','/admin/dictionary/list.jhtml');
insert into `sys_menu` (`id`, `create_date`, `modify_date`, `version`, `icon`, `name`, `perms`, `pid`, `sort`, `url`) values('13','2017-04-11 11:38:46','2017-04-11 11:38:46','0','icon-shujuchaxun','角色管理',NULL,'15','0','/admin/systemrole/list.jhtml');
insert into `sys_menu` (`id`, `create_date`, `modify_date`, `version`, `icon`, `name`, `perms`, `pid`, `sort`, `url`) values('14','2017-04-11 11:44:01','2017-04-11 11:44:01','0','icon-shujuchaxun','用户管理',NULL,'15','0','/admin/systemuser/list.jhtml');
insert into `sys_menu` (`id`, `create_date`, `modify_date`, `version`, `icon`, `name`, `perms`, `pid`, `sort`, `url`) values('15','2017-04-11 11:45:42','2017-04-11 11:45:42','0','icon-shujuchaxun','权限管理',NULL,'1','0','12');
insert into `sys_menu` (`id`, `create_date`, `modify_date`, `version`, `icon`, `name`, `perms`, `pid`, `sort`, `url`) values('16','2017-04-11 11:45:42','2017-04-11 11:45:42','0','icon-shujuchaxun','demo示例',NULL,'1','0',NULL);
insert into `sys_menu` (`id`, `create_date`, `modify_date`, `version`, `icon`, `name`, `perms`, `pid`, `sort`, `url`) values('17','2017-04-11 11:45:42','2017-04-11 11:45:42','0','icon-shujuchaxun','tab',NULL,'16','0','/demo/tab/list.jhtml');
insert into `sys_menu` (`id`, `create_date`, `modify_date`, `version`, `icon`, `name`, `perms`, `pid`, `sort`, `url`) values('18','2017-04-13 20:54:32','2017-04-13 20:54:32','0','icon-shujuchaxun','Demo-Grid',NULL,'16','0','/demo/grid/list.jhtml');
