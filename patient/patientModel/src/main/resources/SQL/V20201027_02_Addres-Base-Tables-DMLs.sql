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
  `longitudes` bigint(20) NOT NULL,
  `latitude` bigint(20) NOT NULL,
  `creartedby` varchar(45) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(45)  NULL,
  `lastUpdatedTs` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;