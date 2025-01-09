package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\OpenCart_testdata.xlsx"; // taking excel file from testdata folder in project. 
		
		Excelutility xlutil = new Excelutility(path); // creating object for Excelutility class so that we can access the methods inside it.
		
		int totalRows= xlutil.getRowCount("sheet1");
		int totalCols= xlutil.getCellCount("sheet1", 1);
		
		// Created two dimensional array for data from excel in string format.
		String logindata[][] = new String[totalRows][totalCols];
		
// this forloop will read the data from excel and store in two dimensional array.i=1 one because first row is header section we are ignoring it.
		for(int i=1; i<=totalRows;i++) // i is row
		{
			for(int j=0;j<totalCols;j++) // j is col
			{
				logindata[i-1][j]= xlutil.getCellData("sheet1",i, j); // here we have mentioned logindata[i-1] because array always starts from zeroth element.
			}
			
		}
		return logindata;
	}

}
