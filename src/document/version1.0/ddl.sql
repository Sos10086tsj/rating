drop schema if exists `rating`;
CREATE SCHEMA `rating` DEFAULT CHARACTER SET utf8 ;

drop table if exists `rating`.`config`;
CREATE TABLE `rating`.`config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `property` varchar(45) DEFAULT NULL,
  `property_value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_authority`;
CREATE TABLE `rating`.`sys_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `show` tinyint(1) DEFAULT '1',
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `SYS_AUTHORITY_PK_INDEX` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_group`;
CREATE TABLE `rating`.`sys_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_operation`;
CREATE TABLE `rating`.`sys_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `show` tinyint(1) DEFAULT '1',
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `SYS_OPR_PK_INDEX` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_resource`;
CREATE TABLE `rating`.`sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `show` tinyint(1) DEFAULT '1',
  `parent_code` varchar(45) DEFAULT NULL,
  `seq` decimal(10,2) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `SYS_RESOURCE_PK_INDEX` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_res_au_opr_mapping`;
CREATE TABLE `rating`.`sys_res_au_opr_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `res_code` varchar(45) DEFAULT NULL,
  `auth_code` varchar(45) DEFAULT NULL,
  `opr_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mw2nsuvt1vrh21qyipbc5csn4` (`auth_code`),
  KEY `FK_hb59skpf34gvcri1hvc06nw6w` (`opr_code`),
  KEY `FK_t6g592n66q76t7jlw0egjclkg` (`res_code`),
  CONSTRAINT `FK_hb59skpf34gvcri1hvc06nw6w` FOREIGN KEY (`opr_code`) REFERENCES `sys_operation` (`code`),
  CONSTRAINT `FK_mw2nsuvt1vrh21qyipbc5csn4` FOREIGN KEY (`auth_code`) REFERENCES `sys_authority` (`code`),
  CONSTRAINT `FK_t6g592n66q76t7jlw0egjclkg` FOREIGN KEY (`res_code`) REFERENCES `sys_resource` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_role`;
CREATE TABLE `rating`.`sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_role_auth_mapping`;
CREATE TABLE `rating`.`sys_role_auth_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `auth_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_flb7aube3aw87obwdcwjr2c75` (`auth_id`),
  KEY `FK_t8id7qh3p93jfe3a30a6abl9d` (`role_id`),
  CONSTRAINT `FK_flb7aube3aw87obwdcwjr2c75` FOREIGN KEY (`auth_id`) REFERENCES `sys_authority` (`id`),
  CONSTRAINT `FK_t8id7qh3p93jfe3a30a6abl9d` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_user`;
CREATE TABLE `rating`.`sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `salt` varchar(20) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `position_id` bigint(20) DEFAULT NULL COMMENT '职位',
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `INDEX_USERNAME` (`username`),
  KEY `FK_qjxugyjvbiewwbmq7kgv4d9qm` (`group_id`),
  CONSTRAINT `FK_qjxugyjvbiewwbmq7kgv4d9qm` FOREIGN KEY (`group_id`) REFERENCES `sys_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_user_role`;
CREATE TABLE `rating`.`sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fxu3td9m5o7qov1kbdvmn0g0x` (`role_id`),
  KEY `FK_fethvr269t6stivlddbo5pxry` (`user_id`),
  CONSTRAINT `FK_fethvr269t6stivlddbo5pxry` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_fxu3td9m5o7qov1kbdvmn0g0x` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`sys_user_session`;
CREATE TABLE `rating`.`sys_user_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `session_id` varchar(64) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_SYS_USER_SESSION` (`session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating`;
CREATE TABLE `rating`.`rating` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `eff_from` timestamp NULL DEFAULT NULL,
  `eff_to` timestamp NULL DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_score`;
CREATE TABLE `rating`.`rating_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_supp_options`;
CREATE TABLE `rating`.`rating_supp_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `seq` decimal(10,2) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_supp_template`;
CREATE TABLE `rating`.`rating_supp_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_supp_template_voter`;
CREATE TABLE `rating`.`rating_supp_template_voter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supp_tmpl_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_supp_tmpl_option`;
CREATE TABLE `rating`.`rating_supp_tmpl_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supp_tmpl_id` bigint(20) DEFAULT NULL,
  `option_id` bigint(20) DEFAULT NULL,
  `seq` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kkubqqghg8i0bemt5a3a1ibfw` (`option_id`),
  KEY `FK_3mtp7tg07l2ibwvvepo29rra8` (`supp_tmpl_id`),
  CONSTRAINT `FK_3mtp7tg07l2ibwvvepo29rra8` FOREIGN KEY (`supp_tmpl_id`) REFERENCES `rating_supp_template` (`id`),
  CONSTRAINT `FK_kkubqqghg8i0bemt5a3a1ibfw` FOREIGN KEY (`option_id`) REFERENCES `rating_supp_options` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_supp_tmpl_option_weight`;
CREATE TABLE `rating`.`rating_supp_tmpl_option_weight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `supp_tmpl_id` bigint(20) DEFAULT NULL,
  `supp_option_id` bigint(20) DEFAULT NULL,
  `weight` decimal(10,2) DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_template`;
CREATE TABLE `rating`.`rating_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `rating_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_template_voter`;
CREATE TABLE `rating`.`rating_template_voter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tmpl_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_tmpl_option`;
CREATE TABLE `rating`.`rating_tmpl_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tmpl_id` bigint(20) DEFAULT NULL,
  `option_id` bigint(20) DEFAULT NULL,
  `seq` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_oywcoxnstvtfsoel8n5puajga` (`option_id`),
  KEY `FK_nk73hc666snrejju8x96emy8s` (`tmpl_id`),
  CONSTRAINT `FK_nk73hc666snrejju8x96emy8s` FOREIGN KEY (`tmpl_id`) REFERENCES `rating_template` (`id`),
  CONSTRAINT `FK_oywcoxnstvtfsoel8n5puajga` FOREIGN KEY (`option_id`) REFERENCES `rating_supp_options` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_tmpl_option_weight`;
CREATE TABLE `rating`.`rating_tmpl_option_weight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tmpl_id` bigint(20) DEFAULT NULL,
  `option_id` bigint(20) DEFAULT NULL,
  `weight` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_user_vote`;
CREATE TABLE `rating`.`rating_user_vote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rating_id` bigint(20) DEFAULT NULL,
  `tmpl_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  `vote_date` timestamp NULL DEFAULT NULL,
  `version` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_37b4oyo6fwyrck4tvnbj4wde1` (`rating_id`),
  KEY `FK_dbxrclqy1j0qls9dq5bspqj2l` (`tmpl_id`),
  KEY `FK_kkm1bsjt7b26f1nldxsi3jy58` (`user_id`),
  KEY `FK_fif0sua1ddb2psw8r5hkh1ykx` (`group_id`),
  CONSTRAINT `FK_37b4oyo6fwyrck4tvnbj4wde1` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`),
  CONSTRAINT `FK_dbxrclqy1j0qls9dq5bspqj2l` FOREIGN KEY (`tmpl_id`) REFERENCES `rating_template` (`id`),
  CONSTRAINT `FK_fif0sua1ddb2psw8r5hkh1ykx` FOREIGN KEY (`group_id`) REFERENCES `sys_group` (`id`),
  CONSTRAINT `FK_kkm1bsjt7b26f1nldxsi3jy58` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

drop table if exists `rating`.`rating_user_vote_item`;
CREATE TABLE `rating`.`rating_user_vote_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_vote_id` bigint(20) DEFAULT NULL,
  `option_id` bigint(20) DEFAULT NULL,
  `score_id` bigint(20) DEFAULT NULL,
  `scorer` bigint(20) DEFAULT NULL,
  `scorer_group` bigint(20) DEFAULT NULL,
  `scorer_position` int(11) DEFAULT NULL,
  `score` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

commit;
