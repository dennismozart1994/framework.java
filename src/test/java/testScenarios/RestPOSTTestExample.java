package testScenarios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import appModule.RestCommands;
import utility.Constants;
import utility.Log;
import utility.PDFCreator;

public class RestPOSTTestExample extends RestCommands{
	
	// Variable to create JSON
	private static JSONObject json;
	private static String FirstName = "Dennis";
	private static String LastName = "Mozart";
	private static String UserName = "dennismozart2";
	private static String Password = "M0z@rt290794";
	private static String Email = "dennis.mozart@ssasempreit.com.br";
	public static List<String> HeaderTitle;
	public static List<String> HeaderValue;
	
	
	@Before
	public void buildHeaders() throws MalformedURLException, DocumentException, IOException
	{
		// add headers
		HeaderTitle = new ArrayList<String>();
		HeaderValue = new ArrayList<String>();
		HeaderTitle.add(new String("Content-Type"));
		HeaderValue.add(new String("application/json"));
		// create JSON
		buildJSON();

		Log.startTestCase(RestPOSTTestExample.class.getName());
		// Creating evidence
		PDFCreator.createPDF(readConfig("Project"), 
							RestPOSTTestExample.class.getName(),
							"Post de API teste",
							readConfig("Environment"), 
							readConfig("Sprint"), 
							Constants.PROJECT_ED,
							"Post efetuado com sucesso");
	}
	// JSON Object in case of a POST method
	@SuppressWarnings("unchecked")
	private static void buildJSON()
	{
		json = new JSONObject();
		json.put("FirstName", FirstName);
		json.put("LastName", LastName);
		json.put("UserName", UserName);
		json.put("Password", Password);
		json.put("Email", Email);
	}
	
	@Test
	public void PostExample() throws IOException, DocumentException
	{
		// Step 1 - Montar JSON de Request
		PDFCreator.addStep("Step 1 - Montar JSON de Request");
		PDFCreator.addJSON(json.toJSONString());
		
		//Step 2 - Send Request e receba o response do sistema
		PDFCreator.addStep("Step 2 - Send Request e receba o response do sistema");
		POSTCommand(
				Constants.URL,
				Constants.URL_PATH, 
				HeaderTitle, 
				HeaderValue, 
				json);
		PDFCreator.addJSON(getResponse());
		
		// Step 3 - Validate the response Code
		PDFCreator.addStep("Step 3 - Validate the response Code");
		try 
		{
			Assert.assertEquals("201", getStatusCode().toString());
			PDFCreator.addJSON(getStatusCode().toString());
		}
		catch(AssertionError e)
		{
			PDFCreator.logFatal(e.toString());
			Assert.assertEquals("201", getStatusCode().toString());
		}
		
	}
	
	@After
	public void end()
	{
		Log.endTestCase(TestWebExample.class.getName());
		PDFCreator.quitPDF();
	}
}
