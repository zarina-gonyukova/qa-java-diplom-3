package ru.popcorn.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Главная страница.
 */
public class MainPage extends BasePage {

    @FindBy(xpath = "//main")
    private WebElement mainRoot;

    @FindBy(xpath = "//button[contains(@class,'button_button_type_primary') and normalize-space()='Войти в аккаунт']")
    private WebElement loginButtonMain;

    // Локатор для «Личный кабинет» по href /account
    @FindBy(xpath = "//header//a[contains(@href, '/account')]")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//a[.//p[normalize-space()='Конструктор']]")
    private WebElement constructorRootLink;

    private static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public boolean isMainRootVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(mainRoot)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public LoginPage clickLoginButtonMain() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonMain)).click();
        return new LoginPage(driver);
    }

    public LoginPage clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
        return new LoginPage(driver);
    }

    public ConstructorPage openConstructor() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorRootLink)).click();
        return new ConstructorPage(driver);
    }
}