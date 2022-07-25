
ALTER TABLE `curemetricuser`.`relationship` 
ADD INDEX `userid_reluserId_idx` (`userId` ASC, `relationUserId` ASC);
;

ALTER TABLE `curemetricuser`.`user` 
ADD INDEX `userIdx` (`userId` ASC);
;

ALTER TABLE `curemetricuser`.`diagnosticcenterprescriptionappointment` 
ADD COLUMN `amount` DOUBLE NULL AFTER `invoiceId`;

ALTER TABLE `curemetricuser`.`user` 
ADD INDEX `useremailIdx` (`emailId` ASC);
;
ALTER TABLE `curemetricuser`.`user` 
ADD INDEX `useremail_paswdIdx` (`emailId` ASC, `password` ASC);
;

ALTER TABLE `curemetricuser`.`relationship` 
ADD INDEX `userid_idx` (`userId` ASC);
;
ALTER TABLE `curemetricuser`.`notificationalerts` 
ADD INDEX `patientid_idx` (`patientId` ASC);
;

ALTER TABLE `curemetricuser`.`appointment` 
ADD INDEX `refrenceEntityId_appoitmentfor_idx` (`appointmentFor` ASC, `referenceEntityId` ASC);
;

ALTER TABLE `curemetricuser`.`appointment` 
ADD INDEX `appointmentId_idx` (`appointmentId` ASC);
;

ALTER TABLE `curemetricuser`.`invoice` 
ADD INDEX `patientid_idx` (`patientid` ASC);
;

