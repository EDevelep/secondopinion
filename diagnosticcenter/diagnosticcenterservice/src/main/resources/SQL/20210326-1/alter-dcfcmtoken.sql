ALTER TABLE `diagnosticcenter`.`diagnosticcenterfcmtoken`
	CHANGE COLUMN `diagnosticenteruserId` `diagnosticCenterUserId` BIGINT(20) NOT NULL DEFAULT '0' AFTER `diagnosticcenterfcmtokenId`;