package ru.popcorn.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(xpath = "//main")
    private WebElement mainRoot;

    @FindBy(xpath = "//button[contains(@class,'button_button_type_primary') and normalize-space()='Войти в аккаунт']")
    private WebElement loginButtonMain;

    @FindBy(xpath = "//header//a[contains(@href, '/account')]")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//a[.//p[normalize-space()='Конструктор']]")
    private WebElement constructorRootLink;

    private static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную страницу")
    public MainPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Проверить видимость главной страницы")
    public boolean isMainRootVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(mainRoot)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Кликнуть кнопку «Войти в аккаунт» на главной")
    public LoginPage clickLoginButtonMain() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonMain)).click();
        return new LoginPage(driver);
    }

    @Step("Кликнуть «Личный кабинет» в шапке")
    public LoginPage clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
        return new LoginPage(driver);
    }

    @Step("Открыть конструктор")
    public ConstructorPage openConstructor() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorRootLink)).click();
        return new ConstructorPage(driver);
    }
}