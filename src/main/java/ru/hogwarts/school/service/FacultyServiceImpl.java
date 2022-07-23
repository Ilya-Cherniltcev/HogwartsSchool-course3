package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(long idFac) {
        return facultyRepository.findById(idFac).get();
    }

    @Override
    public Faculty getFacultyByStudentId(long studId) {
        return facultyRepository.getFacultyByStudentsId(studId);
    }

    // ---------- фильтрация по цвету  --------------------
    @Override
    public Collection<Faculty> filterFacultyByColorIgnoreCase(String color) {
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }

    // ---------- фильтрация по имени  --------------------
    @Override
    public Collection<Faculty> filterFacultyByNameIgnoreCase(String name) {
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long idFac) {
        facultyRepository.deleteById(idFac);
    }

}
