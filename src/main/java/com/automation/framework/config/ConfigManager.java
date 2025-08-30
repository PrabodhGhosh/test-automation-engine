package com.automation.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigManager.class);
    private static final Properties properties = new Properties();

    static {
        // Default to a specific file inside the env_configs folder, e.g., config-dev.properties
        String configFileName = "config-dev.properties";

        // Check for a system property to override the default config file
        String env = System.getProperty("env");
        if (env != null && !env.isEmpty()) {
            configFileName = "config-" + env + ".properties";
        }

        try (FileInputStream fis = new FileInputStream("src/test/resources/env_configs/" + configFileName)) {
            properties.load(fis);
            LOGGER.info("Successfully loaded configuration from: {}", configFileName);
        } catch (IOException e) {
            LOGGER.error("FATAL: Could not load configuration file: {}. {}", configFileName, e.getMessage());
            throw new RuntimeException("Could not load configuration properties.", e);
        }
    }

    private ConfigManager() {}

    public static String getProperty(String key) {
        String property = properties.getProperty(key);
        if (property == null) {
            LOGGER.warn("WARN: Property '{}' not found in config.properties.", key);
        }
        return property;
    }
}