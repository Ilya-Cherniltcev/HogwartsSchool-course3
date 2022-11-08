create table faculty
(
    id    bigint not null
        primary key,
    color varchar(255),
    name  varchar(255),
    constraint color_name_unique
        unique (color, name),
    constraint colour_name_unique
        unique (color, name),
    constraint name_color_unique
        unique (color, name)
);

alter table faculty
    owner to student;

create index faculty_name_color_index
    on faculty (name, color);

