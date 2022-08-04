package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentAvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;
    private final StudentAvatarService studentAvatarService;

    public StudentController(StudentService studentService, StudentAvatarService studentAvatarService) {
        this.studentService = studentService;
        this.studentAvatarService = studentAvatarService;
    }

    @GetMapping("{id}") // GET http://localhost:8080/student/22
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // ******** определяем всех студентов по id факультета ************
    @GetMapping("id") // GET http://localhost:8080/student/id
    public ResponseEntity<Collection<Student>> getStudentsByFacultyId(@RequestParam long facId) {
        return new ResponseEntity<>(studentService.getAllStudentsByFacultyId(facId), HttpStatus.OK);
    }

    // ///////////////// выводим общее число всех студентов \\\\\\\\\\\\\\\\\\\\\\\\\
    @GetMapping("total") // GET http://localhost:8080/student/total
    public ResponseEntity<Integer> getNumberOfStudents() {
        return new ResponseEntity<>(studentService.getTotalNumberOfStudents(), HttpStatus.OK);
    }

    // ///////////////// выводим средний возраст студентов \\\\\\\\\\\\\\\\\\\\\\\\\
    @GetMapping("averageAge") // GET http://localhost:8080/student/averageAge
    public ResponseEntity<Integer> getAvgAgeOfStudents() {
        return new ResponseEntity<>(studentService.getAverageAgeOfStudents(), HttpStatus.OK);
    }

    // ///////////////// выводим последних 5-ти студентов (у которых больше id) \\\\\\\\\\\\\\\\\\\\\\\\\
    @GetMapping("last5") // GET http://localhost:8080/student/last5
    public ResponseEntity<List<Student>> getLast5Students() {
        return new ResponseEntity<List<Student>>(studentService.getLast5Students(), HttpStatus.OK);
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


    // *************** Работаем с аватаркой студента ********************
    // ***************  (1) загружаем аватарку  ***********************
    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 400 * 400) {
            return ResponseEntity.badRequest().body("The file is too big");
        }
        studentAvatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    // ***************  (2) скачиваем превьюшку аватарки  ***********************
    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatarPreview(@PathVariable Long id) {
        Avatar avatar = studentAvatarService.findStudentAvatar(id);
        // -----------  передаем в http-заголовке тип файла и его длину   ---------
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getPreview().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getPreview());

    }

    // ***************  (3) скачиваем аватарку  ***********************
    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = studentAvatarService.findStudentAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200); // --- возвращаем вручную ответ ОК
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    // ***************  (4) получаем списки аватарок постранично (пагинация)  ***********************
    @GetMapping("avatars") // GET http://localhost:8080/student/avatars
    public ResponseEntity<List<Avatar>> getAllAvatars(@RequestParam("page") int pageNumber,
                                                      @RequestParam("size") int pageSize) {
        return new ResponseEntity<List<Avatar>>(studentAvatarService.getAllAvatars(pageNumber, pageSize), HttpStatus.OK);
    }

}
