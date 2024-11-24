package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class ExcelTestUtils {

    // 读取 Excel 测试用例
    public static List<Map<String, Object>> readTestCases(String filePath) throws IOException {
        List<Map<String, Object>> testCases = new ArrayList<>();
        try (InputStream fis = Files.newInputStream(Paths.get(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            if (headerRow == null) throw new IllegalArgumentException("Excel 表头不能为空");
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 跳过表头
                Map<String, Object> testCase = new HashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i);
                    Object value = getCellValue(cell);
                    testCase.put(headers.get(i), value);
                }
                testCases.add(testCase);
            }
        }
        return testCases;
    }

    // 写入测试结果到 Excel
    public static void writeTestResults(String filePath, List<Map<String, Object>> testCases, String resultColumnName) throws IOException {
        try (InputStream fis = Files.newInputStream(Paths.get(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            // 查找结果列的索引
            int resultColumnIndex = -1;
            for (Cell cell : headerRow) {
                if (resultColumnName.equals(cell.getStringCellValue().trim())) {
                    resultColumnIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (resultColumnIndex == -1) { // 如果结果列不存在，追加一列
                resultColumnIndex = headerRow.getLastCellNum();
                Cell newHeaderCell = headerRow.createCell(resultColumnIndex);
                newHeaderCell.setCellValue(resultColumnName);
            }

            // 创建样式
            CellStyle passStyle = createCellStyle(workbook, IndexedColors.GREEN);
            CellStyle failStyle = createCellStyle(workbook, IndexedColors.RED);

            // 写入结果
            for (int i = 0; i < testCases.size(); i++) {
                Row row = sheet.getRow(i + 1);
                if (row == null) row = sheet.createRow(i + 1);

                Cell resultCell = row.createCell(resultColumnIndex, CellType.STRING);
                Object result = testCases.get(i).get(resultColumnName);

                if (result != null) {
                    String resultText = result.toString();
                    resultCell.setCellValue(resultText);

                    // 设置样式
                    if ("PASS".equalsIgnoreCase(resultText)) {
                        resultCell.setCellStyle(passStyle);
                    } else if ("FAIL".equalsIgnoreCase(resultText)) {
                        resultCell.setCellStyle(failStyle);
                    }
                }
            }

            try (OutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        }
    }

    // 创建单元格样式
    private static CellStyle createCellStyle(Workbook workbook, IndexedColors color) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true); // 加粗
        font.setColor(color.getIndex()); // 设置颜色
        style.setFont(font);
        return style;
    }

    // 获取单元格值
    private static Object getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return (cell.getNumericCellValue() % 1 == 0)
                    ? (int) cell.getNumericCellValue()
                    : cell.getNumericCellValue();
            case BOOLEAN: return cell.getBooleanCellValue();
                default: return null;
        }
    }
}


