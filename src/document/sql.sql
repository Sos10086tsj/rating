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
