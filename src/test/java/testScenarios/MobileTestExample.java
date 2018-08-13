package testScenarios;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.DocumentException;

import appCore.MobileCommands;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pageObjects.MobilePageObjectExample;

public class MobileTestExample{
	
	private AndroidDriver<MobileElement> driver = null;
	
	@Before
	public void begin() throws MalformedURLException, DocumentException, IOException
	{
		MobileCommands.createEvidence(MobileTestExample.class.getName(), "Test apk demos", "Apk test passed", "Android");
		
		this.driver = MobileCommands.LaunchLocalApp("Android Device", "ApiDemos-debug.apk");
	}
	
	@After
	public void tearDown()
	{
		MobileCommands.FinishEvidence(MobileTestExample.class.getName());
	}
	
	@Test
	public void FindElementSimple() throws DocumentException, IOException, InterruptedException
	{
		new MobilePageObjectExample(driver).TapPreferences();		
	}
}
