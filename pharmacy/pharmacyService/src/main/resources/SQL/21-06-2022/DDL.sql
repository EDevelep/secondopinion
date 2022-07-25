ALTER TABLE `curemetricpharmacy`.`invoice` 
ADD COLUMN `medicalPrescriptionId` BIGINT(20) NULL AFTER `trackingId`;