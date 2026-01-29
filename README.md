# Java Retrofit API Testing Framework

<div style="text-align: center;">
  <a href="https://www.oracle.com/java/"><img src="https://img.shields.io/badge/Java-17-orange" alt="Java"></a>
  <a href="https://gradle.org/"><img src="https://img.shields.io/badge/Gradle-8.2-blue" alt="Gradle"></a>
  <a href="https://square.github.io/retrofit/"><img src="https://img.shields.io/badge/Retrofit-2.11.0-green" alt="Retrofit"></a>
  <a href="https://junit.org/junit5/"><img src="https://img.shields.io/badge/JUnit5-5.9.1-red" alt="JUnit5"></a>
  <a href="https://docs.qameta.io/allure/"><img src="https://img.shields.io/badge/Allure-2.24.0-purple" alt="Allure"></a>
  <a href="http://wiremock.org/"><img src="https://img.shields.io/badge/WireMock-2.35.0-yellow" alt="WireMock"></a>
</div>



## Описание проекта

Этот проект представляет собой фреймворк для автоматизированного тестирования API с использованием Java и Retrofit. Он предназначен для тестирования REST API сервисов с использованием моков через WireMock для изоляции тестов, без обращения к внешним сервисам, и генерации детализированных отчетов с помощью Allure.

## Особенности

- Полный набор CRUD операций для тестирования API
- Параметризованные тесты для различных сценариев
- Тесты на негативные случаи (ошибки 404, 400)
- Мокинг API с помощью WireMock для изоляции тестов
- Детальная отчетность с аннотациями Allure

## Технологии

- **Java 17**: Основной язык программирования
- **Gradle**: Система сборки
- **Retrofit**: HTTP клиент для Java
- **Gson**: Библиотека для сериализации/десериализации JSON
- **OkHttp**: HTTP клиент
- **JUnit 5**: Фреймворк для тестирования
- **Allure**: Инструмент для генерации отчетов о тестировании
- **WireMock**: Библиотека для мокинга HTTP сервисов
- **AssertJ**: Библиотека для fluent assertions
- **Lombok**: Библиотека для сокращения boilerplate кода
- **JavaFaker**: Библиотека для генерации тестовых данных

## Структура проекта

```
src/
├── main/java/com/wiremock/api/
│   ├── interceptor/ 
│   │    └── AllureLoggingInterceptor.java
│   ├── model/
│   │   ├── User.java
│   │   ├── request/
│   │   │   ├── CreateUserRequest.java
│   │   │   └── UpdateUserRequest.java
│   │   └── response/
│   │       ├── CreateUserResponse.java
│   │       ├── ErrorResponse.java
│   │       ├── UpdateUserResponse.java
│   │       ├── UserResponse.java
│   │       └── UsersResponse.java
│   └── service/
│   │    └── ReqresApiService.java
└── test/java/com/wiremock/api/
    ├── mock/
    │   ├── ApiMocks.java
    │   └── WireMockSetup.java
    └── tests/
        ├── BaseTest.java
        └── UserApiTest.java
```

## Запуск тестов

Для запуска тестов выполните следующую команду:

```bash
./gradlew test
```

## Генерация отчета Allure

После выполнения тестов сгенерируйте отчет Allure:

```bash
./gradlew allureReport
```

Отчет будет доступен в директории `build/reports/allure-report/`.

## Конфигурация

- `build.gradle`: Конфигурация зависимостей и задач Gradle
- `src/test/resources/allure.properties`: Настройки Allure


## Ссылки

- [Документация Retrofit](https://square.github.io/retrofit/)
- [Документация Allure](https://docs.qameta.io/allure/)
- [Документация WireMock](http://wiremock.org/docs/)


<div style="text-align: right;">
Автор:

SL
</div>