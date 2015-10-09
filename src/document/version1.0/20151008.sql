INSERT INTO `rating`.`config` (`property`, `property_value`) 
 SELECT 'AUTHORISE_SALT', '20151008' FROM DUAL 
 WHERE NOT EXISTS (SELECT 1 FROM `rating`.`config` WHERE property ='AUTHORISE_SALT');