package ru.hogwarts.school.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(long idFac) {
        return facultyRepository.findById(idFac).get();
    }

    // ---------- фильтрация по цвету --------------------
    @Override
    public Collection<Faculty> filter(String color) {
        String colorWriteCase = StringUtils.capitalize(StringUtils.lowerCase(color));
        return facultyRepository.findByColor(colorWriteCase);
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
