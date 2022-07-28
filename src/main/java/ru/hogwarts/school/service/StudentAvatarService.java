package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class StudentAvatarService {

    @Value("${students.avatars.dir.path}")
    private String avatarDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public StudentAvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    // ---------- загружаем новую аватарку / либо обновляем существующую ------------------------------------
    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = studentService.getStudent(studentId);

        Path filepath = Path.of(avatarDir, studentId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filepath.getParent());
        Files.deleteIfExists(filepath);

        // =====================  читаем файл пользователя -> создаем пустой файл в нашей папке
        // =====================  -> переписываем в него файл пользователя  ===================================
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filepath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos); // ============  непосредственная перезапись файлов (из входного потока в выходной)
        } // ===  все потоки автоматически закрываются ===

        // ***********   создаем аватарку и записываем ее уменьшенную копию (превьюшку) в базу данных конкретного студента *************
        Avatar avatar = findStudentAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filepath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setPreview(generateImagePreview(filepath));

        avatarRepository.save(avatar);
    }

    // -------- делаем превьюшку из аватарки --------------------------------------------------
    private byte[] generateImagePreview(Path filepath) throws IOException{
        try (InputStream is = Files.newInputStream(filepath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            BufferedImage image = ImageIO.read(bis);

            int width = image.getWidth();
            int ratio = image.getHeight() / width;

            width = 150;
            int height = width * ratio;
            if (height < 1) {
                height = 150;
            }
            BufferedImage preview = new BufferedImage(width, height , image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, width, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filepath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    // ------  находим аватарку студента по его id ----------------------
    // ------  Если ее нет, создаем новую аватарку ---------------------
    public Avatar findStudentAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }


    // ----- определяем расширение файла аватарки ------------------------------
    private String getExtension(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }

}
