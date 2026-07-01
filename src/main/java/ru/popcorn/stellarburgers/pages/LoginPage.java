package ru.popcorn.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Страница логина.
 * Позволяет авторизоваться и перейти на главную страницу/личный кабинет.
 */
public class LoginPage extends BasePage {

    // Поле логина (email/имя пользователя)
    @FindBy(xpath = "//input[@name='name' or @name='email' or @type='text']")
    private WebElement loginInput;

    // Поле пароля
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    // Кнопка «Войти»
    @FindBy(xpath = "//button[contains(@class,'button_button_type_primary') and normalize-space()='Войти']")
    private WebElement loginButton;

    // Ссылка «Зарегистрироваться»
    @FindBy(xpath = "//a[normalize-space()='Зарегистрироваться']")
    private WebElement registerLink;

    // Ссылка «Восстановить пароль»
    @FindBy(xpath = "//a[normalize-space()='Восстановить пароль']")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ожидание загрузки страницы логина.
     */
    public LoginPage waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return this;
    }

    /**
     * Заполнение логина и пароля.
     */
    public LoginPage fillCredentials(String login, String password) {
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        loginInput.clear();
        loginInput.sendKeys(login);

        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        return this;
    }

    /**
     * Отправка формы логина, ожидаем переход на главную страницу.
     */
    public MainPage submitLoginExpectingMainPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new MainPage(driver);
    }

    /**
     * Переход со страницы логина на страницу регистрации.
     */
    public RegistrationPage goToRegistrationPage() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        return new RegistrationPage(driver);
    }

    /**
     * Переход на страницу восстановления пароля.
     * Ожидаемый тип для теста: ForgotPasswordPage.
     */
    public ForgotPasswordPage goToForgotPasswordPage() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
        return new ForgotPasswordPage(driver);
    }
}