create database demo;
USE demo;
CREATE TABLE
    IF NOT EXISTS category (
                               id INTEGER,
                               `name` VARCHAR (100),
    description VARCHAR (2000),
    age_group VARCHAR (20),
    created DATETIME,
    inserted BIGINT,
    PRIMARY KEY (`id`)
    );
CREATE TABLE
    IF NOT EXISTS Lego_Set (id INTEGER, NAME VARCHAR (100), min_Age INTEGER, max_Age INTEGER);
CREATE TABLE
    IF NOT EXISTS Handbuch (handbuch_id INTEGER, author VARCHAR (100), `text` TEXT);
CREATE TABLE
    IF NOT EXISTS Model (NAME VARCHAR (100), description TEXT, lego_set INTEGER);