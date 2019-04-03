CREATE SCHEMA `uk` ;

CREATE TABLE IF NOT EXISTS `log_operation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `no` VARCHAR(45) NOT NULL,
  `offset` BIGINT NULL COMMENT 'kafka offset 记录',
  `module_name` VARCHAR(45) NULL COMMENT '模块名称',
  `description` VARCHAR(256) NULL COMMENT '操作描述',
  `req_user` VARCHAR(45) NULL COMMENT '请求用户',
  `req_method` VARCHAR(8) NULL COMMENT '请求方法类型',
  `req_params` VARCHAR(512) NULL COMMENT '请求参数',
  `is_success` VARCHAR(45) NULL COMMENT '是否成功，0：false;1:true',
  `error_reason` VARCHAR(512) NULL,
  `create_time` TIMESTAMP NULL DEFAULT current_timestamp,
  `update_time` TIMESTAMP NULL DEFAULT current_timestamp,
  `version` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `no_UNIQUE` (`no` ASC),
  UNIQUE INDEX `offset_UNIQUE` (`offset` ASC))
ENGINE = InnoDB