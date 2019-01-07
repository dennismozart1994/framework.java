package testScenarios;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageObjects.CabApp.HomeScreen;
import pageObjects.CabApp.Maps;
import utility.Constants;

public class MobileiOSExample {
private AppiumDriver<MobileElement> driver = null;
	
	@Before
	public void begin() throws MalformedURLException, DocumentException, IOException
	{
		MobileCommands.createEvidence(MobileiOSExample.class.getName(), "Test ios", "ios opened", "iOS");
		this.driver = MobileCommands.LaunchIOSLocalApp(Constants.CAB_APP_DEV);
	}
	
	@After
	public void tearDown()
	{
		MobileCommands.FinishEvidence(MobileiOSExample.class.getName());
	}
	
	@Test
	public void Test() throws Exception
	{
		HomeScreen home = new HomeScreen(driver);
		Maps mapScreen = home.TapOnMap();
		mapScreen.selectField("09");
		mapScreen.selectCrop("2019 Soja");
		mapScreen.selectMapType(1, "Velocidade da Plantadeira");
	}
}
