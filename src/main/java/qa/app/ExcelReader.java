package qa.app;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;

public class ExcelReader {

    @SneakyThrows
    public static Iterator<String[]> getData(String excelName, String sheetName) {
        String path = System.getProperty("user.dir") + String.format("/src/test/resources/testdata/%s.xlsx", excelName);
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        String[][] data = new String[rowCount - 1][colCount];

        for (int r = 0; r < rowCount - 1; r++) {
            row = sheet.getRow(r + 1);
            for (int c = 0; c < colCount; c++) {
                XSSFCell cell = row.getCell(c);
                data[r][c] = formatter.formatCellValue(cell);
            }
        }
        return Arrays.stream(data).iterator();
    }
}
