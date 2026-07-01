package ru.popcorn.stellarburgers.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.popcorn.stellarburgers.driver.DriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}