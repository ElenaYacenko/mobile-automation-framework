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

import static config.Project.auth;
import static config.Project.testConfig;

public class BrowserstackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        String platform = System.getProperty("platform", "android");
        System.out.println("=== PLATFORM: " + platform + " ===");

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", auth.user());
        bstackOptions.put("accessKey", auth.key());
        bstackOptions.put("projectName", testConfig.projectName());
        bstackOptions.put("buildName", testConfig.buildName());
        bstackOptions.put("appiumVersion", testConfig.appiumVersion());

        if ("ios".equals(platform)) {
            bstackOptions.put("deviceName", testConfig.iosDevice());
            bstackOptions.put("osVersion", testConfig.iosOsVersion());

            caps.setCapability("platformName", "ios");
            caps.setCapability("appium:app", testConfig.iosApp());
            caps.setCapability("appium:automationName", "XCUITest");
        } else {
            bstackOptions.put("deviceName", testConfig.androidDevice());
            bstackOptions.put("osVersion", testConfig.androidOsVersion());

            caps.setCapability("platformName", "android");
            caps.setCapability("appium:app", testConfig.androidApp());
        }

        caps.setCapability("bstack:options", bstackOptions);

        System.out.println("=== AUTH DEBUG ===");
        System.out.println("user = [" + auth.user() + "]");
        System.out.println("key  = [" + auth.key() + "]");
        System.out.println("hub  = [" + auth.hubUrl() + "]");
        System.out.println("==================");
        System.out.println("marker = [" + auth.marker() + "]");

        try {
            String hub = auth.hubUrl().replaceFirst("^https?://", "");
            URL url = new URL("https://" + auth.user() + ":" + auth.key() + "@" + hub);
            return new RemoteWebDriver(url, caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Не удалось собрать URL хаба: " + e.getMessage(), e);
        }
    }
}