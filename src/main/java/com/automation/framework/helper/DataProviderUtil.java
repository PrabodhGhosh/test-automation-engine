package com.automation.framework.helper;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DataProviderUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataProviderUtil.class.getName());

    @DataProvider(name = "csvDataProvider")
    public static Object[][] csvDataProvider(Method method) {
        String filePath = "src/test/resources/testdata/" + method.getName() + ".csv";
        List<String[]> csvData = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            csvData.addAll(records);
        } catch (IOException | CsvException e) {
            LOGGER.error("ERROR: Could not read CSV file at {}: {}", filePath, e.getMessage());
            return new Object[0][0]; // Return an empty data set on error
        }

        // This simple implementation assumes the first row is a header and skips it.
        // For more advanced usage, you might want to use a more robust
        // data processing method.
        if (csvData.isEmpty() || csvData.size() == 1) {
            return new Object[0][0];
        }

        Object[][] data = new Object[csvData.size() - 1][];
        for (int i = 1; i < csvData.size(); i++) {
            data[i - 1] = csvData.get(i);
        }

        return data;
    }
}
