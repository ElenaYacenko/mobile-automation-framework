package config;

import org.aeonbits.owner.Config;

/**
 * Доступы к BrowserStack: логин и ключ.
 * <p>
 * {@code LoadType.MERGE} обязателен. По умолчанию Owner работает в режиме
 * {@code FIRST}: берёт первый источник, который удалось открыть, и остальные не смотрит.
 * Источник {@code system:properties} открывается всегда, поэтому в режиме по умолчанию
 * файл просто не читался бы.
 * <p>
 * Ключи названы как переменные окружения, чтобы одно и то же имя работало во всех трёх
 * источниках: {@code -DBROWSERSTACK_ACCESS_KEY=...}, переменная окружения и строка в файле.
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:config.properties"
})
public interface AuthConfig extends Config {

    @Key("browserstack.user")
    String user();

    @Key("browserstack.key")
    String key();

    @Key("browserstack.app")
    String app();
}