
  ALTER TABLE `rating`.`rating_user_vote_item` 
ADD COLUMN `scorer_group` BIGINT NULL AFTER `scorer`,
ADD COLUMN `scorer_position` INT NULL AFTER `scorer_group`;


ALTER TABLE `rating`.`rating_supp_template` 
ADD COLUMN `code` VARCHAR(45) NULL AFTER `id`;
UPDATE `rating`.`rating_supp_template` SET `code`='A' WHERE `id`='1';
UPDATE `rating`.`rating_supp_template` SET `code`='B' WHERE `id`='2';
UPDATE `rating`.`rating_supp_template` SET `code`='C' WHERE `id`='3';
UPDATE `rating`.`rating_supp_template` SET `code`='D' WHERE `id`='4';

ALTER TABLE `rating`.`rating_template` 
ADD COLUMN `code` VARCHAR(45) NULL AFTER `id`;
