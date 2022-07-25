CREATE TABLE `invoice` (
  `invoiceid` bigint(20) NOT NULL AUTO_INCREMENT,
  `patientid` bigint(20) NOT NULL,
  `invoicetype` varchar(45) DEFAULT NULL,
  `invoicereferenceid` bigint(45) DEFAULT NULL,
  `invoiceentityname` varchar(45) DEFAULT NULL,
   `invoiceentityreferenceid` bigint(45) DEFAULT NULL,
  `invoicestatus` varchar(45) DEFAULT NULL,
  `patientname` varchar(45) DEFAULT NULL,
 `cardnumber` varchar(45) DEFAULT NULL,
  `transactiontype` varchar(45) DEFAULT NULL,
   `amount`  Double DEFAULT NULL,
  `paid` char(1) DEFAULT NULL,
   `payByDate` date DEFAULT NULL,
    `paidOn` date DEFAULT NULL,
  `paymentReferenceId` varchar(45) DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`invoiceid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
ALTER TABLE `curemetricuser`.`patientratings` 
ADD COLUMN `patientname` VARCHAR(45) NULL AFTER `referenceId`;