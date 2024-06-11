package com.sqltest.demo.config;

import com.sqltest.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private ExcelService excelService;

    @Override
    public void run(String... args) throws Exception {
        try {
            excelService.readExcelAndSaveToDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
