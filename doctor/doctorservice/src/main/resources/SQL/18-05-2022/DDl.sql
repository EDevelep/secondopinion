


ALTER TABLE `curemetricdoctor`.`baseschedule` 
ADD COLUMN `associateEntityType` VARCHAR(45) NULL AFTER `numberofmonths`,
ADD COLUMN `associateEntityId` BIGINT(20) NULL AFTER `associateEntityType`;

ALTER TABLE `curemetricdoctor`.`schedulehours` 
ADD COLUMN `associateEntityType` VARCHAR(45) NULL AFTER `scheduleId`,
ADD COLUMN `associateEntityId` BIGINT(20) NULL AFTER `associateEntityType`;


ALTER TABLE `curemetricdoctor`.`schedule` 
ADD COLUMN `associateEntityType` VARCHAR(45) NULL AFTER `doctorId`,
ADD COLUMN `associateEntityId` BIGINT(20) NULL AFTER `associateEntityType`;