package com.sqltest.demo.config;

import com.sqltest.demo.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private ExcelService excelService;

    @Value("${fms.sync.cron}")
    private String cronExpression;

    @Scheduled(cron = "${fms.sync.cron}")
    public void checkAndUpdateDatabase() {
        try {
            excelService.readExcelAndSaveToDatabase();
            logger.info("Scheduled task completed: Database updated successfully.");
        } catch (IOException e) {
            logger.error("Scheduled task failed: Error reading Excel file and updating database.", e);
        }
    }
}
