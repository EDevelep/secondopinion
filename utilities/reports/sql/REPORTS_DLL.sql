CREATE TABLE `dashboard` (
  `dashboardId` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) DEFAULT NULL,
  `name` varchar(150) NOT NULL,
  `defaultDashboard` char(1) NOT NULL,
  `lastUpdatedBy` varchar(45) NOT NULL,
  `lastUpdatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dashboardId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `dashboardreports` (
  `dashboardReportsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `dashboardId` int(11) NOT NULL,
  `reportId` bigint(20) NOT NULL,
  `orderId` int(11) NOT NULL,
  `lastUpdatedBy` varchar(45) NOT NULL,
  `lastUpdatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dashboardReportsId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

CREATE TABLE `dashboardruleassociation` (
  `dashboardRuleAssociationId` bigint(20) NOT NULL AUTO_INCREMENT,
  `dashboardId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `companyId` int(11) NOT NULL,
  `isDefault` char(1) NOT NULL DEFAULT 'Y',
  `lastUpdatedBy` varchar(45) NOT NULL,
  `lastUpdatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dashboardRuleAssociationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `report` (
  `reportId` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyId` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `header` varchar(150) DEFAULT NULL,
  `type` varchar(45) NOT NULL DEFAULT 'GRAPH',
  `sourceName` varchar(150) NOT NULL,
  `columns` varchar(500) NOT NULL,
  `columnAliases` varchar(500) DEFAULT NULL,
  `conditions` varchar(250) DEFAULT NULL,
  `groupColumns` varchar(250) DEFAULT NULL,
  `orderByColumns` varchar(255) DEFAULT NULL,
  `transposeData` varchar(250) DEFAULT NULL,
  `transposeWithTotals` char(1) DEFAULT NULL,
  `keyColumns` varchar(250) DEFAULT NULL,
  `sumOnColumn` varchar(45) DEFAULT NULL,
  `count` char(1) DEFAULT NULL,
  `graphType` varchar(45) DEFAULT NULL,
  `email` char(1) DEFAULT NULL,
  `displayColumns` int(11) NOT NULL DEFAULT '1',
  `lastUpdatedBy` varchar(45) NOT NULL,
  `lastUpdatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reportId`),
  UNIQUE KEY `reportId_paramName_UNIQUE` (`companyId`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

CREATE TABLE `reportparameter` (
  `reportParameterId` bigint(20) NOT NULL AUTO_INCREMENT,
  `reportId` bigint(20) NOT NULL,
  `parameterName` varchar(100) DEFAULT NULL,
  `parameterValue` varchar(150) DEFAULT NULL,
  `parameterType` varchar(45) NOT NULL,
  `lastUpdatedBy` varchar(45) NOT NULL,
  `lastUpdatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reportParameterId`),
  UNIQUE KEY `reportId_paramName_UNIQUE` (`reportId`,`parameterName`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

CREATE TABLE `reportroleassociation` (
  `reportRoleAssociationId` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) NOT NULL,
  `reportId` bigint(20) NOT NULL,
  `roleId` int(11) NOT NULL,
  `lastUpdatedBy` varchar(45) NOT NULL,
  `lastUpdatedTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`reportRoleAssociationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
