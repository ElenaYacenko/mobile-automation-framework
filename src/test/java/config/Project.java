package config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Точка входа во все настройки. В коде везде используем Project.auth и
 * Project.testConfig — они создаются один раз при загрузке класса.
 */
public class Project {

    public static final AuthConfig auth =
            ConfigFactory.create(AuthConfig.class, System.getProperties());

    public static final TestConfig testConfig =
            ConfigFactory.create(TestConfig.class, System.getProperties());
}