--A卷权重
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(1,1,12.5);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(1,2,12.5);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(1,3,12.5);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(1,4,12.5);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(1,5,12.5);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(1,6,12.5);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(1,7,12.5);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(1,8,12.5);

--B卷权重
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,1,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,2,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,3,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,4,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,5,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,6,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,7,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,8,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,9,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,10,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,11,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,12,7.69);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(2,13,7.69);

--C卷权重
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,14,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,15,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,16,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,17,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,18,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,19,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,20,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,21,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,22,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,23,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,24,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(3,25,8.33);

--D卷权重
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,14,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,15,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,16,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,17,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,18,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,19,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,20,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,21,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,22,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,23,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,24,8.33);
insert into rating.rating_supp_tmpl_option_weight(supp_tmpl_id,supp_option_id,weight) values(4,25,8.33);

--用户投票记录表
CREATE TABLE `rating`.`rating_user_vote` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `rating_id` BIGINT NULL,
  `tmpl_id` BIGINT NULL,
  `user_id` BIGINT NULL,
  `vote_date` TIMESTAMP NULL,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

ALTER TABLE `rating`.`rating_user_vote` 
ADD COLUMN `group_id` BIGINT NULL AFTER `user_id`,
ADD COLUMN `position_id` INT NULL AFTER `group_id`;

  
--投票详情
  CREATE TABLE `rating`.`rating_user_vote_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_vote_id` BIGINT NULL,
  `option_id` BIGINT NULL,
  `score_id` BIGINT NULL,
  `scorer` BIGINT NULL,
  PRIMARY KEY (`id`));

--得分可选项
CREATE TABLE `rating`.`rating_score` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(45) NULL,
  `score` INT NULL,
  `version` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`id`));
  
  ALTER TABLE `rating`.`rating_score` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT ;

--初始化数据
INSERT INTO `rating`.`rating_score` (`name`, `score`) VALUES ('表现突出', '5');
INSERT INTO `rating`.`rating_score` (`name`, `score`) VALUES ('表现较好', '4');
INSERT INTO `rating`.`rating_score` (`name`, `score`) VALUES ('表现一般', '3');
INSERT INTO `rating`.`rating_score` (`name`, `score`) VALUES ('表现较差', '2');
INSERT INTO `rating`.`rating_score` (`name`, `score`) VALUES ('表现很差', '1');
INSERT INTO `rating`.`rating_score` (`name`, `score`) VALUES ('表现极差', '0');


