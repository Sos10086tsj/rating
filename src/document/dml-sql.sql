--初始化管理员账号
INSERT INTO `rating`.`sys_user` (`username`, `salt`, `name`, `status`) VALUES ('admin', '123456', '管理员', '1');
UPDATE `rating`.`sys_user` SET `password`='ea48576f30be1669971699c09ad05c94' WHERE `id`='1';


INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `seq`) VALUES ('SYS_MGMT', '系统管理', 'system', '1', '1.00');
INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `parent_code`, `seq`) VALUES ('USER_MGMT', '用户管理', 'system/user', '1', 'SYS_MGMT', '1.10');
INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `parent_code`, `seq`) VALUES ('RATING_MGMT', '投票管理', 'system/rating', '1', 'SYS_MGMT', '1.20');
INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `seq`) VALUES ('RATING', '投票', 'rating', '1', '2.00');
INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `parent_code`, `seq`) VALUES ('RATING_VOTE', '参与投票', 'rating', '1', 'RATING', '2.01');
INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `parent_code`, `seq`) VALUES ('RATING_STATISTICS', '投票统计', 'rating/statistics', '1', 'RATING', '2.02');
INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `parent_code`, `seq`) VALUES ('GROUP_MGMT', '组管理', 'system/group', '1', 'SYS_MGMT', '1.30');

INSERT INTO `rating`.`sys_authority` (`code`, `name`, `show`) VALUES ('AU_SYS_MGMT', '系统管理', '1');
INSERT INTO `rating`.`sys_authority` (`code`, `name`, `show`) VALUES ('AU_USER_MGMT', '用户管理', '1');
INSERT INTO `rating`.`sys_authority` (`code`, `name`, `show`) VALUES ('AU_RATING_MGMT', '投票管理', '1');
INSERT INTO `rating`.`sys_authority` (`code`, `name`, `show`) VALUES ('AU_RATING', '投票权限', '1');
INSERT INTO `rating`.`sys_authority` (`code`, `name`, `show`) VALUES ('AU_RATING_VOTE', '参与投票', '1');
INSERT INTO `rating`.`sys_authority` (`code`, `name`, `show`) VALUES ('AU_RATING_STATISTICS', '投票统计', '1');
INSERT INTO `rating`.`sys_authority` (`code`, `name`, `show`) VALUES ('AU_GROUP_MGMT', '组/部门管理', '1');

INSERT INTO `rating`.`sys_operation` (`code`, `name`, `show`) VALUES ('MGMT', '管理', '1');

INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) VALUES ('SYS_MGMT', 'AU_SYS_MGMT', 'MGMT');
INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) VALUES ('USER_MGMT', 'AU_USER_MGMT', 'MGMT');
INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) VALUES ('RATING_MGMT', 'AU_RATING_MGMT', 'MGMT');
INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) VALUES ('RATING', 'AU_RATING', 'MGMT');
INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) VALUES ('RATING_VOTE', 'AU_RATING_VOTE', 'MGMT');
INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) VALUES ('RATING_STATISTICS', 'AU_RATING_STATISTICS', 'MGMT');
INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) VALUES ('GROUP_MGMT', 'AU_GROUP_MGMT', 'MGMT');

INSERT INTO `rating`.`sys_role` (`name`) VALUES ('管理员');
INSERT INTO `rating`.`sys_role` (`name`) VALUES ('DEFAULT');

INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('1', '1');
INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('1', '2');
INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('1', '3');
INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('1', '4');
INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('1', '5');
INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('1', '6');
INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('1', '7');
INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('2', '4');
INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('2', '5');
INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) VALUES ('2', '6');
