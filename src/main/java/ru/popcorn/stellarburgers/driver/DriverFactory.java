package ru.popcorn.stellarburgers.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Фабрика для создания WebDriver.
 * Поддерживает Chrome и Яндекс.Браузер (через chrome-драйвер).
 */
public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");

        if ("yandex".equalsIgnoreCase(browser)) {
            return createYandexDriver();
        } else {
            return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        WebDriverManager.chromedriver().setup();

        String defaultPath = "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";
        String yandexPath = System.getProperty("yandexPath", defaultPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexPath);

        return new ChromeDriver(options);
    }
}