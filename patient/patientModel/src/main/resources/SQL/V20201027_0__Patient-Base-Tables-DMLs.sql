
DROP DATABASE IF EXISTS `curemetricuser`;
CREATE DATABASE IF NOT EXISTS `curemetricuser`;
--
-- Table structure for table `address`
--


DROP TABLE IF EXISTS `curemetricuser`.`address`;
CREATE TABLE `curemetricuser`.`address` (
  `addressId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `street1` varchar(150) DEFAULT NULL,
  `street2` varchar(150) DEFAULT NULL,
  `city` varchar(150) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45)  NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `address`
--

--
-- Table structure for table `ailments`
--

DROP TABLE IF EXISTS `curemetricuser`.`ailments`;


CREATE TABLE `curemetricuser`.`ailments` (
  `ailmentsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `ailmentId` bigint(20) NOT NULL,
  `ailmentValue` varchar(45) NOT NULL,
  `ailmentDetails` text,
  `recordedDate` date DEFAULT NULL,
 `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45)  NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ailmentsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `allergies`
--

DROP TABLE IF EXISTS `curemetricuser`.`allergies`;


CREATE TABLE `curemetricuser`.`allergies` (
  `allergiesId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `allergyId` bigint(20) NOT NULL,
  `allergyValue` text NOT NULL,
  `recordDate` date NOT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45)  NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`allergiesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `curemetricuser`.`appointment`;


CREATE TABLE `curemetricuser`.`appointment` (
  `appointmentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `appointmentFor` varchar(45) DEFAULT NULL,
  `referenceAppointmentId` bigint(20) DEFAULT NULL,
  `appointmentDate` date DEFAULT NULL,
  `to` float DEFAULT NULL,
  `from` float DEFAULT NULL,
  `videoURL` varchar(500) DEFAULT NULL,
  `videoChatRoomName` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45)  NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`appointmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `lookupstatic`
--

DROP TABLE IF EXISTS `curemetricuser`.`lookupstatic`;


CREATE TABLE `curemetricuser`.`lookupstatic` (
  `lookupId` bigint(20) NOT NULL AUTO_INCREMENT,
  `lookupType` varchar(45) NOT NULL,
  `lookupKey` varchar(45) DEFAULT NULL,
  `lookupValue` varchar(150) NOT NULL,
  `refId` bigint(20) NOT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45)  NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`lookupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `medicalinsurance`
--

DROP TABLE IF EXISTS `curemetricuser`.`medicalinsurance`;


CREATE TABLE `curemetricuser`.`medicalinsurance` (
  `medicalInsuranceId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `policyNumber` varchar(45) NOT NULL,
  `policyType` varchar(45) NOT NULL,
  `validUpTo` date NOT NULL,
  `providerName` varchar(200) NOT NULL,
  `isPrimaryHolder` char(1) DEFAULT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45)  NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`medicalInsuranceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `medicalprescription`
--

DROP TABLE IF EXISTS `curemetricuser`.`medicalprescription`;


CREATE TABLE `curemetricuser`.`medicalprescription` (
  `medicalPrescriptionId` bigint(20) NOT NULL,
  `appointmentId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `medicine` varchar(100) NOT NULL,
  `type` varchar(100) DEFAULT NULL,
  `power` varchar(45) DEFAULT NULL,
  `dosage` varchar(150) NOT NULL,
  `notes` varchar(250) DEFAULT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45)  NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`medicalPrescriptionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `medicalreports`
--

DROP TABLE IF EXISTS `curemetricuser`.`medicalreports`;


CREATE TABLE `curemetricuser`.`medicalreports` (
  `medicalReportsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `appointmentId` bigint(20) NOT NULL,
  `medicalTestId` bigint(20) NOT NULL,
  `reportName` varchar(250) DEFAULT NULL,
  `alignment` varchar(250) DEFAULT NULL,
  `documentName` varchar(250) DEFAULT NULL,
  `documentLocation` varchar(250) DEFAULT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45)  NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`medicalReportsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `medicaltest`
--

DROP TABLE IF EXISTS `curemetricuser`.`medicaltest`;


CREATE TABLE `curemetricuser`.`medicaltest` (
  `medicalTestId` bigint(20) NOT NULL AUTO_INCREMENT,
  `appointmentId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `testName` varchar(250) DEFAULT NULL,
  `testType` varchar(100) DEFAULT NULL,
  `notes` text,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`medicalTestId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `personaldetail`
--

DROP TABLE IF EXISTS `curemetricuser`.`personaldetail`;


CREATE TABLE `curemetricuser`.`personaldetail` (
  `personalDetailId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `DOB` date NOT NULL,
  `height` varchar(25) DEFAULT NULL,
  `eyeColor` varchar(45) DEFAULT NULL,
  `hairColor` varchar(45) DEFAULT NULL,
  `highestDegree` varchar(100) DEFAULT NULL,
  `specialization` varchar(150) DEFAULT NULL,
  `ethinicity` varchar(45) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`personalDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `curemetricuser`.`registration`;


CREATE TABLE `curemetricuser`.`registration` (
  `registrationId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `newlyRegistered` char(1) NOT NULL,
  `registeredDate` date DEFAULT NULL,
  `activatedDate` date DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `verificationId` varchar(250) DEFAULT NULL,
  `verificationNeededBy` timestamp NULL DEFAULT NULL,
  `userVerified` char(1) DEFAULT NULL,
  `userVerifiedOn` date DEFAULT NULL,
  `otp` varchar(45) DEFAULT NULL,
  `otpVerified` char(1) DEFAULT NULL,
  `otpVerifiedOn` date DEFAULT NULL,
  `active` char(1) DEFAULT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`registrationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `registration`
--

LOCK TABLES `curemetricuser`.`registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationship`
--

DROP TABLE IF EXISTS `curemetricuser`.`relationship`;


CREATE TABLE `curemetricuser`.`relationship` (
  `relationshipId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `relationUserId` bigint(20) NOT NULL,
  `relationship` varchar(45) NOT NULL,
  `verificationId` varchar(200) DEFAULT NULL,
  `verified` char(1) DEFAULT NULL,
  `verifiedOn` date DEFAULT NULL,
  `approved` char(1) DEFAULT NULL,
  `approvedOn` date DEFAULT NULL,
  `accessToRecords` char(1) NOT NULL,
  `accessToPaymentDetails` char(1) NOT NULL,
  `accessToPrescription` char(1) NOT NULL,
  `accessToInsurance` char(1) NOT NULL,
  `accessToPersonalDetails` char(1) NOT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`relationshipId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `curemetricuser`.`user`;


CREATE TABLE `curemetricuser`.`user` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(25) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `middleName` varchar(45) DEFAULT NULL,
  `emailId` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `cellNumber` varchar(45) DEFAULT NULL,
  `officeNumber` varchar(45) DEFAULT NULL,
  `homeNumber` varchar(45) DEFAULT NULL,
  `primaryContact` varchar(45) NOT NULL,
  `active` char(1) NOT NULL,
  `operatedByUser` bigint(20) DEFAULT NULL,
  `lastLogin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  UNIQUE KEY `userName_UNIQUE` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--

DROP TABLE IF EXISTS `curemetricuser`.`vitals`;
CREATE TABLE `curemetricuser`.`vitals` (
  `vitalsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `vitalId` bigint(20) DEFAULT NULL,
  `vitalValue` text,
  `recordedDate` date DEFAULT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`vitalsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



