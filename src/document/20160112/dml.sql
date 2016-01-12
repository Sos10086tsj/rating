DROP TABLE IF EXISTS `rating`.`attachment`;
CREATE TABLE `rating`.`attachment` (
  `id` BIGINT(20) NOT NULL COMMENT '',
  `file_name` VARCHAR(45) CHARACTER SET 'utf8' NULL COMMENT '',
  `file_path` VARCHAR(45) NULL COMMENT '',
  `file_size` BIGINT(20) NULL COMMENT '',
  `upload_date` TIMESTAMP NULL COMMENT '',
  `upload_user` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');
ALTER TABLE `rating`.`attachment` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '' ;
ALTER TABLE `rating`.`attachment` 
CHANGE COLUMN `file_name` `file_name` VARCHAR(200) NULL COMMENT '' ,
CHANGE COLUMN `file_path` `file_path` VARCHAR(200) NULL DEFAULT NULL COMMENT '' ;

  
ALTER TABLE `rating`.`rating_user_vote` 
ADD COLUMN `excel_id` BIGINT(20) NULL COMMENT '' AFTER `vote_date`;
