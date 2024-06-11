package com.sqltest.demo.service;

import com.sqltest.demo.model.SalesOrder;
import com.sqltest.demo.repository.SalesOrderRepo;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    private SalesOrderRepo salesOrderRepository;

    private static final String PLACEHOLDER = "N/A";

    public void readExcelAndSaveToDatabase() throws IOException {
        ClassPathResource resource = new ClassPathResource("excel/data.xlsx");
        InputStream file = resource.getInputStream();
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<SalesOrder> salesOrders = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // Skip header row
            }
            if (row.getCell(0)==null) {
                continue; // Skip empty rows
            }

            SalesOrder salesOrder = new SalesOrder();
            salesOrder.setSoId(getCellValueAsInteger(row.getCell(0)));
            salesOrder.setWarehouseName(getCellValueAsString(row.getCell(1)));
            salesOrder.setPickupLocation(getCellValueAsString(row.getCell(2)));
            salesOrder.setDropLocation(getCellValueAsString(row.getCell(3)));
            salesOrder.setTags(parseTags(row.getCell(4)));
            salesOrder.setQuantity(getCellValueAsInteger(row.getCell(5)));
            salesOrder.setDistance(getCellValueAsInteger(row.getCell(6)));
            salesOrder.setLoadingDate(getCellValueAsDate(row.getCell(7)));
            salesOrder.setLoadingTime(getCellValueAsTime(row.getCell(8)));
            salesOrder.setUnloadingDate(getCellValueAsDate(row.getCell(9)));
            salesOrder.setUnloadingTime(getCellValueAsTime(row.getCell(10)));
            salesOrder.setTotalAmount(getCellValueAsDouble(row.getCell(11)));
            salesOrder.setBidding(getCellValueAsString(row.getCell(12)));
            salesOrder.setBidStartDate(getCellValueAsDate(row.getCell(13)));
            salesOrder.setBidStartTime(getCellValueAsTime(row.getCell(14)));
            salesOrder.setBidDuration(getCellValueAsTime(row.getCell(15)));
            salesOrder.setBidStartAmountPerKm(getCellValueAsDouble(row.getCell(16)));
            salesOrder.setBidCloseAmountPerKm(getCellValueAsDouble(row.getCell(17)));
            salesOrder.setDispatchDetails(getCellValueAsString(row.getCell(18)));

            salesOrders.add(salesOrder);
        }

        salesOrderRepository.saveAll(salesOrders);
        workbook.close();
        file.close();
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else {
            String cellValue = cell.toString();
            return PLACEHOLDER.equals(cellValue) ? null : cellValue;
        }
    }

    private Integer getCellValueAsInteger(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else {
            return (int) cell.getNumericCellValue();
        }
    }

    private Double getCellValueAsDouble(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else {
            return cell.getNumericCellValue();
        }
    }

    private String getCellValueAsDate(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else {
            String cellValue = cell.toString();
            return PLACEHOLDER.equals(cellValue) ? null : cellValue;
        }
    }

    private String getCellValueAsTime(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else {
            String cellValue = cell.toString();
            return PLACEHOLDER.equals(cellValue) ? null : cellValue;
        }
    }

    private List<String> parseTags(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return new ArrayList<>();
        } else {
            String cellValue = cell.toString();
            return Arrays.asList(cellValue.substring(1, cellValue.length() - 1).split(", "));
        }
    }
}

