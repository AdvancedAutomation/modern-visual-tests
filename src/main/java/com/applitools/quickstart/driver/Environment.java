package com.applitools.quickstart.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class Environment {

    private static final String GRADLE_PROPERTIES_PATH = "gradle.properties";
    private static final String TOKEN_VAR_NAME = "token";
    private static Environment instance;

    private Properties properties;

    private Environment() {
        try (InputStreamReader input = new InputStreamReader(new FileInputStream(GRADLE_PROPERTIES_PATH)
                , StandardCharsets.UTF_8)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    public String getToken() {
        return properties.getProperty(TOKEN_VAR_NAME);
    }
}
