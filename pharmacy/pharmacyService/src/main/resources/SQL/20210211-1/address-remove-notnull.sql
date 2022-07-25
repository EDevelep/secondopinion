ALTER TABLE `curemetricpharmacy`.`address`
	CHANGE COLUMN `latitude` `latitude` BIGINT(20) NULL AFTER `isPrimary`,
	CHANGE COLUMN `longitudes` `longitudes` BIGINT(20) NULL AFTER `latitude`,
	DROP COLUMN `userId`;
ALTER TABLE `curemetricpharmacy`.`address`
	CHANGE COLUMN `isPrimary` `isPrimary` CHAR(1) NULL COLLATE 'utf8_general_ci' AFTER `contactPhoneNumber`;