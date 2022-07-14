package ru.hogwarts.school.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long id = 0;


    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++id);
        faculties.put(id, faculty);
        return faculty;
    }

    @Override
    public Faculty getFaculty(long idFac) {
        if (faculties.containsKey(idFac)) {
            return faculties.get(idFac);
        }
        return null;
    }

    // ---------- фильтрация по цвету --------------------
    @Override
    public Collection<Faculty> filter(String color) {
        String colorWriteCase = StringUtils.capitalize(StringUtils.lowerCase(color));
        return faculties.values()
                .stream().filter(e -> e.getColor().equals(colorWriteCase))
                .collect(Collectors.toList());
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.replace(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    @Override
    public Faculty deleteFaculty(long idFac) {
        if (faculties.containsKey(idFac)) {
            return faculties.remove(idFac);
        }
        return null;
    }
}
