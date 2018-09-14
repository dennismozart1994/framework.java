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
private AppiumDriver<MobileElement> driver = null;
	
	@Before
	public void begin() throws MalformedURLException, DocumentException, IOException
	{
		MobileCommands.createEvidence(MobileTestExample.class.getName(), "Test ios", "ios opened", "iOS");
		
<<<<<<< HEAD
		this.driver = MobileCommands.LaunchLocalApp("iPad 2", "landscape", "com.precisionplanting.fieldview-develop");
=======
		this.driver = MobileCommands.LaunchAndInstallApp("iPhone 8", "/Users/dennismozart/Desktop/UICatalog.app");
>>>>>>> a2cfdc04b7eb64489b10285a25935f0d9d997c93
	}
	
	@After
	public void tearDown()
	{
		MobileCommands.FinishEvidence(MobileTestExample.class.getName());
	}
	
	@Test
	public void Test()
	{
		
	}
}
