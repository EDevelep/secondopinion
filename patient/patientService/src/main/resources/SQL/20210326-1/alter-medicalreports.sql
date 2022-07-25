ALTER TABLE `curemetricuser`.`medicalreports`
	CHANGE COLUMN `medicalTestId` BIGINT(20) NULL AFTER `appointmentDate`;
ALTER TABLE `curemetricuser`.`medicalreports`
	ADD COLUMN `doctorAppointmentId` BIGINT(20) NULL AFTER `appointmentId`;
ALTER TABLE `curemetricuser`.`medicalreports`
	ADD COLUMN `diagnosticCenterAppointmentId` BIGINT(20) NULL AFTER `doctorAppointmentId`;
ALTER TABLE `curemetricuser`.`appointment`
	ADD COLUMN `medicalPrescriptionId` BIGINT(20) NULL AFTER `referenceAppointmentId`;