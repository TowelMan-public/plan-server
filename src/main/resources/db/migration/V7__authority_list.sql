SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS authority_list;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE authority_list(
	authority_id INT PRIMARY KEY,
	authority_name VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO authority_list VALUES(1, 'NORMAL');
INSERT INTO authority_list VALUES(2, 'SUPER');
INSERT INTO authority_list VALUES(3, 'TENTATIVE');