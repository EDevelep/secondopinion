ALTER TABLE `curemetricpharmacy`.`invoice` 
	DROP COLUMN `SGST`,
	DROP COLUMN `CGST`;
ALTER TABLE `curemetricpharmacy`.`invoice`
	ADD COLUMN `discount` FLOAT(12) NULL DEFAULT NULL AFTER `prescriptionFillRequestId`;
