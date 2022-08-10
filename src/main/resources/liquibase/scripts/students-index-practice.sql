-- liquibase formatted sql

-- changeset ilyacherniltsev:1
CREATE INDEX student_name_index ON student(name)