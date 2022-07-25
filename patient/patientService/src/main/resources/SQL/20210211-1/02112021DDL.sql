ALTER TABLE `curemetricuser`.`vitals` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `recordedDate`;

ALTER TABLE `curemetricuser`.`ailments` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `recordedDate`,
CHANGE COLUMN `lastUpdatedTs` `lastUpdatedTs` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP AFTER `createdDate`;

ALTER TABLE `curemetricuser`.`appointment` 
ADD COLUMN `active` CHAR(1) NULL AFTER `ailment`,
CHANGE COLUMN `lastUpdatedTs` `lastUpdatedTs` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `createdDate`;

ALTER TABLE `curemetricuser`.`customerdetails` 
ADD COLUMN `active` CHAR(15) NOT NULL AFTER `errorMessage`;

ALTER TABLE `curemetricuser`.`surgerydetails` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `surgerlookupid`;
ALTER TABLE `curemetricuser`.`socialhistory` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `csection`;
ALTER TABLE `curemetricuser`.`questionairre` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `alcohol`;
ALTER TABLE `curemetricuser`.`personalsymptoms` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `userId`;
ALTER TABLE `curemetricuser`.`familyhistory` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `relationship`;
ALTER TABLE `curemetricuser`.`surgerydetails` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `surgerlookupid`;
ALTER TABLE `curemetricuser`.`socialhistory` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `csection`;
ALTER TABLE `curemetricuser`.`questionairre` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `alcohol`;
ALTER TABLE `curemetricuser`.`personalsymptoms` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `userId`;
ALTER TABLE `curemetricuser`.`familyhistory` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `relationship`;


ALTER TABLE `curemetricuser`.`medicalprescription` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `documentName`;

ALTER TABLE `curemetricuser`.`medicaltest` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `documentName`;
ALTER TABLE `curemetricuser`.`medication` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `afternoon`;
ALTER TABLE `curemetricuser`.`notificationalerts` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `expirydate`;

