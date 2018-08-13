package pageObjects;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MobilePageObjectExample extends MobileCommands{
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	public MobileElement Preference;
	
	public MobilePageObjectExample(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void TapPreferences() throws DocumentException, IOException, InterruptedException
	{
		Tap(Preference);
		addStep("Tap Preferences");
		Thread.sleep(500);
		TakeScreenshot();
	}
}
