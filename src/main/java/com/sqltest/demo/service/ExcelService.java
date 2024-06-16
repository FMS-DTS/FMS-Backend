package com.sqltest.demo.service;

import com.sqltest.demo.model.SalesOrder;
import com.sqltest.demo.repository.SalesOrderRepo;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

    @Autowired
    private SalesOrderRepo salesOrderRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${excel.file.path}")
    private String excelFilePath;
    private static final String PLACEHOLDER = "N/A";

    public void readExcelAndSaveToDatabase() throws IOException {
        logger.info("Reading Excel file from {}", excelFilePath);

        // Load the resource from the classpath
        Resource resource = resourceLoader.getResource(excelFilePath);

        try (InputStream file = resource.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            List<SalesOrder> salesOrders = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header row
                }
                if (row.getCell(0) == null) {
                    continue; // Skip empty rows
                }

                SalesOrder salesOrder = createSalesOrderFromRow(row);
                if (salesOrder != null) {
                    salesOrders.add(salesOrder);
                }
            }

            try {
                salesOrderRepository.saveAll(salesOrders);
                logger.info("{} sales orders saved to the database.", salesOrders.size());
            } catch (Exception e) {
                logger.error("Error saving sales orders to the database.", e);
                throw e; // Propagate the exception to handle it at a higher level if necessary
            } finally {
                workbook.close();
            }
        } catch (IOException e) {
            logger.error("Error reading Excel file: {}", e.getMessage());
            throw e;
        }
    }

    private SalesOrder createSalesOrderFromRow(Row row) {
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setSoId(getCellValueAsInteger(row.getCell(0)));
        salesOrder.setWarehouseName(getCellValueAsString(row.getCell(1)));
        salesOrder.setPickupLocation(getCellValueAsString(row.getCell(2)));
        salesOrder.setDropLocation(getCellValueAsString(row.getCell(3)));
        salesOrder.setTags(parseTags(row.getCell(4)));
        salesOrder.setQuantity(getCellValueAsInteger(row.getCell(5)));
        salesOrder.setDistance(getCellValueAsInteger(row.getCell(6)));
        salesOrder.setLoadingDate(getCellValueAsString(row.getCell(7)));
        salesOrder.setLoadingTime(getCellValueAsString(row.getCell(8)));
        salesOrder.setUnloadingDate(getCellValueAsString(row.getCell(9)));
        salesOrder.setUnloadingTime(getCellValueAsString(row.getCell(10)));
        salesOrder.setTotalAmount(getCellValueAsDouble(row.getCell(11)));
        salesOrder.setBidding(getCellValueAsString(row.getCell(12)));
        salesOrder.setBidStartDate(getCellValueAsString(row.getCell(13)));
        salesOrder.setBidStartTime(getCellValueAsString(row.getCell(14)));
        salesOrder.setBidDuration(getCellValueAsString(row.getCell(15)));
        salesOrder.setBidStartAmountPerKm(getCellValueAsDouble(row.getCell(16)));
        salesOrder.setBidCloseAmountPerKm(getCellValueAsDouble(row.getCell(17)));
        salesOrder.setDispatchDetails(getCellValueAsString(row.getCell(18)));

        return salesOrder;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        }
        String cellValue = cell.toString();
        return PLACEHOLDER.equals(cellValue) ? null : cellValue;

    }

    private Integer getCellValueAsInteger(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        }
        return (int) cell.getNumericCellValue();

    }

    private Double getCellValueAsDouble(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        }
        return cell.getNumericCellValue();

    }

    private List<String> parseTags(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return new ArrayList<>();
        }
        String cellValue = cell.toString();
        return Arrays.asList(cellValue.substring(1, cellValue.length() - 1).split(", "));

    }
}
