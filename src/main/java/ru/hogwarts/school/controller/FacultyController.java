package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}") // GET http://localhost:8080/faculty/2
    public ResponseEntity<Faculty> getFacultyById(@PathVariable long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    // ***********  находим факультет у студента с заданным id
    @GetMapping("id") // GET http://localhost:8080/faculty/id
    public ResponseEntity<Faculty> getFacultyByStudentId(@RequestParam long studId) {
        return new ResponseEntity<>(facultyService.getFacultyByStudentId(studId), HttpStatus.OK);
    }

    // =====   фильтруем факультеты по цвету или имени ===========================
    @GetMapping("filter") // GET http://localhost:8080/faculty/filter/White (/Хогвартс)
    public ResponseEntity<Collection<Faculty>> filterFacultiesByColorOrName(@RequestParam(required = false) String name,
                                                                            @RequestParam(required = false) String color) {
        Collection<Faculty> dataFaculty = null;
        if (name != null && !name.isBlank() && color == null) {
            dataFaculty = facultyService.filterFacultyByNameIgnoreCase(name);
        }
        if (color != null && !color.isBlank() && name == null) {
            dataFaculty = facultyService.filterFacultyByColorIgnoreCase(color);
        }
        if (dataFaculty == null || dataFaculty.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dataFaculty, HttpStatus.OK);
    }

    @PostMapping // POST http://localhost:8080/faculty
    public ResponseEntity<Faculty> createNewFaculty(@RequestBody Faculty faculty) {
        return new ResponseEntity<>(facultyService.createFaculty(faculty), HttpStatus.CREATED);
        // 201 Created	Успешный код состояния HTTP сервера 201: создан
    }


    @PutMapping // PUT http://localhost:8080/faculty
    public ResponseEntity<Faculty> updateStudent(@RequestBody Faculty faculty) {
        Faculty facultyTest = facultyService.updateFaculty(faculty);
        if (facultyTest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(facultyTest, HttpStatus.OK);
        // 200 OK	Успешный код состояния HTTP сервера 200:
    }

    @DeleteMapping("{id}") // DELETE http://localhost:8080/faculty/2
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
