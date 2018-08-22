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

public class MobileiOSExample {
@SuppressWarnings("unused")
private AppiumDriver<MobileElement> driver = null;
	
	@Before
	public void begin() throws MalformedURLException, DocumentException, IOException
	{
		MobileCommands.createEvidence(MobileTestExample.class.getName(), "Test ios", "ios opened", "iOS");
		
		this.driver = MobileCommands.LaunchLocalApp("iPhone 8", "/Users/dennismozart/Desktop/UICatalog.app");
	}
	
	@After
	public void tearDown()
	{
		MobileCommands.FinishEvidence(MobileTestExample.class.getName());
	}
	
	@Test
	public void Test()
	{
		MobileCommands.findByAcessibilityID("Alert Views").click();
		MobileCommands.findByXpath("//*[@value='Text Entry']").click();
		MobileCommands.findByClassName("XCUIElementTypeTextField").sendKeys("hello");
		MobileCommands.findByName("OK").click();
		MobileCommands.NavigateBack();
		MobileCommands.ScrollUntil(true, MobileCommands.findByAcessibilityID("Steppers"));
		MobileCommands.findByAcessibilityID("Steppers").click();
		MobileCommands.findByAcessibilityID("Increment").click();
		MobileCommands.findByAcessibilityID("Increment").click();
		driver.findElementsByXPath("//XCUIElementTypeButton[@name=\"Increment\"]").get(0).click();
		driver.findElementsByXPath("//XCUIElementTypeButton[@name=\"Increment\"]").get(0).click();
		driver.findElementsByClassName("XCUIElementTypeStaticText").get(0).getText();
		MobileCommands.NavigateBack();
		MobileCommands.findByAcessibilityID("Picker View").click();
		MobileCommands.setWhellValue(MobileCommands.findByAcessibilityID("Green color component value"), "255");
		MobileCommands.setWhellValue(MobileCommands.findByAcessibilityID("Red color component value"), "55");
		MobileCommands.setWhellValue(MobileCommands.findByAcessibilityID("Blue color component value"), "130");
	}
}
