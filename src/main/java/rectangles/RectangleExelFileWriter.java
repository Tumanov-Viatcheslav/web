package rectangles;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RectangleExelFileWriter implements RectangleFileWriter {
    @Override
    public void writeRectangleToFile(Rectangle rectangle, String fileName) {
        Workbook workbook = null;
        File file = new File(fileName);
        if (file.exists() && file.length() != 0) {
            try (FileInputStream input = new FileInputStream(fileName)) {
                workbook = new XSSFWorkbook(input);
            } catch (Exception e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        else {
            workbook = new XSSFWorkbook();
            workbook.createSheet();
        }
        Sheet sheet = workbook.getSheetAt(0);
        sheet.createRow(sheet.getLastRowNum() + 1);
        Row newRow = sheet.getRow(sheet.getLastRowNum());
        newRow.createCell(0);
        newRow.createCell(1);
        newRow.getCell(0).setCellValue(rectangle.width);
        newRow.getCell(1).setCellValue(rectangle.length);
        try (FileOutputStream exelFile = new FileOutputStream(fileName)) {
            workbook.write(exelFile);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
