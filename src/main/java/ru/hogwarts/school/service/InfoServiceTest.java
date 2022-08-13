package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;

@Service
@Profile("test")
public class InfoServiceTest implements InfoService {
    Logger logger = LoggerFactory.getLogger(InfoServiceTest.class);

    private final ServletRequest request;

    public InfoServiceTest(ServletRequest request) {
        this.request = request;
    }

    // --------------  возвращаем текущий порт работы приложения ------------
    @Override
    public Integer getThisPort() {
        logger.debug("Запущен метод getThisPort");
        Integer thisPort = request.getLocalPort();
        logger.debug("Текущий порт - {}", thisPort);
        return thisPort;
    }
}
