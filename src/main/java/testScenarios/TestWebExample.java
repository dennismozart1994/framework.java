package testScenarios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import appModule.WebCommands;
import pageObjects.LoginPage;
import utility.Constants;
import utility.ExcelUtils;
import utility.Log;
import utility.PDFCreator;

public class TestWebExample extends WebCommands{
	
	@Before
	public void begin() throws Exception
	{
		// Log4j settings
		Log.startTestCase(TestWebExample.class.getName());
		//create evidence
		PDFCreator.createPDF(readConfig("Project"), 
							TestWebExample.class.getName(),
							"Efetuar o Login",
							readConfig("Environment"), 
							readConfig("Sprint"), 
							Constants.PROJECT_ED,
							"Login efetuado com sucesso");
	}
	
	@Test
	public void exampleTest() throws Exception
	{
		// Test Steps
		new LoginPage(driver).LogIn();

		// Validation
		try {
			// validation
			Assert.assertTrue(driver.findElement(By.id("main-content")).isDisplayed());
			// add result into Excel File
			ExcelUtils.setCellData("Passed", 1, 3);
			Constants.logger.error("Login realizado com sucesso");
		}catch(Exception e) {
			//add result into excel worksheet
			ExcelUtils.setCellData("Failed - " + e.toString(), 1, 3);
			// add throw delaration into evidence
			PDFCreator.logFatal("Failed - " + e.toString());
			Constants.logger.error("Login não foi realizado com sucesso");
		}
	}
	
	@After
	public void end()
	{
		Log.endTestCase(TestWebExample.class.getName());
		PDFCreator.quitPDF();
	}
}
