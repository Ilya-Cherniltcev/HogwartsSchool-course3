//package ru.hogwarts.school;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.hogwarts.school.model.Student;
//import ru.hogwarts.school.service.StudentService;
//import ru.hogwarts.school.service.StudentServiceImpl;
//
//import java.util.Collection;
//
//import static ru.hogwarts.school.StudentServiceConstants.*;
//
//@SpringBootTest
//class StudentServiceTests {
//    StudentService studentService = new StudentServiceImpl();
//
//    @BeforeEach
//    void setup() {
//        Student tempStudent = studentService.createStudent(new Student(1, "CreateStudent", 14));
//        tempStudent = studentService.createStudent(new Student(2, "ExistStudent", 15));
//        tempStudent = studentService.createStudent(new Student(3, "UpdateStudent", 15));
//    }
//
//    @Test
//    void shouldReturnNewStudent() {
//        Student actual = studentService.createStudent(CREATE_STUDENT);
//        Assertions.assertEquals(CREATE_STUDENT, actual);
//    }
//
//    @Test
//    void shouldReturnExistStudentById() {
//        Student actual = studentService.getStudent(2);
//        Assertions.assertEquals(EXIST_STUDENT, actual);
//    }
//
//    @Test
//    void shouldReturnCollectionOfStudents() {
//        Collection<Student> actualFilterCollection = studentService.filter(FILTER_AGE);
//        Assertions.assertEquals(STUDENT_FILTER_COLLECTION, actualFilterCollection);
//    }
//
//
//    @Test
//    void shouldReturnUpdateStudent() {
//        Student actual = studentService.updateStudent(UPDATE_STUDENT);
//        Assertions.assertEquals(UPDATE_STUDENT, actual);
//    }
//
//    @Test
//    void shouldReturnStudentIfSuccessDeletingById() {
//        Student actual = studentService.deleteStudent(2);
//        Assertions.assertEquals(EXIST_STUDENT, actual);
//    }
//
//    // ==========================
//    @Test
//    void shouldReturnNullIfDoesNotContainKeyByGetting() {
//        Student studentNull = studentService.getStudent(WRONG_STUDENT_NUMBER);
//        Assertions.assertNull(studentNull);
//    }
//
//    @Test
//    void shouldReturnNullIfDoesNotContainKeyByUpdating() {
//        Student studentNull = studentService.updateStudent(WRONG_STUDENT);
//        Assertions.assertNull(studentNull);
//    }
//
//    @Test
//    void shouldReturnNullIfDoesNotContainKeyByDeleting() {
//        Student studentNull = studentService.deleteStudent(WRONG_STUDENT_NUMBER);
//        Assertions.assertNull(studentNull);
//    }
//}
