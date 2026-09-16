package tests;

import com.codeborne.selenide.WebDriverProvider;
import drivers.BrowserstackIosDriver;

public class TestBaseIos extends TestBase {

    @Override
    protected Class<? extends WebDriverProvider> driver() {
        return BrowserstackIosDriver.class;
    }
}