create table avatar
(
    id         bigint not null
        primary key,
    file_path  varchar(255),
    file_size  bigint not null,
    media_type varchar(255),
    preview    oid,
    student_id bigint
        constraint fkm6mgi7ty5gienen5rege7rc22
            references student
);

alter table avatar
    owner to student;

