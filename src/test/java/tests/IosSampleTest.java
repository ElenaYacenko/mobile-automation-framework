package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.appium.java_client.AppiumBy.iOSClassChain;
import static io.qameta.allure.Allure.step;
import static org.asynchttpclient.util.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IosSampleTest extends TestBaseIos {

    @Test
    void appLaunchesAndHasElements() {
        WebDriver driver = getWebDriver();

        step("Проверить, что iOS-сессия создана", () -> {
            RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
            assertNotNull(remoteDriver.getSessionId(),
                    "Сессия должна быть активной");
            System.out.println("iOS session id: " + remoteDriver.getSessionId());
        });

        step("Проверить, что на экране есть хотя бы один элемент", () -> {
            List<WebElement> allElements = driver.findElements(
                    iOSClassChain("**/XCUIElementTypeAny"));

            System.out.println("Найдено элементов на экране: " + allElements.size());
            assertFalse(allElements.isEmpty(),
                    "На экране должно быть хотя бы что-то — значит приложение открылось");
        });
    }
}