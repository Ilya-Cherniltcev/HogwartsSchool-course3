package ru.hogwarts.school;

import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FacultyServiceConstants {
    public final static Faculty CREATE_FACULTY = new Faculty(1, "1st faculty", "Red");
    public final static Faculty EXIST_FACULTY = new Faculty(2, "2nd faculty", "White");
    public final static Faculty UPDATE_FACULTY = new Faculty(3, "3d faculty", "White");
    public final static Faculty WRONG_FACULTY = new Faculty(4, "4th faculty", "Black");
    public final static String FILTER_COLOR = "White";
    public final static int WRONG_FACULTY_NUMBER = 100;
    public final static Collection<Faculty> FACULTY_FILTER_COLLECTION = new ArrayList<>(List.of(
            new Faculty(2, "2nd faculty", FILTER_COLOR),
            new Faculty(3, "3d faculty", FILTER_COLOR)));
}

