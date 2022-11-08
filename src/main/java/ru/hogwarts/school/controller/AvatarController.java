package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.StudentAvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("avatar")
public class AvatarController {
    private final StudentAvatarService studentAvatarService;

    public AvatarController(StudentAvatarService studentAvatarService) {
        this.studentAvatarService = studentAvatarService;
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
