package ru.popcorn.stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String DELETE_PATH = "/api/auth/user";

    @Step("Создать тестового пользователя через API: email={email}")
    public static Response register(String email, String password, String name) {
        RestAssured.baseURI = BASE_URL;

        UserRequest request = new UserRequest(email, password, name);

        return given()
                .header("Content-type", "application/json")
                .body(request)
                .post(REGISTER_PATH);
    }

    @Step("Удалить тестового пользователя через API")
    public static void delete(String accessToken) {
        RestAssured.baseURI = BASE_URL;

        given()
                .header("Authorization", accessToken)
                .delete(DELETE_PATH);
    }
}