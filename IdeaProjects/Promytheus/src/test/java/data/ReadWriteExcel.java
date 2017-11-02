package data;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadWriteExcel {


    public static void main(String[] args) {
        ReadWriteExcel rw = new ReadWriteExcel();

        rw.readDataFromExcel("promy.xlsx", "personal");
    }

    public Object[][] readDataFromExcel(String fileName, String sheetName) {
        Object[][] data = null;
        DataFormatter df = new DataFormatter();
        try {

            FileInputStream file = new FileInputStream(fileName);
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
//            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            int rownum = 0;
            int colnum = 0;
            Row row = rowIterator.next();
            int rowcount=sheet.getLastRowNum();
            int colcount=row.getPhysicalNumberOfCells();
            data = new Object[rowcount][colcount];
            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    data[rownum][colnum] =  df.formatCellValue(cell);
                    System.out.print(df.formatCellValue(cell));
                    System.out.print(" ");
                }
                rownum++;
                System.out.println("");
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
