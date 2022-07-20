//package ru.hogwarts.school;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.hogwarts.school.model.Faculty;
//import ru.hogwarts.school.service.FacultyService;
//import ru.hogwarts.school.service.FacultyServiceImpl;
//
//import java.util.Collection;
//
//import static ru.hogwarts.school.FacultyServiceConstants.*;
//
//@SpringBootTest
//class FacultyServiceTests {
//    FacultyService facultyService = new FacultyServiceImpl();
//
//    @BeforeEach
//    void setup() {
//        Faculty tempFaculty = facultyService.createFaculty(new Faculty(1, "1st faculty", "Red"));
//        tempFaculty = facultyService.createFaculty(new Faculty(2, "2nd faculty", "White"));
//        tempFaculty = facultyService.createFaculty(new Faculty(3, "3d faculty", "White"));
//    }
//
//    @Test
//    void shouldReturnNewFaculty() {
//        Faculty actual = facultyService.createFaculty(CREATE_FACULTY);
//        Assertions.assertEquals(CREATE_FACULTY, actual);
//    }
//
//    @Test
//    void shouldReturnExistFacultyById() {
//        Faculty actual = facultyService.getFaculty(2);
//        Assertions.assertEquals(EXIST_FACULTY, actual);
//    }
//
//    @Test
//    void shouldReturnCollectionOfFaculties() {
//        Collection<Faculty> actualFilterCollection = facultyService.filter(FILTER_COLOR);
//        Assertions.assertEquals(FACULTY_FILTER_COLLECTION, actualFilterCollection);
//    }
//
//    @Test
//    void shouldReturnUpdateFaculty() {
//        Faculty actual = facultyService.updateFaculty(UPDATE_FACULTY);
//        Assertions.assertEquals(UPDATE_FACULTY, actual);
//    }
//
//    @Test
//    void shouldReturnFacultyIfSuccessDeletingById() {
//        Faculty actual = facultyService.deleteFaculty(2);
//        Assertions.assertEquals(EXIST_FACULTY, actual);
//    }
//
//    // ==========================
//    @Test
//    void shouldReturnNullIfDoesNotContainKeyByGetting() {
//        Faculty facultyNull = facultyService.getFaculty(WRONG_FACULTY_NUMBER);
//        Assertions.assertNull(facultyNull);
//    }
//
//    @Test
//    void shouldReturnNullIfDoesNotContainKeyByUpdating() {
//        Faculty facultyNull = facultyService.updateFaculty(WRONG_FACULTY);
//        Assertions.assertNull(facultyNull);
//    }
//
//    @Test
//    void shouldReturnNullIfDoesNotContainKeyByDeleting() {
//        Faculty facultyNull = facultyService.deleteFaculty(WRONG_FACULTY_NUMBER);
//        Assertions.assertNull(facultyNull);
//    }
//}
