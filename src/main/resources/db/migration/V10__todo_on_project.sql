SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS todo_on_project;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE todo_on_project(
	todo_on_project_id INT PRIMARY KEY,
	project_id INT,
	todo_name VARCHAR(100) NOT NULL,
	start_date DATETIME NOT NULL,
	finish_date DATETIME NOT NULL,
	is_copy_contents_to_users TINYINT(1) DEFAULT 1,
	is_completed TINYINT(1) DEFAULT 0,
	is_deleted TINYINT(1) DEFAULT 0,
	
	CONSTRAINT fk_todo_id_to_todo_on_project
	    FOREIGN KEY (todo_on_project_id) 
	    REFERENCES todo (todo_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_project_id_to_todo_on_project
	    FOREIGN KEY (project_id) 
	    REFERENCES project (project_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);