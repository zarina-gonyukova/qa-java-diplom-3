package ru.popcorn.stellarburgers.tests;

import org.junit.jupiter.api.Test;
import ru.popcorn.stellarburgers.pages.ConstructorPage;
import ru.popcorn.stellarburgers.pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты переключения вкладок конструктора бургеров.
 */
public class ConstructorTabsTest extends BaseTest {

    @Test
    public void bunsTabSwitchesToBunsSection() {
        MainPage mainPage = new MainPage(driver).open();
        ConstructorPage constructorPage = mainPage.openConstructor();
        constructorPage.clickBunsTab();
        assertTrue(constructorPage.isBunsSectionVisible(),
                "Секция «Булки» должна быть видна после переключения на вкладку «Булки»");
    }

    @Test
    public void saucesTabSwitchesToSaucesSection() {
        MainPage mainPage = new MainPage(driver).open();
        ConstructorPage constructorPage = mainPage.openConstructor();
        constructorPage.clickSaucesTab();
        assertTrue(constructorPage.isSaucesSectionVisible(),
                "Секция «Соусы» должна быть видна после переключения на вкладку «Соусы»");
    }

    @Test
    public void fillingsTabSwitchesToFillingsSection() {
        MainPage mainPage = new MainPage(driver).open();
        ConstructorPage constructorPage = mainPage.openConstructor();
        constructorPage.clickFillingsTab();
        assertTrue(constructorPage.isFillingsSectionVisible(),
                "Секция «Начинки» должна быть видна после переключения на вкладку «Начинки»");
    }
}