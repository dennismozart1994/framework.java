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
		shouldTest = ShouldTest(Constants.FILE_PATH + Constants.FILE_NAME, Constants.REST_START_CONTENT_LINE);
		
		// check if should test
		if(shouldTest)
		{
			// Creating evidence
			createEvidence(
				RestPOSTTestExample.class.getName(),
				"Post de API teste",
				"API",
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
			POSTCommand(Constants.API_URL, Constants.API_PATH);
			
			// Step 2 - Validate the response Code
			ValidateString
			(
				Constants.REST_START_CONTENT_LINE,
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
