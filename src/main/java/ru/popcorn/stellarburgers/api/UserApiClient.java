package ru.popcorn.stellarburgers.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String DELETE_PATH = "/api/auth/user";

    public static Response register(String email, String password, String name) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .header("Content-type", "application/json")
                .body(String.format(
                        "{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                        email, password, name
                ))
                .post(REGISTER_PATH);
    }

    public static void delete(String accessToken) {
        RestAssured.baseURI = BASE_URL;
        given()
                .header("Authorization", accessToken)
                .delete(DELETE_PATH);
    }
}