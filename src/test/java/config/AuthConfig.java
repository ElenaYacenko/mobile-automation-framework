package config;

import org.aeonbits.owner.Config;

/**
 * Хранит секретные данные: логин, ключ и адрес хаба BrowserStack.
 * <p>
 * LoadType.MERGE — важен: Owner по умолчанию берёт только ПЕРВЫЙ доступный
 * источник, а нам нужно, чтобы он смотрел во все и подставлял то, что найдёт.
 * Порядок: system properties → env → config-local.properties → config.properties.
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:config-local.properties",
        "classpath:config.properties"
})
public interface AuthConfig extends Config {

    @Key("browserstack.user")
    String user();

    @Key("browserstack.key")
    String key();

    @Key("browserstack.hubUrl")
    String hubUrl();

    @Key("marker")
    String marker();
}