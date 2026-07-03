package ru.popcorn.stellarburgers.tests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.popcorn.stellarburgers.api.UserApiClient;
import ru.popcorn.stellarburgers.driver.DriverFactory;

import static org.apache.http.HttpStatus.SC_OK;

public abstract class BaseTest {

    protected WebDriver driver;
    protected String userEmail;
    protected String userPassword;
    protected String accessToken;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();

        userEmail = "test" + System.currentTimeMillis() + "@mail.ru";
        userPassword = "qwerty123";

        Response response = createTestUser(userEmail, userPassword, "Test User");
        accessToken = response.jsonPath().getString("accessToken");

        if (accessToken == null || accessToken.isBlank()) {
            throw new IllegalStateException("В ответе API отсутствует accessToken");
        }
    }

    @AfterEach
    public void tearDown() {
        if (accessToken != null && !accessToken.isBlank()) {
            deleteTestUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Создать тестового пользователя {email} для UI-тестов")
    protected Response createTestUser(String email, String password, String name) {
        Response response = UserApiClient.register(email, password, name);
        validateUserCreated(response);
        return response;
    }

    @Step("Проверить, что тестовый пользователь успешно создан")
    protected void validateUserCreated(Response response) {
        int statusCode = response.getStatusCode();
        Boolean success = response.jsonPath().getBoolean("success");

        if (statusCode != SC_OK || success == null || !success) {
            throw new IllegalStateException(
                    "Не удалось создать пользователя для теста. " +
                            "statusCode=" + statusCode + ", success=" + success
            );
        }
    }

    @Step("Удалить тестового пользователя")
    protected void deleteTestUser(String accessToken) {
        UserApiClient.delete(accessToken);
    }
}