SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS notice_todo;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE notice_todo(
	notice_id INT PRIMARY KEY,
	todo_id INT,
	message VARCHAR(2000) NOT NULL,
	
	CONSTRAINT fk_project_id_to_notice_todo
	    FOREIGN KEY (notice_id) 
	    REFERENCES notice (notice_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_todo_id_to_notice_todo
	    FOREIGN KEY (todo_id) 
	    REFERENCES todo (todo_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);