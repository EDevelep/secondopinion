ALTER TABLE `curemetricuser`.`invoice`
	ADD COLUMN `entityInvoiceId` BIGINT NULL DEFAULT NULL AFTER `paymentReferenceId`;