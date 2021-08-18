SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS unsent_notice;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE unsent_notice(
	unsend_notice_id INT AUTO_INCREMENT PRIMARY KEY,
	notice_id INT,
	terminal_id INT,
	
	CONSTRAINT fk_notice_id_to_unsend_notice
	    FOREIGN KEY (notice_id) 
	    REFERENCES notice (notice_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT,
	    
	CONSTRAINT fk_terminal_id_to_unsend_notice
	    FOREIGN KEY (terminal_id) 
	    REFERENCES have_terminal_list (terminal_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);