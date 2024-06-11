package com.sqltest.demo.config;

import com.sqltest.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledTasks {
    @Autowired
    private ExcelService excelService;

    @Scheduled(cron = "0 0 * * * ?") // Run every hour
    public void checkAndUpdateDatabase() throws IOException {
        excelService.readExcelAndSaveToDatabase();
    }
}
