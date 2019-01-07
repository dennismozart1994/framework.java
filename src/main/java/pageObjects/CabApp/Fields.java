package pageObjects.CabApp;

import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import org.openqa.selenium.support.PageFactory;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Fields extends MobileCommands{
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="homeButton")
	private static MobileElement homeButton;
	
	// Search Field have no identifier
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(xpath="//XCUIElementTypeTextField")
	private static MobileElement searchField;
	// Field table have no identifier, and the cells face the same issue.

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="addFieldButton")
	private static MobileElement addFieldButton;
		/************************* Add Field Form Selectors *******************************/
		@iOSXCUITFindBy(xpath="//XCUIElementTypeTextField[@name='fieldName']")
		private static MobileElement fieldNameField;
		@iOSXCUITFindBy(accessibility="clientName")
		private static MobileElement clientNameField;
		@iOSXCUITFindBy(accessibility="farmName")
		private static MobileElement farmNameField;
		@iOSXCUITFindBy(accessibility="cancelButton")
		private static MobileElement cancelButton;
		@iOSXCUITFindBy(accessibility="saveButton")
		private static MobileElement saveButton;
	
	/************************** Field Details Selectors *****************************/
	/******************** Field Selected Details Selectors **************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="fieldName")
	private static MobileElement fieldNameText;
	@iOSXCUITFindBy(accessibility="editButton")
	private static MobileElement editButton;
	@iOSXCUITFindBy(accessibility="deleteButton")
	private static MobileElement deleteButton;
	
	/*********************** Field Selected Hybrid Selectors ***********************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="addHybrid")
	private static MobileElement addHybridButton;
		@iOSXCUITFindBy(accessibility="addNewHybridButton")
		private static MobileElement addNewHybridButton;
		// Hybrid Table don't have identifiers
		// Add Hybrid Table don't have identifiers
		// Remove hybrid button have the id remove + hybrid name

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="addTreatment")
	private static MobileElement addTreatmentButton;
	@iOSXCUITFindBy(accessibility="addNewTreatmentButton")
	private static MobileElement addNewTreatmentButton;
	// Treatment table don't have identifiers
	// Add Treatment table don't have identifiers
	// Remove treatment button have the id remove + hybrid name

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="addApplication")
	private static MobileElement addApplicationButton;
		// Application table don't have identifiers
		// Add Application table don't have identifiers
		// Add new application button have localized identifier
		@iOSXCUITFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]//XCUIElementTypeTable/XCUIElementTypeButton")
		private static MobileElement addNewApplicationButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="close")
	private static MobileElement closeButton;
	
	// Add Prescription combo box don't have identifiers
	// Prescription table don't have identifiers
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(xpath="//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton")
	private static MobileElement addPrescriptionButton;
	
	public Fields(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
}