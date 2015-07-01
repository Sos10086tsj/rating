
  ALTER TABLE `rating`.`rating_user_vote_item` 
ADD COLUMN `scorer_group` BIGINT NULL AFTER `scorer`,
ADD COLUMN `scorer_position` INT NULL AFTER `scorer_group`;
