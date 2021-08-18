SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS content;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE content(
	content_id INT AUTO_INCREMENT PRIMARY KEY,
	todo_id INT,
	content_title VARCHAR(100) NOT NULL,
	content_explanation VARCHAR(2000) NOT NULL,
	is_completed TINYINT(1) DEFAULT 0,
	is_deleted TINYINT(1) DEFAULT 0,
	    
	CONSTRAINT fk_todo_id_to_content
	    FOREIGN KEY (todo_id) 
	    REFERENCES todo (todo_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);