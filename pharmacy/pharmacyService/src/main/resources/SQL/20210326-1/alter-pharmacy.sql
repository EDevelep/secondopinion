ALTER TABLE `curemetricpharmacy`.`pharmacy`
	CHANGE COLUMN `addressId` BIGINT(20) NULL AFTER `pharmacyId`;
ALTER TABLE `curemetricpharmacy`.`pharmacy`
	ADD COLUMN `primaryUser` BIGINT(20) NULL AFTER `addressId`;