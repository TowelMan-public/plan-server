SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS notice;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE notice(
	notice_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	
	CONSTRAINT fk_user_id_to_notice
	    FOREIGN KEY (user_id) 
	    REFERENCES users (user_id)
	    ON DELETE CASCADE ON UPDATE RESTRICT
);