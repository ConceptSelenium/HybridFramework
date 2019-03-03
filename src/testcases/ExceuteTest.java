package testcases;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import excelUtilities.ReadExcel;
import objectUtilites.ReadObjectFile;
import operations.PerformUIOperations;

public class ExceuteTest {
	// variables to be used across all methods
	Object testSteps[][] = null;
	WebDriver driver =null;
	ReadObjectFile obj = new ReadObjectFile();
	Properties allObjects;
	Row row;

	@BeforeTest
	@DataProvider(name = "hybridData")
	public Object[][] beforeLogin() throws IOException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");

		allObjects = obj.getObjectRepository();

		ReadExcel file = new ReadExcel();
		Sheet sheet = file.readExcel("KeywordFramework");

		int rowCount = sheet.getLastRowNum();
		testSteps = new String[rowCount][5];

		for (int i = 0; i < rowCount; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				testSteps[i][j] = row.getCell(j).toString();
				System.out.println(testSteps[i][j]);
			}
		}
		return testSteps;
	}

	// @BeforeMethod
	public void invokeBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "hybridData")
	public void testLogin(String testcaseName, String keyword, String objectName, String objectType, String value)
			throws Exception {
		PerformUIOperations operation = new PerformUIOperations(driver);

		if (testcaseName != null && (int) testcaseName.length() != 0) {
			if (driver != null) {
				driver.close();
			}
			this.invokeBrowser();
		}
		operation.performUIActions(allObjects, keyword, objectName, objectType, value);
	}

	// @AfterMethod
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
