package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

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

}
