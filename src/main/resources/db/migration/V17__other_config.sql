SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS other_config;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE other_config(
	other_config_key VARCHAR(100) PRIMARY KEY,
	other_config_value VARCHAR(100) NOT NULL
);

INSERT INTO other_config VALUES('LAST_RUN_NOTICE_TASK_DATE_STRING', '');