ALTER TABLE `curemetricdoctor`.`schedulehours` 
ADD COLUMN `available` CHAR(1) NOT NULL DEFAULT 'Y' AFTER `active`;

ALTER TABLE `curemetricdoctor`.`schedulehours` 
ADD COLUMN `timeOfDay` int NOT NULL AFTER `toTime`;