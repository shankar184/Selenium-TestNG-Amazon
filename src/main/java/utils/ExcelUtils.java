package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IO;
import java.io.IOException;

public class ExcelUtils {

    /**
     * Read Excel data and returns a 2D object array
     * @param filePath path to Excel
     * @param sheetName sheet inside the Excel
     * @return 2D Object[][] for TestNG DataProvider
     * @throws IOException if file read fails
     */

    public static Object[][] getExcelData(String filePath,String sheetName)throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount=sheet.getPhysicalNumberOfRows();//total rows
        int columnCount = sheet.getRow(0).getLastCellNum();// columns in header

        Object[][] data = new Object[rowCount-1][columnCount];//Skip header

        for(int i=1;i<rowCount;i++){
            Row row = sheet.getRow(i);
            for(int j=0;j<columnCount;j++){
                Cell cell = row.getCell(j);
                data[i-1][j]=cell.getStringCellValue();
            }

        }
        workbook.close();
        fis.close();
        return data;
    }
}
