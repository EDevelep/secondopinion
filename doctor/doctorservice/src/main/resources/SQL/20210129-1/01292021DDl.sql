ALTER TABLE `curemetricdoctor`.`invoice` 
ADD COLUMN `doctorname` VARCHAR(45) NULL AFTER `lastUpdatedTs`,
ADD COLUMN `patientname` VARCHAR(45) NULL AFTER `doctorname`,
ADD COLUMN `doctorappointmentid` BIGINT(45) NULL AFTER `patientname`,
ADD COLUMN `patientappointmentid` BIGINT(45) NULL AFTER `doctorappointmentid`,
ADD COLUMN `cardnumber` VARCHAR(45) NULL AFTER `patientappointmentid`,
ADD COLUMN `transactiontype` VARCHAR(45) NULL AFTER `cardnumber`,
ADD COLUMN `invoicestatus` VARCHAR(45) NULL AFTER `transactiontype`,
CHANGE COLUMN `appointmentId` `patientid` BIGINT(20) NOT NULL ;
ALTER TABLE `curemetricdoctor`.`invoice` 
CHANGE COLUMN `docotrid` `doctorid` BIGINT(45) NULL DEFAULT NULL ;
