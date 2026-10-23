# spring-boot-lab7

Лабораторная работа №7 по дисциплине «Технологии программирования».
Выполнил: Урабов Николай Михайлович, группа РИЗ-250213у.

Веб-приложение с формами на Thymeleaf и базой данных H2: студенты и учебные группы.
Spring Boot 4.1.2, Java 25, Maven, Spring Data JPA, H2, Thymeleaf, Actuator, Lombok, Validation.

## Запуск

Запустить `SpringBootLab7Application` и открыть http://localhost:8080/list.
База создаётся в папке `testdb`, таблицы – скриптом `schema.sql`.

## Страницы

| URL | Страница |
|-----|----------|
| `/list` | список студентов |
| `/addStudentForm` | добавление студента |
| `/groups` | список учебных групп |
| `/addGroupForm` | добавление учебной группы |

## Actuator

`/actuator`, `/actuator/health`, `/actuator/info`, `/actuator/metrics`,
`/actuator/metrics/http.server.requests`, `/actuator/beans`, `/actuator/mappings`, `/actuator/env`
