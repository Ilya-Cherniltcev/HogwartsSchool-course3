create table student
(
    id         bigint             not null
        primary key,
    age        integer default 20 not null
        constraint age_constraint
            check (age > 16)
        constraint constraint_age
            check (age >= 16),
    name       varchar(255)       not null
        constraint name_uniq
            unique
        constraint name_unique
            unique
        constraint unique_name
            unique,
    faculty_id bigint
        constraint fk6geq7tnjed7u4hvgv1ac6lyh
            references faculty
);

alter table student
    owner to student;

create index student_name_index
    on student (name);

