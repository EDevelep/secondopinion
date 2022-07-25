ALTER TABLE `curemetricdoctor`.`doctor` CHANGE COLUMN `userId` `emailId` VARCHAR(250) NOT NULL ;

ALTER TABLE `curemetricdoctor`.`appointment` ADD COLUMN `createdby` VARCHAR(45) NULL AFTER `scheduleId`, ADD COLUMN `createdDate` DATE NULL AFTER `createdby`;