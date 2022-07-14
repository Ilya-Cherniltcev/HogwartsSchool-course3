package ru.hogwarts.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// =====   ошибка 400 Bad Request =======
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class WrongNumberException extends RuntimeException {
    public WrongNumberException(String Alert) {
    }
}
