package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long id = 0;

    @Override
    public Student createStudent(Student student) {
        student.setId(++id);
        students.put(id, student);
        return student;
    }

    @Override
    public Student getStudent(long idStud) {
        if (students.containsKey(idStud)) {
            return students.get(idStud);
        }
        return null;
    }

    // ---------- фильтрация по возрасту --------------------
    @Override
    public Collection<Student> filter(int age) {
        return students.values()
                .stream().filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.replace(student.getId(), student);
            return student;
        }
        return null;
    }

    @Override
    public Student deleteStudent(long idStud) {
        if (students.containsKey(idStud)) {
            return students.remove(idStud);
        }
        return null;
    }
}
