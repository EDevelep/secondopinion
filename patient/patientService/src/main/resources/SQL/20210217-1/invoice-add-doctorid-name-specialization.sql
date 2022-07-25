ALTER TABLE `invoice`
	ADD COLUMN `doctorid` BIGINT NULL DEFAULT 0 AFTER `patientname`,
	ADD COLUMN `doctorname` VARCHAR(45) NULL DEFAULT NULL AFTER `doctorid`,
	ADD COLUMN `specialization` VARCHAR(150) NULL DEFAULT NULL AFTER `doctorname`;