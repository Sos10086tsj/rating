ALTER TABLE `rating`.`rating` 
ADD COLUMN `create_date` TIMESTAMP not null  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '' AFTER `version`;

CREATE TABLE `rating`.`rating_statistics_file` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `tmpl_ids` VARCHAR(45) NULL COMMENT '',
  `statistics_date` TIMESTAMP NULL COMMENT '',
  `attach_id` BIGINT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');

  
  ALTER TABLE `rating`.`rating_statistics_file` 
ADD COLUMN `version` BIGINT NULL DEFAULT 0 COMMENT '' AFTER `attach_id`;
