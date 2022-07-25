ALTER TABLE `curemetricuser`.`invoice`
	ADD COLUMN `discount` DOUBLE(22,0) NULL DEFAULT NULL AFTER `amount`;