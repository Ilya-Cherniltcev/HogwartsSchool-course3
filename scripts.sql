select * from student;
select * from student where age between 12 and 14;
select name from student;
select * from student where name like '%Г%';
select * from student where age < id;
select * from student order by age;

select f.name
from student as s, faculty as f
where f.id = s.faculty_id
and s.name like '%Уизли%';