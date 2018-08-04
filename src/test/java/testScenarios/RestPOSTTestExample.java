package testScenarios;

import java.io.IOException;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import appModule.RestCommands;
import utility.Constants;

public class RestPOSTTestExample extends RestCommands{
	
	// Variable to create JSON
	
	
	@Before
	public void buildHeaders() throws MalformedURLException, DocumentException, IOException
	{
		// Creating evidence
		createEvidence(
			RestPOSTTestExample.class.getName(),
			"Post de API teste",
			"Post efetuado com sucesso"
		);
	}
	
	@Test
	public void PostExample() throws Exception
	{
		//Step 1 - Send Request e receba o response do sistema
		POSTCommand(Constants.URL, Constants.URL_PATH, true);
		addStep("Step 1 - Send Request e receba o response do sistema");
		addJSON(getResponse());
		
		// Step 2 - Validate the response Code
		addStep("Step 2 - Validate the response Code");
		try 
		{
			Assert.assertEquals("201", getStatusCode().toString());
			addJSON(getStatusCode().toString());
		}
		catch(AssertionError e)
		{
			ExceptionThrown(e.toString());
			Assert.assertEquals("201", getStatusCode().toString());
		}
		
	}
	
	@After
	public void end()
	{
		FinishEvidence(TestWebExample.class.getName());
	}
}
