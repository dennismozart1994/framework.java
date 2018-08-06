package testScenarios;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import appModule.RestCommands;
import utility.Constants;

public class RestPOSTTestExample extends RestCommands{
	
	// Variable to see if should Test
	boolean shouldTest;
	
	@Before
	public void begin() throws Exception
	{	
		// Open Excel file using sheetName as a parameter
		start("JSON");
		shouldTest = ShouldTest(Constants.REST_FILE_PATH + Constants.REST_FILE_NAME, "JSON", 2);
		
		// check if should test
		if(shouldTest)
		{
			// Creating evidence
			createEvidence(
				RestPOSTTestExample.class.getName(),
				"Post de API teste",
				"Post efetuado com sucesso"
			);
		}
	}
	
	@Test
	public void PostExample() throws Exception
	{
		if(shouldTest)
		{
			//Step 1 - Send Request e receba o response do sistema
			POSTCommand(Constants.URL, Constants.URL_PATH, "JSON");
			
			// Step 2 - Validate the response Code
			ValidateString
			(
				"JSON",
				2,
				"201",
				getStatusCode().toString(),
				"Executado com sucesso via automação"
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
		quitExcel();
	}
}
