package testScenarios;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import appModule.MobileCommands;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class MobileTestExample extends MobileCommands{

	@Test
	public void RunApp() throws MalformedURLException, InterruptedException
	{
		AndroidDriver<AndroidElement> driver = Capabilities("Android8", "ApiDemos-debug.apk");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys("hello");
		driver.findElementsByClassName("android.widget.Button").get(1).click();
	}
	
}
