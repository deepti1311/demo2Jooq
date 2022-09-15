/**
  1. drop flyway_test schema if created
  2. create flyway_test schema
*/
DROP SCHEMA IF EXISTS flyway_test CASCADE;

CREATE SCHEMA flyway_test;

CREATE SEQUENCE flyway_test.s_student_id START WITH 1;

CREATE TABLE flyway_test.Student
(
    id              INT           NOT NULL,
    first_name      VARCHAR(50)   NOT NULL ,
    last_name       VARCHAR(50)   NOT NULL,
    age             INT,
    email           VARCHAR(50)   unique ,
    collage_name    VARCHAR(50) ,

    CONSTRAINT pk_t_student PRIMARY KEY (ID)
);