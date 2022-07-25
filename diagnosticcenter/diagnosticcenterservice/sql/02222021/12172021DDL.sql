ALTER TABLE `diagnosticcenter`.`diagnosticcenteruser` 
ADD COLUMN `emailotp` INT(11) NULL AFTER `lastLogin`,
ADD COLUMN `otp` INT(11) NULL AFTER `emailotp`;