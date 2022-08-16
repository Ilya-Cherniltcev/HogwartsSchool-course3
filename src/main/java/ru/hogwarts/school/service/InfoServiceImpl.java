package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import java.util.stream.Stream;


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


    // === Task 4.5 =======================================
    //--- Шаг 4 -- возвращаем целочисленное значение, равное сумме всех чисел от 0 до 1 000 000 --

    @Override
    public Long getSum() {
        // (1) *** вычисляем время выполнения операции суммирования первоначальным методом ***
        // ---- запускаем таймер -------
        long start = System.nanoTime();
        // ---- вычисляем ---
        long value = Stream.
                iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        long finish = System.nanoTime();
        double elapsed = (double) (finish - start) / 1_000_000;
        logger.debug("(1 метод - в лоб)  Время выполнения операции суммирования {} ", elapsed);
        logger.debug("(1) Результат суммирования всех чисел от 0 до 1 000 000 равен {}", value);

        // (2) *** вычисляем время выполнения операции суммирования, применяя параллельные стримы ***
        // ---- запускаем таймер -------
        long start2 = System.nanoTime();
        // ---- вычисляем ---
        long value2 = Stream
                .iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        long finish2 = System.nanoTime();
        double elapsed2 = (double) (finish2 - start2) / 1_000_000;
        logger.debug("(2 метод - параллельные стримы)  Время выполнения операции суммирования {} ", elapsed2);
        logger.debug("(2) Результат суммирования всех чисел от 0 до 1 000 000 равен {}", value2);

        return value2;
    }
}
