package config;

import org.aeonbits.owner.Config;

/**
 * Не секретные настройки: устройства, версия Appium, имена приложений и т. д.
 * Источники те же: system property → env → config.properties.
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:config.properties"
})
public interface TestConfig extends Config {

    @Key("android.deviceName")
    String androidDevice();

    @Key("android.osVersion")
    String androidOsVersion();

    @Key("android.app")
    String androidApp();

    @Key("ios.deviceName")
    String iosDevice();

    @Key("ios.osVersion")
    String iosOsVersion();

    @Key("ios.app")
    String iosApp();

    @Key("appium.version")
    String appiumVersion();

    @Key("project.name")
    String projectName();

    @Key("build.name")
    String buildName();
}