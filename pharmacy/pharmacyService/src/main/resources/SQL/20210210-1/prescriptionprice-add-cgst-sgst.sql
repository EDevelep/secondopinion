ALTER TABLE `curemetricpharmacy`.`prescriptionprice`
	ADD COLUMN `cgst` FLOAT(12) NOT NULL AFTER `totalPrice`,
	ADD COLUMN `sgst` FLOAT(12) NOT NULL AFTER `cgst`;