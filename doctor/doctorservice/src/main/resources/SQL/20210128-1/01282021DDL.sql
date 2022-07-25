ALTER TABLE `curemetricdoctor`.`doctor` 
CHANGE COLUMN `active` `active` CHAR(1) NULL DEFAULT NULL AFTER `dateOfExpiry`;
ALTER TABLE `curemetricdoctor`.`doctor` 
ADD COLUMN `activatedDate` DATETIME NULL AFTER `dateOfExpiry`;
ALTER TABLE registration
  DROP COLUMN activatedDate;
  ALTER TABLE `curemetricdoctor`.`appointment` 
ADD COLUMN `active` CHAR(1) NULL AFTER `amountPaid`;
ALTER TABLE `curemetricdoctor`.`invoice` 
CHANGE COLUMN `docotrid` `docotrid` BIGINT(45) NULL DEFAULT NULL AFTER `appointmentId`,
CHANGE COLUMN `active` `active` CHAR(1) NULL DEFAULT NULL AFTER `paidOn`;
