package ru.popcorn.stellarburgers.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.popcorn.stellarburgers.pages.ForgotPasswordPage;
import ru.popcorn.stellarburgers.pages.LoginPage;
import ru.popcorn.stellarburgers.pages.MainPage;
import ru.popcorn.stellarburgers.pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной странице")
    @Description("Проверяет, что пользователь может войти через кнопку «Войти в аккаунт» на главной странице")
    public void loginFromMainPageButton() {
        MainPage mainPage = new MainPage(driver).open();
        LoginPage loginPage = mainPage.clickLoginButtonMain().waitForPageToLoad();
        MainPage resultPage = loginPage
                .fillCredentials(userEmail, userPassword)
                .submitLoginExpectingMainPage();
        assertTrue(
                resultPage.isMainRootVisible(),
                "После логина с главной страницы пользователь должен попасть на главную страницу"
        );
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет» в шапке")
    @Description("Проверяет, что пользователь может войти через кнопку «Личный кабинет» в шапке сайта")
    public void loginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver).open();
        LoginPage loginPage = mainPage.clickPersonalAccountButton().waitForPageToLoad();
        MainPage resultPage = loginPage
                .fillCredentials(userEmail, userPassword)
                .submitLoginExpectingMainPage();
        assertTrue(
                resultPage.isMainRootVisible(),
                "После логина через «Личный кабинет» пользователь должен попасть на главную страницу"
        );
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяет, что пользователь может перейти из формы регистрации на логин и успешно войти")
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
                .fillCredentials(userEmail, userPassword)
                .submitLoginExpectingMainPage();
        assertTrue(
                resultPage.isMainRootVisible(),
                "После перехода через форму регистрации и логина пользователь должен попасть на главную страницу"
        );
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверяет, что пользователь может перейти из формы восстановления пароля на логин и успешно войти")
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
                .fillCredentials(userEmail, userPassword)
                .submitLoginExpectingMainPage();
        assertTrue(
                resultPage.isMainRootVisible(),
                "После перехода через форму восстановления пароля и логина пользователь должен попасть на главную страницу"
        );
    }
}