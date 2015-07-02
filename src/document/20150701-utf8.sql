
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


/*查询视图*/
create or replace algorithm = MERGE
view v_score 
as 
select ruvm.id, ruv.tmpl_id, ruv.user_id as voter_id,ruv.group_id as voter_group_id, ruv.position_id as voter_position_id,
ruvm.scorer , ruvm.scorer_group, ruvm.scorer_position,ruvm.option_id, score.score
from
rating.rating_template rt,
rating.rating_user_vote ruv,
rating.rating_user_vote_item ruvm,
rating.rating_score score
where 1=1
and ruv.tmpl_id = rt.id
and ruvm.user_vote_id = ruv.id
and score.id = ruvm.score_id;