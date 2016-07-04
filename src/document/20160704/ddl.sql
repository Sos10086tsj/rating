CREATE TABLE `rating`.`rating_result` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `tmpl_id` BIGINT(20) NULL COMMENT '模板ID',
  `voter_id` BIGINT(20) NULL COMMENT '投票人',
  `voter_group_id` BIGINT(20) NULL COMMENT '投票人所在组别',
  `voter_position_id` BIGINT(20) NULL COMMENT '投票人职位',
  `scorer` BIGINT(20) NULL COMMENT '得分人',
  `scorer_group` BIGINT(20) NULL COMMENT '得分人所在组别',
  `scorer_position` BIGINT(20) NULL COMMENT '得分人职位',
  `option_id` BIGINT(20) NULL COMMENT '得分项目',
  `score` DECIMAL(10,2) NULL COMMENT '分数',
  PRIMARY KEY (`id`)  COMMENT '');
