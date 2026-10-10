
package SwagLabsgenericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelutility {

    public String readDataFromExcel(
            String sheetName, int rowNum, int cellNum)
            throws IOException {

        String path = "./src/test/resources/SwagLab11.xlsx";

        try (FileInputStream fis = new FileInputStream(path);
             Workbook wb = WorkbookFactory.create(fis)) {

            Sheet sheet = wb.getSheet(sheetName);

            if (sheet == null) {
                throw new IOException(
                        "Sheet not found: " + sheetName);
            }

            Row row = sheet.getRow(rowNum);

            if (row == null || row.getCell(cellNum) == null) {
                return "";
            }

            return new DataFormatter().formatCellValue(
                    row.getCell(cellNum));
        }
    }

    // Default method for Sheet1
    public String readDataFromExcel(int row, int column)
            throws IOException {

        return readDataFromExcel("Sheet1", row, column);
    }

    // Read Test ID from Sheet3, column A
    public String getTestID(int row) throws IOException {

        return readDataFromExcel("Sheet3", row, 0);
    }
    public String readSheet2(int row, int column) throws IOException {
        return readDataFromExcel("Sheet2", row, column);
    }

}
