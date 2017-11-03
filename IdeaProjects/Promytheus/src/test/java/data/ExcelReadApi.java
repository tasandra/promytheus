package data;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReadApi {
    public FileInputStream fis = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
    private String xlFilePath;

    public ExcelReadApi(String xlFilePath) throws Exception
    {
        this.xlFilePath = xlFilePath;
        fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    public int getRowCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum()+1;
        return rowCount;
    }

    public int getColumnCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        return colCount;
    }

    public String getCellData(String sheetName,int colNum,int rowNum) {
        DataFormatter df = new DataFormatter();
        try {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            return   df.formatCellValue(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in Excel";
        }
    }

    public Object[][] testData(String sheetName) throws Exception
    {
        Object[][] excelData = null;
        int rows = getRowCount(sheetName);
        int columns = getColumnCount(sheetName);

        excelData = new Object[rows-1][columns];

        for(int i=1; i<rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
                excelData[i-1][j] = getCellData(sheetName, j, i);
            }
        }
        return excelData;
    }

}
