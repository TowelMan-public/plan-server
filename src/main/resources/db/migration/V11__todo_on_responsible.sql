SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS todo_on_responsible;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE todo_on_responsible(
	todo_on_project_id INT,
	user_id INT ,
	todo_on_responsible_id INT  PRIMARY KEY,	
	project_id INT NOT NULL, 	
	is_completed TINYINT(1) DEFAULT 0,
	is_deleted TINYINT(1) DEFAULT 0,

	CONSTRAINT fk_todo_on_project_id_to_todo_on_responsible
	    FOREIGN KEY (todo_on_project_id) 
	    REFERENCES todo_on_project (todo_on_project_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,

	CONSTRAINT fk_todo_id_to_todo_on_responsible
	    FOREIGN KEY (todo_on_responsible_id) 
	    REFERENCES todo (todo_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    	    
	CONSTRAINT fk_project_id_to_todo_on_responsible
	    FOREIGN KEY (project_id) 
	    REFERENCES project (project_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_user_id_to_todo_on_responsible
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);