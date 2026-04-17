package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExcelUtils {

    private static final String EXCEL_PATH = "src/test/resources/test_data_";

    public static void saveTestData(String name, String dynamicEmail, String password) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = EXCEL_PATH + timestamp + ".xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("TestResults");

            // Header oluştur
            Row header = sheet.createRow(0);
            String[] columns = {"Timestamp", "FullName", "DynamicEmail", "Password", "Status"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(getHeaderStyle(workbook));
            }

            // Veriyi yaz
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue(LocalDateTime.now().toString());
            dataRow.createCell(1).setCellValue(name);
            dataRow.createCell(2).setCellValue(dynamicEmail);
            dataRow.createCell(3).setCellValue(password);
            dataRow.createCell(4).setCellValue("PASS");

            // Sütun genişliklerini otomatik ayarla
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Dosyayı kaydet
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                System.out.println("✅ Test verileri kaydedildi: " + fileName);
            }

        } catch (IOException e) {
            System.err.println("❌ Excel kaydedilemedi: " + e.getMessage());
        }
    }

    private static CellStyle getHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }
}