package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;

@RestController
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("getPort")
    public ResponseEntity<Integer> getPortNumber() {
        return new ResponseEntity<>(infoService.getThisPort(), HttpStatus.OK);
    }

    // === Task 4.5 =======================================
    //--- Шаг 4 -- возвращаем целочисленное значение --
    @GetMapping("getSum") // GET http://localhost:8080/student/getSum
    public ResponseEntity<Integer> getSum() {
        return new ResponseEntity<>(infoService.getSum(), HttpStatus.OK);
    }

}
