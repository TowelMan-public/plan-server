SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS have_terminal_list;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE have_terminal_list(
	terminal_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	terminal_name VARCHAR(100) NOT NULL,
	
	
	CONSTRAINT fk_user_id_to_have_terminal_list
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);