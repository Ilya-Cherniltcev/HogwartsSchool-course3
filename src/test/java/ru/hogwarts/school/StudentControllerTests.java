package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    private final String testStudentName = "Simple test student name";
    private final int testStudentAge = 22;

    private long studentId;
    private Student student = new Student();
    private ResponseEntity<Student> response;

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;


    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeEach
    public void createTempStudent() {
        // ------------  создаем временного студента ----------------------
        // -----------  и уточняем его id   -------------------------------
        student.setName(testStudentName);
        student.setAge(testStudentAge);

        String urlPost = "http://localhost:" + port + "/student";
        response = restTemplate.postForEntity(urlPost, student, Student.class);

        studentId = response.getBody().getId();
    }

    @AfterEach
    public void deleteTempStudent() {
        // ======== удаляем временного студента  =====================================================
        String urlDel = "http://localhost:" + port + "/student/" + studentId;

        response = restTemplate.exchange(urlDel, HttpMethod.DELETE, null,
                Student.class, studentId);
    }

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudentById() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/", Student.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByFacultyId() throws Exception {
        String url = "http://localhost:" + port + "/student/id";
//        Student response = restTemplate.getForObject(url, Student.class);
//        ResponseEntity<List<Student>> response = restTemplate.exchange(url, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<Student>>() {});
        Assertions.assertThat(this.restTemplate.getForObject(url, Student.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByAge() throws Exception {
        String url = "http://localhost:" + port + "/student/filter";
        Assertions.assertThat(this.restTemplate.getForObject(url, Student.class))
                .isNotNull();
//        ResponseEntity<Collection> response = restTemplate.getForEntity(url, Collection.class);
//        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testCreateNewStudent() throws Exception {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId(), notNullValue());
        assertThat(response.getBody().getName(), is(testStudentName));
        assertThat(response.getBody().getAge(), is(testStudentAge));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setName("Test student");
        int updateStudentAge = 15;
        testStudent.setAge(updateStudentAge);

        // ******************** (1) создаем временного студента ********************
        String urlPost = "http://localhost:" + port + "/student";
        ResponseEntity<Student> responsePost = restTemplate.postForEntity(urlPost, testStudent, Student.class);
        // ___________ определяем id созданного временного студента ___________________________________
        long studentIdUpdate = responsePost.getBody().getId();

        // ******************** (2) изменяем временного студента ********************
        String urlUpdate = "http://localhost:" + port + "/student";
        HttpEntity<Student> entity = new HttpEntity<Student>(testStudent);

        restTemplate.put(urlUpdate, responsePost);
        ResponseEntity<Student> responseUpdate = restTemplate.exchange(urlUpdate, HttpMethod.PUT, responsePost,
                Student.class, studentIdUpdate);

      //  assertThat(responseUpdate.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseUpdate.getBody().getId(), notNullValue());
       // assertThat(responseUpdate.getBody().getAge(), is(updateStudentAge));

        // ********************  (3) удаляем временного студента  ********************

        String urlDel = "http://localhost:" + port + "/student/" + studentIdUpdate;

        ResponseEntity<Student> responseDel = restTemplate.exchange(urlDel, HttpMethod.DELETE, null,
                Student.class, studentIdUpdate);

    }

    @Test
    public void testDeleteStudent() throws Exception {

        String urlDel = "http://localhost:" + port + "/student/" + studentId;

        ResponseEntity<Student> responseDel = restTemplate.exchange(urlDel, HttpMethod.DELETE, null,
                Student.class, studentId);
        assertThat(responseDel.getStatusCode(), is(HttpStatus.OK));
    }
}



