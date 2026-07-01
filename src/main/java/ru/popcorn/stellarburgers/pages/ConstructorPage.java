package ru.popcorn.stellarburgers.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Страница конструктора бургеров.
 * Позволяет переключаться между вкладками «Булки», «Соусы», «Начинки»
 * и проверять видимость соответствующих секций.
 */
public class ConstructorPage extends BasePage {

    @FindBy(xpath = "//span[normalize-space()='Булки']/parent::div")
    private WebElement bunsTab;

    @FindBy(xpath = "//span[normalize-space()='Соусы']/parent::div")
    private WebElement saucesTab;

    @FindBy(xpath = "//span[normalize-space()='Начинки']/parent::div")
    private WebElement fillingsTab;

    @FindBy(xpath = "//h2[normalize-space()='Булки']")
    private WebElement bunsSectionHeader;

    @FindBy(xpath = "//h2[normalize-space()='Соусы']")
    private WebElement saucesSectionHeader;

    @FindBy(xpath = "//h2[normalize-space()='Начинки']")
    private WebElement fillingsSectionHeader;

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    public ConstructorPage clickBunsTab() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        scrollAndJsClick(element);
        return this;
    }

    public ConstructorPage clickSaucesTab() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        scrollAndJsClick(element);
        return this;
    }

    public ConstructorPage clickFillingsTab() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        scrollAndJsClick(element);
        return this;
    }

    public boolean isBunsSectionVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(bunsSectionHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSaucesSectionVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(saucesSectionHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFillingsSectionVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(fillingsSectionHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void scrollAndJsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        js.executeScript("arguments[0].click();", element);
    }
}