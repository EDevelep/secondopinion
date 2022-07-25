ALTER TABLE `curemetricuser`.`vitals` 
CHANGE COLUMN `vitalname` `vitalname` VARCHAR(45) NOT NULL ,
CHANGE COLUMN `vitalValue` `vitalValue` TEXT NOT NULL ,
CHANGE COLUMN `recordedDate` `recordedDate` TIMESTAMP NOT NULL ,
CHANGE COLUMN `active` `active` CHAR(1) NOT NULL ;

ALTER TABLE `curemetricuser`.`user` 
ADD COLUMN `vitalLastRecordedDate` TIMESTAMP NULL AFTER `operatedByUser`;
