ALTER TABLE `curemetricdoctor`.`doctor` 
ADD COLUMN `medicalLicenceNumber`  varchar(250) NULL,
ADD COLUMN `medicalLicenceExpiryDate` DATETIME NULL AFTER `medicalLicenceNumber`,
ADD COLUMN `totalExperience` INT NULL AFTER `medicalLicenceExpiryDate`;