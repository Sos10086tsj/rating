--调整资源顺序
update rating.sys_resource set seq = 1.10 where code = 'GROUP_MGMT' and id > 0;
update rating.sys_resource set seq = 1.20 where code = 'USER_MGMT' and id > 0;
update rating.sys_resource set seq = 1.30 where code = 'RATING_MGMT' and id > 0;

CREATE TABLE `rating`.`config` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `property` VARCHAR(45) NULL,
  `property_value` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

  INSERT INTO `rating`.`config` (`property`, `property_value`) VALUES ('STATISTICS_FORMAT', '100');

  ALTER TABLE `rating`.`rating_user_vote_item` 
ADD COLUMN `score` DECIMAL(10,2) NULL AFTER `scorer_position`;


/*查询视图*/
create or replace algorithm = MERGE
view rating.v_score 
as 
select ruvm.id, ruv.tmpl_id, ruv.user_id as voter_id,ruv.group_id as voter_group_id, ruv.position_id as voter_position_id,
ruvm.scorer , ruvm.scorer_group, ruvm.scorer_position,ruvm.option_id, ruvm.score
from
rating.rating_template rt,
rating.rating_user_vote ruv,
rating.rating_user_vote_item ruvm
where 1=1
and ruv.tmpl_id = rt.id
and ruvm.user_vote_id = ruv.id