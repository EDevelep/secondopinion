ALTER TABLE registration
  DROP COLUMN activatedDate;
ALTER TABLE `curemetricuser`.`user` 
ADD COLUMN `activatedDate` DATETIME NULL AFTER `active`;
ALTER TABLE `curemetricuser`.`registration` 
CHANGE COLUMN `otp` `otp` INT NULL DEFAULT NULL ;
ALTER TABLE `curemetricuser`.`user` 
ADD COLUMN `emergecycontact` VARCHAR(45) NULL AFTER `primaryContact`;
