package apiUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.RowId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.poifs.crypt.DataSpaceMapUtils.DataSpaceMap;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;

public class ExcelUtilities {

	public FileInputStream inputStream;
	public FileOutputStream outputStream;
	public XSSFWorkbook workbook;
	public	XSSFSheet sheet;
	public	XSSFRow row;
	public XSSFCell cell;
	String filePathString;

	
	public ExcelUtilities(String pathString) {
		this.filePathString = pathString;
		
	}
	
	public String getSheetName(int no) throws IOException {
		inputStream = new FileInputStream(filePathString);
		workbook = new XSSFWorkbook(inputStream);
		String sheetNameString = workbook.getSheetName(no);
		return sheetNameString;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		inputStream = new FileInputStream(filePathString);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		workbook.close();
		inputStream.close();
		
		return rowCount;
	}
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		inputStream = new FileInputStream(filePathString);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		return cellCount;
		
	}
	public String getCelldata(String sheetName, int rownum, int columnNum) throws IOException {
		inputStream = new FileInputStream(filePathString);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
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
	public ArrayList<Map<String,String>> readDataForPetExcel(String sheetName){
		
		ArrayList<Map<String,String>> data= new ArrayList<>();
		 DataFormatter formatter = new DataFormatter();
		
		try{
			inputStream = new FileInputStream(filePathString);
			workbook = new XSSFWorkbook(inputStream);
			sheet= workbook.getSheet(sheetName);
			
			XSSFRow headerRow = sheet.getRow(0);
			int rowCOunt = sheet.getPhysicalNumberOfRows();
			int columnCount = headerRow.getPhysicalNumberOfCells();
			
			for(int i = 1 ;i <rowCOunt; i++) {
				XSSFRow rows = sheet.getRow(i);
				HashMap<String, String> DataMap = new HashMap<>();
				for(int j=0;j<columnCount;j++) {
					XSSFCell headerCell = headerRow.getCell(j);
					XSSFCell valueCell = rows.getCell(j);
					DataMap.put(headerCell.getStringCellValue(), valueCell!=null? formatter.formatCellValue(valueCell) : " ");
					
				}
				data.add(DataMap);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
		
	
	}
public Map<String,String> readDataForPetExcel1(String sheetName, int rowNum, int cellNum){
		 DataFormatter formatter = new DataFormatter();
		 HashMap<String, String> data = new HashMap<>();
		
		try{
			inputStream = new FileInputStream(filePathString);
			workbook = new XSSFWorkbook(inputStream);
			sheet= workbook.getSheet(sheetName);
			XSSFRow headerRow = sheet.getRow(0);
			XSSFRow row = sheet.getRow(rowNum);
			XSSFCell headerCell = headerRow.getCell(cellNum);
			XSSFCell celldataCell = row.getCell(cellNum);
			
			data.put(headerCell.getStringCellValue(), celldataCell!=null? formatter.formatCellValue(celldataCell) : " "); 
		
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
		
	
	}
	
}
