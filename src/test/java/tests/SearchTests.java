package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        step("Type search", () -> {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.accessibilityId("Search Wikipedia"))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))).sendKeys("Appium");
        });
        step("Verify content found", () -> {
            List<WebElement> titles = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")));
            assertFalse(titles.isEmpty());
        });
    }

        @Test
        void openArticleTest() {
            WebDriver driver = getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            step("Кликнуть на search и ввести запрос", () -> {
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.accessibilityId("Search Wikipedia"))).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))).sendKeys("Java");
            });
            step("Открыть статью и проверить результат", () -> {
                WebElement specificArticle = wait.until(ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.xpath("//*[contains(@text, 'Java (programming language)')]")));
                specificArticle.click();

                List<WebElement> articleContent = driver.findElements(
                        AppiumBy.xpath("//*[contains(@text, 'programming language') or contains(@text, 'Java')]"));

                assertTrue(articleContent.size() > 0);
            });
        }
    }