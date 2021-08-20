SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS user_config;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE user_config(
	user_id INT PRIMARY KEY,
	before_deadline_for_todo_notice INT  NOT NULL,
	before_deadline_for_project_notice INT  NOT NULL,
	is_push_inserted_todo_notice TINYINT(1) DEFAULT 1,
	is_push_inserted_started_todo_notice TINYINT(1) DEFAULT 1,
	
	CONSTRAINT fk_user_id_to_user_config
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);