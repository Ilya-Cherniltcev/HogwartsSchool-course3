-- liquibase formatted sql

-- changeset ilyacherniltsev:1
CREATE INDEX IF NOT EXISTS faculty_name_color_index ON faculty(name,color)