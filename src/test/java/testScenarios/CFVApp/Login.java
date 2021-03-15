package testScenarios.CFVApp;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageObjects.CFViOS.LoginPage;
import utility.Constants;

public class Login {
	private AppiumDriver<MobileElement> driver = null;
	
	@Before
	public void begin() throws MalformedURLException, DocumentException, IOException
	{
		MobileCommands.createEvidence(Login.class.getName(), "Login with valid credentials", "User loged in", Constants.CFV_PROD_RELEASE);
		this.driver = MobileCommands.LaunchIOSLocalApp(Constants.CFV_PROD_RELEASE);
	}
	
	@After
	public void tearDown()
	{
		MobileCommands.FinishEvidence(Login.class.getName());
	}
	
	@Test
	public void Test() throws Exception
	{
		LoginPage l = new LoginPage(driver);
		l.Login("USERNAME_HERE", "PWD HERE");
	}
}
