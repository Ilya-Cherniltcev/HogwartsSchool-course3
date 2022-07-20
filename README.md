# 3.3. Введение в базы данных

> Привет! На связи домашнее задание урока 3.3. Введение в базы данных.
В прошлом домашнем задании мы создали полноценное RESTful-приложение с определенной структурой. В этом мы начнем взаимодействовать с базой данных.

Цель сегодняшней домашней работы — установить и настроить взаимодействие приложения с базой данных и изменить логику в сервисных классах, чтобы все изменения сохранялись в БД.

*Среднее время выполнения: 120 минут.*
> 

**Шаг 1**

Установить БД PostgreSQL. Создать базу данных hogwarts. Создать пользователя student с паролем chocolatefrog.

<aside>
<img src="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f1c137e5-8cda-4596-a67b-beb4d637ad33/Рисунок41.png" alt="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f1c137e5-8cda-4596-a67b-beb4d637ad33/Рисунок41.png" width="40px" /> **Критерии оценки:** На компьютер установлена база данных PostgreSQL. Создана база данных и роль для доступа к ней.

</aside>

**Шаг 2**

В application.properties приложения прописать следующие атрибуты:

- spring.datasource.url — путь до установленной БД;
- spring.datasource.username — имя пользователя для подключения, в нашем случае это student;
- spring.datasource.password — пароль пользователя: chocolatefrog;
- spring.jpa.hibernate.ddl = update.

<aside>
<img src="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/56245a9e-9f65-45bb-a07e-d527f496c5a2/Рисунок41.png" alt="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/56245a9e-9f65-45bb-a07e-d527f496c5a2/Рисунок41.png" width="40px" /> **Критерии оценки:** В файл application.properties добавлены четыре свойства. Приложение успешно запускается без ошибок.

</aside>

**Шаг 3**

Изменить модели Student и Faculty. К каждому классу добавить аннотацию @Entity. А к полю id добавить две аннотации: @Id и @GeneratedValue.

А также создать пакет repository, в котором будут находиться два интерфейса: StudentRepository и FacultyRepository. Оба этих интерфейса наследуют JpaRepository. Для интерфейса StudentRepository требуется указать, что в JpaRepository надо работать с моделью Student. Для FacultyRepository указать Faculty.

<aside>
<img src="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ad715dc0-430a-4b06-a4be-53810eb11187/Рисунок41.png" alt="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ad715dc0-430a-4b06-a4be-53810eb11187/Рисунок41.png" width="40px" /> **Критерии оценки:** В модели добавлены аннотации @Entity, @Id и @GeneratedValue. Создан пакет repository. В нем находятся два интерфейса: StudentRepository и FacultyRepository.

</aside>

**Шаг 4**

В сервисах создать приватные поля репозиториев. Для StudentService создать StudentRepository. Для FacultyService создать FacultyRepository. С помощью конструкторов подтягивать зависимости из контекста спринга (@Autowire).

В сервисах удалить HashMap, который использовали для хранения данных, и удалить счетчик идентификатора. Вместо них следует использовать функционал репозиториев.

<aside>
<img src="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9904be5f-3364-450c-abb5-856e1c5ba1c1/Рисунок41.png" alt="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9904be5f-3364-450c-abb5-856e1c5ba1c1/Рисунок41.png" width="40px" /> **Критерии оценки:** В сервисы добавлены репозитории. Переделана логика работы с данными. Теперь все данные хранятся в БД, а сервисы пользуются репозиториями для их получения.

</aside>

**Шаг 5**

Проверить все CRUD-запросы через Postman.

<aside>
<img src="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/be3e697d-a848-4ea4-bd54-ee7210a9b55a/Рисунок41.png" alt="https://s3-us-west-2.amazonaws.com/secure.notion-static.com/be3e697d-a848-4ea4-bd54-ee7210a9b55a/Рисунок41.png" width="40px" /> **Критерий оценки:** Все запросы отрабатывают, как ожидается (без 500 кодов в ответе).

</aside>
