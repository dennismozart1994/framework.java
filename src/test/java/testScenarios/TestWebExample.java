package testScenarios;

import org.junit.Before;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import appModule.WebCommands;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utility.Constants;

public class TestWebExample extends WebCommands{
	// Variable to see if should Test
	boolean shouldTest;
	
	@Before
	public void begin() throws Exception
	{
		// Open Browser and Excel File using SheetName
		start("Web");
		shouldTest = ShouldTest(Constants.FILE_PATH + Constants.FILE_NAME, Constants.WEB_START_CONTENT_LINE);
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
			HomePage home = new LoginPage(driver).LogIn();
			// validation
			PresenceValidation
			(
				home.findMain(), 
				Constants.WEB_START_CONTENT_LINE, 
				"Login realizado com sucesso"
			);
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
