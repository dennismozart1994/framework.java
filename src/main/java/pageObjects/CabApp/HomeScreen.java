package pageObjects.CabApp;

import java.io.IOException;

import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HomeScreen extends MobileCommands{
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="homescreenMapButton")
	private MobileElement MapButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="homescreenEquipmentButton")
	private MobileElement EquipmentButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="homescreenFieldsButton")
	private MobileElement FieldsButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="homescreenSettingsButton")
	private MobileElement SettingsButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="homescreenHelpButton")
	private MobileElement HelpButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="homescreenOverviewButton")
	private MobileElement OverviewButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="cloudSyncButton")
	private MobileElement cloudSyncButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="dataManagerIconUnselected")
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
