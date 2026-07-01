package ru.popcorn.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Страница регистрации.
 * Позволяет заполнить форму, отправить данные и перейти на страницу логина.
 */
public class RegistrationPage extends BasePage {

    // Поле «Имя» — первый input с name='name'
    @FindBy(xpath = "(//input[@name='name'])[1]")
    private WebElement nameInput;

    // Поле Email — второй input с name='name'
    @FindBy(xpath = "(//input[@name='name'])[2]")
    private WebElement emailInput;

    // Поле «Пароль»
    @FindBy(xpath = "//input[@type='password' and @name='Пароль']")
    private WebElement passwordInput;

    // Кнопка «Зарегистрироваться»
    @FindBy(xpath = "//button[contains(@class,'button_button_type_primary') and normalize-space()='Зарегистрироваться']")
    private WebElement registerButton;

    // Ссылка «Войти» под формой регистрации
    @FindBy(xpath = "//a[normalize-space()='Войти']")
    private WebElement loginLink;

    // Сообщение об ошибке пароля (красный текст)
    @FindBy(xpath = "//p[contains(@class,'input__error')]")
    private WebElement passwordErrorLabel;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ожидание загрузки страницы регистрации.
     */
    public RegistrationPage waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(registerButton));
        return this;
    }

    /**
     * Заполнение формы регистрации: имя, email, пароль.
     */
    public RegistrationPage fillForm(String name, String email, String password) {
        // Имя
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.clear();
        nameInput.sendKeys(name);

        // Email (второй input с name='name')
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);

        // Пароль
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        return this;
    }

    /**
     * Отправка формы регистрации, ожидаем переход на страницу логина.
     */
    public LoginPage submitRegistrationExpectingLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        return new LoginPage(driver);
    }

    /**
     * Проверка, что ошибка пароля отображается.
     */
    public boolean isPasswordErrorVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(passwordErrorLabel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Переход со страницы регистрации обратно на страницу логина.
     */
    public LoginPage goToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }
}