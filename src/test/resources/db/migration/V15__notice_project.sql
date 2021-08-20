SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS notice_project;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE notice_project(
	notice_id INT PRIMARY KEY,
	project_id INT,
	message VARCHAR(2000) NOT NULL,
	
	CONSTRAINT fk_notice_id_to_notice_project
	    FOREIGN KEY (notice_id) 
	    REFERENCES notice (notice_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_project_id_to_notice_project
	    FOREIGN KEY (project_id) 
	    REFERENCES project (project_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);