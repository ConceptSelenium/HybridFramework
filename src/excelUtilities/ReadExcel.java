package excelUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public Sheet readExcel(String sheetname) throws IOException{
		
		String filename = System.getProperty("user.dir")+"//Resources//TestCase.xlsx"; 
		File file = new File(filename);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook book = null;
		
		String extenstion = filename.substring(filename.indexOf("."));
		
		if(extenstion.equalsIgnoreCase(".xlsx")){
			book = new XSSFWorkbook(inputStream);
		} else if(extenstion.equalsIgnoreCase(".xls")){
			book = new HSSFWorkbook(inputStream);
		}
		
		Sheet sheet = book.getSheet(sheetname);
		return sheet;
	}
}
