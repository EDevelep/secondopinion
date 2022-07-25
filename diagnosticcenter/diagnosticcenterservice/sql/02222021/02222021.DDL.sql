ALTER TABLE `diagnosticcenter`.`diagnosticcenteruser` 
CHANGE COLUMN `active` `active` CHAR(1) NOT NULL AFTER `officeNumber`;
ALTER TABLE `diagnosticcenter`.`appointment` 
ADD COLUMN `active` CHAR(45) NOT NULL AFTER `toTime`;

ALTER TABLE `diagnosticcenter`.`baseschedule` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `sunday`;

ALTER TABLE `diagnosticcenter`.`coupon` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `validFrom`;

ALTER TABLE `diagnosticcenter`.`diagnosticcenter` 
CHANGE COLUMN `active` `active` CHAR(1) NOT NULL ;

ALTER TABLE `diagnosticcenter`.`invoice` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `CGST`;

ALTER TABLE `diagnosticcenter`.`menu` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `menuName`;

ALTER TABLE `diagnosticcenter`.`packagemenu` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `menuName`;

ALTER TABLE `diagnosticcenter`.`packagesubmenu` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `serviceName`;

ALTER TABLE `diagnosticcenter`.`patientrequest` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `requestAccepted`;

ALTER TABLE `diagnosticcenter`.`privilege` 
CHANGE COLUMN `active` `active` VARCHAR(4) NOT NULL ;

ALTER TABLE `diagnosticcenter`.`role` 
CHANGE COLUMN `active` `active` VARCHAR(4) NOT NULL ;

ALTER TABLE `diagnosticcenter`.`userrole` 
CHANGE COLUMN `diagnosticcenterUserId` `diagnosticcenterUserId` BIGINT(20) NOT NULL AFTER `userRoleId`,
CHANGE COLUMN `roleId` `roleId` BIGINT(20) NOT NULL AFTER `diagnosticcenterUserId`;

ALTER TABLE `diagnosticcenter`.`diagnosticcenteruser` 
ADD COLUMN `locked` CHAR(1) NOT NULL AFTER `lastLogin`,
ADD COLUMN `retry` INT NULL AFTER `locked`;
