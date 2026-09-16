package config;

import org.aeonbits.owner.ConfigFactory;

public class Project {

    public static final AuthConfig auth =
            ConfigFactory.create(AuthConfig.class, System.getProperties());

    public static final TestConfig testConfig =
            ConfigFactory.create(TestConfig.class, System.getProperties());
}