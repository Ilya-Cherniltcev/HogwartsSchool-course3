package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;

@Service
public class InfoServiceImpl implements InfoService {
    private final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);

    private final ServletRequest request;

    public InfoServiceImpl(ServletRequest request) {
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
