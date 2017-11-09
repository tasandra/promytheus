package data;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelWriteApi {
    public FileInputStream inputStream = null;
    public FileOutputStream outputStream = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
    private String xlFilePath;

    public ExcelWriteApi(String xlFilePath) throws Exception
    {
        this.xlFilePath = xlFilePath;
        inputStream = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(inputStream);
        inputStream.close();
    }

    public boolean setCellData(String sheetName, int colNumber, int rowNum, String value)
    {
        try
        {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            if(row==null)
                row = sheet.createRow(rowNum);

            cell = row.getCell(colNumber);
            if(cell == null)
                cell = row.createCell(colNumber);

            cell.setCellValue(value);

            outputStream = new FileOutputStream(xlFilePath);
            workbook.write(outputStream);
            outputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return  false;
        }
        return true;
    }

    public int getRowCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum()+1;
        return rowCount;
    }

//        public static void main(String args[]) throws Exception {
//            ExcelWriteApi eat = new ExcelWriteApi("promy.xlsx");
//            eat.setCellData("users",0,2,"PASS");
//        }
}