package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Вызван метод createStudent");
        return studentRepository.save(student);
    }

    // ========== выбираем всех студентов по id факультета  =======
    @Override
    public Collection<Student> getAllStudentsByFacultyId(long facId) {
        logger.info("Вызван метод getAllStudentsByFacultyId");
        return studentRepository.findAllByFacultyId(facId);
    }

    @Override
    public Student getStudent(long idStud) {
        logger.info("Вызван метод getStudent");
        return studentRepository.findById(idStud).get();
    }

    @Override
    public Student updateStudent(Student student) {
        logger.info("Вызван метод updateStudent");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long idStud) {
        logger.info("Вызван метод deleteStudent");
        studentRepository.deleteById(idStud);
    }

    // ---------- фильтрация по конкретному возрасту --------------------
    @Override
    public Collection<Student> filter(int age) {
        logger.info("Вызван метод filter (student)");
        return studentRepository.findByAge(age);
    }

    // ---------- фильтрация по возрасту в промежутке между --------------------
    @Override
    public Collection<Student> findStudentsByAgeBetween(int minAge, int maxAge) {
        logger.info("Вызван метод findStudentsByAgeBetween");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    // ======= Получение общего количества студентов ===============
    @Override
    public int getTotalNumberOfStudents() {
        logger.info("Вызван метод getTotalNumberOfStudents");
        return studentRepository.getNumberOfAllStudents();
    }

    // ======= Получение среднего возраста студентов ===============
    @Override
    public double getAverageAgeOfStudents() {
        logger.info("Вызван метод getAverageAgeOfStudents");
        return studentRepository.getAverageAgeOfStudents();
    }

    // ======= Получение последних 5-ти студентов (у которых больше id) ===============
    @Override
    public List<Student> getLast5Students() {
        logger.info("Вызван метод getLast5Students");
        return studentRepository.getLast5Students();
    }

    // =============== TASK 4.5 ==============================
    // =======================================================
    // ---- Получение имен всех студентов, чье имя начинается с буквы {letter} ----
    @Override
    public List<String> getNamesOfStudentsWithFirstLetter(char letter) {
        List<Student> allStudents = studentRepository.findAll();
        char needLetter = Character.toUpperCase(letter);
        List<String> allNames = allStudents.stream()
                .map(t -> t.getName().toUpperCase(Locale.ROOT))
                .filter(c -> c.startsWith(Character.toString(needLetter)))
                .sorted()
                .collect(Collectors.toList());

        logger.debug("All suitable student's names are: {}", allNames.toString());
        return allNames;
    }

    // шаг 2  ---- возвращаем средний возраст всех студентов, используя стримы  ----------------
    @Override
    public Double getAverageAgeOfStudentsWithStreams() {
        List<Student> allStudents = studentRepository.findAll();
        Double middleAge = allStudents.stream()
                .mapToInt(t -> t.getAge())
                .average().orElseThrow();
        logger.debug("Average age of all students is - {}", middleAge);
        return middleAge;
    }
}
