package apiUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static FileInputStream inputStream;
	public FileOutputStream outputStream;
	public XSSFWorkbook workbook;
	public	XSSFSheet sheet;
	public	XSSFRow row;
	public XSSFCell cell;
	String filePathString;

	
	public ExcelUtilities(String pathString) {
		this.filePathString = pathString;
		
	}
	
	
	
	public int getRowCount(String sheetName) throws IOException {
		inputStream = new FileInputStream(filePathString);
		workbook = new XSSFWorkbook(inputStream);
		sheet = /*workbook.getSheet(sheetName); /*workbook.getSheetAt(0);*/ workbook.getSheet(workbook.getSheetName(0));
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		workbook.close();
		inputStream.close();
		
		return rowCount;
	}
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		inputStream = new FileInputStream(filePathString);
		workbook = new XSSFWorkbook(inputStream);
		sheet = /*workbook.getSheet(sheetName); /*workbook.getSheetAt(0);*/ workbook.getSheet(workbook.getSheetName(0));
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		return cellCount;
		
	}
	public String getCelldata(String sheetName, int rownum, int columnNum) throws IOException {
		inputStream = new FileInputStream(filePathString);
		workbook = new XSSFWorkbook(inputStream);
		sheet = /*workbook.getSheet(sheetName); /*workbook.getSheetAt(0);*/ workbook.getSheet(workbook.getSheetName(0));
		 row = sheet.getRow(rownum);
		 cell = row.getCell(columnNum);
		 
		 DataFormatter formatter = new DataFormatter();
		 String dataString;
		 try {
			 dataString = formatter.formatCellValue(cell);
					 
		 }catch (Exception e) {
			// TODO: handle exception
			 dataString = "nullValue";
		}
		
		workbook.close();
		inputStream.close();
		return dataString;
	}
	
}
