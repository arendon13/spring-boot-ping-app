DROP DATABASE IF EXISTS ping_warmup;
CREATE DATABASE ping_warmup;
USE ping_warmup;
DROP TABLE IF EXISTS tbl_Pings;

CREATE TABLE tbl_Pings(
	id		int NOT NULL auto_increment UNIQUE,
	ping	DATETIME DEFAULT NULL
);