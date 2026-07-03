package ru.popcorn.stellarburgers.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.popcorn.stellarburgers.pages.LoginPage;
import ru.popcorn.stellarburgers.pages.MainPage;
import ru.popcorn.stellarburgers.pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Проверяет, что новый пользователь может зарегистрироваться и после логина попасть на главную страницу")
    public void successfulRegistration() {
        MainPage mainPage = new MainPage(driver).open();
        LoginPage loginPage = mainPage.clickLoginButtonMain().waitForPageToLoad();

        RegistrationPage registrationPage = loginPage
                .goToRegistrationPage()
                .waitForPageToLoad();

        String email = "test" + System.currentTimeMillis() + "@mail.ru";
        String password = "qwerty123";

        LoginPage backToLogin = registrationPage
                .fillForm("Test User", email, password)
                .submitRegistrationExpectingLogin()
                .waitForPageToLoad();

        MainPage resultPage = backToLogin
                .fillCredentials(email, password)
                .submitLoginExpectingMainPage();

        assertTrue(
                resultPage.isMainRootVisible(),
                "После успешной регистрации и последующего логина пользователь должен попасть на главную страницу"
        );
    }

    @Test
    @DisplayName("Ошибка при регистрации с коротким паролем")
    @Description("Проверяет, что при вводе пароля короче 6 символов отображается сообщение об ошибке")
    public void registrationWithShortPasswordShowsError() {
        MainPage mainPage = new MainPage(driver).open();
        LoginPage loginPage = mainPage.clickLoginButtonMain().waitForPageToLoad();

        RegistrationPage registrationPage = loginPage
                .goToRegistrationPage()
                .waitForPageToLoad();

        registrationPage
                .fillForm("Test User", "shortpass@mail.ru", "123")
                .submitRegistrationExpectingLogin();

        assertTrue(
                registrationPage.isPasswordErrorVisible(),
                "При регистрации с коротким паролем должно отображаться сообщение об ошибке"
        );
    }
}