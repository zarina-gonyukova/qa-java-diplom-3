package ru.popcorn.stellarburgers.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.popcorn.stellarburgers.api.UserApiClient;
import ru.popcorn.stellarburgers.driver.DriverFactory;

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

        var response = UserApiClient.register(userEmail, userPassword, "Test User");
        accessToken = response.jsonPath().getString("accessToken");
    }

    @AfterEach
    public void tearDown() {
        if (accessToken != null) {
            UserApiClient.delete(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}