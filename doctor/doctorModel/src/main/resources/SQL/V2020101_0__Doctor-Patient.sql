CREATE TABLE `doctor`.`doctorpatient` (
  `doctorPatientId` BIGINT NOT NULL AUTO_INCREMENT,
  `doctorId` BIGINT NULL,
  `patientId` BIGINT NULL,
  `newRequest` CHAR(1) NULL,
  `requestAccepted` CHAR(1) NULL,
  `description` VARCHAR(500) NULL,
  `alignment` VARCHAR(100) NULL,
  `lastUpdatedBy` VARCHAR(45) NULL,
  `lastUpdatedTs` TIMESTAMP NULL,
  PRIMARY KEY (`doctorPatientId`));
