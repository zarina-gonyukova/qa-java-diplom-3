package ru.popcorn.stellarburgers.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.popcorn.stellarburgers.pages.ConstructorPage;
import ru.popcorn.stellarburgers.pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTabsTest extends BaseTest {

    @Test
    @DisplayName("Переключение на вкладку «Булки»")
    @Description("Проверяет, что после клика на вкладку «Булки» отображается секция с булками")
    public void bunsTabSwitchesToBunsSection() {
        MainPage mainPage = new MainPage(driver).open();
        ConstructorPage constructorPage = mainPage.openConstructor();
        constructorPage.clickBunsTab();
        assertTrue(
                constructorPage.isBunsSectionVisible(),
                "Секция «Булки» должна быть видна после переключения на вкладку «Булки»"
        );
    }

    @Test
    @DisplayName("Переключение на вкладку «Соусы»")
    @Description("Проверяет, что после клика на вкладку «Соусы» отображается секция с соусами")
    public void saucesTabSwitchesToSaucesSection() {
        MainPage mainPage = new MainPage(driver).open();
        ConstructorPage constructorPage = mainPage.openConstructor();
        constructorPage.clickSaucesTab();
        assertTrue(
                constructorPage.isSaucesSectionVisible(),
                "Секция «Соусы» должна быть видна после переключения на вкладку «Соусы»"
        );
    }

    @Test
    @DisplayName("Переключение на вкладку «Начинки»")
    @Description("Проверяет, что после клика на вкладку «Начинки» отображается секция с начинками")
    public void fillingsTabSwitchesToFillingsSection() {
        MainPage mainPage = new MainPage(driver).open();
        ConstructorPage constructorPage = mainPage.openConstructor();
        constructorPage.clickFillingsTab();
        assertTrue(
                constructorPage.isFillingsSectionVisible(),
                "Секция «Начинки» должна быть видна после переключения на вкладку «Начинки»"
        );
    }
}