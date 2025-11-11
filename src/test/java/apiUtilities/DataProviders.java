package apiUtilities;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.checkerframework.checker.lock.qual.NewObject;
import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="ExcelData")
	public String[][] getDatFromExcel() throws IOException{
		String filePathString = System.getProperty("user.dir") + File.separator + "TestData" + File.separator+ "Testdata.xlsx";
	//	System.out.println(filePathString+ "file path");
		ExcelUtilities utilities = new ExcelUtilities(filePathString);
		int rowCount =  utilities.getRowCount("ExcelData");
		int cellCount = utilities.getCellCount("ExcelData", 1);
		
		String[][] apiDataStrings = new String[rowCount][cellCount];
		
		for(int i =1; i<=rowCount;i++) {
			for(int j=0;j<cellCount;j++) {
				apiDataStrings[i-1][j]= utilities.getCelldata(filePathString, i, j);
			}
		}
		
		return apiDataStrings;
	}
	@DataProvider(name="Username")
	public String[] getUserName() throws IOException {
		
		String filePathString = System.getProperty("user.dir")+ File.separator + "TestData" + File.separator+ "TestData.xlsx";
		ExcelUtilities utilities = new ExcelUtilities(filePathString);
		int rowCount =  utilities.getRowCount("Sheet1");
		String[] apiDataStrings = new String[rowCount];
		for(int i = 1; i<=rowCount;i++) {
			apiDataStrings[i-1]= utilities.getCelldata(filePathString, i, 1);
		}
		return apiDataStrings;
	}
	@DataProvider(name="dataProviderForPets")
	public Iterator<Object[]> getDataForPets() throws IOException{
		String filePathString = System.getProperty("user.dir") + File.separator + "TestData" + File.separator+ "Testdata.xlsx";
		//	System.out.println(filePathString+ "file path");
			ExcelUtilities utilities = new ExcelUtilities(filePathString);
			int rowCount =  utilities.getRowCount(utilities.getSheetName(1));
			int cellCount = utilities.getCellCount(utilities.getSheetName(1), 1);
			ArrayList<Object[]> list = new ArrayList<>();
		
		for(int i=1; i<=rowCount;i++) {
			HashMap<String, String> returnData = new HashMap<>();
			for(int j=0;j<cellCount;j++) {
				returnData.putAll(utilities.readDataForPetExcel1(utilities.getSheetName(1), i, j));
			
			}
			list.add(new Object[] {returnData});
		}
		System.out.println(rowCount +" This is" + cellCount);
		
		return list.iterator();
		
		
	}
	}
