package testScenarios;

import org.junit.Before;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import appCore.WebCommands;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TestWebExample extends WebCommands{
	
	@Before
	public void begin() throws Exception
	{
		// Open Browser and Excel File using SheetName
		start();
		//create evidence
		createEvidence(
			TestWebExample.class.getName(),
			"Efetuar o Login",
			"Login efetuado com sucesso"
		);
	}
	
	@Test
	public void exampleTest() throws Exception
	{
		// Test Steps
		LoginPage loginPage = LoginPage.accessURL();
		HomePage home = loginPage.LogIn();
					
		// validation
		PresenceValidation
		(
			home.findMain(), 
			"Login realizado com sucesso"
		);
	}
	
	@After
	public void tearDown() throws IOException
	{
		// Close PDF Evidence File
		FinishEvidence(TestWebExample.class.getName());
	}
}
