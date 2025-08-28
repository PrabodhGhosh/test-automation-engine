package com.automation.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigManager.class.getName());
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            LOGGER.error("Could not load the config file{}", e.getMessage());
            throw new RuntimeException("Could not load configuration properties.", e);
        }
    }

    private ConfigManager() {
        // Private constructor to prevent instantiation
    }

    public static String getProperty(String key) {
        String property = properties.getProperty(key);
        if(property==null)
        {
            LOGGER.warn("Property {} doesn't exist in config.properties", key);
        }
        return property;
    }


}
