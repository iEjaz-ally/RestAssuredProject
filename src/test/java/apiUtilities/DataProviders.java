package apiUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
}
