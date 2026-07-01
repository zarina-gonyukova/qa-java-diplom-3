package ru.popcorn.stellarburgers.tests;

import org.junit.jupiter.api.Test;
import ru.popcorn.stellarburgers.pages.ForgotPasswordPage;
import ru.popcorn.stellarburgers.pages.LoginPage;
import ru.popcorn.stellarburgers.pages.MainPage;
import ru.popcorn.stellarburgers.pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты авторизации через разные точки входа.
 */
public class LoginTest extends BaseTest {

    private final String validEmail = "lapz@mail.ru";
    private final String validPassword = "1111";

    @Test
    public void loginFromMainPageButton() {
        MainPage mainPage = new MainPage(driver).open();
        LoginPage loginPage = mainPage.clickLoginButtonMain().waitForPageToLoad();
        MainPage resultPage = loginPage
                .fillCredentials(validEmail, validPassword)
                .submitLoginExpectingMainPage();

        assertTrue(resultPage.isMainRootVisible(),
                "После логина с главной страницы пользователь должен попасть на главную страницу");
    }

    @Test
    public void loginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver).open();
        LoginPage loginPage = mainPage.clickPersonalAccountButton().waitForPageToLoad();
        MainPage resultPage = loginPage
                .fillCredentials(validEmail, validPassword)
                .submitLoginExpectingMainPage();

        assertTrue(resultPage.isMainRootVisible(),
                "После логина через «Личный кабинет» пользователь должен попасть на главную страницу");
    }

    @Test
    public void loginFromRegisterForm() {
        MainPage mainPage = new MainPage(driver).open();
        LoginPage loginPage = mainPage.clickLoginButtonMain().waitForPageToLoad();

        RegistrationPage registrationPage = loginPage
                .goToRegistrationPage()
                .waitForPageToLoad();

        LoginPage backToLogin = registrationPage
                .goToLoginPage()
                .waitForPageToLoad();

        MainPage resultPage = backToLogin
                .fillCredentials(validEmail, validPassword)
                .submitLoginExpectingMainPage();

        assertTrue(resultPage.isMainRootVisible(),
                "После перехода через форму регистрации и логина пользователь должен попасть на главную страницу");
    }

    @Test
    public void loginFromForgotPasswordForm() {
        MainPage mainPage = new MainPage(driver).open();
        LoginPage loginPage = mainPage.clickLoginButtonMain().waitForPageToLoad();

        ForgotPasswordPage forgotPage = loginPage
                .goToForgotPasswordPage()
                .waitForPageToLoad();

        LoginPage backToLogin = forgotPage
                .goToLoginPage()
                .waitForPageToLoad();

        MainPage resultPage = backToLogin
                .fillCredentials(validEmail, validPassword)
                .submitLoginExpectingMainPage();

        assertTrue(resultPage.isMainRootVisible(),
                "После перехода через форму восстановления пароля и логина пользователь должен попасть на главную страницу");
    }
}