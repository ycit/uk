CREATE SCHEMA IF NOT EXISTS `uk` ;



CREATE TABLE IF NOT EXISTS `uk`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_no` VARCHAR(45) NOT NULL COMMENT '用户编号',
  `username` VARCHAR(45) NULL COMMENT '用户名、登录用户名',
  `password` VARCHAR(45) NULL,
  `telephone` VARCHAR(16) NULL,
  `email` VARCHAR(128) NULL,
  `create_time` TIMESTAMP NULL DEFAULT current_timestamp,
  `update_time` TIMESTAMP NULL DEFAULT current_timestamp,
  `version` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `no_UNIQUE` (`user_no` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
  ENGINE = InnoDB

;

insert into user (user_no, username, password, telephone, email)
           values (substring(MD5(RAND()),1,20), 'admin', 'admin', '11111111111', '12s@163.com');

CREATE TABLE IF NOT EXISTS `uk`.`log_operation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `log_no` VARCHAR(45) NOT NULL COMMENT '日志编号',
  `offset` BIGINT NOT NULL COMMENT 'kafka offset 记录',
  `module_name` VARCHAR(45) NULL COMMENT '模块名称',
  `description` VARCHAR(256) NULL COMMENT '操作描述',
  `req_user` VARCHAR(45) NULL COMMENT '请求用户',
  `req_method` VARCHAR(8) NULL COMMENT '请求方法类型',
  `req_params` VARCHAR(512) NULL COMMENT '请求参数',
  `is_success` VARCHAR(45) NULL COMMENT '是否成功，0：false;1:true',
  `error_reason` VARCHAR(512) NULL,
  `create_time` TIMESTAMP NULL DEFAULT current_timestamp,
  `update_time` TIMESTAMP NULL DEFAULT current_timestamp,
  `version` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `no_UNIQUE` (`log_no` ASC),
  UNIQUE INDEX `offset_UNIQUE` (`offset` ASC))
  ENGINE = InnoDB