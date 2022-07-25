DROP DATABASE IF EXISTS `curemetricdoctor`;
CREATE DATABASE IF NOT EXISTS `curemetricdoctor`;
--
-- Table structure for table `address`
--
DROP DATABASE IF EXISTS `curemetricdoctor`;
CREATE DATABASE IF NOT EXISTS `curemetricdoctor`;

DROP TABLE IF EXISTS `curemetricdoctor`.`address`;
CREATE TABLE `curemetricdoctor`.`address` (
  `addressId` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctorId` bigint(20) NOT NULL,
  `street1` varchar(150) DEFAULT NULL,
  `street2` varchar(150) DEFAULT NULL,
  `city` varchar(150) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `address`
--


DROP TABLE IF EXISTS `curemetricdoctor`.`appointment`;


CREATE TABLE `curemetricdoctor`.`appointment` (
  `appointmentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctorId` bigint(20) DEFAULT NULL,
  `patientId` bigint(20) DEFAULT NULL,
  `patientAppointmentId` bigint(20) DEFAULT NULL,
  `appointmentDate` date DEFAULT NULL,
  `to` float DEFAULT NULL,
  `from` float DEFAULT NULL,
  `videoURL` varchar(500) DEFAULT NULL,
  `videoChatRoomName` varchar(45) DEFAULT NULL,
  `reason` varchar(150) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdateTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`appointmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Dumping data for table `appointment`
--



DROP TABLE IF EXISTS `curemetricdoctor`.`appointmentnotes`;


CREATE TABLE `curemetricdoctor`.`appointmentnotes` (
  `appointmentNotesId` int(11) NOT NULL,
  `appointmentId` varchar(45) DEFAULT NULL,
  `notes` text,
  `createdBy` varchar(45) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`appointmentNotesId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `association`
--

DROP TABLE IF EXISTS `curemetricdoctor`.`association`;


CREATE TABLE `curemetricdoctor`.`association` (
  `associationId` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctorId` bigint(20) NOT NULL,
  `hospitalId` bigint(20) DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`associationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `baseschedule`
--

DROP TABLE IF EXISTS `curemetricdoctor`.`baseschedule`;


CREATE TABLE `curemetricdoctor`.`baseschedule` (
  `basseScheduleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctorId` bigint(20) NOT NULL,
  `monday` varchar(250) DEFAULT NULL,
  `tuesday` varchar(250) DEFAULT NULL,
  `wednesday` varchar(250) DEFAULT NULL,
  `thursday` varchar(250) DEFAULT NULL,
  `friday` varchar(250) DEFAULT NULL,
  `saturday` varchar(250) DEFAULT NULL,
  `sunday` varchar(250) DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`basseScheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `certification`
--

DROP TABLE IF EXISTS `curemetricdoctor`.`certification`;


CREATE TABLE `curemetricdoctor`.`certification` (
  `certificationId` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `certification` varchar(100) DEFAULT NULL,
  `associatedEntity` varchar(150) DEFAULT NULL,
  `yearCertified` int(11) DEFAULT NULL,
  `dateOfExpiry` date DEFAULT NULL,
  `link` varchar(45) DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`certificationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `curemetricdoctor`.`doctor`;


CREATE TABLE `curemetricdoctor`.`doctor` (
  `doctorId` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(150) NOT NULL,
  `lastName` varchar(150) DEFAULT NULL,
  `middleName` varchar(45) DEFAULT NULL,
  `highestDegree` varchar(45) DEFAULT NULL,
  `specialization` varchar(150) DEFAULT NULL,
  `userId` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `lastLogin` timestamp NULL DEFAULT NULL,
  `emergencyContactNumber` varchar(45) DEFAULT NULL,
  `cellNumber` varchar(45) DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`doctorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `feedetails`
--

DROP TABLE IF EXISTS `curemetricdoctor`.`feedetails`;


CREATE TABLE `curemetricdoctor`.`feedetails` (
  `feeDetailsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctorId` bigint(20) NOT NULL,
  `fee` float DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`feeDetailsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `curemetricdoctor`.`invoice`;


CREATE TABLE `curemetricdoctor`.`invoice` (
  `invoiceId` bigint(20) NOT NULL AUTO_INCREMENT,
  `appointmentId` bigint(20) NOT NULL,
  `amount` float NOT NULL,
  `payByDate` date DEFAULT NULL,
  `paid` char(1) DEFAULT NULL,
  `paidOn` date DEFAULT NULL,
  `paymentReferenceId` varchar(150) DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`invoiceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `personaldetail`
--

DROP TABLE IF EXISTS `personaldetail`;


CREATE TABLE `curemetricdoctor`.`personaldetail` (
  `personalDetailId` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctorId` bigint(20) NOT NULL,
  `DOB` date NOT NULL,
  `height` varchar(25) DEFAULT NULL,
  `eyeColor` varchar(45) DEFAULT NULL,
  `hairColor` varchar(45) DEFAULT NULL,
  `highestDegree` varchar(100) DEFAULT NULL,
  `specialization` varchar(150) DEFAULT NULL,
  `ethinicity` varchar(45) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`personalDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `curemetricdoctor`.`schedule`;


CREATE TABLE `curemetricdoctor`.`schedule` (
  `scheduleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctorId` bigint(20) NOT NULL,
  `scheduleDate` date NOT NULL,
  `day` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `dayOfWeek` int(11) NOT NULL,
  `dayOfYear` int(11) DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`scheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `schedulehours`
--

DROP TABLE IF EXISTS `curemetricdoctor`.`schedulehours`;


CREATE TABLE `curemetricdoctor`.`schedulehours` (
  `scheduleHoursId` bigint(20) NOT NULL AUTO_INCREMENT,
  `scheduleId` bigint(20) NOT NULL,
  `from` float NOT NULL,
  `to` float NOT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`scheduleHoursId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `curemetricdoctor`.`specialization`;

CREATE TABLE `curemetricdoctor`.`specialization` (
  `specializationId` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctorId` bigint(20) NOT NULL,
  `specializations` varchar(500) DEFAULT NULL,
  `educationDetails` varchar(500) DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `lastUpdatedBy` varchar(45) DEFAULT NULL,
  `lastUpdateDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`specializationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



