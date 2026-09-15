package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackDriver_1 implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        // Appium-специфичные настройки ОБЯЗАТЕЛЬНО с префиксом "appium:"
        caps.setCapability("appium:app", "bs://f43f971616c5adb05207cada556ae4bc00e1e657");
        caps.setCapability("browserstack.user", "bsuser_XQJjVN");
        caps.setCapability("browserstack.key", "am7jziiXHzFsVGTXeDns");

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 6");
        caps.setCapability("os_version", "12.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");

        // 2. КРИТИЧЕСКИ ВАЖНО: Правильные пакет и активность для Wikipedia
        caps.setCapability("appium:appPackage", "org.wikipedia.alpha");
        caps.setCapability("appium:appActivity", "org.wikipedia.main.MainActivity");
        // Разрешаем ждать как главный экран, так и экран онбординга
        caps.setCapability("appium:appWaitActivity", "org.wikipedia.main.MainActivity,org.wikipedia.onboarding.InitialOnboardingActivity");
        caps.setCapability("appium:autoGrantPermissions", true);
        // 3. Настройки BrowserStack
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", "bsuser_XQJjVN");
        bstackOptions.put("accessKey", "am7jziiXHzFsVGTXeDns");
        bstackOptions.put("projectName", "First Java Project");
        bstackOptions.put("buildName", "browserstack-build-1");
        bstackOptions.put("sessionName", "first_test");
        bstackOptions.put("os", "android");
        bstackOptions.put("osVersion", "12.0");
        bstackOptions.put("deviceName", "Google Pixel 6");

        //caps.setCapability("bstack:options", bstackOptions);

        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}