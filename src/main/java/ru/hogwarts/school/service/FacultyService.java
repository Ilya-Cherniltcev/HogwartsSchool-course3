package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty getFaculty(long id);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(long id);

    Collection<Faculty> filterFacultyByColorOrByName(String color, String name);

    Faculty getFacultyByStudentId(long studId);

}
