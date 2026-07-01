package ru.popcorn.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Страница восстановления пароля.
 */
public class ForgotPasswordPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[contains(@class,'button_button_type_primary') and normalize-space()='Восстановить']")
    private WebElement restoreButton;

    @FindBy(xpath = "//a[normalize-space()='Войти']")
    private WebElement loginLink;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public ForgotPasswordPage waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(restoreButton));
        return this;
    }

    public ForgotPasswordPage fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage goToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver);
    }
}