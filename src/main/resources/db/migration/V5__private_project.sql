SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS private_project;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE private_project(
	project_id INT PRIMARY KEY,
	project_name VARCHAR(100) NOT NULL,
	user_id INT,
	is_deleted TINYINT(1) DEFAULT 0,
	
	CONSTRAINT fk_project_id_to_private_project
	    FOREIGN KEY (project_id) 
	    REFERENCES project (project_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_user_id_to_private_project
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);