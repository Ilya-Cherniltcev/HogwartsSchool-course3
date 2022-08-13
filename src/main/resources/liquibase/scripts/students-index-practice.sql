-- liquibase formatted sql

-- changeset ilyacherniltsev:1
CREATE INDEX IF NOT EXISTS student_name_index ON student(name)