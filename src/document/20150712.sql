ALTER TABLE `rating`.`rating_tmpl_option_weight` 
ADD COLUMN `origin_supp_tmpl_id` BIGINT NULL AFTER `weight`;


INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `parent_code`, `seq`, `version`) 
 SELECT 'OPTION_MGMT', '得分项管理', 'system/option', '1', 'SYS_MGMT', '1.40', '0' FROM DUAL 
 WHERE NOT EXISTS (SELECT 1 FROM `rating`.`sys_resource` WHERE code ='OPTION_MGMT');
 
INSERT INTO `rating`.`sys_authority` (`code`, `name`, `show`) 
 SELECT 'AU_OPTION_MGMT', '得分项管理权限', '1' FROM DUAL 
 WHERE NOT EXISTS (SELECT 1 FROM `rating`.`sys_authority` WHERE code ='AU_OPTION_MGMT');

INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) 
 SELECT 'OPTION_MGMT', 'AU_OPTION_MGMT', 'MGMT' FROM DUAL 
 WHERE NOT EXISTS (SELECT 1 FROM `rating`.`sys_res_au_opr_mapping` WHERE res_code ='OPTION_MGMT');

INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) 
 SELECT (select id from `rating`.`sys_role` where name ='管理员'),(SELECT id FROM `rating`.`sys_authority` WHERE code ='AU_OPTION_MGMT') FROM DUAL
 WHERE NOT EXISTS (SELECT 1 FROM `rating`.`sys_role_auth_mapping` WHERE role_id =(select id from `rating`.`sys_role` where name ='管理员') 
 and auth_id = (SELECT id FROM `rating`.`sys_authority` WHERE code ='AU_OPTION_MGMT'));

 
INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `parent_code`, `seq`, `version`) 
 SELECT 'TEMPLATE_MGMT', '模板管理', 'system/template', '1', 'SYS_MGMT', '1.50', '0' FROM DUAL 
 WHERE NOT EXISTS (SELECT 1 FROM `rating`.`sys_resource` WHERE code ='TEMPLATE_MGMT');
 
INSERT INTO `rating`.`sys_authority` (`code`, `name`, `show`) 
 SELECT 'AU_TEMPLATE_MGMT', '模板管理权限', '1' FROM DUAL 
 WHERE NOT EXISTS (SELECT 1 FROM `rating`.`sys_authority` WHERE code ='AU_TEMPLATE_MGMT');

INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) 
 SELECT 'TEMPLATE_MGMT', 'AU_TEMPLATE_MGMT', 'MGMT' FROM DUAL 
 WHERE NOT EXISTS (SELECT 1 FROM `rating`.`sys_res_au_opr_mapping` WHERE res_code ='TEMPLATE_MGMT');

INSERT INTO `rating`.`sys_role_auth_mapping` (`role_id`, `auth_id`) 
 SELECT (select id from `rating`.`sys_role` where name ='管理员'),(SELECT id FROM `rating`.`sys_authority` WHERE code ='AU_TEMPLATE_MGMT') FROM DUAL
 WHERE NOT EXISTS (SELECT 1 FROM `rating`.`sys_role_auth_mapping` WHERE role_id =(select id from `rating`.`sys_role` where name ='管理员') 
 and auth_id = (SELECT id FROM `rating`.`sys_authority` WHERE code ='AU_TEMPLATE_MGMT'));
