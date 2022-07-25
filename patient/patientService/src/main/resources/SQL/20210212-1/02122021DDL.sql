
ALTER TABLE `curemetricuser`.`invoice` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `payByDate`;
ALTER TABLE `curemetricuser`.`patientratings` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `feedback`;

ALTER TABLE `curemetricuser`.`patientfcmtoken` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `browsertoken`;

ALTER TABLE `curemetricuser`.`patientpaymentdetails` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `refundTransactionDate`;

ALTER TABLE `curemetricuser`.`patientpreference` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `diagnosticcenterid`;

ALTER TABLE `curemetricuser`.`carddetails` 
ADD COLUMN `active` CHAR(1) NOT NULL AFTER `expmonth`;

