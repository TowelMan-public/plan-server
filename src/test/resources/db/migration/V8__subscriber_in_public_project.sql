SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS subscriber_in_public_project;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE subscriber_in_public_project(
	public_project_id INT,
	user_id INT,
	authority_id INT,
	
	PRIMARY KEY(
		public_project_id,
		user_id
	),

	CONSTRAINT fk_public_project_id_to_subscriber_in_public_project
	    FOREIGN KEY (public_project_id) 
	    REFERENCES public_project (public_project_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_user_id_to_subscriber_in_public_project
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_authority_id_to_subscriber_in_public_project
	    FOREIGN KEY (authority_id) 
	    REFERENCES authority_list (authority_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);