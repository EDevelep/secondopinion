ALTER TABLE `diagnosticcenter`.`diagnosticcenteruser` 
ADD COLUMN `password` VARCHAR(250) NULL AFTER `lastLogin`;

ALTER TABLE `diagnosticcenter`.`diagnosticcenteruser` 
CHANGE COLUMN `lastUpdatedBy` `lastUpdatedBy` VARCHAR(100) NULL DEFAULT NULL AFTER `primaryContactNumber`,
CHANGE COLUMN `lastUpdatedTs` `lastUpdatedTs` DATETIME NULL DEFAULT NULL AFTER `lastUpdatedBy`;
