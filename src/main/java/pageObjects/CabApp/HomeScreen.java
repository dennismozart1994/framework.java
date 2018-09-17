package pageObjects.CabApp;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class HomeScreen extends MobileCommands{
	@iOSFindBy(accessibility="homescreenMapButton")
	private MobileElement MapButton;
	
	@iOSFindBy(accessibility="homescreenEquipmentButton")
	private MobileElement EquipmentButton;
	
	@iOSFindBy(accessibility="homescreenFieldsButton")
	private MobileElement FieldsButton;
	
	@iOSFindBy(accessibility="homescreenSettingsButton")
	private MobileElement SettingsButton;
	
	@iOSFindBy(accessibility="homescreenHelpButton")
	private MobileElement HelpButton;
	
	@iOSFindBy(accessibility="homescreenOverviewButton")
	private MobileElement OverviewButton;
	
	@iOSFindBy(accessibility="cloudSyncButton")
	private MobileElement cloudSyncButton;
	
	@iOSFindBy(accessibility="dataManagerIconUnselected")
	private MobileElement WorkingOnIcon;
	
	public HomeScreen(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public Maps TapOnMap() throws DocumentException, IOException, InterruptedException {
		Tap(MapButton);
		addStepWithScreenshot("Tap on Map");
		return new Maps(driver);
	}
	
}
