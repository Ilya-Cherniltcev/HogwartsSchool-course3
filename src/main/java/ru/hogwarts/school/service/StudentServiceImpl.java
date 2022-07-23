package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // ========== выбираем всех студентов по id факультета  =======
    @Override
    public Collection<Student> getAllStudentsByFacultyId(long facId) {
        return studentRepository.findAllByFacultyId(facId);
    }

    @Override
    public Student getStudent(long idStud) {
        return studentRepository.findById(idStud).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long idStud) {
        studentRepository.deleteById(idStud);
    }

    // ---------- фильтрация по конкретному возрасту --------------------
    @Override
    public Collection<Student> filter(int age) {
        return studentRepository.findByAge(age);
    }

    // ---------- фильтрация по возрасту в промежутке между --------------------
    @Override
    public Collection<Student> findStudentsByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }
}
