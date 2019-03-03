package operations;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class PerformUIOperations {
	WebDriver driver;

	private By getObject(Properties p, Object testSteps2, Object testSteps3) throws Exception {
		
		Thread.sleep(3000);

		if (((String) testSteps3).equalsIgnoreCase("XPATH")) {

			return By.xpath(p.getProperty((String) testSteps2));
		}
		// find by class
		else if (((String) testSteps3).equalsIgnoreCase("CLASSNAME")) {

			return By.className(p.getProperty((String) testSteps2));

		}
		// find by id
		else if (((String) testSteps3).equalsIgnoreCase("ID")) {

			return By.id(p.getProperty((String) testSteps2));

		}
		// find by name
		else if (((String) testSteps3).equalsIgnoreCase("NAME")) {

			return By.name(p.getProperty((String) testSteps2));

		}
		// Find by css
		else if (((String) testSteps3).equalsIgnoreCase("CSS")) {

			return By.cssSelector(p.getProperty((String) testSteps2));

		}
		// find by link
		else if (((String) testSteps3).equalsIgnoreCase("LINK")) {

			return By.linkText(p.getProperty((String) testSteps2));

		}
		// find by partial link
		else if (((String) testSteps3).equalsIgnoreCase("PARTIALLINK")) {

			return By.partialLinkText(p.getProperty((String) testSteps2));

		} else {
			throw new Exception("Wrong object type");
		}

	}

	public PerformUIOperations(WebDriver driver) {
		this.driver = driver;
	}

	public void performUIActions(Properties p, Object testSteps, Object testSteps2, Object testSteps3, Object testSteps4)
			throws Exception {

		switch (((String) testSteps).toUpperCase()) {
		case "CLICK":
			// perform click
			driver.findElement(this.getObject(p, testSteps2, testSteps3)).click();
			break;
		case "SETTEXT":
			// perform sendkeys
			driver.findElement(this.getObject(p, testSteps2, testSteps3)).sendKeys((String)testSteps4);
			break;
		case "GOTOURL":
			// perform open url
			driver.get(p.getProperty((String) testSteps4));
			break;
		case "DISPLAYED":
			// perform get title
			Thread.sleep(3000);
			boolean bool = driver.findElement(this.getObject(p, testSteps2, testSteps3)).isDisplayed();
			Assert.assertEquals(bool,true);
			break;	
		}
	}

}
