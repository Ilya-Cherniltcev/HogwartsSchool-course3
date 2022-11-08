-- Ограничение на возраст студента ----
alter table student
    add constraint constraint_age check (age >= 16);

-- Ограничение на уникальность имени и проверку на ноль ----
alter table student
    alter column name set not null,
    add constraint unique_name unique (name);

-- Ограничение на уникальность пары значений (цвет, имя) в таблице факультетов ---
alter table faculty
    add constraint name_color_unique unique (color, name);

-- При создании студента без возраста ему автоматически присваивается 20 лет ---
alter table student
    alter column age set default (20);



