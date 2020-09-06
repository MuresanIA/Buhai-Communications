-- ####################
-- 1. Create Schema
-- ####################

--CREATE USER buhaiu WITH PASSWORD buhaiu;
--CREATE DATABASE buhai_communications;
--GRANT ALL PRIVILEGES ON DATABASE buhai_communications to buhaiu ;

-- ####################
-- 2. Create Tables
-- ####################

CREATE TABLE IF NOT EXISTS USER
(
user_id INT NOT NULL AUTO_INCREMENT,
user_name TEXT NOT NULL,
user_password TEXT NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS MESSAGE
(
message_id INT NOT NULL AUTO_INCREMENT,
message_text TEXT NOT NULL,
PRIMARY KEY (message_id)
)

