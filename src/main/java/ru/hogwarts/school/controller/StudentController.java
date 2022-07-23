package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}") // GET http://localhost:8080/student/22
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // ******** определяем всех студентов по id факультета ************8
    @GetMapping("id") // GET http://localhost:8080/student/id
    public ResponseEntity<Collection<Student>> getStudentsByFacultyId(@RequestParam long facId) {
        return new ResponseEntity<>(studentService.getAllStudentsByFacultyId(facId), HttpStatus.OK);
    }

    // =====   фильтруем студентов по конкретному возрасту ===========================
    @GetMapping("filter/{age}") // GET http://localhost:8080/student/filter/14
    public ResponseEntity<Collection<Student>> filterStudentsByAge(@PathVariable int age) {
        Collection<Student> ageStudent = studentService.filter(age);
        if (ageStudent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ageStudent, HttpStatus.OK);
    }

    // =====   фильтруем студентов по возрасту в промежутке ===========================
    @GetMapping("filter") // GET http://localhost:8080/student/filter
    public ResponseEntity<Collection<Student>> filterStudentsByAge(@RequestParam int minAge, @RequestParam int maxAge) {
        Collection<Student> ageStudent = studentService.findStudentsByAgeBetween(minAge, maxAge);
        if (ageStudent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ageStudent, HttpStatus.OK);
    }

    @PostMapping // POST http://localhost:8080/student
    public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
        // 201 Created	Успешный код состояния HTTP сервера 201: создан
    }

    @PutMapping // PUT http://localhost:8080/student
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student studentTest = studentService.updateStudent(student);
        if (studentTest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentTest, HttpStatus.OK);
        // 200 OK	Успешный код состояния HTTP сервера 200:
    }

    @DeleteMapping("{id}") // DELETE http://localhost:8080/student/1
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
