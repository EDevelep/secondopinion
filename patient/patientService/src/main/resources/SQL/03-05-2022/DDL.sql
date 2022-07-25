CREATE TABLE `diagnosticcenterprescriptionappointment` (
  `diagnosticcenterprescriptionappointmentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
 `diagnosticcenterPrescriptionId` bigint(20) NOT NULL,
 `diagnosticcenterAppointmentId` bigint(20) NOT NULL,
  `invoiceId` bigint(20) NOT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `lastUpdatedBy` varchar(255) DEFAULT NULL,
  `lastUpdatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`diagnosticcenterprescriptionappointmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
