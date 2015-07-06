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
