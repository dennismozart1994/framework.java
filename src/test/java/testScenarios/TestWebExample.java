package testScenarios;

import org.junit.Assert;
import org.junit.Before;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import appModule.WebCommands;
import pageObjects.LoginPage;
import utility.Constants;
import utility.ExcelUtils;

public class TestWebExample extends WebCommands{
	// Variable to see if should Test
	boolean shouldTest;
	
	@Before
	public void begin() throws Exception
	{
		// Open Browser and Excel File using SheetName
		start("Web");
		shouldTest = ShouldTest(Constants.FILE_PATH + Constants.FILE_NAME, "Web", Constants.WEB_START_CONTENT_LINE);
		// check if should test
		if(shouldTest)
		{
			//create evidence
			createEvidence(
				TestWebExample.class.getName(),
				"Efetuar o Login",
				"Login efetuado com sucesso"
			);
		}
	}
	
	@Test
	public void exampleTest() throws Exception
	{
		if(shouldTest)
		{
			// Test Steps
			new LoginPage(driver).LogIn();
	
			// Validation
			try {
				// validation
				Assert.assertTrue(driver.findElement(By.id("main-content")).isDisplayed());
				// add result into Excel File
				ExcelUtils.setCellData("Passed", 1, 3);
				Log("Login realizado com sucesso");
			}catch(Exception e) {
				//add result into excel worksheet
				ExcelUtils.setCellData("Failed - " + e.toString(), 1, 3);
				// add throw declaration into evidence
				ExceptionThrown("Failed - " + e.toString());
				Log("Login não foi realizado com sucesso");
			}
		}
	}
	
	@After
	public void tearDown() throws IOException
	{
		if(shouldTest)
		{
			// Close PDF Evidence File
			FinishEvidence(TestWebExample.class.getName());
		}
		// Close Excel File
		closeExcel();
	}
}
