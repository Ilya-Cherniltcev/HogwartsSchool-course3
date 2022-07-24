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

    // ---------- фильтрация по цвету или имени --------------------
    @Override
    public Collection<Faculty> filterFacultyByColorOrByName(String color, String name) {
        return facultyRepository.getFacultyByColorIgnoreCaseOrNameIgnoreCase(color, name);
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
