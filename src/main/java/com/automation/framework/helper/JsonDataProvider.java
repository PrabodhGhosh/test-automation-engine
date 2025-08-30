package com.automation.framework.helper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

public class JsonDataProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDataProvider.class);
    private static final String DATA_PATH = "src/test/resources/testdata/";

    @DataProvider(name = "jsonDataProvider")
    public static Object[][] jsonDataProvider(Method method) {
        String filePath = DATA_PATH + method.getName() + ".json";
        JsonArray jsonArray;

        try (FileReader reader = new FileReader(filePath)) {
            jsonArray = new Gson().fromJson(reader, JsonArray.class);
            LOGGER.info("Successfully loaded JSON data from: {}", filePath);
        } catch (IOException e) {
            LOGGER.error("FATAL: Could not read JSON file at {}. {}", filePath, e.getMessage());
            return new Object[0][0];
        }

        Object[][] data = new Object[jsonArray.size()][1];
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            data[i][0] = jsonObject;
        }

        return data;
    }
}