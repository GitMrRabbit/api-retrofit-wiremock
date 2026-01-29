package com.wiremock.api.mock;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.wiremock.api.model.response.UsersResponse;
import com.wiremock.api.model.User;
import com.wiremock.api.model.response.UserResponse;
import com.wiremock.api.model.response.CreateUserResponse;
import com.wiremock.api.model.response.UpdateUserResponse;

public class ApiMocks {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Faker faker = new Faker();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private static String generateRandomDate() {
        Date pastDate = faker.date().past(365, TimeUnit.DAYS);
        return dateFormat.format(pastDate);
    }

    public static String getUsersResponse() {
        UsersResponse response = new UsersResponse();
        response.setPage(1);
        response.setPer_page(6);
        response.setTotal(12);
        response.setTotal_pages(2);
        response.setData(Arrays.asList(
            new User(1, faker.internet().emailAddress(), faker.name().firstName(), faker.name().lastName(), "https://reqres.in/img/faces/1-image.jpg"),
            new User(2, faker.internet().emailAddress(), faker.name().firstName(), faker.name().lastName(), "https://reqres.in/img/faces/2-image.jpg")
        ));
        return gson.toJson(response);
    }

    public static String getSingleUserResponse() {
        UserResponse response = new UserResponse();
        response.setData(new User(1, faker.internet().emailAddress(), faker.name().firstName(), faker.name().lastName(), "https://reqres.in/img/faces/1-image.jpg"));
        return gson.toJson(response);
    }

    public static String createUserResponse() {
        CreateUserResponse response = new CreateUserResponse();
        response.setId("1");
        response.setName(faker.name().fullName());
        response.setJob(faker.job().title());
        response.setCreatedAt(generateRandomDate());
        return gson.toJson(response);
    }

    public static String updateUserResponse() {
        UpdateUserResponse response = new UpdateUserResponse();
        response.setName(faker.name().fullName());
        response.setJob(faker.job().title());
        response.setUpdatedAt(generateRandomDate());
        return gson.toJson(response);
    }

    public static String patchUserResponse() {
        UpdateUserResponse response = new UpdateUserResponse();
        response.setName(faker.name().fullName());
        response.setJob(faker.job().title());
        response.setUpdatedAt(generateRandomDate());
        return gson.toJson(response);
    }

    public static String getUsersResponsePage2() {
        UsersResponse response = new UsersResponse();
        response.setPage(2);
        response.setPer_page(6);
        response.setTotal(12);
        response.setTotal_pages(2);
        response.setData(Arrays.asList(
            new User(7, faker.internet().emailAddress(), faker.name().firstName(), faker.name().lastName(), "https://reqres.in/img/faces/7-image.jpg"),
            new User(8, faker.internet().emailAddress(), faker.name().firstName(), faker.name().lastName(), "https://reqres.in/img/faces/8-image.jpg")
        ));
        return gson.toJson(response);
    }

    public static String getSingleUserResponse2() {
        UserResponse response = new UserResponse();
        response.setData(new User(2, faker.internet().emailAddress(), faker.name().firstName(), faker.name().lastName(), "https://reqres.in/img/faces/2-image.jpg"));
        return gson.toJson(response);
    }

    public static String getSingleUserResponse3() {
        UserResponse response = new UserResponse();
        response.setData(new User(3, faker.internet().emailAddress(), faker.name().firstName(), faker.name().lastName(), "https://reqres.in/img/faces/3-image.jpg"));
        return gson.toJson(response);
    }

    public static String getErrorResponse() {
        return "{\"error\":\"" + faker.lorem().sentence() + "\"}";
    }
}
