SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS public_project;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE public_project(
	public_project_id INT PRIMARY KEY,
	project_name VARCHAR(100) NOT NULL,
	start_date DATETIME,
	finish_date DATETIME,
	is_completed TINYINT(1) DEFAULT 0,
	is_deleted TINYINT(1) DEFAULT 0,
	
	CONSTRAINT fk_project_id_to_public_project
	    FOREIGN KEY (public_project_id) 
	    REFERENCES project (project_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);