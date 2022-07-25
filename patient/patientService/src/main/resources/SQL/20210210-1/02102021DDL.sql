ALTER TABLE `curemetricuser`.`medicalreports` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `lastUpdatedTs`,
CHANGE COLUMN `lastUpdatedBy` `lastUpdatedBy` VARCHAR(100) NULL DEFAULT NULL AFTER `createdDate`,
CHANGE COLUMN `lastUpdatedTs` `lastUpdatedTs` DATETIME NULL DEFAULT NULL AFTER `lastUpdatedBy`;
ALTER TABLE `curemetricuser`.`address` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `primaryaddress`;
ALTER TABLE `curemetricuser`.`personaldetail` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `profilePic`;

