package com.sqltest.demo.config;

import com.sqltest.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    @Autowired
    private ExcelService excelService;

    @Override
    public void run(String... args) throws Exception {
        try {
            excelService.readExcelAndSaveToDatabase();
            logger.info("Excel data successfully read and saved to database.");
        } catch (IOException e) {
            logger.error("Error reading Excel file and saving to database: ", e);
        }
    }
}
