package ru.popcorn.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='name' or @name='email' or @type='text']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class,'button_button_type_primary') and normalize-space()='Войти']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[normalize-space()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[normalize-space()='Восстановить пароль']")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидать загрузки страницы логина")
    public LoginPage waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return this;
    }

    @Step("Ввести логин «{login}» и пароль")
    public LoginPage fillCredentials(String login, String password) {
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        loginInput.clear();
        loginInput.sendKeys(login);
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку «Войти»")
    public MainPage submitLoginExpectingMainPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new MainPage(driver);
    }

    @Step("Перейти на страницу регистрации")
    public RegistrationPage goToRegistrationPage() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        return new RegistrationPage(driver);
    }

    @Step("Перейти на страницу восстановления пароля")
    public ForgotPasswordPage goToForgotPasswordPage() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
        return new ForgotPasswordPage(driver);
    }
}