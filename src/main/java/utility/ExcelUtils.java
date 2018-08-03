package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	public static void setExcelFile(String Path, String SheetName) throws Exception
	{
		try {
			// abre arquivo Excel
			FileInputStream ExcelFile = new FileInputStream(Path);
			// acessa o workbook
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		}
		catch(Exception e)
		{
			throw(e);
		}
	}
	
	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

    public static String getCellData(int RowNum, int ColNum) throws Exception
    {
		try{
			setExcelFile(Constants.PATH + Constants.FILE, "Plan1");
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		}catch (Exception e){
			return"";
		}
    }

	//This method is to write in the Excel cell, Row num and Col num are the parameters
	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	
	{
		try{
  			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.createCell(ColNum);
			Cell.setCellValue(Result);
		}catch(Exception e){
				throw (e);
		}
	}
	
	// Close excel file
	public static void closeExcel() throws IOException
	{
		FileOutputStream fileOut = new FileOutputStream(Constants.PATH + Constants.FILE);
		ExcelWBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
}
