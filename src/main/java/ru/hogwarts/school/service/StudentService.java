package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {

    Student createStudent(Student student);

    Student getStudent(long id);

    Student updateStudent(Student student);

    void deleteStudent(long id);

    Collection<Student> filter(int age);

    Collection<Student> findStudentsByAgeBetween(int minAge, int maxAge);

    Collection<Student> getAllStudentsByFacultyId(long facId);
}
