ALTER TABLE `curemetricuser`.`patientpaymentdetails`
	ADD COLUMN `chargeFor` VARCHAR(150) NOT NULL DEFAULT '' AFTER `patientId`;