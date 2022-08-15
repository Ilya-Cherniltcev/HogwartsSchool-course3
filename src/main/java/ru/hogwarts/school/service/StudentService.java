package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student getStudent(long id);

    Student updateStudent(Student student);

    void deleteStudent(long id);

    Collection<Student> filter(int age);

    Collection<Student> findStudentsByAgeBetween(int minAge, int maxAge);

    Collection<Student> getAllStudentsByFacultyId(long facId);

    // ======= Получение общего количества студентов ===============
    int getTotalNumberOfStudents();

    // ======= Получение среднего возраста студентов ===============
    double getAverageAgeOfStudents();

    // ======= Получение последних 5-ти студентов (у которых больше id) ===============
    List<Student> getLast5Students();

    // ----- TASK 4.5 -----
    // ---- Получение имен всех студентов, чье имя начинается с буквы {letter} ----
    List<String> getNamesOfStudentsWithFirstLetter(char letter);

    // шаг 2  ---- возвращаем средний возраст всех студентов, используя стримы  ----------------
    Double getAverageAgeOfStudentsWithStreams();
}
