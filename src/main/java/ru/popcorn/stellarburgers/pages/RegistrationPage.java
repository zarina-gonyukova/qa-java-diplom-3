package ru.popcorn.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    @FindBy(xpath = "(//input[@name='name'])[1]")
    private WebElement nameInput;

    @FindBy(xpath = "(//input[@name='name'])[2]")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type='password' and @name='Пароль']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class,'button_button_type_primary') and normalize-space()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = "//a[normalize-space()='Войти']")
    private WebElement loginLink;

    @FindBy(xpath = "//p[contains(@class,'input__error')]")
    private WebElement passwordErrorLabel;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидать загрузки страницы регистрации")
    public RegistrationPage waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(registerButton));
        return this;
    }

    @Step("Заполнить форму регистрации: имя «{name}», email «{email}»")
    public RegistrationPage fillForm(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.clear();
        nameInput.sendKeys(name);

        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        return this;
    }

    @Step("Нажать кнопку «Зарегистрироваться»")
    public LoginPage submitRegistrationExpectingLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        return new LoginPage(driver);
    }

    @Step("Проверить видимость ошибки пароля")
    public boolean isPasswordErrorVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(passwordErrorLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Перейти на страницу логина")
    public LoginPage goToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }
}