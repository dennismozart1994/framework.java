package pageObjects.CFViOS;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen extends MobileCommands{
	
	/****************** Menu Controllers **********************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="TabItem_Overview")
	private static MobileElement overviewMenu;
	@iOSXCUITFindBy(accessibility="TabItem_Fields")
	private static MobileElement fieldsMenu;
	@iOSXCUITFindBy(accessibility="TabItem_Prescriptions")
	private static MobileElement prescriptionsMenu;
	@iOSXCUITFindBy(accessibility="TabItem_Settings")
	private static MobileElement settingsMenu;
	@iOSXCUITFindBy(accessibility="TabItem_Help")
	private static MobileElement helpMenu;
	@iOSXCUITFindBy(accessibility="TabItem_Cab")
	private static MobileElement fvCabMenu;
	
	/******************* Rainfall Card ********************/
	@iOSXCUITFindBy(accessibility="OverviewCardRainfallTitle")
	private static MobileElement rainfallCardTitle;
	@iOSXCUITFindBy(accessibility="OverviewCardRainfallSubtitle")
	private static MobileElement rainfallCardlastUpdateText;
	@iOSXCUITFindBy(accessibility="OverviewCardRainfallButton")
	private static MobileElement rainfallCardViewMoreButton;
	
	/******************* Nitrogen Card *******************/
	@iOSXCUITFindBy(accessibility="OverviewCardNitrogenTitle")
	private static MobileElement nitrogenlCardTitle;
	@iOSXCUITFindBy(accessibility="OverviewCardNitrogenButton")
	private static MobileElement nitrogenCardViewMoreButton;
	
	/******************* FHA Card ***********************/
	// The whole FHA Card have no identifiers
	@iOSXCUITFindBy(accessibility="FieldHealthL1_Title")
	private static MobileElement fhaCardTitle;
	@iOSXCUITFindBy(accessibility="FieldHealthL1_Subtitle")
	private static MobileElement fhaCardlastUpdateText;
	@iOSXCUITFindBy(accessibility="FieldHealthL1_ActionButton")
	private static MobileElement fhaCardViewMoreButton;
	
	/******************* Yield Analysis Card ***********************/
	// The whole FHA Card have no identifiers
	@iOSXCUITFindBy(accessibility="OverviewCard_YieldAnalysis_Title")
	private static MobileElement YACardTitle;
	@iOSXCUITFindBy(accessibility="OverviewCard_YieldAnalysis_Subtitle")
	private static MobileElement YACardlastUpdateText;
	@iOSXCUITFindBy(accessibility="OverviewCard_YieldAnalysis_Button")
	private static MobileElement YACardViewMoreButton;
	
	/******************* Notifications Card ***********************/
	// The whole FHA Card have no identifiers
	@iOSXCUITFindBy(accessibility="OverviewCardNotificationsTitle")
	private static MobileElement notificationsCardTitle;
	@iOSXCUITFindBy(accessibility="OverviewCardNotificationsSubtitle")
	private static MobileElement notificationsCardlastUpdateText;
	@iOSXCUITFindBy(accessibility="OverviewCardNotificationsButton")
	private static MobileElement notificationsCardViewMoreButton;
	// -- Reminder -- To get notifications use xpath //*[contains(@label, '"+text+"')]
	
	/******************* Activities Card ***********************/
	// The whole FHA Card have no identifiers
	@iOSXCUITFindBy(accessibility="OverviewCardActivitiesTitle")
	private static MobileElement activitiesCardTitle;
	@iOSXCUITFindBy(accessibility="OverviewCardActivitiesSubtitle")
	private static MobileElement activitiesCardlastUpdateText;
	@iOSXCUITFindBy(accessibility="OverviewCardActivitiesButton")
	private static MobileElement activitiesCardViewMoreButton;
	// activities Table have no identifier
	// -- Reminder -- To get notifications use xpath //*[contains(@label, '"+text+"')]

	public HomeScreen(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
