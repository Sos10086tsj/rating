CREATE SCHEMA `rating` DEFAULT CHARACTER SET utf8 ;

--用户表
CREATE TABLE `rating`.`sys_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `salt` VARCHAR(20) NULL,
  `password` VARCHAR(64) NULL,
  `name` VARCHAR(45) CHARACTER SET 'utf8' NULL,
  `status` INT NULL,
  `phone` VARCHAR(45) NULL,
  `group_id` BIGINT NULL,
  `position_id` BIGINT NULL COMMENT '职位',
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `INDEX_USERNAME` (`username` ASC));

--用户组
 CREATE TABLE `rating`.`sys_group` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `level` INT NULL,
  `name` VARCHAR(45) CHARACTER SET 'utf8' NULL,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

--用户session
  CREATE TABLE `rating`.`sys_user_session` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `session_id` VARCHAR(64) NULL,
  `create_date` TIMESTAMP NULL,
  PRIMARY KEY (`id`));
ALTER TABLE `rating`.`sys_user_session` 
ADD INDEX `INDEX_SYS_USER_SESSION` (`session_id` ASC);

--初始化管理员账号
INSERT INTO `rating`.`sys_user` (`username`, `salt`, `name`, `status`) VALUES ('admin', '123456', '管理员', '1');
UPDATE `rating`.`sys_user` SET `password`='ea48576f30be1669971699c09ad05c94' WHERE `id`='1';


--系统资源表
CREATE TABLE `rating`.`sys_resource` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  `show` TINYINT(1) NULL DEFAULT 1,
  `parent_code` VARCHAR(45) NULL,
  `seq` DECIMAL(2) NULL,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `SYS_RESOURCE_PK_INDEX` (`code` ASC));

ALTER TABLE `rating`.`sys_resource` 
CHANGE COLUMN `seq` `seq` DECIMAL(10,2) NULL DEFAULT NULL ;

INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `seq`) VALUES ('SYS_MGMT', '系统管理', 'system', '1');
INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `parent_code`, `seq`) VALUES ('USER_MGMT', '用户管理', 'system/user', 'SYS_MGMT', '1.1');

CREATE TABLE `rating`.`sys_authority` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `show` TINYINT(1) NULL DEFAULT 1,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `SYS_AUTHORITY_PK_INDEX` (`code` ASC));
INSERT INTO `rating`.`sys_authority` (`code`, `name`) VALUES ('AU_SYS_MGMT', '系统管理');
INSERT INTO `rating`.`sys_authority` (`code`, `name`) VALUES ('AU_USER_MGMT', '用户管理');

CREATE TABLE `rating`.`sys_operation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `show` TINYINT(1) NULL DEFAULT 1,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `SYS_OPR_PK_INDEX` (`code` ASC));
INSERT INTO `rating`.`sys_operation` (`code`, `name`) VALUES ('MGMT', '管理');

CREATE TABLE `rating`.`sys_res_au_opr_mapping` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `res_code` VARCHAR(45) NULL,
  `auth_code` VARCHAR(45) NULL,
  `opr_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) VALUES ('SYS_MGMT', 'AU_SYS_MGMT', 'MGMT');
INSERT INTO `rating`.`sys_res_au_opr_mapping` (`res_code`, `auth_code`, `opr_code`) VALUES ('USER_MGMT', 'AU_USER_MGMT', 'MGMT');


CREATE TABLE `rating`.`sys_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `rating`.`sys_role_auth_mapping` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT NULL,
  `auth_id` BIGINT NULL,
  PRIMARY KEY (`id`));