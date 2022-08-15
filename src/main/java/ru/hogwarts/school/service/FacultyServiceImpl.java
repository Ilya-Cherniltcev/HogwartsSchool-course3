package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Вызван метод createFaculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(long idFac) {
        logger.info("Вызван метод getFaculty");
        return facultyRepository.findById(idFac).get();
    }

    @Override
    public Faculty getFacultyByStudentId(long studId) {
        logger.info("Вызван метод getFacultyByStudentId");
        return facultyRepository.getFacultyByStudentsId(studId);
    }

    // ---------- фильтрация по цвету или имени --------------------
    @Override
    public Collection<Faculty> filterFacultyByColorOrByName(String color, String name) {
        logger.info("Вызван метод filterFacultyByColorOrByName");
        return facultyRepository.getFacultyByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Вызван метод updateFaculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long idFac) {
        logger.info("Вызван метод deleteFaculty");
        facultyRepository.deleteById(idFac);
    }

    // ========== Task 4.5 (Параллельные стримы) =============
    // ----- Шаг 3. Возвращаем самое длинное название факультета ---------------------
    @Override
    public String getFacultiesLongestName() {
        List<Faculty> allFaculties = facultyRepository.findAll();
        String theLongestName = allFaculties.stream()
                .map(t -> t.getName())
                .max(Comparator.comparing(String::length)).get();
        logger.debug("The longest faculties name is '{}', and his length is {}", theLongestName, theLongestName.length());
        return theLongestName;
    }

}
