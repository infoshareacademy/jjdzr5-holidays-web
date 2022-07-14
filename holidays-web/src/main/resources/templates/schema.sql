DROP TABLE IF EXISTS TBL_EMPLOYEES;

CREATE TABLE TBL_EMPLOYEES (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               first_name VARCHAR(250) NOT NULL,
                               last_name VARCHAR(250) NOT NULL,
                               department VARCHAR(250) DEFAULT NULL,
                               created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);